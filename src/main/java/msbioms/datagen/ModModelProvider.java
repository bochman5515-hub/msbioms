package msbioms.datagen;

import msbioms.block.ModBlocks;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        blockStateModelGenerator.createTrivialCube(ModBlocks.DRIED_EARTH);

    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerator) {

    }

    @Override
    public @NonNull String getName() {
        return "MSBiomsModelProvider";
    }
}