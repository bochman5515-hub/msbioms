package msbioms.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.dimension.DimensionType;
import msbioms.MSBioms;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(BiomeSource.class)
public abstract class BiomeSourceMixin {

    private static final ResourceKey<DimensionType> MSBIOMS_OVERWORLD =
            ResourceKey.create(
                    Registries.DIMENSION_TYPE,
                    MSBioms.id("msbioms_overworld")
            );

    @Inject(
            method = "findClosestBiome3d",
            at = @At("HEAD"),
            cancellable = true
    )
    private void msbioms$findClosestBiome3d(
            BlockPos origin,
            int searchRadius,
            int sampleResolutionHorizontal,
            int sampleResolutionVertical,
            Predicate<Holder<Biome>> allowed,
            Climate.Sampler sampler,
            LevelReader level,
            CallbackInfoReturnable<@Nullable Pair<BlockPos, Holder<Biome>>> cir
    ) {
        /*
         * Оставляем vanilla-поведение во всех остальных измерениях.
         */
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        if (!serverLevel.dimensionTypeRegistration()
                .unwrapKey()
                .map(MSBIOMS_OVERWORLD::equals)
                .orElse(false)) {
            return;
        }

        /*
         * Для MSBioms не используем possibleBiomes().
         *
         * Biolith уже изменяет getNoiseBiome(), поэтому непосредственно
         * проверяем результат noise sampling.
         */

        int sampleRadius =
                Math.floorDiv(
                        searchRadius,
                        sampleResolutionHorizontal
                );

        int[] sampleYs = Mth.outFromOrigin(
                origin.getY(),
                level.getMinY() + 1,
                level.getMaxY() + 1,
                sampleResolutionVertical
        ).toArray();

        BiomeSource self = (BiomeSource) (Object) this;

        for (BlockPos.MutableBlockPos sampleColumn :
                BlockPos.spiralAround(
                        BlockPos.ZERO,
                        sampleRadius,
                        Direction.EAST,
                        Direction.SOUTH
                )) {

            int blockX =
                    origin.getX()
                            + sampleColumn.getX()
                            * sampleResolutionHorizontal;

            int blockZ =
                    origin.getZ()
                            + sampleColumn.getZ()
                            * sampleResolutionHorizontal;

            int noiseX = QuartPos.fromBlock(blockX);
            int noiseZ = QuartPos.fromBlock(blockZ);

            for (int blockY : sampleYs) {

                int noiseY = QuartPos.fromBlock(blockY);

                Holder<Biome> biome =
                        self.getNoiseBiome(
                                noiseX,
                                noiseY,
                                noiseZ,
                                sampler
                        );

                if (allowed.test(biome)) {
                    cir.setReturnValue(
                            Pair.of(
                                    new BlockPos(
                                            blockX,
                                            blockY,
                                            blockZ
                                    ),
                                    biome
                            )
                    );

                    return;
                }
            }
        }

        cir.setReturnValue(null);
    }
}