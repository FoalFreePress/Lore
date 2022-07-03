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

import com.google.gson.Gson;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.shonx.lore.command.CommandExceptionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.StringNBT;

public class ItemAPI {

    private static final Gson gson = new Gson();

    public static void clearLore(ItemStack item) throws CommandSyntaxException {
        NBTAPI.removeLore(item);
    }

    public static void deleteName(ItemStack item) throws CommandSyntaxException {
        NBTAPI.removeName(item);
    }

    public static void renameItem(ItemStack item, String json) throws CommandSyntaxException {
        NBTAPI.setNameTag(item, StringNBT.valueOf(json));
    }

    public static void setLore(ItemStack item, String json) throws CommandSyntaxException {
        NBTAPI.removeLore(item);
        String[] entries = getJSONEntries(json);
        for (String entry : entries)
            NBTAPI.addLore(item, StringNBT.valueOf(entry));
    }

    private static String[] getJSONEntries(String json) throws CommandSyntaxException {
        try {
            return gson.fromJson(json, String[].class);
        } catch (Exception e) {
            throw CommandExceptionTypes.ERROR_INVALID_JSON.create(e.getMessage());
        }
    }

}
