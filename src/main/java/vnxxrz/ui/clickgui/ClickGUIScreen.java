package vnxxrz.ui.clickgui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import vnxxrz.VnnxxrzClient;
import vnxxrz.module.Category;
import vnxxrz.util.RenderUtil;

public class ClickGUIScreen extends GuiScreen {
    private final FontRenderer fr = VnnxxrzClient.INSTANCE.getFr();
    private final ScaledResolution sr = new ScaledResolution(VnnxxrzClient.INSTANCE.getMc());

    private final int boxW = 224;
    private final int boxH = 138;
    private final int boxX = (sr.getScaledWidth() / 2 - (boxW / 2));
    private final int boxY = (sr.getScaledHeight() / 2 - (boxH / 2));

    private Category catSelected = Category.COMBAT;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        RenderUtil.drawRect(boxX - 4, boxY - 4, boxW + 8, boxH + 8, 0x66000000);
        drawBorderedRect(boxX, boxY, boxW, boxH, 0xFF101010, 0xFF3B3B3B);

        RenderUtil.drawRect(boxX + 70, boxY + 1, 1, boxH - 2, 0xFF2C2C2C);
        RenderUtil.drawRect(boxX + 71, boxY + 1, 1, boxH - 2, 0xFF0A0A0A);

        int yOffset = boxY + 6;
        for(Category category : Category.values()) {
            boolean selected = category == catSelected;
            String categoryName = category.name();

            RenderUtil.drawRect(boxX + 6, yOffset, 58, 15, selected ? 0xFF2F5C35 : 0xFF181818);
            RenderUtil.drawRect(boxX + 6, yOffset, 58, 1, selected ? 0xFF73B978 : 0xFF303030);
            fr.drawString(categoryName, boxX + 10, yOffset + 4, selected ? 0xFFFFFFFF : 0xFF9A9A9A);

            yOffset += 18;
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawBorderedRect(int x, int y, int w, int h, int inside, int border) {
        RenderUtil.drawRect(x, y, w, h, border);
        RenderUtil.drawRect(x + 1, y + 1, w - 2, h - 2, inside);
    }

}
