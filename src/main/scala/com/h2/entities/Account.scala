package com.h2.entities

import java.util.UUID
import com.h2.entities.Dollars.Zero

abstract class Account {
  val id: UUID = UUID.randomUUID()
  val customer: Customer
  val product: Product

  def getBalance: Dollars
}
  class DepositsAccount(val customer: Customer,
                        val product: Deposits,
                        private var balance: Dollars) extends Account {

  def deposit(dollars: Dollars): Unit = {
    require(dollars > Zero, "amount deposited should be greater than zero.")
    balance += dollars
    println(s"Depositing $dollars to ${this.toString}")

  }

  def withdraw(dollars: Dollars): Unit = {
    require(dollars > Zero && balance > dollars,
      "amount should be greater than zero and requested amount should be less than or equal to balance.")
    balance -= dollars
    println(s"Withdrawing $dollars from ${this.toString}")

  }

    override def getBalance: Dollars = balance

    override def toString = s"$customer with $product has remaining balance of $balance"

  }

class LendingAccount(val customer: Customer,
                     val product: Lending,
                     private var balance: Dollars) extends Account {

  def payBill(dollars: Dollars): Unit = {
    require(dollars > Zero, "The payment must be made for amount greater than zero.")
    balance += dollars
    println(s"Paying bill of $dollars against ${this.toString}")
  }

  def withdraw(dollars: Dollars): Unit = {
    require(dollars > Zero, "The withdrawal amount must be greater than zero.")
    balance -= dollars
    println(s"debiting $dollars from ${this.toString}")

  }

  override def getBalance: Dollars = balance

  override def toString = s"$customer with $product has remaining balance of $balance"
}