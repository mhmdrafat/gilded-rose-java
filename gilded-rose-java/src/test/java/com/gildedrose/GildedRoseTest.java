package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item[] items;
    private GildedRose gildedRose;
    
    //@Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Test
    public void testNormalItemDecreasesQuality() {
        items = new Item[]{new Item("Normal Item", 10, 20)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(19, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    public void testNormalItemQualityDoesNotGoNegative() {
        items = new Item[]{new Item("Normal Item", 0, 0)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testAgedBrieIncreasesQuality() {
        items = new Item[]{new Item("Aged Brie", 10, 20)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(21, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    public void testAgedBrieQualityDoesNotExceed50() {
        items = new Item[]{new Item("Aged Brie", 10, 50)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testBackstagePassesIncreaseQuality() {
        items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(21, items[0].quality);
        assertEquals(14, items[0].sellIn);
    }

    @Test
    public void testBackstagePassesIncreaseQualityMore() {
        items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 9, 20)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(22, items[0].quality);
        assertEquals(8, items[0].sellIn);

        gildedRose.updateQuality(); // Another day
        assertEquals(24, items[0].quality);
        assertEquals(7, items[0].sellIn);
    }

    @Test
    public void testBackstagePassesQualityDropsToZeroAfterConcert() {
        items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testSulfurasQualityRemainsConstant() {
        items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 80)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(80, items[0].quality);
        assertEquals(10, items[0].sellIn);
    }

    @Test
    public void testQualityDoesNotGoNegative() {
        items = new Item[]{new Item("Normal Item", 0, 0)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testQualityDoesNotExceed50() {
        items = new Item[]{new Item("Aged Brie", 10, 50)};
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(50, items[0].quality);
    }

    // Additional Tests for new features

    @Test
    void testConjuredItemsQualityDegradesCorrectly() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, items[0].quality);
        assertEquals(9, items[0].sellIn);
    }

    @Test
    void testConjuredItemsQualityDoesNotGoBelowZero() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].quality);
    }

    @Test
    void testAgedBrieQualityDoesNotExceedFifty() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality(); // Update once more to exceed 50
        assertEquals(50, items[0].quality);
    }

    @Test
    void testBackstagePassesQualityDoesNotExceedFifty() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, items[0].quality); // Should cap at 50
    }

    @Test
    void testQualityDoesNotExceedFiftyForAnyItem() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 5, 48),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48),
            new Item("Conjured Mana Cake", 5, 48)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, items[0].quality); // Aged Brie
        assertEquals(50, items[1].quality); // Backstage passes
        assertEquals(46, items[2].quality); // Conjured Mana Cake
    }

}
