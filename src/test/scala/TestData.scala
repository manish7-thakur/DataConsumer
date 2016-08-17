
import com.glassbeam.domain.DataElement

import scala.collection.immutable.Queue

trait TestData {

  val r1Queue = Queue(DataElement("2", "R1"))
  val r2Queue = Queue()
  val g1Queue = Queue(DataElement("3", "G1"))
  val g2Queue = Queue(DataElement("4", "G2"), DataElement("5", "G2"))
  val b1Queue = Queue(DataElement("1", "B1"))
  val b2Queue = Queue(DataElement("3", "B2"))

}
