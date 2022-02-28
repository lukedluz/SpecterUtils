package com.lucas.specterutils.API;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class APIGeral {
	
	public static Double GetFortuna(ItemStack item) {
		if (item.hasItemMeta() && item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
			Integer i = item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
			return i * 0.2;
		} else {
			return 0.0;
		}
	}
}
