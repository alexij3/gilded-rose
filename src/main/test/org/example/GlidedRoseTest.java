package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GlidedRoseTest {

    private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";
    private static final String REGULAR_ITEM = "Regular Item";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @Test
    public void testRegularItemQualityDecrease() {
        Item[] items = { new Item(REGULAR_ITEM, 10, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(19, items[0].quality);
    }

    @Test
    public void testQualityNotNegative() {
        Item[] items = { new Item(REGULAR_ITEM, 10, 0) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testAgedBrieQualityIncrease() {
        Item[] items = { new Item(AGED_BRIE, 10, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(11, items[0].quality);
    }

    @Test
    public void testAgedBrieQualityCapAt50() {
        Item[] items = { new Item(AGED_BRIE, 10, 50) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testSulfurasQualityUnchanged() {
        Item[] items = { new Item(SULFURAS, 0, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testSulfurasSellInUnchanged() {
        Item[] items = { new Item(SULFURAS, 5, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(5, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testBackstagePassesQualityIncrease() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 15, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(14, items[0].sellIn);
        assertEquals(21, items[0].quality);
    }

    @Test
    public void testBackstagePassesQualityIncrease10DaysOrLess() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 10, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    public void testBackstagePassesQualityIncrease5DaysOrLess() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 5, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    public void testBackstagePassesQualityDropToZero() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 0, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testQualityDegradesTwiceAsFastAfterSellIn() {
        Item[] items = { new Item(REGULAR_ITEM, 0, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    public void testAgedBrieQualityIncreaseAfterSellIn() {
        Item[] items = { new Item(AGED_BRIE, 0, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    public void testBackstagePassesQualityCappedAt50() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 5, 49) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testNegativeSellInRegularItem() {
        Item[] items = { new Item(REGULAR_ITEM, -1, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    public void testQualityNeverExceeds50AgedBrie() {
        Item[] items = { new Item(AGED_BRIE, 0, 49) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testBackstagePassesQualityDoesNotExceed50() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 3, 49) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(2, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testRegularItemQualityDecreaseToZero() {
        Item[] items = { new Item(REGULAR_ITEM, 1, 1) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testNoChangeForSulfuras() {
        Item[] items = { new Item(SULFURAS, -1, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testAgedBrieIncreaseAfterExpiration() {
        Item[] items = { new Item(AGED_BRIE, -1, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    public void testBackstagePassesZeroQualityAfterConcert() {
        Item[] items = { new Item(BACKSTAGE_ITEM, -1, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testQualityNotNegativeAfterMultipleUpdates() {
        Item[] items = { new Item(REGULAR_ITEM, 5, 1) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 3; i++) {
            app.updateQuality();
        }
        assertEquals(2, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testSulfurasNoChangeAfterMultipleUpdates() {
        Item[] items = { new Item(SULFURAS, 5, 80) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 5; i++) {
            app.updateQuality();
        }
        assertEquals(5, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testAgedBrieMaxQualityAfterMultipleUpdates() {
        Item[] items = { new Item(AGED_BRIE, 2, 45) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 10; i++) {
            app.updateQuality();
        }
        assertEquals(-8, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testBackstagePassesMaxAndDropToZero() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 1, 48) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality(); // Should increase to 50
        app.updateQuality(); // Should drop to 0
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testRegularItemDegradesTwiceAfterSellInMultipleUpdates() {
        Item[] items = { new Item(REGULAR_ITEM, 0, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(6, items[0].quality); // 10 -> 8 -> 6
    }

    @Test
    public void testBackstagePassesIncreaseBelow5Days() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 4, 45) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 2; i++) {
            app.updateQuality();
        }
        assertEquals(2, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testSulfurasMaxQualityNoChange() {
        Item[] items = { new Item(SULFURAS, -1, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testAgedBrieIncreaseNegativeSellIn() {
        Item[] items = { new Item(AGED_BRIE, -2, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-3, items[0].sellIn);
        assertEquals(12, items[0].quality);
    }

    @Test
    public void testBackstagePassesIncreaseAt10Days() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 10, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(9, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    public void testBackstagePassesIncreaseAt5Days() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 5, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].sellIn);
        assertEquals(23, items[0].quality);
    }

    @Test
    public void testNegativeSellInSulfuras() {
        Item[] items = { new Item(SULFURAS, -10, 80) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-10, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testTwiceDegradationAfterSellInExpires() {
        Item[] items = { new Item(REGULAR_ITEM, -1, 10) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(8, items[0].quality);
    }

    @Test
    public void testAgedBrieReachingQualityCap() {
        Item[] items = { new Item(AGED_BRIE, 5, 48) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 3; i++) {
            app.updateQuality();
        }
        assertEquals(2, items[0].sellIn);
        assertEquals(50, items[0].quality);
    }

    @Test
    public void testBackstagePassesDropAfterConcert() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 0, 50) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testSulfurasNoEffectOnMultipleUpdates() {
        Item[] items = { new Item(SULFURAS, 1, 80) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 10; i++) {
            app.updateQuality();
        }
        assertEquals(1, items[0].sellIn);
        assertEquals(80, items[0].quality);
    }

    @Test
    public void testAgedBrieIncreasesWithNegativeSellIn() {
        Item[] items = { new Item(AGED_BRIE, -1, 30) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-2, items[0].sellIn);
        assertEquals(32, items[0].quality);
    }

    @Test
    public void testNoItemQualityNegative() {
        Item[] items = { new Item(REGULAR_ITEM, 0, 1) };
        GlidedRose app = new GlidedRose(items);
        for (int i = 0; i < 5; i++) {
            app.updateQuality();
        }
        assertEquals(-5, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testRegularItemDropToZero() {
        Item[] items = { new Item(REGULAR_ITEM, 1, 2) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }

    @Test
    public void testAgedBrieIncreaseAtSellInZero() {
        Item[] items = { new Item(AGED_BRIE, 0, 20) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(22, items[0].quality);
    }

    @Test
    public void testBackstagePassesCapBeforeZero() {
        Item[] items = { new Item(BACKSTAGE_ITEM, 1, 49) };
        GlidedRose app = new GlidedRose(items);
        app.updateQuality(); // Increases to 50, then drops to 0
        app.updateQuality();
        assertEquals(-1, items[0].sellIn);
        assertEquals(0, items[0].quality);
    }
}
