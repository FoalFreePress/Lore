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

package net.shonx.lore.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.shonx.lore.command.arguments.ClearLoreCommand;
import net.shonx.lore.command.arguments.ClearNameCommand;
import net.shonx.lore.command.arguments.SetLoreCommand;
import net.shonx.lore.command.arguments.SetNameCommand;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class RootCommand {
    public static LiteralArgumentBuilder<CommandSource> register() {
        // @formatter:off
        return Commands.literal("lore").requires(cs -> cs.hasPermission(1))
                .then(Commands.literal("set")
                        .then(SetLoreCommand.register())
                        .then(SetNameCommand.register())
                        )
                .then(Commands.literal("clear")
                        .then(ClearLoreCommand.register())
                        .then(ClearNameCommand.register())
                        );
        // @formatter:on
    }
}
