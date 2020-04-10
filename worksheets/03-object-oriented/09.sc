object Employee {

  def apply(first: String, last: String, stocks: Int): Employee =
    new Employee(first, last, stocks)

}
class Employee(f: String, l: String, s: Int){
  private val first: String = f
  private val last: String = l
  private var stocks: Int = s

  def getFirst = first
  def getLast = last
  def getStocks = stocks

  def awardMoreStocks(numberOfStocks: Int): Employee =
    Employee(first,last,stocks + numberOfStocks)

  override def toString: String = first + " " + last + " " + stocks

}

val kettyAguirre = new Employee("Ketty", "Aguirre", 10)
kettyAguirre.awardMoreStocks(25)