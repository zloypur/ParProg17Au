package ru.spbstu.telematics.java;

import java.io.File;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        boolean flag = true;
        while(flag) {
            Scanner input = new Scanner(System.in);
            System.out.print("Введите директорию, содержимое которой вы хотите увидеть (введите \"none\" для выхода)\n");
            String dir = input.nextLine();
            if(dir.equals("none") == false) {
                String[] destDirStorage = getDirContent(dir);
                if(destDirStorage[0].equals("%NAD%") == false){
                    System.out.print("Содержимое директории: \n--------------------\n");
                    for (int i = 0; i < destDirStorage.length; i++)
                        System.out.print(destDirStorage[i] + "\n");
                }else{
                    System.out.print("Это не директория \n");
                }
            }else{
                flag = false;
            }
        }
    }

    public static String[] getDirContent(String currDir){
        File destDir = new File(currDir);
        if (destDir.isDirectory()) {
            return destDir.list();
        } else {
            String[] NAD = new String[1];
            NAD[0] = "%NAD%";
            return NAD;
        }

    }

}

