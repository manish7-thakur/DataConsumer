package com.glassbeam.domain

import scala.collection.immutable.Queue

trait BufferStore {
  var dataBuffer = Map("R1" -> Queue(),
    "R2" -> Queue[DataElement](),
    "G1" -> Queue[DataElement](),
    "G2" -> Queue[DataElement](),
    "B1" -> Queue[DataElement](),
    "B2" -> Queue[DataElement]())

  def updateDataStore(key: String, queue: Queue[DataElement]) = {
    dataBuffer = dataBuffer.updated(key, queue)
  }
}
