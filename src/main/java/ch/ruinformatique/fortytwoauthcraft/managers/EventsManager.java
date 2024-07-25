package ch.ruinformatique.fortytwoauthcraft.managers;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

import ch.ruinformatique.fortytwoauthcraft.FortytwoAuthCraft;

public class EventsManager {
	public static void spawnFirework(Location location) {
		FireworkMeta fireworkMeta = (FireworkMeta) Bukkit.getItemFactory()
				.getItemMeta(org.bukkit.Material.FIREWORK_ROCKET);
		fireworkMeta.setPower(2);
		fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

		for (int i = 0; i < 6; i++) {
			Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK_ROCKET);
			firework.setFireworkMeta(fireworkMeta);
			firework.setMetadata("noDamage", new FixedMetadataValue(FortytwoAuthCraft.getPlugin(), true));
			if (i == 0) {
				firework.detonate();
			}
		}
	}
}
