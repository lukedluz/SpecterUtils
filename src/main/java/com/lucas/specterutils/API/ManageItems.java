package com.lucas.specterutils.API;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ManageItems {
	public static boolean test(Player p, int count, Material mat) {
		Map<Integer, ? extends ItemStack> ammo = p.getInventory().all(mat);
		int found = 0;
		for (ItemStack stack : ammo.values())
			found += stack.getAmount();
		if (count > found)
			return false;
		return true;
	}

	public static boolean consumeItem(Player p, int count, Material mat) {
		Map<Integer, ? extends ItemStack> ammo = p.getInventory().all(mat);
		int found = 0;
		for (ItemStack stack : ammo.values())
			found += stack.getAmount();
		if (count > found)
			return false;
		for (Integer index : ammo.keySet()) {
			ItemStack stack = ammo.get(index);
			int removed = Math.min(count, stack.getAmount());
			count -= removed;
			if (stack.getAmount() == removed)
				p.getInventory().setItem(index, null);
			else
				stack.setAmount(stack.getAmount() - removed);
			if (count <= 0)
				break;
		}
		p.updateInventory();
		return true;
	}
}
