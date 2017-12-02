package ru.spbstu.telematics.java;

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
        ArrayList<Integer> test2 = test;
        c = test.equals(test2);
        c = (test.hashCode() == test2.hashCode());
        ArrayList<Integer> test3 = new ArrayList<>(3);
        test3.add(11);
        test3.add(22);
        test3.add(33);
        c = test3.equals(test);
        c = (test3.hashCode() == test.hashCode());
        ArrayList<String> test4 = new ArrayList<>();
        test4.add("Hello");
        test4.add("nn");
        test4.add(0,"aaaa");
        test4.add(2, "h");
        test4.remove(2);
        test4.add(2, "h");
        test4.remove("h");
        c = test4.equals(test);
        c = (test4.hashCode() == test.hashCode());
    }
}
