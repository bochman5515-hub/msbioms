package msbioms.block;

import java.util.Set;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class PlantableBlock extends NaturalMossBlock {

    private final Set<Block> allowedPlants;

    public PlantableBlock(
            Properties properties,
            Block... allowedPlants
    ) {
        super(properties);

        this.allowedPlants = Set.of(allowedPlants);
    }

    public boolean canPlant(Block plant) {
        return allowedPlants.contains(plant);
    }

    public boolean canPlant(BlockState plantState) {
        return canPlant(plantState.getBlock());
    }
}
