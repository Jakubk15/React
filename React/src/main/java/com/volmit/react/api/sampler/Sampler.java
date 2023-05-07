package com.volmit.react.api.sampler;

import com.volmit.react.React;
import com.volmit.react.util.scheduling.J;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public interface Sampler {
    double sample();

    default Material getIcon() {
        return Material.SLIME_BALL;
    }

    default String format(double t) {
        return formattedValue(t) + " " + formattedSuffix(t);
    }

    default Component format(Component value, Component suffix) {
        return Component.empty().append(value).append(suffix);
    }

    String formattedValue(double t);

    String formattedSuffix(double t);

    void start();

    void stop();

    String getId();

    default <T> T executeSync(Supplier<T> executor) {
        if (Bukkit.isPrimaryThread()) {
            return executor.get();
        }

        AtomicReference<T> result = new AtomicReference<>();
        J.s(() -> result.set(executor.get()));

        while (result.get() == null) {
            J.sleep(5);
        }

        return result.get();
    }

    default Sampler getSampler(String id) {
        return React.instance.getSampleController().getSamplers().get(id);
    }

    default String sampleFormatted() {
        return format(sample());
    }
}
