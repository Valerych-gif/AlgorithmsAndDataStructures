package ru.geekbrains.valerych.lesson4;

import java.util.Iterator;

public class SimpleLinkedListImpl<E> implements LinkedList<E>, Iterable<E> {

    protected int size;
    protected Node<E> firstElement; //001[005]

    private SimpleLinkedListImpl<E> getThisList(){
        return this;
    }

    @Override
    public void insertFirst(E value) {
        Node<E> newNode = new Node<>(value, firstElement); // 002[007] | 002[..next->005]
        firstElement = newNode; //001[007]
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<E> removedElement = firstElement;
        E data = removedElement.item;

        firstElement = firstElement.next;
        removedElement.next = null;
        removedElement.item = null;

        size--;
        return data;
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;
        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (current == firstElement) {
            firstElement = firstElement.next;
        }
        else {
            previous.next = current.next;
        }

        current.next = null;
        current.item = null;

        size--;
        return true;
    }

    @Override
    public boolean contains(E value) {
        Node<E> current = firstElement;
        while (current != null) {
            if (current.item.equals(value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void display() {
        System.out.println("-----------");
        Node<E> current = firstElement;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
        System.out.println("-----------");
    }

    @Override
    public E getFirst() {
        return firstElement.item;
    }

    private class SimpleLinkedListIterator<E> implements Iterator<E>{

        private Node<E> currentElement;

        public SimpleLinkedListIterator(Node<E> firstElement) {
            this.currentElement= firstElement;
        }

        @Override
        public boolean hasNext() {
            return currentElement!=null;
        }

        @Override
        public E next() {
            Node<E> returnedElement = currentElement;
            currentElement = currentElement.next;
            return returnedElement.item;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIterator<>(firstElement);
    }
}
