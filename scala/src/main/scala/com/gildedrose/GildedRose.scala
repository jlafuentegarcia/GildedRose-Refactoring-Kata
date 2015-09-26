package com.gildedrose

import com.gildedrose.items.ExtendedItem
import ExtendedItem.Item2ExtendedItem

class GildedRose(val items: Array[Item]) {
  def updateQuality(): Unit = {
    for (i <- 0 until items.length) {
      items(i).process()
    }
  }
}