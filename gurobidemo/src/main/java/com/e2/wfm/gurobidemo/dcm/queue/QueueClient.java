package com.e2.wfm.gurobidemo.dcm.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class QueueClient {
    public static void main(String[] argv) {
        Random rn = new Random();
        PriorityBlockingQueue<EventAt> queue = new PriorityBlockingQueue<>();

        List<EventAt> list = new ArrayList<>();

        int max = 100;
        int min = 0;
        int size = 1000000;
        for (int i = 0; i < size; i++) {
            int index = rn.nextInt(max - min + 1) + min;
            EventAt eventAt = new EventAt("name-" + i, index);
            list.add(eventAt);
        }

        long start = System.currentTimeMillis();
        for (EventAt eventAt : list) {
            queue.add(eventAt);
        }
        long end = System.currentTimeMillis();
        System.out.println("========== " + (end - start));

        start = System.currentTimeMillis();
        EventAt updatedEvent = list.get(1);
        boolean removed = queue.remove(updatedEvent);

        updatedEvent.setIndex(-100 + updatedEvent.getIndex());
        queue.add(updatedEvent);
        end = System.currentTimeMillis();
        System.out.println("========== " + (end - start));
        while (!queue.isEmpty()) {
            EventAt eventAt = queue.poll();
            System.out.println(eventAt);
        }
    }
}
