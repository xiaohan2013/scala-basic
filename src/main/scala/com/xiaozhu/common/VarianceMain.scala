package com.xiaozhu.common


abstract class Animal[A] {
  def name : A
  def printName(): Unit ={

  }
}

case class Cat[A](name: A) extends Animal[A]
case class Dog[A](name: A) extends Animal[A]

// 逆变
abstract class Printer[-A] {
  def print(value: A): Unit
}
class AnimalPrinter extends Printer[Animal[String]] {
  def print(animal: Animal[String]): Unit =
    println("The animal's name is: " + animal.name)
}

class CatPrinter extends Printer[Cat[String]] {
  def print(cat: Cat[String]): Unit =
    println("The cat's name is: " + cat.name)
}

object VarianceMain {


  def main(args: Array[String]): Unit = {

    val myCat = Cat("Boots")

    def printMyCat(printer: Printer[Cat[String]]): Unit = {
      printer.print(myCat)
    }

    val catPrinter: Printer[Cat[String]] = new CatPrinter
    val animalPrinter: Printer[Animal[String]] = new AnimalPrinter

    printMyCat(catPrinter)
    // Printer[Cat[String]] 不能使用Animal的printer, 想用父类的printer必须要用-A
    //
    printMyCat(animalPrinter)

  }
}
