package com.gildedrose.items

import com.gildedrose.Item

/**
 * Created by jlafuente on 26/09/2015.
 */
class Conjured (item : Item) extends ExtendedItem (item) {
  override def updateQuality() = {
    decreaseQuality()
    decreaseQuality()
  }
}
