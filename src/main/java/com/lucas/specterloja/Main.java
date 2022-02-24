package com.lucas.specterloja;

import com.lucas.specterloja.cache.ItensLoja;
import com.lucas.specterloja.commands.LojaCommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	public static Economy eco = null;
	public static Main m;

	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("§7| §bSpecterLoja            §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §bVersão 1.0             §7|");
		Bukkit.getConsoleSender().sendMessage("§7| §fStatus: §aLigado       §7|");
		Bukkit.getConsoleSender().sendMessage("§7==========================");
		Bukkit.getConsoleSender().sendMessage("");
		m = this;
		saveDefaultConfig();
		for (String lista : getConfig().getStringList("ItensLoja")) {
			String[] pp = lista.split(":");
			String id = pp[0].toUpperCase();
			if (!isDouble(pp[1]))
				continue;
			double precocompra = Double.valueOf(pp[1]);
			ItensLoja.itensloja.put(Material.getMaterial(id), precocompra);
		}
		setupEconomy();
		getCommand("loja").setExecutor(new LojaCommand());
		Bukkit.getPluginManager().registerEvents(new LojaCommand(), this);
		Bukkit.getOnlinePlayers().forEach(t -> ItensLoja.livros.put(t.getName(), LojaCommand.getLivro(t)));
	}

	public static boolean isDouble(String s) {
		try {
			if (Double.parseDouble(s) != 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			eco = economyProvider.getProvider();
		}
		return eco != null;
	}
}
