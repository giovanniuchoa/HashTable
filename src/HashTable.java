public class HashTable<K extends Comparable<K>, V> {
    private int capacity;
    private BinaryTree<Entry<K, V>>[] table;
    private int size;
    private double loadFactorThreshold;

    public HashTable(int capacity, double loadFactorThreshold) {
        this.capacity = capacity;
        this.loadFactorThreshold = loadFactorThreshold;
        this.table = new BinaryTree[capacity];
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new BinaryTree<>();
        }

        Entry<K, V> entry = new Entry<>(key, value);
        table[index].insert(entry);
        size++;

        if ((1.0 * size) / capacity > loadFactorThreshold) {
            resizeTable();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return null;
        }

        Entry<K, V> target = new Entry<>(key, null);
        Entry<K, V> result = table[index].search(target);
        if (result != null) {
            return result.getValue();
        }

        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        if (table[index] == null) {
            return null;
        }

        Entry<K, V> target = new Entry<>(key, null);
        Entry<K, V> result = table[index].delete(target);
        if (result != null) {
            size--;
            return result.getValue();
        }

        return null;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % capacity;
    }

    private void resizeTable() {
        capacity *= 2;
        BinaryTree<Entry<K, V>>[] newTable = new BinaryTree[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i].inOrderTraversal()) {
                    int index = getIndex(entry.getKey());
                    if (newTable[index] == null) {
                        newTable[index] = new BinaryTree<>();
                    }
                    newTable[index].insert(entry);
                }
            }
        }
        table = newTable;
    }


    public int size() {
        return size;
    }

    private static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public int compareTo(Entry<K, V> other) {
            return key.compareTo(other.key);
        }
    }
}
