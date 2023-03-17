package dev.teamcitrus.arcane.block;

import dev.teamcitrus.arcane.blockentity.GlassJarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GlassJarBlock extends Block implements EntityBlock {
    public static final BooleanProperty CORKED = BooleanProperty.create("corked");
    public static final BooleanProperty HANGING = BlockStateProperties.HANGING;
    public static final BooleanProperty SEALED = BooleanProperty.create("sealed");

    private static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 14, 13);

    public GlassJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(CORKED, false)
                .setValue(HANGING, false)
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
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof GlassJarBlockEntity jar) {
                return jar.use(level, pos, state, player, hand, player.getItemInHand(hand));
            }
        }

        return super.use(state, level, pos, player, hand, hitResult);
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
        builder.add(CORKED, HANGING, SEALED);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        if(direction == Direction.UP) {
            return Block.canSupportCenter(level, pos.relative(direction).above(1), direction.getOpposite());
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
        return SHAPE;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        super.setPlacedBy(level, pos, state, entity, stack);
    }
}
