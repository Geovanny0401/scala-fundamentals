class Employee{
  var first: String= ""
  var last: String= ""

  override def toString: String = first + " " + last
}

val geovannyMendoza = new Employee
geovannyMendoza.first="Geovanny"
geovannyMendoza.last="Mendoza"

geovannyMendoza

geovannyMendoza.first="Ketty"
geovannyMendoza.last="Aguirre"

geovannyMendoza