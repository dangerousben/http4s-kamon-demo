object Main {
  def main(args: Array[String]): Unit = {
    kamon.Kamon.init()
    App.main(args)
  }
}
