package github.djiker.djikerscreateadditions;

import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod(DjikersCreateAdditions.MODID)
public class DjikersCreateAdditions {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "djikerscreateadditions";

    public static final CreateRegistrate REGISTRATE =
            CreateRegistrate.create(MODID);

    public DjikersCreateAdditions(IEventBus modEventBus) {
        REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
        REGISTRATE.registerEventListeners(modEventBus);

        DCAFluids.register();                   // Registrate fluids
        DCACreativeTab.register(modEventBus);  // Creative tab
        modEventBus.addListener(this::commonSetup);
        LOGGER.info("DJikersCreateAdditions initialized.");
    }

    private void commonSetup(net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent event) {
        event.enqueueWork(DCAFluidInteractions::register);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}