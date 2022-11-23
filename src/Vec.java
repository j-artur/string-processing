import java.util.Iterator;
import java.util.Optional;

// implementation of a vector that works like Rust's Vec
public class Vec<T> implements Iterable<T> {
    private T[] data;
    private int size;
    private int capacity;

    public Vec() {
        this(0);
    }

    public Vec(int capacity) {
        this.data = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public void push(T element) {
        if (size == capacity) {
            if (capacity == 0)
                capacity = 1;
            else
                capacity *= 2;
            T[] newData = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            data = newData;
        }

        data[size] = element;
        size++;
    }

    public Optional<T> pop() {
        if (size == 0)
            return Optional.empty();

        size--;
        return Optional.of(data[size]);
    }

    public Optional<T> get(int index) {
        if (index < 0 || index >= size)
            return Optional.empty();

        return Optional.of(data[index]);
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size)
            return;

        data[index] = element;
    }

    public void insert(int index, T element) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds");

        if (size == capacity) {
            capacity *= 2;
            T[] newData = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            data = newData;
        }

        for (int i = size; i > index; i--)
            data[i] = data[i - 1];
        data[index] = element;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new RuntimeException("Index out of bounds");

        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        size--;
    }

    public Optional<T> first() {
        return get(0);
    }

    public Optional<T> last() {
        return get(size - 1);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return data[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (size == 0)
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++)
            sb.append(data[i]).append(", ");
        sb.append(data[size - 1]).append(']');
        return sb.toString();
    }

}
