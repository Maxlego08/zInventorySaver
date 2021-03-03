package fr.maxlego08.zinventorysaver.zcore.utils.gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.inventory.ItemStack;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import fr.maxlego08.zinventorysaver.zcore.ZPlugin;
import fr.maxlego08.zinventorysaver.zcore.utils.ItemDecoder;

public class ItemStackAdapter extends TypeAdapter<ItemStack> {

	private static Type seriType = new TypeToken<Map<String, Object>>() {
	}.getType();

	private static String ITEM = "item";

	public ItemStackAdapter() {
	}

	@Override
	public void write(JsonWriter jsonWriter, ItemStack location) throws IOException {
		if (location == null) {
			jsonWriter.nullValue();
			return;
		}
		jsonWriter.value(getRaw(location));
	}

	@Override
	public ItemStack read(JsonReader jsonReader) throws IOException {
		if (jsonReader.peek() == JsonToken.NULL) {
			jsonReader.nextNull();
			return null;
		}
		return fromRaw(jsonReader.nextString());
	}

	private String getRaw(ItemStack itemStack) {
		Map<String, Object> serial = new HashMap<String, Object>();
		serial.put(ITEM, ItemDecoder.serializeItemStack(itemStack));
		return ZPlugin.z().getGson().toJson(serial);
	}

	private ItemStack fromRaw(String raw) {
		Map<String, Object> keys = ZPlugin.z().getGson().fromJson(raw, seriType);
		if (keys.containsKey(ITEM))
			return ItemDecoder.deserializeItemStack((String) keys.get(ITEM));
		return null;
	}

}
