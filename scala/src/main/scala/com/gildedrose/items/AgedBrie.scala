package com.gildedrose.items

import com.gildedrose.Item

/**
 * Created by jlafuente on 26/09/2015.
 */
class AgedBrie(kk : Item) extends ExtendedItem(kk) {
  protected override def updateQuality(): Unit = {
    increaseQuality()
  }
}
