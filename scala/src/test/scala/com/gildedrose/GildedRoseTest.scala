package com.gildedrose

import org.scalatest._

class GildedRoseTest  extends FlatSpec with Matchers {
  it should "foo" in {
    val items = Array[Item](new Item("foo", 0, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).name) should equal("foo")
  }

  it should "Sulfuras never change quality" in {
    val items = Array[Item](new Item("Sulfuras, Hand of Ragnaros", 0, 80))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(80)
  }

  it should "Normal items always decrease quality" in {
    val items = Array[Item](new Item("Normal Item", 10, 30))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(29)
  }

  it should "Normal items always decrease quality by two when sellin Passed" in {
    val items = Array[Item](new Item("Normal Item", 0, 30), new Item("Normal Item", -3, 30))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(28)
    (app.items(1).quality) should equal(28)
  }

  it should "Quality is never negative" in {
    val items = Array[Item](new Item("Normal Item", 0, 0))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(0)
  }

  it should "decrease sellIn" in {
    val items = Array[Item](new Item("Normal Item", 1, 1))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).sellIn) should equal(0)
  }

  it should "decrease sellIn under 0" in {
    val items = Array[Item](new Item("Normal Item", 0, 1))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).sellIn) should equal(-1)
  }

  it should "increase Aged Brie quality" in {
    val items = Array[Item](new Item("Aged Brie", 10, 1))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(2)
  }

  it should "never increase Aged Brie quality over 50" in {
    val items = Array[Item](new Item("Aged Brie", 10, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(50)
  }

  it should "keep backstage passes quality when sellin > 10" in {
    for ( si <- 11 until 15) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(11)
    }
  }

  it should "add 2 to backstage passes quality when sellin > 5 && <= 10" in {
    for ( si <- 6 until 10) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(12)
    }
  }

  it should "add 3 to backstage passes quality when sellin <= 5 && > 0" in {
    for ( si <- 1 until 5) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(13)
    }
  }

  it should "set backstage passes quality to 0 when sellIn <= 0" in {
    for ( si <- -10 until 0) {
      val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", si, 10))
      val app = new GildedRose(items)
      app.updateQuality()
      (app.items(0).quality) should equal(0)
    }
  }

  it should "never increase backstage passes quality over 50" in {
    val items = Array[Item](new Item("Backstage passes to a TAFKAL80ETC concert", 7, 50))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(50)
  }

  it should "decrease conjured items by two" in {
    val items = Array[Item](new Item("Conjured", 7, 20))
    val app = new GildedRose(items)
    app.updateQuality()
    (app.items(0).quality) should equal(18)
  }
}