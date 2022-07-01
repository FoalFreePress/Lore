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

package net.shonx.lore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TextEntrySeralizer implements JsonSerializer<TextEntry> {

    @Override
    public JsonElement serialize(TextEntry src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        if (src.text == null)
            throw new IllegalStateException("Text cannot be null here.");
        result.add("text", new JsonPrimitive(src.text));
        if (src.font != null)
            result.add("font", new JsonPrimitive(src.font));
        if (src.color != null)
            result.add("color", new JsonPrimitive(src.color));
        if (src.bold != null)
            result.add("bold", new JsonPrimitive(src.bold.booleanValue()));
        if (src.italic != null)
            result.add("italic", new JsonPrimitive(src.italic.booleanValue()));
        if (src.underlined != null)
            result.add("underlined", new JsonPrimitive(src.underlined.booleanValue()));
        if (src.strikethrough != null)
            result.add("strikethrough", new JsonPrimitive(src.strikethrough.booleanValue()));
        if (src.obfuscated != null)
            result.add("obfuscated", new JsonPrimitive(src.obfuscated.booleanValue()));
        return result;
    }

}
