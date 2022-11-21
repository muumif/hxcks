package client.UI.clickgui.setting;

import client.UI.clickgui.ModuleButton;
import client.modules.settings.BooleanSetting;
import client.modules.settings.Setting;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class CheckBox extends Component {

    private BooleanSetting boolSet = (BooleanSetting)setting;
    public CheckBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.boolSet = (BooleanSetting)setting;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(0, 0, 0, 160).getRGB());
        int textOffset = ((parent.parent.height / 2) - mc.textRenderer.fontHeight /2);
        if (isHovered(mouseX, mouseY)) {
            DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(0, 0, 0, 160).getRGB());
        }
        DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + 2, parent.parent.y + parent.offset + offset + parent.parent.height, Color.red.getRGB());
        if (boolSet.isEnabled()) {
            mc.textRenderer.drawWithShadow(matrices, boolSet.getName() + ": On", parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, Color.GREEN.getRGB());
        }else {
            mc.textRenderer.drawWithShadow(matrices, boolSet.getName() + ": Off", parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, Color.RED.getRGB());
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void onMouseClick(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) boolSet.toggle();
        super.onMouseClick(mouseX, mouseY, button);
    }

    @Override
    public boolean isHovered(double mouseX, double mouseY) {
        return super.isHovered(mouseX, mouseY);
    }
}
