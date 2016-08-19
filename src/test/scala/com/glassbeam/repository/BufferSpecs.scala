package com.glassbeam.repository

import com.glassbeam.TestData
import com.glassbeam.domain.DataElement
import com.glassbeam.reposiotry.InMemoryBufferService
import org.specs2.matcher.Scope
import org.specs2.mutable.Specification

import scala.collection.immutable.Queue

class BufferSpecs extends Specification with TestData {
  sequential

  trait BufferScope extends Scope {
    val dataBuffer = Map("R1" -> Queue(),
      "R2" -> Queue[DataElement](),
      "G1" -> Queue[DataElement](),
      "G2" -> Queue[DataElement](DataElement("4", "G2")),
      "B1" -> Queue[DataElement](),
      "B2" -> Queue[DataElement]())
    val bufferService = new InMemoryBufferService(dataBuffer)
  }

  "Buffer" should {
    "be updated for G1" in new BufferScope {
      bufferService.getMatchingPair(DataElement("1", "G2"))
      bufferService.dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(DataElement("4", "G2"), DataElement("1", "G2")),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for G2" in new BufferScope {
      bufferService.getMatchingPair(DataElement("6", "G1"))
      bufferService.dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for R1" in new BufferScope {
      bufferService.getMatchingPair(DataElement("3", "R2"))
      bufferService.dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(DataElement("3", "R2")),
        "G1" -> Queue(),
        "G2" -> Queue(DataElement("4", "G2")),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for R2" in new BufferScope {
      bufferService.getMatchingPair(DataElement("8", "R1"))
      bufferService.dataBuffer shouldEqual Map("R1" -> Queue(DataElement("8", "R1")),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(DataElement("4", "G2")),
        "B1" -> Queue(),
        "B2" -> Queue())
    }
    "be updated for B2" in new BufferScope {
      bufferService.getMatchingPair(DataElement("4", "B1"))
      bufferService.dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(DataElement("4", "G2")),
        "B1" -> Queue(DataElement("4", "B1")),
        "B2" -> Queue())
    }
    "be updated for B1" in new BufferScope {
      bufferService.getMatchingPair(DataElement("5", "B2"))
      bufferService.dataBuffer shouldEqual Map("R1" -> Queue(),
        "R2" -> Queue(),
        "G1" -> Queue(),
        "G2" -> Queue(DataElement("4", "G2")),
        "B1" -> Queue(),
        "B2" -> Queue(DataElement("5", "B2")))
    }
  }
}
