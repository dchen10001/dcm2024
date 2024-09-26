package com.e2.wfm.gurobidemo.thread;

public class ThreadTest {

    public static void main(String[] args) {
        MasterController masterController = new MasterController(10);       
        masterController.start();
        System.out.println("Done");
    }
}
