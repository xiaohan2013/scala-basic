package com.xiaozhu.common

import org.apache.spark.internal.Logging
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{StringType, StructField, StructType}

import java.util.Arrays

case class OrderItem(id: String, orderId: String, name: String, amount: Int, price: Double, userId: String)

object Main extends App with Logging{

  val sparkSession = SparkSession.builder
    .config("spark.sql.inMemoryColumnarStorage.compressed", true)
    .config("spark.sql.inMemoryColumnarStorage.batchSize", 10000)
    .config("spark.sql.files.maxPartitionBytes", 10000)
    .config("spark.sql.files.openCostInBytes", 10000)
    .config("spark.sql.files.minPartitionNum", 100)
    .config("spark.sql.broadcastTimeout", 100)
    .config("spark.sql.autoBroadcastJoinThreshold", 100)
    .config("spark.sql.shuffle.partitions", 100)
    .config("spark.sql.sources.parallelPartitionDiscovery.parallelism", 100)
    .config("spark.sql.adaptive.enabled", true) // as of 3.2.0
    .appName("CommonMain")
    .master("local[*]")
    .getOrCreate()

  log.info("Main Start")

  // DataFrame的类型是ROW
  val orderItems = sparkSession.read.json("./data/order_items.json")
  orderItems.show(false)
  orderItems.printSchema()
  orderItems.cache()
  orderItems.createGlobalTempView("order")

  val schemaType = StructType(Seq(
    StructField("id", StringType, nullable = false)
  ))

  // 行转列
  // 以name列中的值作为Header, 按照userId进行分组，求price聚合
  orderItems.groupBy("userId")
    .pivot("name")
    .sum("price")
    .show(false)

  // 指定列的值
  orderItems.groupBy("userId")
    .pivot("name", Seq("apple", "cake"))
    .sum("price")
    .show()

  orderItems.explain(true)

  orderItems.unpersist()

  Runtime.getRuntime.addShutdownHook(new Thread(new Runnable {
    override def run(): Unit = {
      log.info("Shutdown Hook")
    }
  }));
}
