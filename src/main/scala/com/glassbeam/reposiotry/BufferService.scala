package com.glassbeam.reposiotry


import com.glassbeam.domain.DataElement

import scala.collection.immutable.Queue

trait BufferService {

  def getG2Queue: Queue[DataElement]

  def getG1Queue: Queue[DataElement]

  def getR1Queue: Queue[DataElement]

  def getR2Queue: Queue[DataElement]

  def getB1Queue: Queue[DataElement]

  def getB2Queue: Queue[DataElement]

  def updateDataStore(key: String, elements: Queue[DataElement])

  private def getPairFromG1Queue(element: DataElement) = {
    val outQueue = getG1Queue
    val inQueue = getG2Queue
    outQueue.dequeueOption.fold {
      updateDataStore("G2", inQueue enqueue element)
      element
    } { case (e, q) =>
      updateDataStore("G1", q)
      e
    }
  }

  private def getPairFromB1Queue(element: DataElement) = {
    val outQueue = getB1Queue
    val inQueue = getB2Queue
    outQueue.dequeueOption.fold {
      updateDataStore("B2", inQueue enqueue element)
      element
    } { case (e, q) =>
      updateDataStore("B1", q)
      e
    }
  }

  private def getPairFromR1Queue(element: DataElement) = {
    val outQueue = getR1Queue
    val inQueue = getR2Queue
    outQueue.dequeueOption.fold {
      updateDataStore("R2", inQueue enqueue element)
      element
    } { case (e, q) =>
      updateDataStore("R1", q)
      e
    }
  }

  private def getPairFromG2Queue(element: DataElement) = {
    val outQueue = getG2Queue
    val inQueue = getG1Queue
    outQueue.dequeueOption.fold {
      updateDataStore("G1", inQueue enqueue element)
      element
    } { case (e, q) =>
      updateDataStore("G2", q)
      e
    }
  }

  private def getPairFromB2Queue(element: DataElement) = {
    val outQueue = getB2Queue
    val inQueue = getB1Queue
    outQueue.dequeueOption.fold {
      updateDataStore("B1", inQueue enqueue element)
      element
    } { case (e, q) =>
      updateDataStore("B2", q)
      e
    }
  }


  private def getPairFromR2Queue(element: DataElement) = {
    val outQueue = getR2Queue
    val inQueue = getR1Queue
    outQueue.dequeueOption.fold {
      updateDataStore("R1", inQueue enqueue element)
      element
    } { case (e, q) =>
      updateDataStore("R2", q)
      e
    }
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
