package com.xiaozhu.common



object TypeEraseMain extends App {

  // 类型擦除，两种参数都能够实现
  def test[T](a: T) = a match {
    case a: List[T] => println("ixxxxx")
    case _ =>
  }

  test(List("abcd"))
  test(List(2013))
}
