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

package net.shonx.lore.command.arguments;

import java.util.regex.Pattern;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.shonx.lore.ItemAPI;
import net.shonx.lore.command.CommandExceptionTypes;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;

public class SetLoreCommand {
    public static LiteralArgumentBuilder<CommandSource> register() {
        return Commands.literal("lore").then(Commands.argument("{nbt}", StringArgumentType.greedyString()).executes(SetLoreCommand::executeCommand));
    }

    private static int executeCommand(CommandContext<CommandSource> ctx) throws CommandSyntaxException {
        CommandSource source = ctx.getSource();
        ItemStack item = source.getPlayerOrException().getItemInHand(Hand.MAIN_HAND);
        if (item.isEmpty())
            throw CommandExceptionTypes.ERROR_NO_ITEM.create(source.getDisplayName());
        ItemAPI.setLore(item, StringArgumentType.getString(ctx, "{nbt}").replaceAll(Pattern.quote("\\"), ""));
        ctx.getSource().sendSuccess(new StringTextComponent("Added lore to item."), true);
        return 1;
    }
}
