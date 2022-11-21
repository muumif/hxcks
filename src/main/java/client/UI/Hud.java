package client.UI;

import client.modules.Mod;
import client.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

public class Hud {

    private static MinecraftClient mc = MinecraftClient.getInstance();

    private static final DecimalFormat df = new DecimalFormat("0.00");


    public static void render(MatrixStack matrices, float tickDelta) {
        renderArrayList(matrices);

        Vec3d v = mc.player.getVelocity();
        double currentSpeed = Double.parseDouble(df.format(Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.z, 2))));
        double playerX = Double.parseDouble(df.format(mc.player.getX()));
        double playerY = Double.parseDouble(df.format(mc.player.getY()));
        double playerZ = Double.parseDouble(df.format(mc.player.getZ()));

        mc.textRenderer.drawWithShadow(matrices, String.valueOf(playerX) + " / " + String.valueOf(playerY) + " / " + String.valueOf(playerZ), 5, 5, -1);
        mc.textRenderer.drawWithShadow(matrices, String.valueOf(currentSpeed), 5, mc.textRenderer.fontHeight + 6, -1);
    }

    public static void renderArrayList(MatrixStack matrices) {
        int index = 0;
        int windowWidth = mc.getWindow().getScaledWidth();
        int windowHeight = mc.getWindow().getScaledHeight();

        List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();

        enabled.sort(Comparator.comparing(m -> (int)mc.textRenderer.getWidth(((Mod)m).getName())).reversed());

        for (Mod module : enabled){
            mc.textRenderer.drawWithShadow(matrices, module.getName(), (windowWidth - 4) - mc.textRenderer.getWidth(module.getName()), 10 + (index * mc.textRenderer.fontHeight), - 1);
            index++;
        }
    }
}
