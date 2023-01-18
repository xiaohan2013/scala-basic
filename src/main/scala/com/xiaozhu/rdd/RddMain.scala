package com.xiaozhu.rdd

import org.apache.logging.log4j.Level
import org.apache.spark.{SparkConf, SparkContext}

object RddMain {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf()
      .setAppName("RDD Main")
      .setMaster("local"))

    sc.setLogLevel(Level.OFF.toString)

    val rdd1 = sc.parallelize(Seq(1,2,3,4,5,6,7,8,9))
    // 只能ZIP相同数量的RDD
    val rdd2 = rdd1.map(_ * 2)

    rdd1.zip(rdd2).foreach(println)

    val rdd3 = rdd1 ++ rdd2
    rdd3.distinct().sortBy((s) => s , true).foreach(println)

    val rdd4 = rdd3.zipWithIndex().foreach(println)
  }


}
