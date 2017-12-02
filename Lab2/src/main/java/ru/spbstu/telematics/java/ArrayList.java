package ru.spbstu.telematics.java;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

public class ArrayList<T> implements Iterable<T>{

    T[] data;

    int size;
    int capacity;

    ArrayList() {
        capacity = 1;
        data = (T[]) new Object[capacity];
        size = 0;
    }

    ArrayList(int capacity){
        data = (T[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public class ArrayListIterator implements ListIterator<T>{

        int index;

        ArrayListIterator(){
            index = 0;
        }

        public void add(T t) {
            if(capacity - size > 1) {
                int tmpCapacity = (capacity * 3) / 2 + 1;
                T[] tmpData = (T[]) new Object[tmpCapacity];
                System.arraycopy(data, 0, tmpData, 0, size);
                capacity = tmpCapacity;
                data = tmpData;
            }
            data[size++] =  t;
        }

        public void remove() {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            data[--size] = null;
        }

        public void set(T t) {
            data[index] = (T) t;
        }

        public int nextIndex() throws NoSuchElementException {
            if(index < size - 1)
                return index + 1;
            else
                throw new NoSuchElementException();
        }

        public int previousIndex() throws NoSuchElementException {
            if(index > 0)
                return index - 1;
            else
                throw new NoSuchElementException();
        }

        public T next() throws NoSuchElementException {
            if(index < size - 1)
                return  data[++index];
            else
                throw new NoSuchElementException();

        }

        public T previous() throws NoSuchElementException {
            if(index > 0)
                return  data[--index];
            else
                throw new NoSuchElementException();
        }

        public boolean hasNext() {
            return (index + 1) < size;
        }

        public boolean hasPrevious() {
            return (index - 1) > 0;
        }
    }

    public int size(){
        return size;
    }

    private boolean checkCapacity(){ return (capacity - size) > 1; }

    private void resize() {
        int tmpCapacity = (capacity * 3) / 2 + 1;
        T[] tmpData = (T[]) new Object[tmpCapacity];
        System.arraycopy(data, 0, tmpData, 0, size);
        capacity = tmpCapacity;
        data = tmpData;
    }

    public boolean add(T element){
        if(!checkCapacity())
            resize();
        data[size++] = element;
        return true;
    }

    public boolean add(int index, T element) throws IndexOutOfBoundsException {
        if(index > -1 && index < size) {
            if(!checkCapacity())
                resize();
            size++;
            System.arraycopy(data, index, data, index + 1, size - index - 1);
            data[size - 1] = element;
        }else
            throw new IndexOutOfBoundsException();
        return true;
    }

    public T set(int index, T element) throws IndexOutOfBoundsException {
        if(index > -1 && index < size){
            T tmp = data[index];
            data[index] = element;
            return tmp;
        }else
            throw new IndexOutOfBoundsException();
    }

    public T remove(int index) throws IndexOutOfBoundsException {
        if(index > -1 && index < size){
            T tmp = data[index];
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            data[--size] = null;
            return tmp;
        }else
            throw new IndexOutOfBoundsException();
    }

    public boolean remove(Object o){
        for(int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o){
        for(int i = 0; i < size; i++)
            if (o.equals(data[i]))
                return true;
        return false;
    }

    public T get(int index) throws IndexOutOfBoundsException{
        if(index > -1 && index < size)
            return data[index];
        else
            throw new IndexOutOfBoundsException();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public ListIterator<T> iterator(){
        return new ArrayListIterator();
    }
}
