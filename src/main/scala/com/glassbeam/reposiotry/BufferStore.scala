package com.glassbeam.reposiotry

import com.glassbeam.domain.DataElement

import scala.collection.immutable.Queue

object BufferStore {
  var dataBuffer = Map("R1" -> Queue(),
    "R2" -> Queue[DataElement](),
    "G1" -> Queue[DataElement](),
    "G2" -> Queue[DataElement](),
    "B1" -> Queue[DataElement](),
    "B2" -> Queue[DataElement]())
}
