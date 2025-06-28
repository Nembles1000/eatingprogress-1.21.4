package me.nembs.eatprogress;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class EatingProgressClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            renderEatingProgress(drawContext);
        });
    }

    private void renderEatingProgress(DrawContext drawContext) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null && player.isUsingItem()) {
            ItemStack itemStack = player.getActiveItem();

            if (itemStack.isOf(Items.SHIELD)) {
                return;
            }
            if (itemStack.isOf(Items.BOW)) {
                return;
            }
            if (itemStack.isOf(Items.CROSSBOW)) {
                return;
            }

            int maxUseTime = itemStack.getMaxUseTime(player);
            int useTimeLeft = player.getItemUseTimeLeft();

            if (maxUseTime > 0) {
                float progress = 1.0f - (useTimeLeft / (float) maxUseTime);
                progress = Math.min(progress, 1.0f);
                renderProgressText(drawContext, progress);
            }
        }
    }

    // progress bar
    private void renderProgressText(DrawContext drawContext, float progress) {
        int screenWidth = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int screenHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
        int x = screenWidth / 2;
        int y = screenHeight / 2 + 20; // Adjust as needed

        int percent = Math.round(progress * 100);
        String percentText = percent + "%";
        int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(percentText);
        int textX = x - textWidth / 2;

        drawContext.drawText(
                MinecraftClient.getInstance().textRenderer,
                percentText,
                textX,
                y,
                0xFFFFFF,       // color (white)
                true                 // shadow
        );
    }

}


