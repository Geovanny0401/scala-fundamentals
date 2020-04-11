import java.util.UUID

import com.h2.entities._

object BankOfScala {
  def main(args: Array[String]): Unit = {
    println("Opening Bank")

    val bank = new Bank(name = "Bank of Scala", country = "Colombia", city = "BBBA", email = Email("bank", "scala.com"))
    val customerIds = getCustomers map (c => bank.createNewCustomer(c._1, c._2, c._3, c._4))
    val depositProductIds = getDepositProducts map (p => bank.addNewDepositProduct(p._1, p._2, p._3))
    val lendingProductIds = getLendingProducts map (l => bank.addNewLendingProduct(l._2, l._3, l._4))

    /* logging */
    println(s"Bank: $bank")
    println(s"CustomerIds: $customerIds")
    println(s"Deposits Products Ids: $depositProductIds")
    println(s"LendingProductIds: $lendingProductIds")

    def openAccounts(customerId: UUID, productId: UUID, productType: String) = productType match {
      case "Deposits" => bank.openDepositAccount(customerId, productId, _: Dollars)
        case "Lending" => bank.openLendingAccount(customerId, productId, _: Dollars)
    }
    /*
    Bank clerk opening the account.
    There is no money deposited in the account yet, so the accounts are not active
    */
    val depositAccounts = for {
      c <- customerIds
      p <- depositProductIds
    } yield openAccounts(c, p, "Deposits")
    /* Depositing money into the accounts */
    val random = new scala.util.Random()
    val depositAccountIds = depositAccounts map { account => account(Dollars(10000 + random.nextInt(10000))) }


    /* logging */
    println(s"Deposits Accounts: $depositAccounts")
    println(s"Deposits Account Ids: $depositAccountIds")

    /*
         Open credit card accounts.
         The bank process has not finished the credit check, so, balance will be known later
        */
    val lendingAccounts = for {
      c <- customerIds
      p <- lendingProductIds
    } yield openAccounts(c, p, "Lending")
    val lendingAccountIds = lendingAccounts map { account => account(Dollars(random.nextInt(500))) }

    /* logging */
    println(s"Lending Accounts: $lendingAccounts")
    println(s"Lending Account Ids: $lendingAccountIds")
    println(s"Bank: $bank")

    /*
      Performing Deposit Accounts transactions
     */
    val randomAmount = new scala.util.Random(100)
    depositAccountIds foreach { accountId =>
      bank deposit(accountId, Dollars(1 + randomAmount.nextInt(100)))
      bank withdraw(accountId, Dollars(1 + randomAmount.nextInt(50)))
    }

    /*
      Performing Lending Accounts transactions
    */
    lendingAccountIds foreach { accountId =>
      bank useCreditCard(accountId, Dollars(1 + randomAmount.nextInt(500)))
      bank payCreditCardBill(accountId, Dollars(1 + randomAmount.nextInt(100)))
    }
  }

  /* ------------------- Data ------------------- */
  def getCustomers: Seq[(String, String, String, String)] = {
    Seq(
      ("Geovanny", "Mendoza", "geovanny@mendoza.com", "1976/11/25"),
      ("Valeria", "Ahumada", "valeria.ahumada@google.com", "1983/4/12"),
      ("Matias", "Mendoza", "matias33@mendoza.com", "1985/4/5")
    )
  }

  def getDepositProducts: Seq[(String, Int, Double)] = {
    Seq(
      ("CoreChecking", 1000, 0.025),
      ("StudentCheckings", 0, 0.010),
      ("RewardsSavings", 10000, 0.10),
    )
  }

  def getLendingProducts: Seq[(String, Double, Double, Double)] = {
    Seq(("CreditCard", 99.00, 14.23, 20.00))
  }

}
