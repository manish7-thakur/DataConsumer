package com.glassbeam.repository

import com.glassbeam.TestData
import com.glassbeam.domain.DataElement
import com.glassbeam.reposiotry.BufferService
import org.specs2.mutable.Specification

import scala.collection.immutable.Queue

class BufferServiceSpecs extends Specification with BufferService with TestData {
  sequential

  "BufferService" >> {
    "#getMatchingPair" should {
      "insert the data element in  queue & return it when matching data element couldn't be found for R1" in {
        getMatchingPair(DataElement("1", "R1")) shouldEqual DataElement("1", "R1")
      }
      "return the matching data & queue element when found for R2" in {
        getMatchingPair(DataElement("7", "R2")) shouldEqual DataElement("2", "R1")
      }
      "return the matching data & queue element when found for G2" in {
        getMatchingPair(DataElement("2", "G2")) shouldEqual DataElement("3", "G1")
      }
      "return the matching data & queue element when found for G1" in {
        getMatchingPair(DataElement("3", "G1")) shouldEqual DataElement("4", "G2")
      }
      "return the matching data & queue element when found for B1" in {
        getMatchingPair(DataElement("4", "B1")) shouldEqual DataElement("3", "B2")
      }
      "return the matching data & queue element when found for B2" in {
        getMatchingPair(DataElement("5", "B2")) shouldEqual DataElement("1", "B1")
      }
    }
  }

  override def updateDataStore(key: String, elements: Queue[DataElement]) = {}
}
