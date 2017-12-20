package ru.spbstu.telematics.java;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class WordStats {

    private Map<String, Integer> statisic;



    WordStats(String[] files) throws InterruptedException{
        statisic = new ConcurrentHashMap<>();

        Thread[] tr = new Thread[files.length];

        for(int i = 0; i < files.length; i++){
            FileStats f = new FileStats(files[i]);
            tr[i] = new Thread(f);
            tr[i].start();
            try {
                tr[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                tr[i].interrupt();
            }
        }
    }

    public Map<String, Integer> getStatisic() {
        return statisic;
    }

    private class FileStats implements Runnable {

        private String text;

        public FileStats(String text){
            this.text = text;
        }


        @Override
        public void run() {
            String[] words = text.split("[\\W\0]+");//this regexp works only with english words

            //for(String s : words)
              //  System.out.println(s);

            Map<String, Integer> tmpStat = new TreeMap<>();


            for(String w : words)
                if(!Thread.currentThread().isInterrupted()) {
                    if (tmpStat.containsKey(w))
                        tmpStat.replace(w, tmpStat.get(w) + 1);
                    else
                        tmpStat.put(w, 1);
                }else {
                    System.out.println("Thread is interrupted");
                    return;
                }
                //concurent hash map

            for(Map.Entry<String, Integer> entry : tmpStat.entrySet())
                if(!Thread.currentThread().isInterrupted()) {
                    if(null != statisic.putIfAbsent(entry.getKey(), entry.getValue()))
                        statisic.put(entry.getKey(), entry.getValue() + statisic.get(entry.getKey()));

                    /*synchronized (statisic) {
                        if (statisic.containsKey(entry.getKey()))
                            statisic.replace(entry.getKey(), statisic.get(entry.getKey()) + entry.getValue());
                        else
                            statisic.put(entry.getKey(), entry.getValue());
                    }*/
                }else {
                    System.out.println("Thread is interrupted");
                    return;
                }
        }
    }
}
