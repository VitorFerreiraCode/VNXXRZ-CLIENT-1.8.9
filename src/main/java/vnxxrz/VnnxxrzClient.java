package vnxxrz;

import lombok.Getter;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import vnxxrz.event.impl.EventKey;
import vnxxrz.module.ModuleManager;
import vnxxrz.ui.clickgui.ClickGUIScreen;

@Getter
public enum VnnxxrzClient implements Subscriber {
    INSTANCE;

    private String clientName = "VNXXRZ Client";
    private String version = "0.0.1";

    private final Minecraft mc = Minecraft.getMinecraft();
    private final FontRenderer fr = mc.fontRendererObj;

    private ModuleManager moduleManager;

    public static final EventBus BUS = EventManager.builder()
            .setName("root/vnnxrz")
            .setSuperListeners()
            .build();

    public void init() {
        BUS.subscribe(this);
        Display.setTitle(clientName + " " + version);

        moduleManager = new ModuleManager();
    }

    public void shutdown() {
        BUS.subscribe(this);
    }

    // ABRE O CHEAT
    @Subscribe
    private final Listener<EventKey> keyListener = new Listener<>(e -> {
        //trocar pra RCTRL para windows
        if(e.getKey() == Keyboard.KEY_RSHIFT) {
            mc.displayGuiScreen(new ClickGUIScreen());
        }
    });

    // ABRE O CLIENT
//    @Subscribe
//    private final Listener<EventKey> keyListener = new Listener<>(e -> {
//        if(e.getKey() == Keyboard.KEY_RCONTROL) {
//            mc.displayGuiScreen(new ClickGUIScreen());
//        }
//    });

}
