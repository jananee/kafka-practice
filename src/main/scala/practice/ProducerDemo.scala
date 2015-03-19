package practice

object ProducerDemo {

  def main(args: Array[String]) {
    val producer = new Producer("test2")
    producer.run()
  }

}
