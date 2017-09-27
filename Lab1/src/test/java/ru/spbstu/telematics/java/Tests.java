package ru.spbstu.telematics.java;

import java.io.File;
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
        String hiddenFile = ".emacs.d";
        String[] result = Lab1.getDirContent("/home/zloypur/");
        int filePos = -1;
        for (int i = 0; i < result.length; i++) {
            if(hiddenFile.compareTo(result[i]) == 0){
                filePos = i;
                break;
            }
        }
        assertEquals("Невидимый файл виден ", result[filePos], hiddenFile);
		
	//if(hiddenFile.compareTo(result[filePos]) == 0){
	//	System.out.println("Hidden File founded");
	//}else{
	//	System.out.println("No hidden file");
	//}
    }
}
