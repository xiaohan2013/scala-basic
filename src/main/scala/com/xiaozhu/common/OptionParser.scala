package com.xiaozhu.common

import java.util.regex.Pattern

object OptionParser {
  val CONF = "--a"

  val options = List(
    List(CONF, "-a")
  )

  def findCliOption(name: String, available: List[List[String]]): Option[List[String]] ={
    for (elem <- available) {
      for (e <- elem) {
        if(e.equals(name)){
          Some(elem.take(0))
        }
      }
    }
    None
  }

  def parse(args: List[String]): Unit = {
    val eqSeparatedOpt = Pattern.compile("(--[^=]+)=(.+)")

    for (elem <- args) {
      val arg = elem
      val value = Nil

      val m = eqSeparatedOpt.matcher(arg)
      if(m.matches()){
        println(m.group(1), m.group(2))
      }

      val name = findCliOption(arg, options)
    }
  }

  def main(args: Array[String]): Unit = {

    parse("--c=d" :: List("--a=c"))

  }
}
