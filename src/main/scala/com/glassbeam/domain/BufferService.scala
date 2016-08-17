package com.glassbeam.domain

import scala.collection.immutable.Queue

trait BufferService extends BufferStore {

  def getG2Queue: Queue[DataElement]

  def getG1Queue: Queue[DataElement]

  def getR1Queue: Queue[DataElement]

  def getR2Queue: Queue[DataElement]

  def getB1Queue: Queue[DataElement]

  def getB2Queue: Queue[DataElement]

  def getPair(element: DataElement, inQueue: Queue[DataElement], outQueue: Queue[DataElement]) = outQueue.dequeueOption.getOrElse((element, inQueue enqueue element))

  def getPairFromG1Queue(element: DataElement) = {
    val outQueue = getG1Queue
    val inQueue = getG2Queue
    outQueue.dequeueOption.fold {
      updateDataStore("G2", inQueue enqueue element)
      (element, inQueue enqueue element)
    } { case (e, q) =>
      updateDataStore("G1", q)
      (e, q)
    }
  }

  def getPairFromG2Queue(element: DataElement) = {
    val outQueue = getG2Queue
    val inQueue = getG1Queue
    outQueue.dequeueOption.fold {
      updateDataStore("G1", inQueue enqueue element)
      (element, inQueue enqueue element)
    } { case (e, q) =>
      updateDataStore("G2", q)
      (e, q)
    }
  }

  def getPairFromB2Queue(element: DataElement) = {
    getPair(element, getB1Queue, getB2Queue)
  }

  def getPairFromB1Queue(element: DataElement) = {
    getPair(element, getB2Queue, getB1Queue)
  }

  def getPairFromR2Queue(element: DataElement) = {
    getPair(element, getR1Queue, getR2Queue)
  }

  def getPairFromR1Queue(element: DataElement) = {
    getPair(element, getR2Queue, getR1Queue)
  }

  def getMatchingPair(element: DataElement) = {
    element.channelNumber match {
      case "R1" => getPairFromR2Queue(element)
      case "R2" => getPairFromR1Queue(element)
      case "G1" => getPairFromG2Queue(element)
      case "G2" => getPairFromG1Queue(element)
      case "B1" => getPairFromB2Queue(element)
      case "B2" => getPairFromB1Queue(element)
    }
  }
}
