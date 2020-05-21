package com.company;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T>{
    private LinkedList<T> stack;
    private int capacity;
    private int size = 0;

    public Stack(int capacity) {
        stack = new LinkedList<T>();
        this.capacity=capacity;
    }

    public void setSize(int size){
        this.size=size;
    }

    public void changeCapacity(int capacity){
        this.capacity=capacity;
    }

    public void showCapacity(){
        System.out.println(capacity);
    }

    public void showSize(){
        System.out.println("Size of stack: " + size);
    }

    public T showValue(int position) throws EmptyStackException {
        if(stack.isEmpty())
            throw new EmptyStackException();
        else
            return stack.get(position);
    }

    public void push(T val) throws Exception{
        if(size<capacity){
            stack.add(val);
            size++;
        }
        else
            throw new Exception("Stack is full!");
    }

    public T pop() throws EmptyStackException {
        if(stack.isEmpty())
            throw new EmptyStackException();
        else
            size--;
        return stack.pollLast();
    }

    public int getSize() {
        return stack.size();
    }

    public void deleteAll(){
        while (size>0){
            pop();
        }
    }

    public void showUsingIterator() {
        Iterator it=stack.iterator();
        System.out.print("Stack (from down to top): ");
        while(it.hasNext()) {
            Object element = it.next();
            System.out.print(element + " ");
        }
        System.out.print("\n");
    }

    public String toString() {
        String temp="Stack: ";
        for(T val: stack) {
            temp+=val+" ";
        }
        return temp;
    }
}