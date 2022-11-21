package client.modules.movement;

import client.modules.Mod;
import org.lwjgl.glfw.GLFW;

public class Sprint extends Mod {

    public Sprint(){
        super("Sprint", "Always sprinting!", Category.MOVEMENT);
    }

    @Override
    public void onTick() {
        mc.player.setSprinting(true);
        super.onTick();
    }
}
