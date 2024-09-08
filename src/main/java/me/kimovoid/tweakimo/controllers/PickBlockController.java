package me.kimovoid.tweakimo.controllers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class PickBlockController {

    public static final PickBlockController instance = new PickBlockController();

    public boolean pickFromInventory(MovingObjectPosition target, World world, EntityPlayer player) {
        ItemStack result = this.getPickBlock(target, world, player);
        if (result == null) {
            return false;
        }

        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.isItemEqual(result) && ItemStack.areItemStackTagsEqual(stack, result)) {
                Minecraft mc = Minecraft.getMinecraft();
                mc.playerController.windowClick(player.inventoryContainer.windowId, i, player.inventory.currentItem, 2, player);
                return true;
            }
        }
        return false;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, EntityPlayer player) {
        ItemStack result = null;
        if (target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int x = target.blockX;
            int y = target.blockY;
            int z = target.blockZ;
            Block block = world.getBlock(x, y, z);

            if (!block.isAir(world, x, y, z)) {
                result = block.getPickBlock(target, world, x, y, z, player);
            }
        }
        else {
            if (target.entityHit == null) return null;
            result = target.entityHit.getPickedResult(target);
        }
        return result;
    }
}
