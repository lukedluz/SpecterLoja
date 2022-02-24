package com.lucas.specterloja.objetos;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

import com.lucas.specterloja.Main;
import com.lucas.specterprestigio.api.API;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.lucas.specterloja.cache.ItensLoja;

public class LojaItem {

	protected ItemStack c(Player p, Material mt, String nome) {
		if (Main.m.getConfig().getBoolean("HabilitarDebug"))
			Bukkit.getConsoleSender().sendMessage("Setando item: " + mt);
		return c(p, mt, "§4" + nome,
				new String[] { "", " §aQuantidade: §7x" + 64,
						" §aValor: §aR$ §7" + getFormat(getSoma(p, ItensLoja.itensloja.get(mt))), "§c",
						"§cClique para comprar x" + 64 + " " + nome + "." });
	}

	protected ItemStack c(Player p, Material mt, String nome, int quantidade) {
		if (Main.m.getConfig().getBoolean("HabilitarDebug"))
			Bukkit.getConsoleSender().sendMessage("Setando item: " + mt);
		return c(p, mt, "§4" + nome,
				new String[] { "", " §aQuantidade: §7x" + quantidade,
						" §aValor: §aR$ §7" + getFormat(getSoma(p, ItensLoja.itensloja.get(mt))), "§c",
						"§cClique para comprar x" + quantidade + " " + nome + "." });
	}

	protected ItemStack c(Player p, Material mt, int data, String nome) {
		if (Main.m.getConfig().getBoolean("HabilitarDebug"))
			Bukkit.getConsoleSender().sendMessage("Setando item: " + mt);
		return c(p, mt, data, "§4" + nome,
				new String[] { "", " §aQuantidade: §7x" + 64,
						" §aValor: §aR$ §7" + getFormat(getSoma(p, ItensLoja.itensloja.get(mt))), "§c",
						"§cClique para comprar x" + 64 + " " + nome + "." });
	}

	protected ItemStack c(Player p, Material mt, int data, String nome, int quantidade) {
		if (Main.m.getConfig().getBoolean("HabilitarDebug"))
			Bukkit.getConsoleSender().sendMessage("Setando item: " + mt);
		return c(p, mt, data, "§4" + nome,
				new String[] { "", " §aQuantidade: §7x" + quantidade,
						" §aValor: §aR$ §7" + getFormat(getSoma(p, ItensLoja.itensloja.get(mt))), "§c",
						"§cClique para comprar x" + quantidade + " " + nome + "." });
	}

	protected String format(double valor) {
		DecimalFormat decimalFormat = new DecimalFormat("#.##", new DecimalFormatSymbols(new Locale("pt", "BR")));
		return decimalFormat.format(valor);
	}

	protected String getFormat(double valor) {
		String[] simbols = new String[] { "", "k", "M", "B", "T", "Q", "QQ", "S", "SS", "O", "N", "D", "UN", "DD", "TD",
				"QD", "QID", "SD", "SSD", "OD", "ND" };
		int index;
		for (index = 0; valor / 1000.0 >= 1.0; valor /= 1000.0, ++index) {
		}
		return format(valor) + simbols[index];
	}

	protected ItemStack c(Player p, Material mt, String nome, String[] lore) {
		ItemStack item = new ItemStack(mt);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	protected ItemStack c(Player p, Material mt, int by, String nome, String[] lore) {
		ItemStack item = new ItemStack(mt, 1, (short) by);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

	protected Double getSoma(Player p, Double preco) {
		Double desconto = 1.0;
		if (p.hasPermission("loja.mvp+"))
			desconto = 0.40;
		else if (p.hasPermission("loja.mvp"))
			desconto = 0.70;
		else if (p.hasPermission("loja.vip"))
			desconto = 0.90;
		Double total = preco * desconto;
		total = total * (1.0 - (API.getPrestigio(p.getUniqueId()) * 0.05));
		return total;
	}
}
