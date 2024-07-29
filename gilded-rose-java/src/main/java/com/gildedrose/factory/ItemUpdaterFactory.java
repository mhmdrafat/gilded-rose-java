package com.gildedrose.factory;

import com.gildedrose.Item;
import com.gildedrose.strategy.*;

public class ItemUpdaterFactory {
    public static ItemUpdater getUpdater(Item item) {
        switch (item.name) {
            case "Aged Brie":
                return new AgedBrieUpdater();
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackstagePassUpdater();
            case "Sulfuras, Hand of Ragnaros":
                return new SulfurasUpdater();
            case "Conjured":
                return new ConjuredItemUpdater();
            default:
                return new NormalItemUpdater();
        }
    }
}
