package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {

    private KeyBinding f1Key;
    private KeyBinding f2Key;

    @Override
    public void onInitializeClient() {

        // Register F1 and F2 keybindings
        f1Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.fkeyhotbar.f1",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F1,
            "category.fkeyhotbar.hotbar"
        ));

        f2Key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.fkeyhotbar.f2",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F2,
            "category.fkeyhotbar.hotbar"
        ));

        // Handle key presses each tick
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            // F1 → hotbar slot 7 (index 6)
            while (f1Key.wasPressed()) {
                client.player.getInventory().selectedSlot = 6;
            }

            // F2 → hotbar slot 8 (index 7)
            while (f2Key.wasPressed()) {
                client.player.getInventory().selectedSlot = 7;
            }
        });
    }
}
