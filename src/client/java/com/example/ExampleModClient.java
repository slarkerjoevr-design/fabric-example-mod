package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {

    private boolean f1WasPressed = false;
    private boolean f2WasPressed = false;

    @Override
    public void onInitializeClient() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            long handle = client.getWindow().getHandle();

            boolean f1Pressed = InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_F1);
            boolean f2Pressed = InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_F2);

            // F1 → Hotbar slot 7 (index 6)
            if (f1Pressed && !f1WasPressed) {
                client.player.getInventory().selectedSlot = 6;
            }

            // F2 → Hotbar slot 8 (index 7)
            if (f2Pressed && !f2WasPressed) {
                client.player.getInventory().selectedSlot = 7;
            }

            f1WasPressed = f1Pressed;
            f2WasPressed = f2Pressed;

            // Disable vanilla F1/F2 behavior
            client.options.toggleHudKey.setPressed(false);
            client.options.screenshotKey.setPressed(false);
        });
    }
}
