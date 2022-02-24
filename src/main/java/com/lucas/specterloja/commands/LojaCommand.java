package com.lucas.specterloja.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import com.lucas.specterloja.cache.ItensLoja;
import com.lucas.specterloja.objetos.LojaItem;
import net.milkbowl.vault.economy.Economy;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.lucas.specterlimite.api.LimiteAPI;

public class LojaCommand extends LojaItem implements CommandExecutor, Listener {
	Economy eco = com.lucas.specterloja.Main.eco;
	private HashMap<String, String> maquina = new HashMap<>();
	private HashMap<String, String> spawner = new HashMap<>();

	private ItemStack head(String nome, String[] lore) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(getHead(nome));
		meta.setDisplayName(nome.replace("&", "§"));
		meta.setLore(Arrays.asList(lore));
		skull.setItemMeta(meta);
		return skull;
	}

	private static String getHead(String m) {
		if (m.contains("Cogumelo")) {
			return "MHF_MUSHROOMCOW";
		}
		m = m.split(" ")[2];
		if (m.contains("Vaca")) {
			return "MHF_Cow";
		}
		if (m.contains("Porco")) {
			return "MHF_Pig";
		}
		if (m.contains("Ovelha")) {
			return "MHF_Sheep";
		}
		if (m.contains("Zumbi")) {
			return "MHF_Zombie";
		}
		if (m.contains("Esqueleto")) {
			return "MHF_Skeleton";
		}
		if (m.contains("Aranha")) {
			return "MHF_Spider";
		}
		if (m.contains("Golem")) {
			return "MHF_Golem";
		}
		if (m.contains("Ghast")) {
			return "MHF_Ghast";
		}
		if (m.contains("Enderman")) {
			return "MHF_Enderman";
		}
		if (m.contains("Pigman")) {
			return "MHF_PigZombie";
		}
		if (m.contains("Blaze")) {
			return "MHF_Blaze";
		}
		if (m.contains("Galinha")) {
			return "MHF_Chicken";
		}
		if (m.contains("Creeper") || m.contains("Super")) {
			return "MHF_CREEPER";
		}
		if (m.contains("Bruxa")) {
			return "MHF_WITCH";
		}
		if (m.contains("Guardian")) {
			return "MHF_GUARDIAN";
		}
		if (m.contains("Wither")) {
			return "MHF_WITHER";
		}
		if (m.contains("Ghast")) {
			return "MHF_GHAST";
		}
		if (m.contains("Slime")) {
			return "MHF_SLIME";
		}
		return "";
	}

	private ItemStack maquina(Player p, String nome, Double preco) {
		return head(nome, new String[] { " §7Preço: §f" + getFormat(getSoma(p, preco)), "", " §eAções:",
				"  §7Botão Esquerdo: §fQuantidade personalizada." });
	}

	@EventHandler
	public void joinevent(PlayerJoinEvent e) {
		ItensLoja.livros.put(e.getPlayer().getName(), getLivro(e.getPlayer()));
	}

	private Inventory createLoja(Player p, String nome, int pagina) {
		Inventory inv = null;
		ItemStack livro = ItensLoja.livros.get(p.getName());
		if (nome.equalsIgnoreCase("Maquinas")) {
			inv = Bukkit.createInventory(null, 5 * 9, "§8Categoria: [" + nome + " #" + pagina + "]");
			inv.setItem(40, c(p, Material.ARROW, "§cFechar", new String[] {}));
			inv.setItem(0, livro);
			inv.setItem(11, maquina(p, "§8Maquina de Vaca", 7E5));
			inv.setItem(12, maquina(p, "§dMaquina de Porco", 1E10));
			inv.setItem(13, maquina(p, "§fMaquina de Ovelha", 45E9));
			inv.setItem(14, maquina(p, "§2Maquina de Zumbi", 7E10));
			inv.setItem(15, maquina(p, "§7Maquina de Esqueleto", 45E10));
			inv.setItem(20, maquina(p, "§cMaquina de Aranha", 75E10));
			inv.setItem(21, maquina(p, "§FMaquina de Golem", 63E12));
			inv.setItem(22, maquina(p, "§fMaquina de Ghast", 75E15));
			inv.setItem(23, maquina(p, "§5Maquina de Enderman", 475E15));
			inv.setItem(24, maquina(p, "§6Maquina de Pigman", 755E17));
			inv.setItem(29, maquina(p, "§eMaquina de Blaze", 35E20));
			inv.setItem(30, maquina(p, "§fMaquina de Galinha", 1E22));
			inv.setItem(31, maquina(p, "§aMaquina de Creeper", 13E22));
			inv.setItem(32, maquina(p, "§5Maquina de Bruxa", 25E22));
			inv.setItem(33, maquina(p, "§bMaquina de Guardian", 425E21));
			return inv;
		} else if (nome.equalsIgnoreCase("Geradores")) {
			inv = Bukkit.createInventory(null, 5 * 9, "§8Categoria: [" + nome + " #" + pagina + "]");
			inv.setItem(40, c(p, Material.ARROW, "§cFechar", new String[] {}));
			inv.setItem(11, maquina(p, "§fGerador de Ovelha", 1E1));
			inv.setItem(12, maquina(p, "§dGerador de Porco", 1E1));
			inv.setItem(13, maquina(p, "§cGerador de VacaCogumelo", 1E1));
			inv.setItem(14, maquina(p, "§aGerador de SuperCreeper", 1E1));
			inv.setItem(15, maquina(p, "§eGerador de Blaze", 1E1));
			inv.setItem(20, maquina(p, "§2Gerador de Zumbi", 1E1));
			inv.setItem(21, maquina(p, "§fGerador de Golem", 1E1));
			inv.setItem(22, maquina(p, "§fGerador de Ghast", 1E1));
			inv.setItem(23, maquina(p, "§8Gerador de Wither", 1E1));
		} else {
			inv = Bukkit.createInventory(null, 6 * 9, "§8Categoria: [" + nome + " #" + pagina + "]");
			inv.setItem(49, c(p, Material.ARROW, "§cFechar", new String[] {}));
		}
		if (pagina >= 2) {
			inv.setItem(18, c(p, Material.ARROW, "§ePágina #" + (pagina - 1), new String[] {}));
		}
		inv.setItem(0, livro);
		List<ItemStack> itens = new ArrayList<>();
		if (nome.equalsIgnoreCase("blocos")) {
			if (pagina == 1) {
				itens.add(c(p, Material.STONE, 1, "Granitos"));
				itens.add(c(p, Material.STONE, 2, "Granitos Polidos"));
				itens.add(c(p, Material.STONE, 3, "Dioritos"));
				itens.add(c(p, Material.STONE, 4, "Dioritos Polidos"));
				itens.add(c(p, Material.STONE, 5, "Andesitos"));
				itens.add(c(p, Material.STONE, 6, "Andesitos Polidos"));
				itens.add(c(p, Material.GRASS, "Blocos de Grama"));
				itens.add(c(p, Material.DIRT, "Terra"));
				itens.add(c(p, Material.DIRT, 1, "Terras Grossas"));
				itens.add(c(p, Material.DIRT, 2, "Podzols"));
				itens.add(c(p, Material.MYCEL, "Micelium"));
				itens.add(c(p, Material.COBBLESTONE, "Pedregulhos"));
				itens.add(c(p, Material.WOOD, "Tábuas de Carvalho"));
				itens.add(c(p, Material.WOOD, 1, "Tábuas de Pinheiro"));
				itens.add(c(p, Material.WOOD, 2, "Tábuas de Eucalipto"));
				itens.add(c(p, Material.WOOD, 3, "Tábuas de Madeira de Selva"));
				itens.add(c(p, Material.SAND, "Areia"));
				itens.add(c(p, Material.SAND, 1, "Areias Vermelhas"));
				itens.add(c(p, Material.GRAVEL, "Cascalhos"));
				itens.add(c(p, Material.LOG, "Madeiras de Carvalho"));
				itens.add(c(p, Material.LOG, 1, "Madeiras de Pinheiro"));
				itens.add(c(p, Material.LOG, 2, "Madeiras de Eucalipto"));
				itens.add(c(p, Material.LOG, 3, "Madeiras de Selva"));
				itens.add(c(p, Material.WOOD, 4, "Madeiras de Acácia"));
				itens.add(c(p, Material.WOOD, 5, "Madeiras de Carvalho Escuro"));
				itens.add(c(p, Material.SANDSTONE, "Arenitos"));
				itens.add(c(p, Material.SANDSTONE, 1, "Arenito Talhado"));
				itens.add(c(p, Material.SANDSTONE, 2, "Arenito Liso"));
				itens.add(c(p, Material.WOOL, "Lã"));
			}
			if (pagina == 2) {
				itens.add(c(p, Material.WOOL, "Lã"));
				itens.add(c(p, Material.STEP, "Laje de Pedra"));
				itens.add(c(p, Material.STEP, 1, "Laje de Arenito"));
				itens.add(c(p, Material.STEP, 3, "Laje de Pedregulho"));
				itens.add(c(p, Material.STEP, 4, "Laje de Tijolos"));
				itens.add(c(p, Material.STEP, 5, "Laje de Tijolos de Pedra"));
				itens.add(c(p, Material.STEP, 6, "Laje de Tijolos do Nether"));
				itens.add(c(p, Material.STEP, 7, "Laje de Quartzo"));
				itens.add(c(p, Material.BRICK, "Tijolos"));
				itens.add(c(p, Material.BOOKSHELF, "Estante"));
				itens.add(c(p, Material.MOSSY_COBBLESTONE, "Pedra com Musgo"));
				itens.add(c(p, Material.ICE, "Gelo"));
				itens.add(c(p, Material.PACKED_ICE, "Gelo Compactado"));
				itens.add(c(p, Material.SNOW, "Neve"));
				itens.add(c(p, Material.CLAY, "Argila"));
				itens.add(c(p, Material.NETHERRACK, "Rocha do Nether"));
				itens.add(c(p, Material.SOUL_SAND, "Neve"));
				itens.add(c(p, Material.SMOOTH_BRICK, "Tijolos de Pedra"));
				itens.add(c(p, Material.SMOOTH_BRICK, 1, "Tijolos de Pedra com Musgo"));

			}
		}
		if (nome.equalsIgnoreCase("decoração")) {
			if (pagina == 1) {
				itens.add(c(p, Material.WEB, "Teia de Aranha"));
				itens.add(c(p, Material.LONG_GRASS, 1, "Relvas"));
				itens.add(c(p, Material.LONG_GRASS, 2, "Ervas Daninhas"));
				itens.add(c(p, Material.DEAD_BUSH, 0, "Arbustos Mortos"));
				itens.add(c(p, Material.YELLOW_FLOWER, 0, "Dentes de Leão"));
				itens.add(c(p, Material.RED_ROSE, 0, "Papoilas"));
				itens.add(c(p, Material.RED_ROSE, 1, "Orquídias Azuis"));
				itens.add(c(p, Material.RED_ROSE, 2, "Alhos Silvestres"));
				itens.add(c(p, Material.RED_ROSE, 3, "Orquídias Brancas"));
				itens.add(c(p, Material.RED_ROSE, 4, "Tulipas Vermelhas"));
				itens.add(c(p, Material.RED_ROSE, 5, "Tulipas Laranjas"));
				itens.add(c(p, Material.RED_ROSE, 6, "Tulipas Brancas"));
				itens.add(c(p, Material.RED_ROSE, 7, "Tulipas Rosas"));
				itens.add(c(p, Material.RED_ROSE, 8, "Tulipas Margaridas"));
				itens.add(c(p, Material.BROWN_MUSHROOM, 0, "Cogumelos"));
				itens.add(c(p, Material.RED_MUSHROOM, 0, "Cogumelos"));
				itens.add(c(p, Material.TORCH, 0, "Tochas"));
				itens.add(c(p, Material.LADDER, 0, "Escadas de Mão"));
				itens.add(c(p, Material.SNOW, 0, "Neve"));
				itens.add(c(p, Material.CACTUS, 0, "Cactos"));
				itens.add(c(p, Material.JUKEBOX, 0, "Jukeboxes"));
				itens.add(c(p, Material.VINE, 0, "Vinhas"));
				itens.add(c(p, Material.CARPET, 0, "Tapetes"));
				itens.add(c(p, Material.DOUBLE_PLANT, 0, "Girassois"));
				itens.add(c(p, Material.DOUBLE_PLANT, 1, "Lírios"));
				itens.add(c(p, Material.DOUBLE_PLANT, 2, "Relvas Grandes"));
				itens.add(c(p, Material.DOUBLE_PLANT, 3, "Reventos Grandes"));
				itens.add(c(p, Material.DOUBLE_PLANT, 4, "Roseiras"));
				itens.add(c(p, Material.DOUBLE_PLANT, 5, "Roseiras"));
			}
			if (pagina == 2) {
				itens.add(c(p, Material.DOUBLE_PLANT, 5, "Teias de Peónias"));
				itens.add(c(p, Material.PAINTING, 0, "Quadros"));
				itens.add(c(p, Material.FLOWER_POT_ITEM, "Pote de Flor"));
				itens.add(c(p, Material.SIGN, 0, "Tabuletas"));
				itens.add(c(p, Material.BANNER, 0, "Bandeira"));
				itens.add(c(p, Material.WOOD_PLATE, 0, "Placa de Pressão de Madeira", 16));
				itens.add(c(p, Material.STONE_PLATE, 0, "Placa de Pressão de Pedra", 16));
				itens.add(c(p, Material.GOLD_PLATE, 0, "Placa de Pressão (leves)", 16));
				itens.add(c(p, Material.IRON_PLATE, 0, "Placa de Pressão (pesadas)", 16));
				itens.add(c(p, Material.STONE_BUTTON, 0, "Botão de Pedra"));
				itens.add(c(p, Material.LEVER, 0, "Alavancas"));
			}
		}
		if (nome.equalsIgnoreCase("corantes")) {
			itens.add(c(p, Material.INK_SACK, 0, "Corante Preto", 8));
			itens.add(c(p, Material.INK_SACK, 1, "Corante Vermelho", 8));
			itens.add(c(p, Material.INK_SACK, 2, "Corante Verde Escuro", 8));
			itens.add(c(p, Material.INK_SACK, 3, "Corante Castanho", 8));
			itens.add(c(p, Material.INK_SACK, 4, "Corante Azul", 8));
			itens.add(c(p, Material.INK_SACK, 5, "Corante Roxo", 8));
			itens.add(c(p, Material.INK_SACK, 6, "Corante Azul Ciano", 8));
			itens.add(c(p, Material.INK_SACK, 7, "Corante Cinza Claro", 8));
			itens.add(c(p, Material.INK_SACK, 8, "Corante Cinza Escuro", 8));
			itens.add(c(p, Material.INK_SACK, 9, "Corante Rosa", 8));
			itens.add(c(p, Material.INK_SACK, 10, "Corante Verde Claro", 8));
			itens.add(c(p, Material.INK_SACK, 11, "Corante Amarelo", 8));
			itens.add(c(p, Material.INK_SACK, 12, "Corante Azul Claro", 8));
			itens.add(c(p, Material.INK_SACK, 13, "Corante Magenta", 8));
			itens.add(c(p, Material.INK_SACK, 14, "Corante Laranja", 8));
			itens.add(c(p, Material.INK_SACK, 15, "Farinha de Osso", 8));
		}
		if (nome.equalsIgnoreCase("Utilitários")) {
			itens.add(c(p, Material.TRAPPED_CHEST, "Baú de Armadilha", 16));
			itens.add(c(p, Material.CHEST, "Baú", 16));
			itens.add(c(p, Material.WORKBENCH, "Mesa de Criação", 1));
			itens.add(c(p, Material.FURNACE, "Fornalha", 1));
			itens.add(c(p, Material.ENCHANTMENT_TABLE, "Mesa de Encantamento", 1));
			itens.add(c(p, Material.ENDER_CHEST, "Baú do Ender", 1));
			itens.add(c(p, Material.ANVIL, "Bigorna", 1));
			itens.add(c(p, Material.WATER_BUCKET, "Balde de Água", 1));
		}
		if (nome.equalsIgnoreCase("Ferramentas")) {
			inv.setItem(4, c(p, Material.ENCHANTED_BOOK, "Sabedoria do Dragão", 1));
			inv.setItem(11, c(p, Material.DIAMOND_HELMET, "Capacete P4", 3));
			inv.setItem(20, c(p, Material.DIAMOND_CHESTPLATE, "Peitoral P4", 3));
			inv.setItem(29, c(p, Material.DIAMOND_LEGGINGS, "Calça P4", 3));
			inv.setItem(38, c(p, Material.DIAMOND_BOOTS, "Bota P4", 3));
			inv.setItem(13, c(p, Material.DIAMOND_SWORD, "Espada de Diamante", 1));
			inv.setItem(15, c(p, Material.DIAMOND_PICKAXE, "Picareta de Diamante", 1));
			inv.setItem(22, c(p, Material.DIAMOND_AXE, "Machado de Diamante", 1));
			inv.setItem(24, c(p, Material.DIAMOND_SPADE, "Pá de Diamante", 1));
			inv.setItem(31, c(p, Material.POTION, 8233, "Poção de Força", 5));
			inv.setItem(33, c(p, Material.POTION, 8226, "Poção de Velocidade", 5));
		}
		if (nome.equalsIgnoreCase("Diversos")) {
			itens.add(c(p, Material.FISHING_ROD, "Vara de Pesca", 1));
			itens.add(c(p, Material.GOLDEN_APPLE, 1, "Maça Dourada", 1));
			itens.add(c(p, Material.SLIME_BALL, "Gosma de  Slime"));
			itens.add(c(p, Material.MELON_SEEDS, "Sementes de Melão", 16));
		}
		if (itens.isEmpty())
			return inv;
		int i = 10;
		for (ItemStack item : itens) {
			if (i == 17 || i == 26 || i == 35) {
				i = i + 2;
			}
			if (i == 44) {
				inv.setItem(26, c(p, Material.ARROW, "§ePágina #" + (pagina + 1), new String[] {}));
				break;
			}
			inv.setItem(i, item);
			i++;
		}
		itens.clear();
		return inv;
	}

	@EventHandler
	private void onChat(ChatMessageEvent e) {
		if (SimpleClans.getInstance().getClanManager().getClanPlayer(e.getSender()) != null
				&& e.getTags().contains("clantag")
				&& SimpleClans.getInstance().getClanManager().getClanPlayer(e.getSender()) != null) {
			e.setTagValue("clantag",
					SimpleClans.getInstance().getClanManager().getClanPlayer(e.getSender()).getTagLabel());
		}
	}

	@EventHandler
	public void clickinv(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player))
			return;
		if (e.getInventory().getName().equalsIgnoreCase("§8Loja de Itens (Coins)")) {
			e.setCancelled(true);
			if (e.getRawSlot() == 0) {
				return;
			}
			if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null)
				return;
			Player p = (Player) e.getWhoClicked();
			p.closeInventory();
			String categoria = e.getCurrentItem().getItemMeta().getDisplayName().replace("§6", "");
			if (categoria.equalsIgnoreCase("ferramentas e armaduras"))
				categoria = "Ferramentas";
			p.openInventory(createLoja(p, categoria, 1));
			return;
		}
		if (e.getInventory().getName().startsWith("§8Categoria: ")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR)
				return;
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cFechar")) {
				p.closeInventory();
				p.openInventory(getLoja(p));
				return;
			}
			if (e.getRawSlot() == 0) {
				return;
			}
			if (e.getRawSlot() == 18) {
				p.closeInventory();
				p.openInventory(createLoja(p, e.getInventory().getName().split(": ")[1].split(" ")[0].replace("[", ""),
						Integer.valueOf(e.getInventory().getName().split("#")[1].replace("]", "")) - 1));
				return;
			}
			if (e.getRawSlot() == 26) {
				p.closeInventory();
				p.openInventory(createLoja(p, e.getInventory().getName().split(": ")[1].split(" ")[0].replace("[", ""),
						Integer.valueOf(e.getInventory().getName().split("#")[1].split("]")[0]) + 1));
				return;
			}
			ItemStack item = e.getCurrentItem().clone();
			ItemMeta im = item.getItemMeta();
			if (item.getType() == Material.SKULL_ITEM) {
				p.closeInventory();
				String path = ChatColor.stripColor(com.lucas.specterrankup.api.API.getRank(p.getUniqueId()).getPrefix().replace("III]", "")
				.replace("II]", "").replace("I]", "").replace("[", ""));
				String l = im.getDisplayName();
				l = l.split(" ")[2];
				if (l.contains("Golem"))
					l = "IronGolem";
				if (!p.hasPermission("mint.adadknsa")) {
					 if (!l.equalsIgnoreCase(path)) {
						p.sendMessage("§cOPS! Você precisa estar no rank '" + l.split(" ")[2]
								+ "' para comprar este item :(");
						return;
					}
				}
				if (e.getInventory().getName().substring(14).replace(" #1]", "").equalsIgnoreCase("Geradores")) {
					spawner.put(p.getName(), im.getDisplayName());
					p.sendMessage(new String[] { "", "§aDigite no chat a quantidade de geradores desejada!",
							"§7Máximo de 48 caracteres!", "§8Para cancelar digite §c'cancelar'§8.", "" });
				} else {
					maquina.put(p.getName(), im.getDisplayName());
					p.sendMessage(new String[] { "", "§aDigite no chat a quantidade de maquinas desejada!",
							"§7Máximo de 48 caracteres!", "§8Para cancelar digite §c'cancelar'§8.", "" });
				}
				return;
			}
			double preco = getSoma(p, ItensLoja.itensloja.get(item.getType()));
			String lq = im.getLore().get(1);
			int quantidade = Integer.valueOf(lq.split("x")[1]);
			Double total = preco * (1 - (0.05 * com.lucas.specterprestigio.api.API.getPrestigio(p)));
			OfflinePlayer player = Bukkit.getOfflinePlayer(p.getUniqueId());
			if (!eco.has(player, total)) {
				p.sendMessage("§cOPS! Você não possui dinheiro para comprar este item.");
				return;
			}
			if (im.getDisplayName().equalsIgnoreCase("§4Sabedoria do Dragão")) {
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 1F);
				p.getInventory()
						.addItem(c(p, Material.ENCHANTED_BOOK, "§6Sabedoria do Dragão I",
								new String[] { "§7Aumenta o nível de todos os", "§7outros encantamentos do",
										"§7seu item em 5.", "", "§fEncanta: §aTodos os itens.",
										"§eArraste para cima de um item para encantar." }));
				eco.withdrawPlayer(player, total);
				return;
			}
			if (im.getDisplayName().contains("P4")) {
				im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
				im.addEnchant(Enchantment.DURABILITY, 3, true);
			}
			eco.withdrawPlayer(player, total);
			p.playSound(p.getLocation(), Sound.LEVEL_UP, 0.5F, 1F);
			im.setLore(null);
			if (im.getDisplayName().contains("P4")) {
				im.setDisplayName("");
				item.setItemMeta(im);
				for (int i = quantidade; i > 0; i--) {
					p.getInventory().addItem(item);
				}
				return;
			}
			im.setDisplayName("");
			item.setItemMeta(im);
			item.setAmount(quantidade);
			p.getInventory().addItem(item);
		}
	}

	@EventHandler
	public void spawner(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String nome = p.getName();
		if (!spawner.containsKey(nome))
			return;
		e.setCancelled(true);
		if (e.getMessage().equalsIgnoreCase("cancelar")) {
			p.sendMessage("§cOperação Cancelada!");
			spawner.remove(nome);
			return;
		}
		Double d = 0.0;
		if (!isDouble(e.getMessage())) {
			p.sendMessage("§cPor favor digite um número valido!");
			return;
		}
		d = Double.parseDouble(e.getMessage());
		if (d > LimiteAPI.getRunas(p)) {
			p.sendMessage("§cOPS! Seu limite máximo por compra é de " + getFormat(LimiteAPI.getRunas(p)) + ".");
			p.sendMessage("§cDigite uma quantidade novamente ou 'cancelar'.");
			return;
		}
		String pc = spawner.get(nome);
		Double precoun = getSoma(p, getPc(pc));
		Double total = precoun * d;
		total = total * (1 - (0.05 * com.lucas.specterprestigio.api.API.getPrestigio(p)));
		Double runas = com.lucas.specterrunas.api.RunasAPI.getRunas(p);
		if (runas < total) {
			p.sendMessage("§cOPS! Você não possui runas para comprar este item.");
			p.sendMessage("§cDigite uma quantidade novamente ou 'cancelar'.");
			return;
		}
	}

	@EventHandler
	public void maquina(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String nome = p.getName();
		if (!maquina.containsKey(nome))
			return;
		e.setCancelled(true);
		if (e.getMessage().equalsIgnoreCase("cancelar")) {
			p.sendMessage("§cOperação Cancelada!");
			maquina.remove(nome);
			return;
		}
		Double d = 0.0;
		if (!isDouble(e.getMessage())) {
			p.sendMessage("§cPor favor digite um número valido!");
			return;
		}
		d = Double.parseDouble(e.getMessage());
		if (d > LimiteAPI.getRunas(p)) {
			p.sendMessage("§cOPS! Seu limite máximo por compra é de " + getFormat(LimiteAPI.getRunas(p)) + ".");
			p.sendMessage("§cDigite uma quantidade novamente ou 'cancelar'.");
			return;
		}
		Double precoun = getSoma(p, getPc(maquina.get(nome)));
		Double total = precoun * d;
		//total = total * (1 - (0.05 * com.specter.pombaprestigio.api.API.getPrestigio(p)));
		OfflinePlayer player = Bukkit.getOfflinePlayer(p.getUniqueId());
		/*if (!eco.has(player, total)) {
			p.sendMessage("§cOPS! Você não possui dinheiro para comprar este item.");
			p.sendMessage("§cDigite uma quantidade novamente ou 'cancelar'.");
			return;
		}*/
		p.sendMessage("§eVocê adiquiriu " + d + "x " + maquina.get(p.getName()));
		//eco.withdrawPlayer(player, total);
		p.getInventory().addItem(head(maquina.get(nome),
				new String[] { "§aUpgrade: §f0", "§aCombustível: §f0", "§aMáquinas stackadas: §fx" + d }));
		maquina.remove(nome);
	}

	private Double getPc(String m) {
		boolean ola = m.contains("Gerador");
		m = m.substring(13);
		// ola ? spawner : maquina
		if (m.contains("VacaCogumelo")) {
			return (ola ? 1E1 : 1);
		}
		if (m.contains("Vaca")) {
			return (ola ? 1E1 : 7E5);
		}
		if (m.contains("Porco")) {
			return (ola ? 1E1 : 1E10);
		}
		if (m.contains("Ovelha")) {
			return (ola ? 1E1 : 45E9);
		}
		if (m.contains("Zumbi")) {
			return (ola ? 1E1 : 7E10);
		}
		if (m.contains("Esqueleto")) {
			return (ola ? 1E1 : 45E10);
		}
		if (m.contains("Aranha")) {
			return (ola ? 1E1 : 75E10);
		}
		if (m.contains("Golem")) {
			return (ola ? 1E1 : 63E12);
		}
		if (m.contains("Ghast")) {
			return (ola ? 1E1 : 75E15);
		}
		if (m.contains("Enderman")) {
			return (ola ? 1E1 : 475E15);
		}
		if (m.contains("Pigman")) {
			return (ola ? 1E1 : 755E17);
		}
		if (m.contains("Blaze")) {
			return (ola ? 1E1 : 35E20);
		}
		if (m.contains("Galinha")) {
			return (ola ? 1E1 : 1E22);
		}
		if (m.contains("Creeper") || m.contains("SuperCreeper")) {
			return (ola ? 1E1 : 13E22);
		}
		if (m.contains("Bruxa")) {
			return (ola ? 1E1 : 25E22);
		}
		if (m.contains("Guardian")) {
			return (ola ? 1E1 : 425E21);
		}
		if (m.contains("Wither")) {
			return (ola ? 1E1 : 1E1);
		}
		Bukkit.getConsoleSender().sendMessage("Falha ao achar um valor para: " + m);
		return 666.0;
	}

	private boolean isDouble(String m) {
		try {
			if (Double.parseDouble(m) > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if (!(s instanceof Player))
			return false;
		if (!c.getName().equalsIgnoreCase("loja"))
			return false;
		Player p = (Player) s;
		p.openInventory(getLoja(p));
		return false;
	}

	public static ItemStack getLivro(Player p) {
		List<String> lore = new ArrayList<>();
		lore.add("§7");
		if (p.hasPermission("loja.vip") && !p.hasPermission("loja.admin")) {
			lore.add("§aVocê possui §c10% §ade desconto na loja, exclusivo para o grupo §aVIP");
		} else {
			lore.add("§8§mVocê possui §8§m10% §8§mde desconto na loja, exclusivo para o grupo §8§mVIP");
		}
		if (p.hasPermission("loja.mvp") && !p.hasPermission("loja.admin")) {
			lore.add("§aVocê possui §c30% §ade desconto na loja, exclusivo para o grupo §bMVP");
		} else {
			lore.add("§8§mVocê possui §8§m30% §8§mde desconto na loja, exclusivo para o grupo §8§mMVP");
		}
		if (p.hasPermission("loja.mvp+")) {
			lore.add("§aVocê possui §c60% §ade desconto na loja, exclusivo para o grupo §bMVP§6+");
			lore.add("§7");
		} else {
			lore.add("§8§mVocê possui §8§m60% §8§mde desconto na loja, exclusivo para o grupo §8§mMVP+");
			lore.add("§7");
			lore.add("§4Adiquira maiores grupos VIP's para obter mais benefícios!");
		}

		return c(Material.BOOK_AND_QUILL, "§4Vantagens do seu Grupo", lore);
	}

	private Inventory getLoja(Player p) {
		Inventory inv = Bukkit.createInventory(null, 4 * 9, "§8Loja de Itens (Coins)");
		inv.setItem(0, ItensLoja.livros.get(p.getName()));
		inv.setItem(25, c(p, Material.MOB_SPAWNER, "§6Geradores",
				new String[] { "§7Clique para abrir", "§7a categoria 'geradores'" }));
		inv.setItem(23, c(p, Material.ENDER_PORTAL_FRAME, "§6Maquinas",
				new String[] { "§7Clique para abrir", "§7a categoria 'maquinas'" }));
		inv.setItem(10,
				c(p, Material.GRASS, "§6Blocos", new String[] { "§7Clique para abrir", "§7a categoria 'blocos'" }));
		inv.setItem(12, c(p, Material.SAPLING, "§6Decoração",
				new String[] { "§7Clique para abrir", "§7a categoria 'decoração'" }));
		inv.setItem(14, c(p, Material.INK_SACK, 14, "§6Corantes",
				new String[] { "§7Clique para abrir", "§7a categoria 'blocos'" }));
		inv.setItem(16, c(p, Material.TRAPPED_CHEST, "§6Utilitários",
				new String[] { "§7Clique para abrir", "§7a categoria 'utilitários'" }));
		inv.setItem(19, c(p, Material.DIAMOND_PICKAXE, "§6Ferramentas e Armaduras",
				new String[] { "§7Clique para abrir", "§7a categoria 'ferramentas e armaduras'" }));
		inv.setItem(21, c(p, Material.FISHING_ROD, "§6Diversos",
				new String[] { "§7Clique para abrir", "§7a categoria 'diversos'" }));
		return inv;
	}

	public static ItemStack c(Material mt, String nome, List<String> lore) {
		ItemStack item = new ItemStack(mt);
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		return item;
	}

}
