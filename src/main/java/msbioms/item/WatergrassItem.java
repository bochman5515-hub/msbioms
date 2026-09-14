package msbioms.item;
import msbioms.block.ModBlocks;



import net.minecraft.world.item.PlaceOnWaterBlockItem;

public class WatergrassItem extends PlaceOnWaterBlockItem {

    public WatergrassItem(Properties properties) {
        super(
                ModBlocks.BOG_PLANT,
                properties
        );
    }
}
