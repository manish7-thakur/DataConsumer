package com.glassbeam

import com.glassbeam.domain.{Consumer, DataElement}
import org.specs2.mutable.Specification

class ConsumerSpecs extends Specification with Consumer with TestData {

  "ChannelConsumer" >> {
    "on receiving the data element for channel" should {
      "not emit anything when corresponding match could not be found" in {
        emitPairForChannel1(DataElement("3", "R1")) shouldEqual None
      }
      "return the corresponding pair from the other channel" in {
        emitPairForChannel1(DataElement("3", "G1")) shouldEqual Some((DataElement("3", "G1"), DataElement("4", "G2")))
      }
      "maintain the order for the channel" in {
        emitPairForChannel1(DataElement("4", "B2")) shouldEqual Some(DataElement("1", "B1"), DataElement("4", "B2"))
      }
    }
  }
}
