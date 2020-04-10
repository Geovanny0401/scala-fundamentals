import java.time.LocalDate
import com.h2.entities._

object BankOfScala {
  def main(args: Array[String]): Unit = {
    println("Instantiating Bank")

    val coreChecking = new CoreChecking(Dollars(1000), 0.025)
    val studentCheckings = new StudentCheckings(Dollars(0), 0.010)
    val rewardsSavings = new RewardsSavings(Dollars(10000), 0.10, 1)
    val creditCard = new CreditCard(99.00, 14.23, 20.00)
    val products = Set(coreChecking, studentCheckings, rewardsSavings, creditCard)

    val geovannyMendoza = new Customer("Geovanny", "Mendoza", Email("geovanny", "mendoza.com"), LocalDate.of(1983, 8, 22))
    val bobCheckingAccount = new DepositsAccount(geovannyMendoza, coreChecking, Dollars(10000))
    val bobSavingsAccount = new DepositsAccount(geovannyMendoza, rewardsSavings, Dollars(20000))
    val bobCreditAccount = new LendingAccount(geovannyMendoza, creditCard, Dollars(4500))
    val accounts = Set(bobCheckingAccount, bobSavingsAccount, bobCreditAccount)

    val bank = new Bank("Bank Of Scala", "BBBA", "Colombia",
      Email("bank","scala.com"), products, Set(geovannyMendoza), accounts)


    println(bobCheckingAccount)

    bobCheckingAccount deposit 100
    println(bobCheckingAccount)

    bobCheckingAccount withdraw 200
    println(bobCheckingAccount)
  }
}
