package ru.spbstu.telematics.java;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

import org.junit.Test;



public class Tests{
    @Test
    public void rootTest(){
        File destDir = new File("/");
        String[] expectations = destDir.list();
        String[] result = Lab1.getDirContent("/");
        for (int i = 0; i < expectations.length; i++) {
            assertEquals("Совпало " + i,  expectations[i], result[i]);
        }
    }

    @Test
    public void hiddenFiles(){
        String hiddenFile = ".hidden.file";
        String path = new File("").getAbsolutePath();
        String fileSeparator = System.getProperty("file.separator");
        File hiddenFileDir = new File(path + fileSeparator + hiddenFile);
        try{

            if(hiddenFileDir.createNewFile()){
                System.out.println("created " + path + fileSeparator + hiddenFile);
            }else{
                System.out.println("not created");
            }
            String[] result = Lab1.getDirContent(path);
            int filePos = -1;
            for (int i = 0; i < result.length; i++) {
                if(hiddenFile.compareTo(result[i]) == 0){
                    filePos = i;
                    break;
                }
            }
            if(hiddenFileDir.delete()){
                System.out.println("deleted " + path + fileSeparator + hiddenFile);
            }else{
                System.out.println("not deleted");
            }
            assertEquals("Невидимый файл не виден ", result[filePos], hiddenFile);
        }
       catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
