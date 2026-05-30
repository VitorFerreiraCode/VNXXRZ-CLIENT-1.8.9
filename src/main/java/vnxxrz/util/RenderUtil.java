package vnxxrz.util;

import net.minecraft.client.gui.Gui;

public class RenderUtil {
    public static void drawRect(int x, int y, int w, int h, int color) {
        Gui.drawRect(x, y, x + w, y + h, color);
    }
}
