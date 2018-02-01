package fr.goug.BossBar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.goug.BossBar.Main.Langue;

public class CommandBBinfo implements CommandExecutor {

	public Langue langue = Main.getInstance().langue;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player){
			Player p = (Player)sender;
			if (msg.equalsIgnoreCase("bbinfo")&& p.hasPermission("bb.info") == true){
				p.sendMessage("§6Plugin by Goug3");
				p.sendMessage("§6Version : §3" +Main.getInstance().getConfig().getString("version"));
				//p.sendMessage(getConfig().getString("version"));
				if (langue.getLangue() == "FR"){
					p.sendMessage("§6Le contenue de votre BB:§3 " + Main.getInstance().getBar(1));
					if (Main.getInstance().getConfig().getBoolean("bar_temporaire") == true){
						p.sendMessage("§6Vous avez activer le mode BarTemporaire !");
					}
				}else if (langue.getLangue() == "EN"){
					p.sendMessage("§6The content of your BB:§3 " + Main.getInstance().getBar(1));
					if (Main.getInstance().getConfig().getBoolean("bar_temporaire") == true){
						p.sendMessage("§6You have enabled BarTemporaire !");
				}
				}else if (langue.getLangue() == "DE"){
					p.sendMessage("§6Der Inhalt Ihrer BB3:§3 " + Main.getInstance().getBar(1));
					if (Main.getInstance().getConfig().getBoolean("bar_temporaire") == true){
						p.sendMessage("§6Sie aktivieren die BarTemporaire !");
					}
				}

				if (langue.getLangue() == "FR"){						
					p.sendMessage("§6Le contenue de votre BB2 : §3 " + Main.getInstance().getBar(2));
					p.sendMessage("§6Est-t-elle active : §3 " + Main.getInstance().getBar(2));
					p.sendMessage("§6Le contenue de votre BB3: §3 " + Main.getInstance().getBar(3));
					p.sendMessage("§6Est-t-elle active : §3 " + Main.getInstance().getBar(3));
					p.sendMessage("§6Le contenue de votre BB4 : §3 " + Main.getInstance().getBar(4));
					p.sendMessage("§6Est-t-elle active : §3 " + Main.getInstance().getBar(4));
				}else if (langue.getLangue() == "EN"){
					p.sendMessage("§6The content of your BB2 : §3 " + Main.getInstance().getBar(2));
					p.sendMessage("§6Is she enable : §3 " + Main.getInstance().getBar(2));
					p.sendMessage("§6The content of your BB3 :§3 " + Main.getInstance().getBar(3));
					p.sendMessage("§6Is she enable : §3 " + Main.getInstance().getBar(3));
					p.sendMessage("§6The content of your BB4 : §3 " + Main.getInstance().getBar(4));
					p.sendMessage("§6Is she enable : §3 " + Main.getInstance().getBar(4));
					}else if (langue.getLangue() == "DE"){
					p.sendMessage("§6Der Inhalt Ihrer BB2 : §3 " + Main.getInstance().getBar(2));
					p.sendMessage("§6Sie ist aktiv: §3 " + Main.getInstance().getBar(2));
					p.sendMessage("§6Der Inhalt Ihrer BB3: §3 " + Main.getInstance().getBar(3));
					p.sendMessage("§6Sie ist aktiv: §3 " + Main.getInstance().getBar(3));
					p.sendMessage("§6Der Inhalt Ihrer BB4: §3 " + Main.getInstance().getBar(4));
					p.sendMessage("§6Sie ist aktiv: §3 " + Main.getInstance().getBar(4));
					}

				if (langue.getLangue() == "FR"){
					p.sendMessage("§6Durée d'affichage de la bar : "+ Main.getInstance().dure_spawn + " secondes");
					p.sendMessage("§6Langue choisie : §3FRANCAIS");
				}else if (langue.getLangue() == "EN"){
					p.sendMessage("§6The bar display timer : §3"+ Main.getInstance().dure_spawn + " seconds");
					p.sendMessage("§6Language choose : §3ENGLISH");
				}else if (langue.getLangue() == "DE"){
					p.sendMessage("§6Die Balkenanzeige Zeit : §3"+ Main.getInstance().dure_spawn + " zweite");
					p.sendMessage("§6Langue choisie : §3DEUTSCH");
				}else {
					p.sendMessage("§6The bar display time : §3"+ Main.getInstance().dure_spawn + " seconds");
					p.sendMessage("§l§cLanguage error !");				
				}
				//p.sendMessage(getConfig().getString("contenu_bar"));
			}
		}
		return false;
	}

}
