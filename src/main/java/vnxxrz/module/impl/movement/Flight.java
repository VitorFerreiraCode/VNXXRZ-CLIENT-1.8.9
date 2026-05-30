package vnxxrz.module.impl.movement;


import vnxxrz.module.Category;
import vnxxrz.module.Module;
import vnxxrz.module.ModuleInfo;

@ModuleInfo(
        name = "Flight",
        description = "Você ganha asas :D",
        category = Category.MOVEMENT,
        enabled = true
)

public class Flight extends Module {
    @Override
    public void onUpdate() {
        if(mc.thePlayer != null) {
            mc.thePlayer.capabilities.isFlying = true;

            if(mc.gameSettings.keyBindJump.isPressed()) {
                mc.thePlayer.motionY += 0.2;
            }

            if(mc.gameSettings.keyBindSneak.isPressed()) {
                mc.thePlayer.motionY -= 0.2;
            }

            if(mc.gameSettings.keyBindForward.isPressed()) {
                mc.thePlayer.capabilities.setFlySpeed(0.25f);
            }
        }
    }

    @Override
    public void onDisable() {
        mc.thePlayer.capabilities.isFlying = false;
    }
}
