package jp.github.erudo.eantitroll.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class User {

	JavaPlugin plg;


	public User(JavaPlugin plg) {
		this.plg = plg;
	}

	public static HashMap<String, Player> map = new HashMap<>();
	public static HashMap<Player, Integer> warning = new HashMap<>();
	public static HashMap<Player,Boolean> freezing = new HashMap<>();

	public static void addMap(String player_name, Player p) {
		if (!map.containsKey(player_name)) {
			map.put(player_name, p);
		}
	}

	public static void removeMap(String player_name) {
		if (map.containsKey(player_name)) {
			map.remove(player_name);
		}
	}

	public static int getWarning(Player p) {
		return warning.get(p);

	}

	public static void addWarning(Player p) {
		if (warning.containsKey(p)) {
			getWarning(p);
			warning.put(p, warning.get(p) + 1);
		} else {
			warning.put(p, 0);
		}
	}

	public static void initWarnig(Player p) {
		if (warning.containsKey(p)) {
			warning.remove(p);
		}
	}

	public static boolean getFreezing(Player player) {
		if(freezing.containsKey(player)) {
			return freezing.get(player);
		}else {
			setFreezing(player,false);
			return freezing.get(player);
		}

	}

	public static void setFreezing(Player player, boolean b) {
		freezing.put(player, b);
	}
}
