package ru.geekbrains.valerych.lesson8;

import java.util.LinkedList;
import java.util.List;

public class ChainsHashTableImpl<K, V> implements HashTable<K, V> {

    static class Node<K, V> implements Entry<K, V> {

        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private final int capacity;

    private List<Node<K, V>>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ChainsHashTableImpl(int capacity) {
        this.capacity = capacity;
        data = new List[capacity];
        for (int i = 0; i < capacity; i++) {
            data[i] = new LinkedList<>();
        }

    }

    @Override
    public boolean put(K key, V value) {
        int index = indexOf(key);
        data[index].add(new Node<>(key, value));
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        for (Node<K, V> node : data[index]) {
            if (node.getKey().equals(key)){
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        for (Node<K, V> node : data[index]) {
            if (node.getKey().equals(key)){
                V value = node.getValue();
                data[index].remove(node);
                size--;
                return value;
            }
        }
        return null;
    }

    private int indexOf(K key) {
        return key.hashCode() % capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        for (int i = 0; i < data.length; i++) {
            System.out.println("[" + i + "]: " + data[i]);
        }
    }
}
