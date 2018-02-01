package fr.goug.BossBar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.goug.BossBar.Main.Langue;
import me.confuser.barapi.BarAPI;

public class CommandBBcheck implements CommandExecutor {

	public Langue langue = Main.getInstance().langue;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if (sender instanceof Player){
			Player p = (Player)sender;
			if (msg.equalsIgnoreCase("bbcheck")&& p.hasPermission("bb.check")){
				if (BarAPI.hasBar(p) == true){
					if (langue.getLangue() == "FR"){
						p.sendMessage("§cVous avez déja une bar !");
					}else if (langue.getLangue() == "EN"){
						p.sendMessage("§cYou already have a bar !");
					}else if (langue.getLangue() == "DE"){
						p.sendMessage("§cSie haben bereits eine Bar !");
					}
				}else {
					p.sendMessage("§4Affichage de la BossBar pendant "+ Main.getInstance().dure_spawn + " secondes !");
					BarAPI.setMessage(p, Main.getInstance().getBar(1), Main.getInstance().dure_spawn);
				}
			}
		}
		return false;
	}

}
