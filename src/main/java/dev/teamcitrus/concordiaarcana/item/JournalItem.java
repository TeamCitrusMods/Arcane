package dev.teamcitrus.concordiaarcana.item;

import com.klikli_dev.modonomicon.registry.SoundRegistry;
import dev.teamcitrus.concordiaarcana.client.renderer.item.JournalRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.ClientUtils;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class JournalItem extends Item implements GeoItem {
    private static final RawAnimation OPEN_BOOK_ANIMATION = RawAnimation.begin().thenPlayAndHold("animation.journal.opening");
    private static final RawAnimation CLOSE_BOOK_ANIMATION = RawAnimation.begin().thenPlayAndHold("animation.journal.closing");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public JournalItem(Properties properties) {
        super(properties);

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final JournalRenderer renderer = new JournalRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "book_controller", 60, state -> PlayState.STOP)
                .triggerableAnim("open_book", OPEN_BOOK_ANIMATION)
                .triggerableAnim("close_book", CLOSE_BOOK_ANIMATION)
                .setSoundKeyframeHandler(state -> {
                    Player player = ClientUtils.getClientPlayer();

                    if (player != null) {
                        player.playSound(SoundRegistry.TURN_PAGE.get(), 1, 1);
                    }
                })
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
