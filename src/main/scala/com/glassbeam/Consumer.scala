package com.glassbeam

import com.glassbeam.domain.DataElement
import com.glassbeam.reposiotry.BufferService

class Consumer(bufferService: BufferService) {
  def emitPairFor(element: DataElement) = {
    val pair = bufferService.getMatchingPair(element)
    pair == element match {
      case true => None
      case false => if(pair.getChannel == 2) Some((element, pair)) else Some(pair, element)
    }
  }
}
