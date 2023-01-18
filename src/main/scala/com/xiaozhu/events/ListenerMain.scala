package com.xiaozhu.events

import org.apache.spark.internal.Logging
import org.apache.spark.scheduler.{JobFailed, JobSucceeded, SparkListener, SparkListenerApplicationEnd, SparkListenerJobEnd}
import org.apache.spark.{SparkConf, SparkContext}

class MySparkListener extends SparkListener with Logging {
  override def onApplicationEnd(applicationEnd: SparkListenerApplicationEnd) {
    println("*************************************************")
    println("app:end")
    println("*************************************************")
  }

  override def onJobEnd(jobEnd: SparkListenerJobEnd) {
    println("*************************************************")
    println("job:end")
    jobEnd.jobResult match {
      case JobSucceeded =>
        println("job:end:JobSucceeded")
//      case JobFailed(exception) =>
//        println("job:end:file")
//        exception.printStackTrace()
    }
    println("*************************************************")
  }
}


object ListenerMain {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("KafkaWordCountProducer").setMaster("local")
    val sc = new SparkContext(sparkConf)
    /*  sc.setJobGroup("test1","testdesc")
      val completedJobs= sc.jobProgressListener*/
    sc.addSparkListener(new MySparkListener)
    val rdd1 = sc.parallelize(List(('a', 'c', 1), ('b', 'a', 1), ('b', 'd', 8)))
    val rdd2 = sc.parallelize(List(('a', 'c', 2), ('b', 'c', 5), ('b', 'd', 6)))
    val rdd3 = rdd1.union(rdd2).map {
      x => {
        Thread.sleep(500)
        x
      }
    }.count()
    rdd1.map(x => 0.2).map(x => 0).map {
      x => {
        if (x == 0) {
          throw new Exception("my exeception")
        }
      }
        x
    }.reduce(_ + _)
    println(rdd3)
    sc.stop()
  }

}
