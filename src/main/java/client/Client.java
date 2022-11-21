package client;

import client.UI.clickgui.ClickGUI;
import client.modules.Mod;
import client.modules.ModuleManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client implements ModInitializer {

    public static final Client INSTANCE = new Client();
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    private MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {
        LOGGER.info("Client is initializing!");
    }

    public void onKeypress(int key, int action) {
        if (action == GLFW.GLFW_PRESS) {
            for (Mod module: ModuleManager.INSTANCE.getModules()) {
                if (key == module.getKey()) module.toggle();
            }

            if (key == GLFW.GLFW_KEY_RIGHT_ALT) mc.setScreen(ClickGUI.INSTANCE);
        }
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod module: ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }

    public static double round(double toRound) {
        toRound = Math.round(toRound * 100) / 100d;
        return Math.nextAfter(toRound, toRound + Math.signum(toRound));
    }
}
