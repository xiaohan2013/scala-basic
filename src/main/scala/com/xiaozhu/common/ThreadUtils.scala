package com.xiaozhu.common

import com.google.common.util.concurrent.ThreadFactoryBuilder

import java.util.concurrent.{Executors, ThreadFactory, ThreadPoolExecutor}

object ThreadUtils {

  def namedThreadFactory(prefix: String): ThreadFactory = {
    new ThreadFactoryBuilder().setDaemon(true).setNameFormat(prefix + "-%d").build();
  }

  def newDaemonCachedThreadPool(prefix: String): ThreadPoolExecutor={
    val threadFactory = namedThreadFactory(prefix)
    Executors.newCachedThreadPool(threadFactory).asInstanceOf[ThreadPoolExecutor]
  }


}
