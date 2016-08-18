package com.glassbeam

import com.glassbeam.domain.{Consumer, DataElement, InMemoryBufferService}
import org.specs2.mutable.Specification

import scala.collection.immutable.Queue

class ConsumerSpecs extends Specification with TestData {
  val dataBuffer = Map("R1" -> Queue(DataElement("2", "R1")),
    "R2" -> Queue[DataElement](),
    "G1" -> Queue(DataElement("3", "G1")),
    "G2" -> Queue(DataElement("4", "G2"), DataElement("5", "G2")),
    "B1" -> Queue(DataElement("1", "B1")),
    "B2" -> Queue(DataElement("3", "B2")))
  val consumer = new Consumer(new InMemoryBufferService(dataBuffer))
  "ChannelConsumer" >> {
    "on receiving the data element for channel" should {
      "not emit anything when corresponding match could not be found" in {
        consumer.emitPairForChannel1(DataElement("3", "R1")) shouldEqual None
      }
      "return the corresponding pair from the other channel" in {
        consumer.emitPairForChannel1(DataElement("3", "G1")) shouldEqual Some((DataElement("3", "G1"), DataElement("4", "G2")))
      }
      "maintain the order for the channel" in {
        consumer.emitPairForChannel1(DataElement("4", "B2")) shouldEqual Some(DataElement("1", "B1"), DataElement("4", "B2"))
      }
    }
    "on continuously feeding data element" should {
      "emit pair continuously" in {
        val dataBuffer = Map("R1" -> Queue[DataElement](),
          "R2" -> Queue[DataElement](),
          "G1" -> Queue(),
          "G2" -> Queue(),
          "B1" -> Queue(),
          "B2" -> Queue())
        val consumer = new Consumer(new InMemoryBufferService(dataBuffer))

        Stream.apply(DataElement("1", "R1"), DataElement("6", "B2"), DataElement("2", "R1"),
          DataElement("8", "B2"), DataElement("3", "R1"), DataElement("9", "R2"),
          DataElement("4", "B1"), DataElement("10", "G2"), DataElement("8", "B1"),
          DataElement("7", "B2"), DataElement("5", "G1"), DataElement("20", "R2")).map(consumer.emitPairForChannel1).toList.flatten shouldEqual List((DataElement("1", "R1"), DataElement("9", "R2")), (DataElement("4", "B1"), DataElement("6", "B2")),
            (DataElement("8", "B1"),DataElement("8", "B2")), (DataElement("5", "G1"),DataElement("10", "G2")), (DataElement("2", "R1"),DataElement("20", "R2")))
      }
    }
  }
}
