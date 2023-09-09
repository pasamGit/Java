package com.pasam.basic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PubSub {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue=new ArrayBlockingQueue<>(10);
        Runnable pub=()->{

            for(int i=1;i<=10;i++){
                String msg="msg"+i;
                try {
                    queue.put(msg);
                    System.out.println("published"+msg);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        };

        Runnable sub=()->{

            while(true){
                try {
                    String msg=queue.take();
                    System.out.println("consumed---"+msg);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread publisher=new Thread(pub);
        Thread subscriber=new Thread(sub);
        publisher.start();
        publisher.join();
        subscriber.start();
    }

}
