package fr.goug.BossBar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.goug.BossBar.Main.Langue;
import me.confuser.barapi.BarAPI;

public class CommandBBbroadcast implements CommandExecutor {

	public Langue langue = Main.getInstance().langue;
	
	int db;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player p = (Player)sender;
		if (args.length == 1){
			String bbb_temps = args[0];
			if (sender instanceof Player){
				if (msg.equalsIgnoreCase("bbbroadcast")&& p.hasPermission("bb.broadcast") == true){
					if (Main.getInstance().getConfig().getBoolean("bar_temporaire") == false){
						Integer db = Integer.valueOf(bbb_temps);
						if (db >= 121){
							if (langue.getLangue() == "FR"){
								p.sendMessage("$c[BossBar] Erreur cette valeur "+db+" est trop élevée !");
							}else if (langue.getLangue() == "EN"){
								p.sendMessage("$c[BossBar] This error value "+db+" is too high !");
							}else if (langue.getLangue() == "DE"){
								p.sendMessage("$c[BossBar] Dieser Fehlerwert "+db+" ist zu hoch !");
							}
						}else {
							BarAPI.setMessage(Main.getInstance().getBar(1), db);
							if (langue.getLangue() == "FR"){
								p.sendMessage("[BossBar] La bar a été affichée à tous les joueurs pendant "+db+"secondes");
							}else if (langue.getLangue() == "EN"){
								p.sendMessage("[BossBar] The bar has been displayed to all players during "+db+"seconds");
							}else if (langue.getLangue() == "DE"){
								p.sendMessage("[BossBar] Die Bar wurde für alle Spieler angezeigt für "+db);
							}
						}
					}else if (Main.getInstance().getConfig().getBoolean("bar_temporaire") == false){
						if (langue.getLangue() == "FR"){
							p.sendMessage("§cVous ne pouvez diffuser que temporairement une bar : activez bar_temporaire dans la config pour pouvoir broadcast !");
						}else if (langue.getLangue() == "EN"){
							p.sendMessage("§cYou can broadcast only a temporary bar, so turn at true temporary bar in the config for broadcast !");
						}else if (langue.getLangue() == "DE"){
							p.sendMessage("§cSie können nur eine temporäre Bar-Turn temporäre Bar in der Config-Reihenfolge ausgestrahlt broadcast !");
						}
					}
				}
			}
		}else {
				p.sendMessage("$c[BossBar] Error : /bbbroadcast <during>");
		}
		return false;
	}
}
