package ru.spbstu.telematics.java;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;


public class AppTest{
    @Test
    public void fullStatTest(){
        File dir = new File("Files");

        System.out.print(dir.getAbsolutePath());

        String[] fileNames = dir.list();

        List<String> files = new ArrayList<>();

        for(String s : fileNames){
            FileInputStream inFile = null;
            try {
                inFile = new FileInputStream(dir.getPath() + File.separator + s);
                byte[] str = new byte[inFile.available()];
                inFile.read(str);
                String text = new String(str);
                files.add(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        WordStats parStatGen = null;
        try {
            parStatGen = new WordStats(files.toArray(new String[files.size()]));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, Integer> parallelStatistic = parStatGen.getStatisic();

        Map<String, Integer> consistentStats = new TreeMap<>();

        for(String s : files){
            String[] words = s.split("[\\W]+");

            for (String w : words)
                if (consistentStats.containsKey(w))
                    consistentStats.replace(w, consistentStats.get(w) + 1);
                else
                    consistentStats.put(w, 1);
        }

        System.out.println("consistentStats = " + consistentStats);
        System.out.println("parallelStatistic = " + parallelStatistic);

        for(Map.Entry<String, Integer> entry : consistentStats.entrySet())
            assertEquals("Полученые статистики не совпадают: " + entry,entry.getValue(), parallelStatistic.get(entry.getKey()));
    }
}
