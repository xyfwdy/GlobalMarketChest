package fr.epicanard.globalmarketchest.GUI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoriesHandler {
  private Map<UUID, InventoryGUI> inventories;
  
  public InventoriesHandler() {
    this.inventories = new HashMap<UUID, InventoryGUI>();
  }
  
  public void addInventory(UUID playerID, InventoryGUI inv) {
    this.inventories.put(playerID, inv);
  }
  
  public void removeInventory(UUID playerID) {
    this.inventories.remove(playerID);
  }
  
  public Boolean hasInventory(UUID playerID) {
    return this.inventories.containsKey(playerID);
  }
  
  public InventoryGUI getInventory(UUID playerID) {
    return this.inventories.get(playerID);
  }
}
