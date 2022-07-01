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
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class TextEntryArrayDeseralizer implements JsonDeserializer<TextEntry[]> {

    @Override
    public TextEntry[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<TextEntry> textEntries = new ArrayList<TextEntry>(0);
        if (!json.isJsonArray())
            throw new JsonParseException("Json is not of JsonArray. Object is of class " + json.getClass().getName() + ". Submitted json is " + json);
        JsonArray array = json.getAsJsonArray();
        array.forEach(e -> {
            if (!e.isJsonObject())
                throw new JsonParseException("Entry is not of JsonObject. Object is of class " + e.getClass().getName() + ". Submitted json is " + json);
            JsonObject entry = e.getAsJsonObject();
            TextEntry textEntry = new TextEntry();
            textEntry.text = getStringOrFail(entry.getAsJsonPrimitive("text"));
            textEntry.font = getStringOrDefault(entry.getAsJsonPrimitive("font"));
            textEntry.color = getStringOrDefault(entry.getAsJsonPrimitive("color"));
            textEntry.bold = getBooleanOrDefault(entry.getAsJsonPrimitive("bold"));
            textEntry.italic = getBooleanOrDefault(entry.getAsJsonPrimitive("italic"));
            textEntry.underlined = getBooleanOrDefault(entry.getAsJsonPrimitive("underlined"));
            textEntry.strikethrough = getBooleanOrDefault(entry.getAsJsonPrimitive("strikethrough"));
            textEntry.obfuscated = getBooleanOrDefault(entry.getAsJsonPrimitive("obfuscated"));
            textEntries.add(textEntry);
        });

        return textEntries.toArray(new TextEntry[textEntries.size()]);
    }

    private Boolean getBooleanOrDefault(JsonPrimitive element) {
        if (element == null)
            return null;
        return element.getAsBoolean();
    }

    private String getStringOrDefault(JsonPrimitive element) {
        if (element == null)
            return null;
        return element.getAsString();
    }

    private String getStringOrFail(JsonPrimitive element) throws JsonParseException {
        if (element == null)
            throw new JsonParseException("Text element not present.");
        return element.getAsString();
    }

}
