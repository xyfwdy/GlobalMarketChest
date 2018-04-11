package fr.epicanard.globalmarketchest.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.epicanard.globalmarketchest.GlobalMarketChest;

public class ConfigLoader {

  private YamlConfiguration config;
  private YamlConfiguration languages;
  private YamlConfiguration categories;
  private YamlConfiguration worldGroups;

  public ConfigLoader() {
    this.config = null;
    this.languages = null;
    this.categories =  null;
    this.worldGroups = null;
  }

  public YamlConfiguration getConfig() {
    return this.config;
  }

  public YamlConfiguration getLanguages() {
    return this.languages;
  }

  public YamlConfiguration getCategories() {
    return this.categories;
  }

  public YamlConfiguration getWorldGroups() {
    return this.worldGroups;
  }
  
  private YamlConfiguration loadOneFile(String fileName) {
    if (!fileName.substring(fileName.length() - 4).equals(".yml"))
      fileName += ".yml";

    File confFile = new File(GlobalMarketChest.plugin.getDataFolder(), fileName);

    if (!confFile.exists()) {
      confFile.getParentFile().mkdirs();
      GlobalMarketChest.plugin.saveResource(fileName, false);
    }

    YamlConfiguration conf = new YamlConfiguration();
    try {
      conf.load(confFile);
      return conf;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    }
    return null;
  }

  public YamlConfiguration loadResource(String filename) {
  	try {
      InputStream res = GlobalMarketChest.plugin.getResource(filename);
      return YamlConfiguration.loadConfiguration(new InputStreamReader(res));
  	} catch (IllegalArgumentException e) {
  		return null;
		}
  }
  
  public void loadFiles() {
    this.config = this.loadOneFile("config.yml");
    this.categories = this.loadOneFile("categories.yml");
    this.worldGroups = this.loadOneFile("worldGroups.yml");
    if (this.config != null) {
      String tmp = this.config.getString("General.Lang");
      if (tmp == null)
        tmp = "lang-en_EN.yml";
      this.languages = this.loadOneFile(tmp);
    }
  }
}
