package com.xiaozhu.rdd

import org.apache.spark.{SparkConf, SparkContext}

object PipeMain {

  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf()
      .setAppName("Pipe Main")
      .setMaster("local"))

    val data = sc.parallelize(List("hi", "hello", "how", "are", "you"), 2)
    data.pipe("D:\\java-demo\\scala-basic\\src\\main\\resources\\cmd.bat").collect()


  }

}
