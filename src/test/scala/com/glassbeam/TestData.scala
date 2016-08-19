package com.glassbeam

import com.glassbeam.domain.DataElement
import scala.collection.immutable.Queue

trait TestData {

  val getR1Queue = Queue(DataElement("2", "R1"))
  val getR2Queue = Queue()
  val getG1Queue = Queue(DataElement("3", "G1"))
  val getG2Queue = Queue(DataElement("4", "G2"), DataElement("5", "G2"))
  val getB1Queue = Queue(DataElement("1", "B1"))
  val getB2Queue = Queue(DataElement("3", "B2"))

}
