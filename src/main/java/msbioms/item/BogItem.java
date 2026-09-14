package msbioms.item;

import msbioms.block.ModBlocks;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.Item;

public class BogItem extends PlaceOnWaterBlockItem {

    public BogItem(Properties properties) {
        super(
                ModBlocks.BOG,
                properties
        );
    }
}