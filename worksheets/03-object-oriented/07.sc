object MyApplication {
  def main(args: Array[String]): Unit = {
    println("I am entry point to the application")
    args.foreach(println)
  }
}
MyApplication.main(Array("hello", "world!"))