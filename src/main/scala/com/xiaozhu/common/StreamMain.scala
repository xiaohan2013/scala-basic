package com.xiaozhu.common

import java.io.{ByteArrayOutputStream, PrintStream}
import java.nio.charset.StandardCharsets
import scala.io.Source

object StreamMain {

  def main(args: Array[String]): Unit = {

    val currentOut = System.out
    val currentIn = System.in
    val sm = System.getSecurityManager

    val out = new ByteArrayOutputStream()
    val stream = new PrintStream(out)
    System.setOut(stream)
    System.setErr(stream)
    System.setSecurityManager(sm)

    stream.flush()

    Source.fromString(new String(out.toByteArray(), StandardCharsets.UTF_8)).getLines
      .filter { line =>
        !line.startsWith("log4j") && !line.startsWith("usage")
      }
      .mkString("\n")
  }

}
