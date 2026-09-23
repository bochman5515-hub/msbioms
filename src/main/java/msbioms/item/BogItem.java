package msbioms.item;

import msbioms.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class BogItem extends BlockItem {

    public BogItem(Properties properties) {
        super(
                ModBlocks.BOG,
                properties
        );
    }
}