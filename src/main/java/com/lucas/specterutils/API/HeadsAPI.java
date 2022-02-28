package com.lucas.specterutils.API;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.lang.reflect.Field;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadsAPI {

	public static ItemStack cactus = getSkull(
			"http://textures.minecraft.net/texture/904f1a55943c594e7119e884c5da2a2bca8e7e6516a0649aa7e55658e0e9");
	public static ItemStack cacto = getSkull(
			"http://textures.minecraft.net/texture/2f585b41ca5a1b4ac26f556760ed11307c94f8f8a1ade615bd12ce074f4793");

	public static ItemStack getSkull(String url) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		if (url != null && !url.isEmpty()) {
			SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
			GameProfile profile = new GameProfile(UUID.randomUUID(), (String) null);
			byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
			profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
			Field profileField = null;

			try {
				profileField = skullMeta.getClass().getDeclaredField("profile");
			} catch (SecurityException | NoSuchFieldException var8) {
				var8.printStackTrace();
			}

			profileField.setAccessible(true);

			try {
				profileField.set(skullMeta, profile);
			} catch (IllegalAccessException | IllegalArgumentException var7) {
				var7.printStackTrace();
			}

			skull.setItemMeta(skullMeta);
			return skull;
		} else {
			return skull;
		}
	}
}