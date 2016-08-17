package com.glassbeam

import com.glassbeam.domain.DataElement
import org.specs2.mutable.Specification

class ConsumerSpecs extends Specification {

  def emitPairFor(element: DataElement) = { Unit }

  "ChannelConsumer" >> {
    "on receiving the data element should not emit anything when corresponding match could not be found" in {
      emitPairFor(DataElement("3", "R2")) shouldEqual Unit
    }
  }

}
