package msbioms.particle;

import msbioms.worldgen.ModBiomes;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

public class FireflySpawner {

    private static final int RADIUS = 10;
    private static final float SPAWN_CHANCE = 0.035F;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(
                FireflySpawner::tick
        );
    }

    private static void tick(Minecraft client) {
        if (client.level == null || client.player == null) {
            return;
        }

        ClientLevel level = client.level;

        /*
         * В Minecraft 26.2 getDayTime() был заменён
         * на getOverworldClockTime().
         */
        long time = level.getOverworldClockTime() % 24000L;

        // День: 0–12999
        // Ночь: 13000–22999
        if (time < 13000L || time >= 23000L) {
            return;
        }

        BlockPos playerPos = client.player.blockPosition();

        boolean inWillow =
                level.getBiome(playerPos)
                        .is(ModBiomes.WILLOW_FOREST_KEY);

        boolean inGloomy =
                level.getBiome(playerPos)
                        .is(ModBiomes.GLOOMY_FOREST_KEY);

        if (!inWillow && !inGloomy) {
            return;
        }

        /*
         * В 26.2 Level.random стал protected.
         * Используем публичный getRandom().
         */
        RandomSource random = level.getRandom();

        if (random.nextFloat() > SPAWN_CHANCE) {
            return;
        }

        double x =
                client.player.getX()
                        + (random.nextDouble() * 2.0 - 1.0) * RADIUS;

        double z =
                client.player.getZ()
                        + (random.nextDouble() * 2.0 - 1.0) * RADIUS;

        double y =
                client.player.getY()
                        + 1.0
                        + random.nextDouble() * 5.0;

        BlockPos particlePos =
                BlockPos.containing(x, y, z);

        if (!level.getBlockState(particlePos).isAir()) {
            return;
        }

        /*
         * Случайное горизонтальное направление.
         */
        double angle =
                random.nextDouble() * Math.PI * 2.0;

        double speed =
                0.005 + random.nextDouble() * 0.01;

        double velocityX =
                Math.cos(angle) * speed;

        double velocityZ =
                Math.sin(angle) * speed;

        double velocityY =
                (random.nextDouble() - 0.5) * 0.003;

        level.addParticle(
                ModParticles.FIREFLY,
                x,
                y,
                z,
                velocityX,
                velocityY,
                velocityZ
        );
    }
}