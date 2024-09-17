package me.kimovoid.tweakimo.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiTexturedButton extends GuiButton {

    private ResourceLocation texture;
    protected final int u;
    protected final int v;
    protected final int vOff;
    protected final int textureWidth;
    protected final int textureHeight;

    public GuiTexturedButton(int id, int x, int y, int width, int height, int u, int v, int hoveredVOffset, ResourceLocation texture, int textureWidth, int textureHeight) {
        super(id, x, y, width, height, "");
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.vOff = hoveredVOffset;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
        super.drawButton(minecraft, mouseX, mouseY);
        if (this.visible) {
            minecraft.getTextureManager().bindTexture(this.texture);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            int u = this.u;
            int v = this.v;
            if (this.field_146123_n) {
                v += this.vOff;
            }
            GuiIngame.func_146110_a(this.xPosition, this.yPosition, u, v, this.width, this.height, this.textureWidth, this.textureHeight);
        }
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }
}