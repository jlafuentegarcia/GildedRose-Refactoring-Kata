package com.gildedrose

import org.scalatest._

class GildedRoseTest  extends FlatSpec with Matchers {
  it should "foo" in {
    val items = Array[Item](new Item("foo", 0, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).name) should equal("foo")
  }

  "Sulfuras" should "never change quality" in {
    val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 0, 80))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(80)
  }

  "Normal Items" should "always decrease quality" in {
    val items = Array[Item](new Item("Normal Item", 10, 30))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(29)
  }

  "Normal Items" should "always decrease quality by two when sellin Passed" in {
    val items = Array[Item](new Item("Normal Item", 0, 30), new Item("Normal Item", -3, 30))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(28)
    (app.items(1).quality) should equal(28)
  }

  "Quality" should "never be negative" in {
    val items = Array[Item](new Item("Normal Item", 0, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(0)
  }

  "SellIn" should "always decrease" in {
    val items = Array[Item](new Item("Normal Item", 1, 1),
                            new Item("Sulfuras, Hand of Ragnaros", 1, 80),
                            new Item("Aged Brie", 1, 10),
                            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 15 ),
                            new Item("Backstage passes to a TAFKAL80ETC concert", -3, 15 ))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).sellIn) should equal(0)
    (app.items(1).sellIn) should equal(0)
    (app.items(2).sellIn) should equal(0)
    (app.items(3).sellIn) should equal(0)
    (app.items(4).sellIn) should equal(-4)
  }

  "Aged Brie" should "increase quality" in {
    val items = Array[Item](new Item("Aged Brie", 10, 1))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(2)
  }

  "Quality" should "never increase over 50" in {
    val items = Array[Item](new Item("Aged Brie", 10, 50),
                            new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50 )
    )
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(50)
    (app.items(1).quality) should equal(50)
  }

  "Backstage Passes" should "increase quality by one when sellin > 10" in {
    for ( si <- 11 until 15) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(11)
    }
  }

  "Backstage Passes" should "increase quality by 2 when sellin > 5 && <= 10" in {
    for ( si <- 6 until 10) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(12)
    }
  }

  "Backstage Passes" should "increase quality by 3 when sellin <= 5 && > 0" in {
    for ( si <- 1 until 5) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(13)
    }
  }

  "Backstage Passes" should "set quality to 0 when sellIn <= 0" in {
    for ( si <- -10 until 0) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(0)
    }
  }

  "Conjured Items" should "decrease quality by twice the normal ones" in {
    val items = Array[Item](new Item("Conjured", 7, 20), new Item("Conjured", 0, 20))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(18)
    (app.items(1).quality) should equal(16)
  }
}