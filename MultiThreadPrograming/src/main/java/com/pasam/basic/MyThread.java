package com.pasam.basic;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MyThread{

    public static void main(String[] args) {
        Runnable ten=()->{
            Thread.currentThread().setName("Ten");
            System.out.println(Thread.currentThread().getName());
           for(int i=1;i<=10;i++){
               System.out.println(Thread.currentThread().getName()+"---"+i);
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        };
        Runnable twenty=()->{
            Thread.currentThread().setName("Twenty");
            System.out.println(Thread.currentThread().getName());
            for(int i=20;i<=30;i++){
                System.out.println(Thread.currentThread().getName()+"---"+i);
                try {
                    Thread.sleep(1001);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Runnable therty=()->{
            Thread.currentThread().setName("Therty");
            System.out.println(Thread.currentThread().getName());
            for(int i=30;i<=40;i++){
                System.out.println(Thread.currentThread().getName()+"---"+i);
                try {
                    Thread.sleep(1003);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread t1=new Thread(ten);
        Thread t2=new Thread(twenty);
        Thread t3=new Thread(therty);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        t2.start();t3.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}