package com.xiaozhu.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class LockUtils {
    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    //
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Player player = new Player(i);
            Thread thread = new Thread(player);
            thread.start();
        }

        countDownLatch.await();
    }

    private static class Player implements Runnable {
        private Integer index;
        public Player(Integer index){
            this.index = index;
        }

        @Override
        public void run() {
            System.out.println("玩家"+index+"准备完成");
            countDownLatch.countDown();
        }
    }
}
