package task1;

import java.util.Arrays;
import java.util.LinkedList;

public class MyHashMap<K, V> {
    private final LinkedList<MyEntry<K, V>>[] buckets;
    private final int size;

    public MyHashMap() {
        this(100);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int size) {
        this.size = size;

        buckets = new LinkedList[size];
        for (int i = 0; i < size; ++i) {
            buckets[i] = new LinkedList<>();
        }
    }

    static class MyEntry<K, V> {
        private final K key;
        private V value;

        MyEntry (K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "MyEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public void put(K key, V value) {
        LinkedList<MyEntry<K, V>> bucket = buckets[Math.abs(key.hashCode()) % size];

        if (get(key) == null) {
            bucket.add(new MyEntry<K, V>(key, value));
        } else {
            for (MyEntry<K, V> entry : bucket) {
                if (entry.getKey() == key) {
                    entry.setValue(value);
                }
            }
        }
    }

    public V get (K key) {
        LinkedList<MyEntry<K, V>> bucket = buckets[Math.abs(key.hashCode()) % size];

        for (MyEntry<K, V> entry : bucket) {
            if (entry.getKey() == key) {
                return entry.getValue();
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Task1.MyHashMap{\n" +
                "buckets=" + Arrays.toString(buckets) +
                ", \nsize=" + size +
                "\n";
    }
}