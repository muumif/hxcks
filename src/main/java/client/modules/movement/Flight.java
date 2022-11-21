package client.modules.movement;

import client.modules.Mod;
import client.modules.settings.BooleanSetting;
import client.modules.settings.NumberSetting;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class Flight extends Mod {

    public NumberSetting speed = new NumberSetting("Speed", 0, 1, 0.05, 0.05);
    public BooleanSetting antiKick = new BooleanSetting("Anti Kick", true);

    private int tickCount = 0;

    public Flight() {
        super("Flight", "The most basic flight!", Category.MOVEMENT);
        addSettings(speed, antiKick);
        setKey(GLFW.GLFW_KEY_G);
    }

    @Override
    public void onTick(){
        mc.player.getAbilities().flying = true;
        mc.player.getAbilities().setFlySpeed((float)speed.getValueFloat());
        tickCount++;
        if (antiKick.isEnabled()) doAntiKick(mc.player.getVelocity(), tickCount);
        super.onTick();
    }

    @Override
    public void onDisable(){
        mc.player.getAbilities().flying = false;
        tickCount = 0;
        super.onTick();
    }

    private void doAntiKick(Vec3d velocity, int tick) {
        if (tick >= 40 && mc.player.world.getBlockState(new BlockPos(mc.player.getPos().subtract(0,0.433D,0))).isAir()) {
            Packet<?> newPacket;
            newPacket = new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX() + 25, mc.player.getY() - 0.07, mc.player.getZ(), false);
            mc.player.networkHandler.getConnection().send(newPacket);
        }
    }
}
