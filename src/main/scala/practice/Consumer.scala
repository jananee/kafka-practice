package practice

import java.util.Properties
import scala.collection.JavaConversions._
import kafka.consumer.ConsumerConfig

class Consumer(topic: String) extends Runnable {

val props = new Properties()
props.put("group.id", "12345")
props.put("auto.commit.enable", "false") // Need this as we want to update the offset after the message is processed
props.put("zookeeper.connect", "localhost:2181")
props.put("zookeeper.connection.timeout.ms", "6000")
props.put("auto.offset.reset", "smallest") // This setting is used to determine what happens when the consumer starts for the first time
props.put("offsets.storage", "kafka") // This will store the offset for each consumer at kafka as a topic and not overload zookeeper

val consumer = kafka.consumer.Consumer.createJavaConsumerConnector(new ConsumerConfig(props))

def run() {
  val topicMap = Map(topic -> new Integer(1))
  val consumerMap = consumer.createMessageStreams(topicMap)
  val messageStream = consumerMap(topic).get(0)
  val it = messageStream.iterator()
  while(it.hasNext()) {
    println(new String(it.next().message()))
    consumer.commitOffsets(true)
  }
}

}
