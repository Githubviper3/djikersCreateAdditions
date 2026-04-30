package github.djiker.djikerscreateadditions;


import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
public class DCACreativeTab {
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DjikersCreateAdditions.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CREATIVE_TAB =
            TAB_REGISTER.register("cdg_creative_tab",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.dca_creative_tab"))
                            .icon(() -> new ItemStack(DCAFluids.CONDENSED_LAVA.get().getBucket()))
                            .displayItems((params, output) -> {
                                output.accept(DCAFluids.CONDENSED_LAVA.get().getBucket().asItem());
                            })
                            .build());

    public static void register(IEventBus modEventBus) {
        TAB_REGISTER.register(modEventBus);
    }
}
