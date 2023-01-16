
case class User(name: String, id: String ) extends Product2[String, String] {
  override def _1: String = name
  override def _2: String = id
}

object ProductMain extends App {
  val a = User("bacd", "23")
  println(a._1)
}
