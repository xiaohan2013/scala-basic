package com.xiaozhu.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.xiaozhu.common.ScopeMain.counter

import java.util.Objects
import java.util.concurrent.atomic.AtomicInteger


class WithNameScope(val name: String, val parent: Option[WithNameScope] = None,
                    val id: String = WithNameScope.nextScopeId().toString){
  private val jsonMapper = new ObjectMapper()

  def toJson: String = {
    jsonMapper.writeValueAsString(this)
  }

  override def hashCode(): Int = Objects.hashCode(id, name, parent)
}

object WithNameScope{
  private val counter = new AtomicInteger(0);
  def nextScopeId() = counter.getAndIncrement()
}

object ScopeMain {

  private val counter = new AtomicInteger(0);

  private val jsonMapper = new ObjectMapper()

  def nextScopeId = counter.getAndIncrement()

  def fromJson(s: String): WithNameScope ={
    jsonMapper.readValue(s, classOf[WithNameScope])
  }

  def main(args: Array[String]): Unit ={
    println(nextScopeId)

    // 堆栈
    val callerName = Thread.currentThread().getStackTrace()
      .map(_.getMethodName)
      .foreach(println)


  }

}
