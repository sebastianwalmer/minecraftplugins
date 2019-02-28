package jp.github.erudo.eantitroll.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.eantitroll.utils.Config;
import jp.github.erudo.eantitroll.utils.MathUtils;
import jp.github.erudo.eantitroll.utils.MessageManager;
import jp.github.erudo.eantitroll.utils.User;

public class TNTListener implements Listener {

	JavaPlugin plg;

	Block block;
	Player player;

	public TNTListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteractive(PlayerInteractEvent e) {
		Action action = e.getAction();

		if (action == Action.RIGHT_CLICK_BLOCK) {
			final Player p = e.getPlayer();
			final Material block = e.getClickedBlock().getType();

			if (p.isOp() || p.hasPermission("eantitroll.admin") || player.hasPermission("eantitroll.tnt") ||player.hasPermission("eantitroll.tnt." + player.getWorld().getName())) {
				return;
			}

			if (p.getItemInHand().getType() == Material.FLINT_AND_STEEL) {
				if(block == Material.TNT) {
					e.setCancelled(true);

					Bukkit.broadcastMessage("§4" + p.getName() + "がTNTを着火しようとしています！！");

					User.addWarning(p);

					if (User.getWarning(p) == Config.getInstance(plg).getWarningCount()) {
						MessageManager.sendBANMessage(p);
						User.initWarnig(p);
					}

					User.setFreezing(p,true);
				}
			}

		}
	}


	@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent event) {
		if (event.getNewCurrent() >= 1) {
			Block blk = event.getBlock();
			ArrayList<Block> tntBlocks = new ArrayList<Block>();
			Boolean tntExists = false;
			Location loc = blk.getLocation();

			double x = MathUtils.eFloor(loc.getX(), 1);
			double y = MathUtils.eFloor(loc.getY(), 1);
			double z = MathUtils.eFloor(loc.getZ(), 1);

			if (blk.getRelative(BlockFace.NORTH).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.NORTH));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.EAST).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.EAST));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.SOUTH).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.SOUTH));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.WEST).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.WEST));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN));
				tntExists = true;
			} else if (blk.getRelative(BlockFace.DOWN).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.DOWN));
				tntExists = true;
			}else if(blk.getRelative(BlockFace.UP).getType().equals(Material.TNT)){
				tntBlocks.add(blk.getRelative(BlockFace.UP));
				tntExists = true;
			}else if(blk.getRelative(BlockFace.UP).getRelative(BlockFace.WEST).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.UP).getRelative(BlockFace.WEST));
				tntExists = true;
			}else if(blk.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.UP).getRelative(BlockFace.NORTH));
				tntExists = true;
			}else if(blk.getRelative(BlockFace.UP).getRelative(BlockFace.EAST).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.UP).getRelative(BlockFace.EAST));
				tntExists = true;
			}else if(blk.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.UP).getRelative(BlockFace.SOUTH));
				tntExists = true;
			}else if(blk.getRelative(BlockFace.SELF).getType().equals(Material.TNT)) {
				tntBlocks.add(blk.getRelative(BlockFace.SELF));
				tntExists = true;
			}

			if (tntExists) {

				tntBlocks.forEach(tntBlk -> tntBlk.setType(Material.AIR));

				Bukkit.broadcastMessage(
						ChatColor.AQUA + "[eAntiTroll]TNTの発生が" + ChatColor.WHITE + loc.getWorld().getName()
								+ ChatColor.AQUA + "の" + ChatColor.WHITE + " x: " + x +
								" y: " + y + " z: " + z + ChatColor.AQUA
								+ "で検知されました");

				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					if (p.isOp() || p.hasPermission("eantitroll.admin")) {
						MessageManager.sendHoverText(p, ChatColor.RED + "発生場所(" + loc.getWorld().getName() + " , "
								+ x + " , " + y + " , " + z + ")にテレポートする", "クリックしてテレポート",
								"/mv tp " + p.getName() + " " + loc.getWorld().getName());

					}
				}
			}
		}
	}


	@EventHandler
	public void onplaceTNT(BlockPlaceEvent e) {
		block = e.getBlock();

		player = e.getPlayer();

		String PlayerName = player.getName();

		if(player.isOp() || player.hasPermission("eantitroll.admin") || player.hasPermission("eantitroll.tnt." + player.getWorld().getName())) {
			return;
		}

		if (block.getType() == Material.TNT) {

			e.setCancelled(true);

			Bukkit.broadcastMessage("§4" + PlayerName + "がTNTを置こうとしています！！");

			User.addWarning(player);

			if (User.getWarning(player) == Config.getInstance(plg).getWarningCount()) {
				MessageManager.sendBANMessage(player);
				User.initWarnig(player);
			}

			User.setFreezing(player,true);

		}
	}

	@EventHandler
	public void onExplosion(ExplosionPrimeEvent e) {
		Entity entity = e.getEntity();

		if(!(entity.getType() == EntityType.PRIMED_TNT)) {
			return;
		}

		for(Player p : entity.getWorld().getPlayers()) {
			if(p.hasPermission("eantitroll.admin") || p.hasPermission("eantitroll.tnt."+entity.getWorld().getName())) {
				return;
			}

			e.setCancelled(true);
			User.addWarning(p);

			if (User.getWarning(player) == Config.getInstance(plg).getWarningCount()) {
				MessageManager.sendBANMessage(player);
				User.initWarnig(player);
			}

			User.setFreezing(p, true);
		}
	}

}
