package msbioms.item;

import msbioms.block.MicroBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class MicroBlockItem extends Item {

    private final Supplier<? extends Block> microBlock;

    public MicroBlockItem(
            Properties properties,
            Supplier<? extends Block> microBlock
    ) {
        super(properties);
        this.microBlock = microBlock;
    }

    private Block getMicroBlock() {
        return microBlock.get();
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level =
                context.getLevel();

        BlockPos clickedPos =
                context.getClickedPos();

        BlockState clickedState =
                level.getBlockState(clickedPos);

        Direction face =
                context.getClickedFace();

        Vec3 hit =
                context.getClickLocation();

        Block microBlock =
                getMicroBlock();

        /*
         * ============================================================
         * 1. КЛИК ПО НАШЕМУ MICRO BLOCK
         * ============================================================
         */

        if (clickedState.is(microBlock)) {

            double localX =
                    clamp(
                            hit.x - clickedPos.getX()
                    );

            double localY =
                    clamp(
                            hit.y - clickedPos.getY()
                    );

            double localZ =
                    clamp(
                            hit.z - clickedPos.getZ()
                    );

            /*
             * Немного смещаем точку внутрь существующей
             * микроячейки относительно той грани,
             * по которой произошёл клик.
             */
            switch (face) {

                case UP ->
                        localY = Math.max(
                                0.001D,
                                localY - 0.001D
                        );

                case DOWN ->
                        localY = Math.min(
                                0.999D,
                                localY + 0.001D
                        );

                case NORTH ->
                        localZ = Math.min(
                                0.999D,
                                localZ + 0.001D
                        );

                case SOUTH ->
                        localZ = Math.max(
                                0.001D,
                                localZ - 0.001D
                        );

                case WEST ->
                        localX = Math.min(
                                0.999D,
                                localX + 0.001D
                        );

                case EAST ->
                        localX = Math.max(
                                0.001D,
                                localX - 0.001D
                        );
            }

            int clickedCell =
                    getCell(
                            localX,
                            localY,
                            localZ
                    );

            /*
             * Клик действительно должен приходиться
             * на существующую ячейку.
             */
            if (!clickedState.getValue(
                    MicroBlock.getCellProperty(clickedCell)
            )) {
                return InteractionResult.PASS;
            }

            /*
             * ========================================================
             * СНАЧАЛА ДОБАВЛЯЕМ СОСЕДНЮЮ ЯЧЕЙКУ
             * В ЭТОТ ЖЕ MICRO BLOCK
             * ========================================================
             */

            int internalCell =
                    getInternalAdjacentCell(
                            clickedCell,
                            face
                    );

            if (!clickedState.getValue(
                    MicroBlock.getCellProperty(internalCell)
            )) {

                if (!level.isClientSide()) {

                    BlockState newState =
                            MicroBlock.setCell(
                                    clickedState,
                                    internalCell,
                                    true
                            );

                    level.setBlock(
                            clickedPos,
                            newState,
                            3
                    );

                    consumeItem(context);
                }

                return InteractionResult.SUCCESS;
            }

            /*
             * ========================================================
             * ВНУТРЕННЯЯ ЯЧЕЙКА УЖЕ ЗАНЯТА
             *
             * Переходим в соседний Minecraft-блок.
             * ========================================================
             */

            BlockPos targetPos =
                    clickedPos.relative(face);

            BlockState targetState =
                    level.getBlockState(targetPos);

            int newCell =
                    getAdjacentCell(
                            clickedCell,
                            face
                    );

            /*
             * ========================================================
             * СОСЕДНИЙ БЛОК УЖЕ ТАКОЙ ЖЕ MICRO BLOCK
             * ========================================================
             */

            if (targetState.is(microBlock)) {

                if (targetState.getValue(
                        MicroBlock.getCellProperty(newCell)
                )) {
                    return InteractionResult.PASS;
                }

                if (!level.isClientSide()) {

                    BlockState newState =
                            MicroBlock.setCell(
                                    targetState,
                                    newCell,
                                    true
                            );

                    level.setBlock(
                            targetPos,
                            newState,
                            3
                    );

                    consumeItem(context);
                }

                return InteractionResult.SUCCESS;
            }

            /*
             * ========================================================
             * СОСЕДНИЙ БЛОК ДОЛЖЕН БЫТЬ ЗАМЕНЯЕМЫМ
             * ========================================================
             */

            if (!targetState.canBeReplaced()) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide()) {

                BlockState newState =
                        microBlock
                                .defaultBlockState();

                newState =
                        MicroBlock.setCell(
                                newState,
                                newCell,
                                true
                        );
                if (level.getFluidState(targetPos).getType() == Fluids.WATER) {
                    newState = newState.setValue(
                            MicroBlock.WATERLOGGED,
                            true
                    );
                }

                level.setBlock(
                        targetPos,
                        newState,
                        3
                );

                consumeItem(context);
            }

            return InteractionResult.SUCCESS;
        }

        /*
         * ============================================================
         * 2. КЛИК ПО ОБЫЧНОМУ БЛОКУ
         * ============================================================
         */

        BlockPos targetPos =
                clickedPos.relative(face);

        BlockState targetState =
                level.getBlockState(targetPos);

        /*
         * Если там уже наш MicroBlock —
         * добавляем в него ячейку.
         */
        if (targetState.is(microBlock)) {

            double localX =
                    clamp(
                            hit.x - targetPos.getX()
                    );

            double localY =
                    clamp(
                            hit.y - targetPos.getY()
                    );

            double localZ =
                    clamp(
                            hit.z - targetPos.getZ()
                    );

            switch (face) {

                case DOWN ->
                        localY = 0.999D;

                case UP ->
                        localY = 0.001D;

                case NORTH ->
                        localZ = 0.999D;

                case SOUTH ->
                        localZ = 0.001D;

                case WEST ->
                        localX = 0.999D;

                case EAST ->
                        localX = 0.001D;
            }

            int cell =
                    getCell(
                            localX,
                            localY,
                            localZ
                    );

            if (targetState.getValue(
                    MicroBlock.getCellProperty(cell)
            )) {
                return InteractionResult.PASS;
            }

            if (!hasSupport(
                    level,
                    clickedPos,
                    face,
                    cell
            )) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide()) {

                BlockState newState =
                        MicroBlock.setCell(
                                targetState,
                                cell,
                                true
                        );

                level.setBlock(
                        targetPos,
                        newState,
                        3
                );

                consumeItem(context);
            }

            return InteractionResult.SUCCESS;
        }

        /*
         * Обычная позиция должна быть заменяемой.
         */
        if (!targetState.canBeReplaced()) {
            return InteractionResult.PASS;
        }

        /*
         * ============================================================
         * ОПРЕДЕЛЯЕМ ЯЧЕЙКУ ПО ТОЧКЕ КЛИКА
         * ============================================================
         */

        double localX =
                hit.x - targetPos.getX();

        double localY =
                hit.y - targetPos.getY();

        double localZ =
                hit.z - targetPos.getZ();

        switch (face) {

            case DOWN ->
                    localY = 0.999D;

            case UP ->
                    localY = 0.001D;

            case NORTH ->
                    localZ = 0.999D;

            case SOUTH ->
                    localZ = 0.001D;

            case WEST ->
                    localX = 0.999D;

            case EAST ->
                    localX = 0.001D;
        }

        localX = clamp(localX);
        localY = clamp(localY);
        localZ = clamp(localZ);

        int cell =
                getCell(
                        localX,
                        localY,
                        localZ
                );

        /*
         * Проверяем наличие опоры.
         */
        if (!hasSupport(
                level,
                clickedPos,
                face,
                cell
        )) {
            return InteractionResult.PASS;
        }

        /*
         * ============================================================
         * СОЗДАЁМ НОВЫЙ MICRO BLOCK
         * ============================================================
         */

        if (!level.isClientSide()) {

            BlockState newState =
                    microBlock
                            .defaultBlockState();

            newState =
                    MicroBlock.setCell(
                            newState,
                            cell,
                            true
                    );
            if (level.getFluidState(targetPos).getType() == Fluids.WATER) {
                newState = newState.setValue(
                        MicroBlock.WATERLOGGED,
                        true
                );
            }

            level.setBlock(
                    targetPos,
                    newState,
                    3
            );

            consumeItem(context);
        }

        return InteractionResult.SUCCESS;
    }

    /*
     * ================================================================
     * СОСЕДНЯЯ ЯЧЕЙКА ВНУТРИ ТОГО ЖЕ BLOCKPOS
     * ================================================================
     */

    private static int getInternalAdjacentCell(
            int cell,
            Direction face
    ) {
        int x =
                cell & 1;

        int z =
                (cell >> 1) & 1;

        int y =
                (cell >> 2) & 1;

        switch (face) {

            case UP ->
                    y = 1;

            case DOWN ->
                    y = 0;

            case NORTH ->
                    z = 0;

            case SOUTH ->
                    z = 1;

            case WEST ->
                    x = 0;

            case EAST ->
                    x = 1;
        }

        return x
                | (z << 1)
                | (y << 2);
    }

    /*
     * ================================================================
     * СОСЕДНЯЯ ЯЧЕЙКА В ДРУГОМ BLOCKPOS
     * ================================================================
     */

    private static int getAdjacentCell(
            int cell,
            Direction face
    ) {
        int x =
                cell & 1;

        int z =
                (cell >> 1) & 1;

        int y =
                (cell >> 2) & 1;

        switch (face) {

            case UP ->
                    y = 0;

            case DOWN ->
                    y = 1;

            case NORTH ->
                    z = 1;

            case SOUTH ->
                    z = 0;

            case WEST ->
                    x = 1;

            case EAST ->
                    x = 0;
        }

        return x
                | (z << 1)
                | (y << 2);
    }

    /*
     * ================================================================
     * ПРОВЕРКА ОПОРЫ
     * ================================================================
     */

    private static boolean hasSupport(
            Level level,
            BlockPos supportPos,
            Direction face,
            int newCell
    ) {
        BlockState supportState =
                level.getBlockState(supportPos);

        /*
         * Если опора — любой наш MicroBlock,
         * проверяем конкретную ячейку.
         */
        if (supportState.getBlock() instanceof MicroBlock) {

            return MicroBlock.hasSupportCell(
                    supportState,
                    face,
                    newCell
            );
        }

        /*
         * Обычный блок.
         */
        VoxelShape supportShape =
                supportState.getBlockSupportShape(
                        level,
                        supportPos
                );

        return !supportShape.isEmpty();
    }

    /*
     * ================================================================
     * ЯЧЕЙКА ПО КООРДИНАТАМ
     * ================================================================
     */

    private static int getCell(
            double x,
            double y,
            double z
    ) {
        int xPart =
                x >= 0.5 ? 1 : 0;

        int zPart =
                z >= 0.5 ? 1 : 0;

        int yPart =
                y >= 0.5 ? 1 : 0;

        return xPart
                | (zPart << 1)
                | (yPart << 2);
    }

    /*
     * ================================================================
     * ОГРАНИЧЕНИЕ КООРДИНАТ
     * ================================================================
     */

    private static double clamp(
            double value
    ) {
        return Math.max(
                0.001D,
                Math.min(
                        0.999D,
                        value
                )
        );
    }

    /*
     * ================================================================
     * ЗАБИРАЕМ ОДИН ПРЕДМЕТ
     * ================================================================
     */

    private static void consumeItem(
            UseOnContext context
    ) {
        if (context.getPlayer() == null) {
            return;
        }

        if (!context.getPlayer()
                .getAbilities()
                .instabuild) {

            context.getItemInHand()
                    .shrink(1);
        }
    }
}