package com.smirnov;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.smirnov.MergeSort.mergeSort;
import static java.lang.System.arraycopy;
import static java.util.Objects.requireNonNull;
import static java.util.stream.IntStream.range;

public class CustomArrayList<T> implements List<T> {
    /**
     * Текущий размер коллекции.
     */
    private int capacity;

    /**
     * Текущее положение указателя в коллекции.
     */
    private int size = 0;

    /**
     * Массив реализации коллекции.
     */
    private Object[] list;

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("initialCapacity должен быть положительным");
        }
        capacity = initialCapacity;
        list = new Object[capacity];
    }

    public CustomArrayList() {
        int initialCapacity = 15;
        capacity = initialCapacity;
        list = new Object[initialCapacity];
    }

    @Override
    public String toString() {
        Object[] stringArray = new Object[size];
        arraycopy(list, 0, stringArray, 0, size);
        return Arrays.toString(stringArray);
    }

    /**
     * Возвращает размер коллекции.
     *
     * @return размер коллекции
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет коллекцию на пустоту.
     *
     * @return true/false если коллекция пустая/не пустая.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    /**
     * Добавляет элемент в коллекцию.
     *
     * @param t element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
    public boolean add(T t) {
        growAdd();
        list[size++] = t;
        return true;
    }

    /**
     * Удаляет элемент из коллекции по значению, если он есть.
     *
     * @param o element to be removed from this list, if present
     * @return true/false, если элемент в коллекции удален/не удален
     */
    @Override
    public boolean remove(Object o) {
        final int initialSize = size;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    remove(i--);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(list[i])) {
                    remove(i--);
                }
            }
        }
        return size != initialSize;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }


    /**
     * Добавляет все элементы коллекции в коллекцию
     *
     * @param c collection containing elements to be added to this collection
     * @return true при успешном добавлении;
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        requireNonNull(c);
        int newSize = size + c.size();
        if (newSize >= capacity) {
            growList(newSize);
        }
        Object[] mergeList = c.toArray();
        arraycopy(mergeList, 0, list, size, newSize - size);
        size = newSize;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Осуществляет сортировку слиянием.
     *
     * @param comparator the {@code Comparator} used to compare list elements.
     *                   A {@code null} value indicates that the elements'
     *                   {@linkplain Comparable natural ordering} should be used
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        requireNonNull(comparator);
        Object[] listSort = new Object[size];
        arraycopy(list, 0, listSort, 0, size);
        mergeSort((T[]) listSort, comparator);
        arraycopy(listSort, 0, list, 0, size);
    }

    /**
     * Очищает создает
     */
    @Override
    public void clear() {
        range(0, size).forEach(i -> list[i] = null);
        size = 0;
    }


    /**
     * Возвращает объект по индексу.
     *
     * @param index index of the element to return
     * @return объект из коллекции по индексу
     */
    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) list[index];
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Вставляет элемент на позицию index.
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, T element) {
        requireNonNull(element);
        validateIndex(index);
        growAdd();
        if (index == size - 1) {
            add(element);
        } else {
            arraycopy(list, index, list, index + 1, size - index + 1);
            list[index] = element;
            size++;
        }
    }

    /**
     * Удаляет элемент по индексу
     *
     * @param index the index of the element to be removed
     * @return Удаленный элемент.
     */
    @Override
    public T remove(int index) {
        validateIndex(index);
        T returnElement = (T) list[index];
        arraycopy(list, index + 1, list, index, size - index);
        list[size-- - 1] = null;
        return returnElement;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index не должен быть отрицательным, index должен быть меньше size");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof List) {
            return rangeEqual((List<?>) o);
        }
        return false;
    }

    private boolean rangeEqual(List<?> o) {
        if (size != o.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if ((list[i] == null && o.get(i) != null) || (list[i] != null && o.get(i) == null)) {
                return false;
            } else if (list[i] != null && o.get(i) != null && (!list[i].equals(o.get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(list);
        result = 31 * result + size;
        return result;
    }

    private void growList(int newSize) {
        final float grow = 1.5f;
        do {
            capacity = (int) Math.ceil(capacity * grow);
        } while (capacity < newSize);
        Object[] bufferList = new Object[capacity];
        arraycopy(list, 0, bufferList, 0, list.length);
        list = bufferList;
    }

    private void growAdd() {
        if (size == capacity) {
            growList(size + 1);
        }
    }
}
