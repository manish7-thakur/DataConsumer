package com.glassbeam.domain

trait Consumer extends BufferService {
  def emitPairForChannel1(element: DataElement) = {
    getMatchingPair(element) match {
      case (pair, _) if (pair.equals(element)) => None
      case (pair, _) => {
        if (pair.getChannel == 2)
          Some((element, pair))
        else Some(pair, element)
      }
    }
  }
}
