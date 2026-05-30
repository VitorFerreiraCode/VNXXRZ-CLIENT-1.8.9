package vnxxrz.module.impl.movement;

import vnxxrz.module.Category;
import vnxxrz.module.Module;
import vnxxrz.module.ModuleInfo;

@ModuleInfo(
        name = "Sprint",
        description = "Sprinta automaticamente",
        category = Category.MOVEMENT,
        enabled = true
)

public class Sprint extends Module {
    @Override
    public void onUpdate() {
        mc.gameSettings.keyBindSprint.setPressed(true);
    }
}
