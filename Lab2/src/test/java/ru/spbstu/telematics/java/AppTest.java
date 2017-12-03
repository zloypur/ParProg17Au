package ru.spbstu.telematics.java;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ListIterator;
import java.util.ArrayList;

public class AppTest{
    @Test
    public void emptyArraysTest(){
        ru.spbstu.telematics.java.ArrayList<Integer> my = new ru.spbstu.telematics.java.ArrayList<>(5);
        ArrayList<Integer> jav = new ArrayList<>();
        assertEquals("Пустые массивы не равны", my, jav);
    }

    @Test
    public void addSetRemoveTest(){
        ru.spbstu.telematics.java.ArrayList<Integer> my = new ru.spbstu.telematics.java.ArrayList<>(5);
        ArrayList<Integer> jav = new ArrayList<>();
        my.add(1);
        jav.add(1);
        assertEquals("Массивы не равны(добавление в конец 1)", my, jav);
        my.add(2);
        my.add(3);
        my.add(4);
        jav.add(2);
        jav.add(3);
        jav.add(4);
        assertEquals("Массивы не равны(добавления в конец 2)", my, jav);
        my.add(2,5);
        jav.add(2,5);
        assertEquals("Массивы не равны(добавление на позицию)", my, jav);
        my.set(1, 8);
        jav.set(1, 8);
        assertEquals("Массивы не равны(изменение значения)", my, jav);
        my.remove(1);
        jav.remove(1);
        assertEquals("Массивы не равны(удаление по индексу)", my, jav);
        my.remove(new Integer(4));
        jav.remove(new Integer(4));
        assertEquals("Массивы не равны(удаление по значению)", my, jav);
    }

    @Test
    public void iteratorTest(){
        ru.spbstu.telematics.java.ArrayList<Integer> my = new ru.spbstu.telematics.java.ArrayList<>(5);
        ArrayList<Integer> jav = new ArrayList<>();
        my.add(1);
        my.add(2);
        my.add(3);
        my.add(4);
        jav.add(1);
        jav.add(2);
        jav.add(3);
        jav.add(4);
        ListIterator<Integer> javIt= jav.listIterator();
        ListIterator<Integer> myIt = my.iterator();

        assertEquals("Возвращаемые значения hasprev не равны", myIt.hasPrevious(), javIt.hasPrevious());
        assertEquals("Возвращаемые значения hasnext не равны", myIt.hasNext(), javIt.hasNext());
        assertEquals("Возвращаемые значения индексов не равны(prev)", myIt.previousIndex(), javIt.previousIndex());
        assertEquals("Возвращаемые значения индексов не равны(next)", myIt.nextIndex(), javIt.nextIndex());
        assertEquals("Возвращаемые значения итераторов не равны(next)", myIt.next(), javIt.next());
        assertEquals("Возвращаемые значения итераторов не равны(previous)", myIt.previous(), javIt.previous());
        myIt.add(8);
        javIt.add(8);
        assertEquals("Массивы не равны(добавление элемента через итератор)", my, jav);
        myIt.next();
        myIt.set(10);
        javIt.next();
        javIt.set(10);
        assertEquals("Массивы не равны(изменение элемента через итератор)", my, jav);
        myIt.remove();
        javIt.remove();
        assertEquals("Массивы не равны(удаление элемента через итератор)", my, jav);
    }
}
