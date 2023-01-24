package com.xiaozhu.common

import java.io.{File, FileInputStream, FileOutputStream, FileReader, IOException}
import java.nio.charset.StandardCharsets

object NioMain {

  def main(args: Array[String]): Unit = {
    val outFile = new File("D:\\java-demo\\scala-basic\\data\\data.txt")
    val inFile = new File("D:\\java-demo\\scala-basic\\data\\in.txt")
    // 字节流：InputStream、OutputStream
    // 文件流:从一端流动到另一端，即从java内存流动到存储介质中。存储介质包括:硬盘文件、数据库与网络等节点(数据源)。记住:一切以java内存为中心。
    val outStream = new FileOutputStream(outFile, true)
    val inStream = new FileInputStream(inFile)

    // NIO方式：Stream和Channel
    val inChannel = inStream.getChannel
    val outChannel = outStream.getChannel
    val inSize = inChannel.size()
    var count = 0L
    val startPos = 0L
    while (count < inSize) {
      count += inChannel.transferTo(count + startPos , inSize, outChannel)
    }
    // 字符流
    // Reader、Writer
//    try (Reader reader = new FileReader("src/readme.txt", StandardCharsets.UTF_8) {
//      // TODO
//    };

    var reader: FileReader = null
    try{
      reader = new FileReader("D:\\java-demo\\scala-basic\\data\\data.txt")

      val buffer = new Array[Char](10)

      var n = 0
      while( n != -1) {
        n = reader.read(buffer)
        buffer.foreach(println)
      }

    } catch {
      case e: IOException => println(e.getCause)
      case _ => println("exception...")
    } finally {
      reader.close
    }
  }


}
