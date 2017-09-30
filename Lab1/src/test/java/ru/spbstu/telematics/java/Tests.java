package ru.spbstu.telematics.java;

import java.io.File;
import java.io.IOException;
//import junit.framework.Test;
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
		//if(expectations[i].compareTo(result[i]) == 0){
		//	System.out.println("Root folders is fine " + i);
		//}
        }
    }

    @Test
    public void hiddenFiles(){
        String hiddenFile = ".hidden.file";
        File hiddenFileDir = new File("/home/zloypur/Документы/" + hiddenFile);
        try{

            if(hiddenFileDir.createNewFile()){
                System.out.println("created");
            }else{
                System.out.println("not created");
            }
            String[] result = Lab1.getDirContent("/home/zloypur/Документы");
            int filePos = -1;
            for (int i = 0; i < result.length; i++) {
                if(hiddenFile.compareTo(result[i]) == 0){
                    filePos = i;
                    break;
                }
            }
            if(hiddenFileDir.delete()){
                System.out.println("deleted");
            }else{
                System.out.println("not deleted");
            }
            assertEquals("Невидимый файл виден ", result[filePos], hiddenFile);
        }
       catch(IOException ex){
            System.out.println(ex.getMessage());
        }
		
	//if(hiddenFile.compareTo(result[filePos]) == 0){
	//	System.out.println("Hidden File founded");
	//}else{
	//	System.out.println("No hidden file");
	//}
    }
}
