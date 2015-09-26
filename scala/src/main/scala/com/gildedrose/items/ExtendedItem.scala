package com.gildedrose.items

import com.gildedrose.Item

/**
 * Created by jlafuente on 26/09/2015.
 */

object ExtendedItem {
  implicit def Item2ExtendedItem(item : Item) : ExtendedItem = item.name match {
    case "Aged Brie" => new AgedBrie(item)
    case "Sulfuras, Hand of Ragnaros" => new Sulfuras(item)
    case "Backstage passes to a TAFKAL80ETC concert" => new BackstagePasses(item)
    case "Conjured" => new Conjured(item)
    case _ => new ExtendedItem(item)
  }
}

class ExtendedItem (private var item : Item) {
  protected def increaseQuality() = {
    if ( item.quality < 50 )
      item.quality = item.quality + 1
  }

  protected def decreaseQuality() = {
    if ( item.quality > 0 )
      item.quality = item.quality - 1
  }

  private def decreaseSellIn() = {
    item.sellIn = item.sellIn - 1
  }

  def process() : Unit = {
    decreaseSellIn()
    updateQuality()
  }

  protected def updateQuality() = {
    decreaseQuality()
    if ( item.sellIn < 0 )
      decreaseQuality()
  }
}
