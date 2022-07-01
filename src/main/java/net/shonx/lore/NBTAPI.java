/*
 * MIT License
 *
 * Copyright (c) 2022 shroomdog27
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.shonx.lore;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;

import net.minecraftforge.common.util.Constants.NBT;

public class NBTAPI {

    public static void addLore(ItemStack stack, StringNBT string) {
        CompoundNBT displayTag = getDisplayTag(stack);
        ListNBT lore = displayTag.getList("Lore", NBT.TAG_STRING);
        lore.add(string);
        displayTag.put("Lore", lore);
        save(stack, displayTag);
    }

    public static void removeLore(ItemStack stack) {
        CompoundNBT displayTag = getDisplayTag(stack);
        if (displayTag.contains("Lore", NBT.TAG_LIST))
            displayTag.remove("Lore");
        save(stack, displayTag);
        removeDisplayTagIfEmpty(stack);
    }

    public static void removeName(ItemStack stack) {
        CompoundNBT displayTag = getDisplayTag(stack);
        if (displayTag.contains("Name", NBT.TAG_STRING))
            displayTag.remove("Name");
        save(stack, displayTag);
        removeDisplayTagIfEmpty(stack);
    }

    public static void setNameTag(ItemStack stack, StringNBT name) {
        CompoundNBT displayTag = getDisplayTag(stack);
        displayTag.put("Name", name);

        save(stack, displayTag);
    }

    private static CompoundNBT getDisplayTag(ItemStack stack) {
        return stack.getOrCreateTagElement("display");
    }

    private static void removeDisplayTagIfEmpty(ItemStack stack) {
        CompoundNBT displayTag = getDisplayTag(stack);
        if (displayTag == null || displayTag.isEmpty())
            displayTag.remove("display");
        save(stack, displayTag);
        removeTagIfEmpty(stack);
    }

    private static void removeTagIfEmpty(ItemStack stack) {
        if (stack.getTag() == null || stack.getTag().isEmpty())
            stack.setTag(null);
    }

    private static void save(ItemStack stack, CompoundNBT displayTag) {
        CompoundNBT itemTag = stack.getOrCreateTag();
        itemTag.put("display", displayTag);
        stack.setTag(itemTag);
    }
}
