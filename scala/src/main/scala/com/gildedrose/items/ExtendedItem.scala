package com.gildedrose.items

import com.gildedrose.Item

/**
 * Created by jlafuente on 26/09/2015.
 */

object ExtendedItem {
  implicit def Item2ExtendedItem(item : Item) : ExtendedItem = {
    if ( item.name.equals("Aged Brie")) {
      new AgedBrie(item)
    } else if ( item.name.equals("Sulfuras, Hand of Ragnaros") ) {
      new Sulfuras(item)
    } else if ( item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
      new BackstagePasses(item)
    } else if ( item.name.equals("Conjured")) {
      new Conjured(item)
    } else {
      new ExtendedItem(item)
    }
  }
}

class ExtendedItem (private var item : Item) {
  def increaseQuality() = {
    if ( item.quality < 50 )
      item.quality = item.quality + 1
  }

  def decreaseQuality() = {
    if ( item.quality > 0 )
      item.quality = item.quality - 1
  }

  def decreaseSellIn() = {
    item.sellIn = item.sellIn - 1
  }

  def process() : Unit = {
    decreaseSellIn()
    updateQuality()
  }

  def updateQuality() = {
    decreaseQuality()
    if ( item.sellIn < 0 )
      decreaseQuality()
  }
}
