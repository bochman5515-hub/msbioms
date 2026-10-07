package msbioms.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class PoplarFluffParticle extends FallingLeavesParticle {

    protected PoplarFluffParticle(
            ClientLevel level,
            double x,
            double y,
            double z,
            TextureAtlasSprite sprite,
            float fallAcceleration,
            float sideAcceleration,
            boolean swirl,
            boolean flowAway,
            float scale,
            float startVelocity
    ) {
        super(
                level,
                x,
                y,
                z,
                sprite,
                fallAcceleration,
                sideAcceleration,
                swirl,
                flowAway,
                scale,
                startVelocity
        );
    }
    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }

    public static class Provider
            implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }



        @Override
        public Particle createParticle(
                SimpleParticleType options,
                ClientLevel level,
                double x,
                double y,
                double z,
                double velocityX,
                double velocityY,
                double velocityZ,
                RandomSource random
        ) {
            TextureAtlasSprite sprite =
                    this.sprites.get(random);

            return new PoplarFluffParticle(
                    level,
                    x,
                    y,
                    z,
                    sprite,

                    // Скорость падения
                    0.05F,

                    // Сила ветра
                    7.0F,

                    // Плавное кружение
                    true,

                    // Постепенно уносит ветром
                    true,

                    // Размер
                    6.0F,

                    // Начальная скорость вниз
                    0.01F
            );
        }
    }
}