package fr.epicanard.globalmarketchest.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import fr.epicanard.globalmarketchest.GlobalMarketChest;
import fr.epicanard.globalmarketchest.gui.InventoryGUI;
import fr.epicanard.globalmarketchest.gui.TransactionKey;
import fr.epicanard.globalmarketchest.shops.ShopInfo;
import fr.epicanard.globalmarketchest.shops.ShopType;
import fr.epicanard.globalmarketchest.utils.ShopUtils;

/**
 * Listener for creation process
 */
public class ShopCreationListener implements Listener {

  @EventHandler
  public void onChangeSign(SignChangeEvent event) {
    final Player player = event.getPlayer();
    }

    if (event.getLine(0).equals(ShopType.GLOBALSHOP.getFirstLineToCreate()))
      this.openCreationShopInterface(player, event);
  }

  /**
   * Open shop interface to create it
   *
   * @param player Player that create the shop
   */
  private void openCreationShopInterface(Player player, SignChangeEvent event) {
    event.setLine(0, ShopType.GLOBALSHOP.getErrorDisplayName());
    ShopInfo shop = new ShopInfo(-1, player.getUniqueId().toString(), ShopType.GLOBALSHOP.setOn(0), event.getBlock().getLocation(), null, ShopUtils.generateName());
    InventoryGUI inv = new InventoryGUI(player);

    inv.getTransaction().put(TransactionKey.SHOPINFO, shop);
    inv.getTransaction().put(TransactionKey.SIGNLOCATION, event.getBlock().getLocation());
    GlobalMarketChest.plugin.inventories.addInventory(player.getUniqueId(), inv);
    inv.open();
    inv.loadInterface("ShopCreationSelectType");
  }
}
