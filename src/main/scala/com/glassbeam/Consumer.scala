package com.glassbeam

import com.glassbeam.domain.DataElement
import com.glassbeam.reposiotry.BufferService

class Consumer(bufferService: BufferService) {
  def emitPairForChannel1(element: DataElement) = {
    bufferService.getMatchingPair(element) match {
      case (pair, _) if (pair.equals(element)) => None
      case (pair, _) => {
        if (pair.getChannel == 2)
          Some((element, pair))
        else Some(pair, element)
      }
    }
  }
}
