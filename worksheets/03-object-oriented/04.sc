class Employee(val first: String, val last: String){

  override def toString: String = first + " " + last
}

val gmendoza = new Employee("Geovanny", "Mendoza")
gmendoza

gmendoza.first
gmendoza.last

class Color(val value: String)
val c = new Color("red")
c.value

class Shape(var value: String)
val s = new Shape("cicle")
s.value
s.value="square"
s.value