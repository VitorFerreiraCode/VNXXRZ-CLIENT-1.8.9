package vnxxrz.module;

import lombok.Getter;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.commons.lang3.Validate;
import vnxxrz.VnnxxrzClient;
import vnxxrz.event.impl.Event2D;
import vnxxrz.event.impl.EventKey;
import vnxxrz.event.impl.EventUpdate;

@Getter
public abstract class Module implements Subscriber {

    private final String name, description;
    private final Category category;
    private final boolean enabledByDefault;
    private boolean toggled;

    protected final Minecraft mc = VnnxxrzClient.INSTANCE.getMc();
    protected final FontRenderer fr = VnnxxrzClient.INSTANCE.getFr();

    public Module() {
        ModuleInfo info = getClass().getAnnotation(ModuleInfo.class);
        Validate.notNull(info, "Confused annotation exception");

        this.name = info.name();
        this.description = info.description();
        this.category = info.category();
        this.enabledByDefault = info.enabled();

        if(enabledByDefault) toggle();

    }

    public void toggle() {
        setEnabled(!toggled);
    }

    public void setEnabled(boolean state) {
        if(this.toggled == state) return;
        this.toggled = state;

        if(state) {
            VnnxxrzClient.BUS.subscribe(this);
            VnnxxrzClient.BUS.subscribe(eventUpdateListener);
            VnnxxrzClient.BUS.subscribe(event2DListener);
            VnnxxrzClient.BUS.subscribe(eventKeyListener);
            onEnable();
        } else {
            VnnxxrzClient.BUS.unsubscribe(this);
            VnnxxrzClient.BUS.unsubscribe(eventUpdateListener);
            VnnxxrzClient.BUS.unsubscribe(event2DListener);
            VnnxxrzClient.BUS.unsubscribe(eventKeyListener);
            onDisable();
        }

    }

    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}
    public void on2D(ScaledResolution sr) {}
    public void onKey(int key) {}

    @Subscribe
    private final Listener<EventUpdate> eventUpdateListener = new Listener<>(e -> {
        if(toggled) onUpdate();
    });

    @Subscribe
    private final Listener<Event2D> event2DListener = new Listener<>(e -> {
        if(toggled) on2D(e.getSr());
    });

    @Subscribe
    private final Listener<EventKey> eventKeyListener = new Listener<>(e -> {
        if(toggled) onKey(e.getKey());
    });

}
