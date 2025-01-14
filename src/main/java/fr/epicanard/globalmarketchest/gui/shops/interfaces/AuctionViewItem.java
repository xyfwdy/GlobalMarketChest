package fr.epicanard.globalmarketchest.gui.shops.interfaces;

import fr.epicanard.globalmarketchest.GlobalMarketChest;
import fr.epicanard.globalmarketchest.auctions.AuctionLoreConfig;
import fr.epicanard.globalmarketchest.gui.InventoryGUI;
import fr.epicanard.globalmarketchest.gui.TransactionKey;
import fr.epicanard.globalmarketchest.gui.shops.baseinterfaces.AuctionViewBase;
import fr.epicanard.globalmarketchest.shops.ShopInfo;
import fr.epicanard.globalmarketchest.utils.DatabaseUtils;
import fr.epicanard.globalmarketchest.utils.ItemStackUtils;
import fr.epicanard.globalmarketchest.utils.Utils;
import org.bukkit.inventory.ItemStack;

import static fr.epicanard.globalmarketchest.utils.LangUtils.format;

public class AuctionViewItem extends AuctionViewBase {
  public AuctionViewItem(InventoryGUI inv) {
    super(inv);

    this.paginator.setLoadConsumer(pag -> {
      final String search = this.inv.getTransactionValue(TransactionKey.ITEM_SEARCH);
      final ShopInfo shop = this.inv.getTransactionValue(TransactionKey.SHOP_INFO);

      GlobalMarketChest.plugin.auctionManager.getAuctionsByItemName(shop.getGroup(), search, this.paginator.getLimit(),
          auctions -> {
            if (pag.getLimit().getLeft() == 0 || auctions.size() > 0)
              this.auctions = auctions;
            pag.setItemStacks(DatabaseUtils.toItemStacks(auctions, (itemstack, auction) -> {
              ItemStackUtils.addItemStackLore(itemstack, auction.getLore(AuctionLoreConfig.TOSELL));
            }));
          });
    });
  }

  @Override
  public void load() {
    super.load();
    final String search = this.inv.getTransactionValue(TransactionKey.ITEM_SEARCH);

    final ItemStack  icon = Utils.getButton("SearchItemText");
    ItemStackUtils.addItemStackLore(icon, Utils.toList(format("Divers.SearchItemTextIcon", "search", search)));
    this.setIcon(icon);
  }

  @Override
  public void destroy() {
    super.destroy();
    this.inv.getTransaction().remove(TransactionKey.ITEM_SEARCH);
  }
}
