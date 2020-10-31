package ru.geekbrains.valerych.lesson3.myqueue;

public class DequeImpl<E> implements Deque<E> {


    private static final int DEFAULT_TAIL = -1;
    private static final int DEFAULT_HEAD = 0;

    protected E[] data;
    protected int size;

    private int tail;
    private int head;

    @SuppressWarnings("unchecked")
    public DequeImpl(int maxSize) {
        this.data = (E[]) new Object[maxSize];
        this.head = DEFAULT_HEAD;
        this.tail = DEFAULT_TAIL;
    }

    @Override
    public boolean insertRight(E value) {
        return insert(value);
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }

        if (head == 0) {
            head = data.length;
        }

        data[--head] = value;

        size++;
        return true;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return null;
        }

        if (tail == -1) {
            tail = data.length-1;
        }

        E removedValue = data[tail--];
        size--;
        return removedValue;
    }

    @Override
    public E removeLeft() {
        return remove();
    }

    @Override
    public boolean insert(E value) {
        if (isFull()) {
            return false;
        }

        if (tail == data.length - 1) {
            tail = DEFAULT_TAIL;
        }

        data[++tail] = value;
        size++;
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }

        if (head == data.length) {
            head = DEFAULT_HEAD;
        }

        E removedValue = data[head++];
        size--;
        return removedValue;
    }

    @Override
    public E peekHead() {
        return data[head];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return data.length == size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Deque is empty";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("DequeImpl\n")
                .append("Size: ")
                .append(size)
                .append(", Head: ")
                .append(head)
                .append(", Tail: ")
                .append(tail)
                .append("\n[");

        if (tail < head) {
            for (int i = head; i < data.length; i++) {
                stringBuilder
                        .append(data[i])
                        .append(", ");
            }
            for (int i = 0; i <= tail; i++) {
                stringBuilder
                        .append(data[i])
                        .append(", ");
            }
        } else {
            for (int i = head; i <= tail; i++) {
                stringBuilder
                        .append(data[i])
                        .append(", ");
            }
        }
        stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 2));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
