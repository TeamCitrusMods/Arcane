package dev.teamcitrus.concordiaarcana.block;

import dev.teamcitrus.concordiaarcana.blockentity.GlassJarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class GlassJarBlock extends Block implements EntityBlock {
    public static final BooleanProperty CORKED = BooleanProperty.create("corked");
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty HAS_CANDLE = BooleanProperty.create("has_candle");
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final BooleanProperty SEALED = BooleanProperty.create("sealed");

    private static final VoxelShape BASE_SHAPE = Block.box(3, 0, 3, 13, 14, 13);
    private static final VoxelShape CORKED_SHAPE = Stream.of(
            Block.box(3, 0, 3, 13, 14, 13),
            Block.box(5, 14, 5, 11, 16, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    private static final VoxelShape CANDLED_SHAPE = Stream.of(
            Block.box(3, 0, 3, 13, 14, 13),
            Block.box(5, 14, 5, 11, 16, 11),
            Block.box(6, 16, 6, 9, 23, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public GlassJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(CORKED, false)
                .setValue(HANGING, false)
                .setValue(HAS_CANDLE, false)
                .setValue(LIT, false)
                .setValue(SEALED, false)
        );
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GlassJarBlockEntity(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof GlassJarBlockEntity jar) {
            if(!(player.getItemInHand(hand).getItem() == Items.CANDLE)) {
                return jar.use(level, pos, state, player, hand, player.getItemInHand(hand));
            } else {
                if(state.getValue(CORKED) && !state.getValue(HAS_CANDLE)) {
                    ItemStack stack = player.getItemInHand(hand);
                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                    level.setBlockAndUpdate(pos, state.setValue(HAS_CANDLE, true));
                    return InteractionResult.sidedSuccess(level.isClientSide);
                }
            }
        }

        return InteractionResult.FAIL;
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(!level.isClientSide()) {
            if(level.getBlockEntity(pos) instanceof GlassJarBlockEntity jar) {
                jar.onBreak();
            }
        }

        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis() == Direction.Axis.Y) {
                BlockState blockstate = this.defaultBlockState().setValue(HANGING, direction == Direction.UP);
                if (blockstate.canSurvive(context.getLevel(), context.getClickedPos())) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CORKED, HANGING, HAS_CANDLE, LIT, SEALED);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        if(direction == Direction.UP) {
            return Block.canSupportCenter(level, pos.relative(direction).above(2), direction.getOpposite());
        }
        return Block.canSupportCenter(level, pos.relative(direction), direction.getOpposite());
    }

    protected static Direction getConnectedDirection(BlockState state) {
        return state.getValue(HANGING) ? Direction.DOWN : Direction.UP;
    }

    @Override
    public BlockState updateShape(BlockState oldState, Direction direction, BlockState newState, LevelAccessor level, BlockPos newPos, BlockPos oldPos) {
        return getConnectedDirection(oldState).getOpposite() == direction && !oldState.canSurvive(level, newPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(oldState, direction, newState, level, newPos, oldPos);
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter getter, BlockPos pos, PathComputationType path) {
        return false;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (state.getValue(HAS_CANDLE)) {
            return CANDLED_SHAPE;
        } else if (state.getValue(CORKED)) {
            return CORKED_SHAPE;
        }
        return BASE_SHAPE;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> beType) {
        if(state.getValue(HANGING)) {
            return GlassJarBlockEntity::tick;
        }
        return null;
    }
}
