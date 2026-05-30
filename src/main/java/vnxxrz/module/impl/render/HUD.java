package vnxxrz.module.impl.render;

import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import vnxxrz.module.Category;
import vnxxrz.module.Module;
import vnxxrz.module.ModuleInfo;

@ModuleInfo(
        name = "HUD",
        description = "",
        category = Category.RENDER,
        enabled = true
)

public class HUD extends Module {
    @Override
    public void on2D(ScaledResolution sr) {
        GL11.glPushMatrix();
        GL11.glScaled(1.5, 1.55, 1.5);
        fr.drawString("VNXXRZ Client", 1, 1, -1);
        GL11.glPopMatrix();
    }
}
