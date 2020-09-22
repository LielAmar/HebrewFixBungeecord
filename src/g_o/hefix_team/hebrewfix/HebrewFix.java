package g_o.hefix_team.hebrewfix;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class HebrewFix extends Plugin {

	@Override
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, new EventManager());
		
		if (!getDataFolder().exists()) getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");
   
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
            	Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Couldn't copy HebrewFix's config. Private messages through proxy won't be fixed!");
            }
        }
		
		Configuration config;
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
			PrivateMessageCommands.setTemplates(config.getStringList("private-messages-commands"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't load HebrewFix's config. Private messages through proxy won't be fixed!");
		}
	}
}
