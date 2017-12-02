package ru.spbstu.telematics.java;

import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main( String[] args ) {
        ArrayList<Integer> test = new ArrayList<>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(2, 4);
        test.add(5);
        boolean c = test.contains('a');
        c = test.contains(1);
        test.remove(2);
        test.remove(new Integer(2));
        test.set(1, 10);
        ListIterator<Integer> it = test.iterator();
        do{
            System.out.println(it.next());
        }while(it.hasNext());
    }
}
