# Gilded Rose Inventory System

## Overview

The Gilded Rose Inventory System is a software application designed to manage the quality and sell-by dates of various items in a fictional inn. The system automatically updates the quality and sell-by dates of items each day based on specific rules for different item types.

## Item Types and Their Rules

1. **Normal Items**
   - Quality decreases by 1 each day.
   - Quality decreases by 2 each day after the sell-by date.
   - Quality cannot go below 0.

2. **Aged Brie**
   - Quality increases by 1 each day.
   - Quality increases by 2 each day after the sell-by date.
   - Quality cannot exceed 50.

3. **Backstage Passes**
   - Quality increases by 1 when there are more than 10 days to the concert.
   - Quality increases by 2 when there are 10 days or less to the concert.
   - Quality increases by 3 when there are 5 days or less to the concert.
   - Quality drops to 0 after the concert.

4. **Sulfuras, Hand of Ragnaros**
   - Quality is always 80 and never changes.
   - Sell-by date does not change.

5. **Conjured Items**
   - Quality decreases by 2 each day.
   - Quality decreases by 4 each day after the sell-by date.
   - Quality cannot go below 0.

## Recent Changes

### Added Support for Conjured Items

- Implemented `ConjuredItemUpdater` to handle the specific rules for Conjured items.
- Updated `ItemUpdaterFactory` to include logic for Conjured items.
- Ensured that existing item types (Aged Brie, Backstage passes, Sulfuras) continue to adhere to their specific rules.

### Refactored Code

- **Strategy Pattern**: Applied the Strategy Pattern to encapsulate update logic for different item types. This includes:
  - `ItemUpdater` interface
  - Concrete implementations for each item type (`NormalItemUpdater`, `AgedBrieUpdater`, `BackstagePassUpdater`, `SulfurasUpdater`, `ConjuredItemUpdater`).
- **Factory Pattern**: Used the Factory Pattern to create the appropriate `ItemUpdater` based on the item type.

## Usage

1. **Initialize Items**: Create items with their names, sell-by dates, and quality.
   ```java
   Item[] items = new Item[] {
       new Item("Aged Brie", 10, 20),
       new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
       new Item("Sulfuras, Hand of Ragnaros", 0, 80),
       new Item("Conjured Mana Cake", 3, 6),
       new Item("Normal Item", 5, 10)
   };

2. **Update Quality**: Create an instance of GildedRose with the items and call the 'updateQuality()' method.
   ```java
   GildedRose gildedRose = new GildedRose(items);
   gildedRose.updateQuality();

## Runnig Tests
Tests are located in the src/test/java directory. These tests cover various scenarios to ensure that all item types are updated correctly according to their rules.
