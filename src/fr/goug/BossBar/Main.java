package fr.goug.BossBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.confuser.barapi.BarAPI;

public class Main extends JavaPlugin implements Listener{
	
	public static Main instance;
	
	public static Main getInstance(){
		return instance;
	}
	
	public class Langue{
		private int langue = getConfig().getInt("langue_plugin");
		private String langue_return;
		
		public String getLangue(){
			if (langue == 1){
				langue_return = "EN";
			}else if (langue == 2){
				langue_return = "FR";
			}else if (langue == 3){
				langue_return = "DE";
			}else {
				langue_return = "EN";
			}
			return langue_return;
		}
		public void setLangue(int new_langue){
			if (new_langue == 1 || new_langue == 2 || new_langue == 3){
				langue = new_langue;
			}
		}
		
		public String getMsg(String class_name, String msg_name){
			return config.getString(class_name + "." + msg_name);
		}
	}
	
	Langue langue = new Langue();
	private File fichier;
	private FileConfiguration config;
	
	public void onEnable(){
		instance = this;
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		}catch (IOException e){
			e.printStackTrace();
			getLogger().severe("Error Metrics !");
		}
		saveDefaultConfig();
		if (getConfig().getBoolean("activer_plugin") == true){
			if (getConfig().getInt("dure_spawn") >= 180){
				if (langue.getLangue() == "FR"){
					getLogger().severe("Error : dure_spawn est trop élevé !");
					Bukkit.getPluginManager().disablePlugin(this);
				}else if (langue.getLangue() == "EN"){
					getLogger().severe("Error: dure_spawn is too high !");
					Bukkit.getPluginManager().disablePlugin(this);
				}else if (langue.getLangue() == "DE"){
					getLogger().severe("Fehler: dure_spawn ist zu hoch !");
					Bukkit.getPluginManager().disablePlugin(this);
				}
			}
			PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(this, this);
			//System.out.println("[BossBar] Plugin activer");
			getCommand("bbinfo").setExecutor(new CommandBBinfo());
			getCommand("bbbroadcast").setExecutor(new CommandBBbroadcast());
			/*getCommand("bbset").setExecutor(new CommandBBset());*/
			getCommand("bbcheck").setExecutor(new CommandBBcheck());
			System.out.println("[BossBar] Version BukkitOnly");
			if (langue.getLangue() == "FR"){
				getLogger().info("Version Francaise activee !");
			}else if (langue.getLangue() == "EN"){
				getLogger().info("English version enabled !");
			}else if (langue.getLangue() == "DE"){
				getLogger().info("Deutsch Version aktiviert !");
			}else {
				getLogger().severe("Error : invalid language !");
				getLogger().severe("Erreur : language invalide !");
				getLogger().severe("Fehler: ungültiger Sprache!");
				getLogger().severe("Plugin disable by the config !");
				getLogger().severe("Plugin desactivé par la config !");
				getLogger().severe("Plugin von der Config deaktiviert!");
				Bukkit.getPluginManager().disablePlugin(this);
			}
			
			if (getConfig().getBoolean("bar_temporaire") == false && getConfig().getBoolean("activer_bar_2") == false  && getConfig().getBoolean("activer_bar_3") == false  && getConfig().getBoolean("activer_bar_4") == false){
				if (langue.getLangue() == "FR"){
					getLogger().severe("Vous devez activer au moins une option !");
					getLogger().severe("Plugin desactivé par la config !");
					Bukkit.getPluginManager().disablePlugin(this);
				}else if (langue.getLangue() == "EN"){
					getLogger().severe("You must enable an option !");
					getLogger().severe("Plugin disable by the config !");
					Bukkit.getPluginManager().disablePlugin(this);
				}else if (langue.getLangue() == "DE"){
					getLogger().severe("Sie müssen eine Option aktivieren!");
					getLogger().severe("Plugin von der Config deaktiviert!");
					Bukkit.getPluginManager().disablePlugin(this);
				}else {
					getLogger().severe("Error : invalid language !");
					getLogger().severe("Erreur : language invalide !");
					getLogger().severe("Fehler: ungültiger Sprache!");
					getLogger().severe("Plugin disable by the config !");
					getLogger().severe("Plugin desactivé par la config !");
					getLogger().severe("Plugin von der Config deaktiviert!");
					Bukkit.getPluginManager().disablePlugin(this);
				}
			}
			
			if (!getDataFolder().exists()){
				getDataFolder().mkdir();
			}
			
			if (langue.getLangue() == "EN"){
				this.fichier = new File(getDataFolder() + File.separator + "english.yml");
			}else if (langue.getLangue() == "FR"){
				this.fichier = new File(getDataFolder() + File.separator + "french.yml");
			}else if (langue.getLangue() == "DE"){
				this.fichier = new File(getDataFolder() + File.separator + "deutsch.yml");
			}
			
			if (!fichier.exists()){
				try {
					fichier.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			this.config = YamlConfiguration.loadConfiguration(fichier);
			
			if (langue.getLangue() == "EN"){
				config.createSection("bbcheck.allready_bar");
				config.set("bbcheck.allready_bar", "You already have a bar !");
				config.createSection("bbbroadcast.err_duree_bar_command");
				config.set("bbbroadcast.err_duree_bar_command", "$c[BossBar] Error, this value {duree_bar_command} is too high !");
				config.createSection("bbbroadcast.display_bar");
				config.set("bbbroadcast.display_bar", "[BossBar] The bar has been displayed to all players during {duree_bar_command} seconds");
				config.createSection("bbbroadcast.err_bar_temp_false");
				config.set("bbbroadcast.err_bar_temp_false", "§cYou can broadcast only a temporary bar, so turn at true temporary bar in the config for broadcast !");
				config.createSection("bbbroadcast.err_syntaxe");
				config.set("bbbroadcast.err_syntaxe", "$c[BossBar] Error : /bbbroadcast <during>");
			}else if (langue.getLangue() == "FR"){
			}else if (langue.getLangue() == "DE"){
			}
			
			
			try {
				config.save(fichier);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if (getConfig().getBoolean("activer_plugin") == false){
			if (langue.getLangue() == "EN"){
				getLogger().severe("disabled : config is on false.");
				Bukkit.getPluginManager().disablePlugin(this);
			}else if (langue.getLangue() == "FR"){
				getLogger().severe("desactivé : config est sur false.");
				Bukkit.getPluginManager().disablePlugin(this);
			}else if (langue.getLangue() == "DE"){
				getLogger().severe("Behinderte: config ist falsch.");
				Bukkit.getPluginManager().disablePlugin(this);
			}
		}
	}  
		
	public String getMax(){
		 int max = Bukkit.getServer().getMaxPlayers();
		 String maxp = Integer.toString(max);
		 return maxp;
	}
	
	public FileConfiguration getCustomConfig(){
		return config;
	}
		
	int playercount = 0;
	int playermax;
	int while_infini = 1;
	public int dure_spawn = getConfig().getInt("dure_spawn");
	Boolean randomiser;
	  
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
	   Player p = (Player)sender;
	   if ((sender instanceof Player)) {
	     if ((label.equalsIgnoreCase("bbreload")) && (p.hasPermission("bb.reload"))){
	      if (langue.getLangue() == "FR"){
	         p.sendMessage("§1BossBar a bien été  reload");
	         reloadConfig();
	         Bukkit.getPluginManager().disablePlugin(this);
	         Bukkit.getPluginManager().enablePlugin(this);
	       }
	       else if (langue.getLangue() == "EN"){
	         p.sendMessage("§1BossBar as been reload");
	         reloadConfig();
	         Bukkit.getPluginManager().disablePlugin(this);
	         Bukkit.getPluginManager().enablePlugin(this);
	       }else if (langue.getLangue() == "DE"){
	         p.sendMessage("§1BossBar wie bereits reload");
	         reloadConfig();
	         Bukkit.getPluginManager().disablePlugin(this);
	         Bukkit.getPluginManager().enablePlugin(this);
	       }
	     }
	    }
	    return false;
	  }
	  
	private Boolean avcontenu_bar_2 = Boolean.valueOf(getConfig().getBoolean("activer_bar_2"));
	private Boolean avcontenu_bar_3 = Boolean.valueOf(getConfig().getBoolean("activer_bar_3"));
	private Boolean avcontenu_bar_4 = Boolean.valueOf(getConfig().getBoolean("activer_bar_4"));
	private String contenu_bar = getConfig().getString("contenu_bar").replace('§', '&').replace("%PLAYERCOUNT%", String.valueOf(this.playercount).replace("%MAXPLAYERS%", String.valueOf(this.playermax)));
	private String vcontenu_bar_2 = getConfig().getString("contenu_bar_2").replace('§', '&').replace("%PLAYERCOUNT%", String.valueOf(this.playercount).replace("%MAXPLAYERS%", String.valueOf(this.playermax)));
	private String vcontenu_bar_3 = getConfig().getString("contenu_bar_3").replace('§', '&').replace("%PLAYERCOUNT%", String.valueOf(this.playercount).replace("%MAXPLAYERS%", String.valueOf(this.playermax)));
	private String vcontenu_bar_4 = getConfig().getString("contenu_bar_4").replace('§', '&').replace("%PLAYERCOUNT%", String.valueOf(this.playercount).replace("%MAXPLAYERS%", String.valueOf(this.playermax)));
	
	public String getBar(int b){
		if (b == 1){
			return contenu_bar; 
		}else if (b == 2){
			return vcontenu_bar_2;
		}else if (b == 3){
			return vcontenu_bar_3;
		}else if (b == 4){
			return vcontenu_bar_4;
		}
		return "hello";
	}
		
	public void setBar(int b, String nexBar){
		if (b == 1){
			this.contenu_bar = nexBar;
		}else if (b == 2){
			this. vcontenu_bar_2 = nexBar;
		}else if (b == 3){
			this.vcontenu_bar_3 = nexBar;
		}else if (b == 4){
			this.vcontenu_bar_4 = nexBar;
		}
	}
		
	
	  @EventHandler
	  public void onJoin(PlayerJoinEvent e){
	    Player p = e.getPlayer();
	    if (BarAPI.hasBar(p)) {
	      BarAPI.removeBar(p);
	    }
	    if (!getConfig().getBoolean("bar_temporaire")) {
	      showBarChanging(p);
	    } else {
	      BarAPI.setMessage(p, this.contenu_bar, this.dure_spawn);
	    }
	  }
	
	public void showBarChanging(final Player p){
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
            public void run(){
            	List<String> list = new ArrayList<>();
            	if (avcontenu_bar_2 == true){
            		list.add(vcontenu_bar_2);
            	}
            	if (avcontenu_bar_3 == true){
            		list.add(vcontenu_bar_3);
            	}
            	if (avcontenu_bar_4 == true){
            		list.add(vcontenu_bar_4);
            	}	

            	if (randomiser == true){
            		Random random = new Random();
            		String message = (String) list.get(random.nextInt(list.size()));
            		BarAPI.setMessage(p, message);
            	}else {
            		int i = 1;
            		String message = (String) list.get(i);
            		BarAPI.setMessage(p, message);
            		i++;
            }}		
		}, 0, dure_spawn * 20);
	}
}