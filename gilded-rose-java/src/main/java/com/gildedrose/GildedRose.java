package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                updateSellIn(item);
                updateItemQuality(item);
                if (item.sellIn < 0) handleExpiredItem(item);
            }
        }
    }

    private void updateSellIn(Item item) {
        item.sellIn--;
    }

    private void updateItemQuality(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            increaseQuality(item);
            if (item.sellIn < 10) increaseQuality(item);
            if (item.sellIn < 5) increaseQuality(item);
        } else {
            decreaseQuality(item);
        }
    }

    private void handleExpiredItem(Item item) {
        if (item.name.equals("Aged Brie")) {
            increaseQuality(item);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            decreaseQuality(item);
        }
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) item.quality++;
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) item.quality--;
    }
}
