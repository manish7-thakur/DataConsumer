import com.glassbeam.domain.DataElement
import org.specs2.mutable.Specification

import scala.collection.immutable.Queue

class ChannelBufferSpecs extends Specification with TestData {

  def getPair(element: DataElement, inQueue: Queue[DataElement], outQueue: Queue[DataElement]) = outQueue.dequeueOption.getOrElse((element, inQueue enqueue element))

  def getPairFormG1Queue(element: DataElement) = {
    getPair(element, g2Queue, g1Queue)
  }

  def getPairFromG2Queue(element: DataElement) = {
    getPair(element, g1Queue, g2Queue)
  }

  def getPairFromB2Queue(element: DataElement) = {
    getPair(element, b1Queue, b2Queue)
  }

  def getPairFromB1Queue(element: DataElement) = {
    getPair(element, b2Queue, b1Queue)
  }

  def getPairFromR2Queue(element: DataElement) = {
    getPair(element, r1Queue, r2Queue)
  }

  def getPairFromR1Queue(element: DataElement) = {
    getPair(element, r2Queue, r1Queue)
  }

  def getMatchingPair(element: DataElement) = {
    element.channelNumber match {
      case "R1" => getPairFromR2Queue(element)
      case "R2" => getPairFromR1Queue(element)
      case "G1" => getPairFromG2Queue(element)
      case "G2" => getPairFormG1Queue(element)
      case "B1" => getPairFromB2Queue(element)
      case "B2" => getPairFromB1Queue(element)
    }
  }

  "ChannelBuffer" >> {
    "#getMatchingPair" should {
      "insert the data element in  queue & return it when matching data element couldn't be found for R1" in {
        getMatchingPair(DataElement("1", "R1")) shouldEqual(DataElement("1", "R1"), Queue(DataElement("2", "R1"), DataElement("1", "R1")))
      }
      "return the matching data & queue element when found for R2" in {
        getMatchingPair(DataElement("7", "R2")) shouldEqual(DataElement("2", "R1"), Queue.empty)
      }
      "return the matching data & queue element when found for G2" in {
        getMatchingPair(DataElement("2", "G2")) shouldEqual(DataElement("3", "G1"), Queue.empty)
      }
      "return the matching data & queue element when found for G1" in {
        getMatchingPair(DataElement("3", "G1")) shouldEqual(DataElement("4", "G2"), Queue(DataElement("5", "G2")))
      }
      "return the matching data & queue element when found for B1" in {
        getMatchingPair(DataElement("4", "B1")) shouldEqual(DataElement("3", "B2"), Queue.empty)
      }
      "return the matching data & queue element when found for B2" in {
        getMatchingPair(DataElement("5", "B2")) shouldEqual(DataElement("1", "B1"), Queue.empty)
      }
    }
  }

}
