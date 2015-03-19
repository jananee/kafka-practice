package practice

import java.util.Properties
import org.apache.kafka.clients.producer.{ProducerRecord, KafkaProducer}
import org.apache.kafka.common.serialization.StringSerializer


class Producer(topic: String) extends Runnable {

  val props = new Properties()
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094")

  val producer = new KafkaProducer[String, String](props)

  def run() {
    var counter = 1
    while(true) {
      producer.send(new ProducerRecord(topic, s"Message $counter"))
      Thread.sleep(1000)
      counter += 1
    }
  }

}
