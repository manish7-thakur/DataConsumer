package com.glassbeam.reposiotry

import com.glassbeam.domain.DataElement

import scala.collection.immutable.Queue

class InMemoryBufferService(var dataBuffer: Map[String, Queue[DataElement]]) extends BufferService {
  override def getG2Queue: Queue[DataElement] = dataBuffer.getOrElse("G2", Queue())

  override def getG1Queue: Queue[DataElement] = dataBuffer.getOrElse("G1", Queue())

  override def getR1Queue: Queue[DataElement] = dataBuffer.getOrElse("R1", Queue())

  override def getR2Queue: Queue[DataElement] = dataBuffer.getOrElse("R2", Queue())

  override def getB1Queue: Queue[DataElement] = dataBuffer.getOrElse("B1", Queue())

  override def getB2Queue: Queue[DataElement] = dataBuffer.getOrElse("B2", Queue())

  override def updateDataStore(key: String, queue: Queue[DataElement]) = {
    dataBuffer = dataBuffer.updated(key, queue)
  }
}
