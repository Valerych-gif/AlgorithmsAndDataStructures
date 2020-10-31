package ru.geekbrains.valerych.lesson3.myqueue;

public interface Deque<E> extends Queue<E>{
    boolean insertLeft(E value);
    boolean insertRight(E value);
    E removeLeft();
    E removeRight();
}
