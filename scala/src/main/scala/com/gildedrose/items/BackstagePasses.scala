package com.gildedrose.items

import com.gildedrose.Item

/**
 * Created by jlafuente on 26/09/2015.
 */
class BackstagePasses (item: Item) extends ExtendedItem(item) {
  override def updateQuality(): Unit ={
    if ( item.sellIn >= 0 ) {
      increaseQuality()

      // Increase <= 10
      if (item.sellIn < 10) {
        increaseQuality()
      }
      if (item.sellIn < 5) {
        increaseQuality()
      }
    } else {
      item.quality = 0
    }
  }
}
