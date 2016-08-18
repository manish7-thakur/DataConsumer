package com.glassbeam

import com.glassbeam.domain.{BufferService, DataElement}
import org.specs2.matcher.Scope
import org.specs2.mutable.Specification

import scala.collection.immutable.Queue


class BufferSpecs extends Specification with BufferService with TestData {
  sequential
  trait BufferScope extends Scope {
    dataBuffer = Map("R1" -> Queue(),
      "R2" -> Queue[DataElement](),
      "G1" -> Queue[DataElement](),
      "G2" -> Queue[DataElement](),
      "B1" -> Queue[DataElement](),
      "B2" -> Queue[DataElement]())
  }
  "Buffer" should {
    "be updated for G1" in new BufferScope {
      getMatchingPair(DataElement("1", "G2"))
      dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for G2" in  new BufferScope{
      getMatchingPair(DataElement("6", "G1"))
      dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(DataElement("5", "G2")),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for R1" in  new BufferScope{
      getMatchingPair(DataElement("3", "R2"))
      dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for R2" in  new BufferScope{
      getMatchingPair(DataElement("8", "R1"))
      dataBuffer shouldEqual Map("R1" -> Queue(DataElement("2", "R1"), DataElement("8", "R1")),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for B2" in  new BufferScope{
      getMatchingPair(DataElement("4", "B1"))
      dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for B1" in  new BufferScope{
      getMatchingPair(DataElement("5", "B2"))
      dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
  }
}
