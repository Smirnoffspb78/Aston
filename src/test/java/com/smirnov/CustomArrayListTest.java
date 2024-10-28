package com.smirnov;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;


class CustomArrayListTest {

    @Test
    void size() {
        List<Integer> testList = createList_size3();
        final int capacity = 3;

        assertEquals(testList.size(), capacity);
    }

    @Test
    void isEmpty_true() {
        final boolean result = true;

        assertEquals(new CustomArrayList<>().isEmpty(), result);
    }

    @Test
    void isEmpty_false() {
        List<Integer> testList = createList_size3();
        final boolean result = false;

        assertEquals(testList.isEmpty(), result);
    }

    @Test
    void add_byIndex() {
        List<Integer> testList = createList_size3();
        final int initialCapacity = 3;
        final int capacity = 4;
        final int index = 0;
        final Integer element = 0;
        List<Integer> resultList = of(0, 1, 2, 3);

        assertEquals(testList.size(), initialCapacity);
        testList.add(index, element);
        assertEquals(testList.size(), capacity);
        assertEquals(testList, resultList);
    }

    @Test
    void add_byIndex_null() {
        List<Integer> testList = createList_size3();
        final int initialCapacity = 3;
        final int capacity = 4;
        final int index = 0;
        final Integer element = null;
        List<Integer> resultList = new CustomArrayList<>();
        resultList.add(null);
        resultList.add(1);
        resultList.add(2);
        resultList.add(3);

        assertEquals(testList.size(), initialCapacity);
        testList.add(index, element);
        assertEquals(testList.size(), capacity);
        assertEquals(testList, resultList);
    }

    @Test
    void remove() {
        List<Integer> testList = createList_size3();
        final int initialCapacity = 3;
        final int capacity = 2;
        final int removeIndex = 1;
        List<Integer> newList = new CustomArrayList<>();
        newList.add(1);
        newList.add(3);

        assertEquals(testList.size(), initialCapacity);
        testList.remove(removeIndex);
        assertEquals(testList.size(), capacity);
        assertEquals(newList, testList);
    }

    @Test
    void remove_Element_null() {
        List<Integer> testList = createList_size3();
        testList.add(null);
        testList.add(null);
        testList.add(6);
        testList.add(null);

        final int initialCapacity = 7;
        final int capacity = 4;
        final Object removeElement = null;
        List<Integer> newList = createList_size3();
        newList.add(6);


        assertEquals(testList.size(), initialCapacity);
        testList.remove(removeElement);
        assertEquals(testList.size(), capacity);
        assertEquals(newList, testList);
    }

    @Test
    void remove_Element() {
        List<Integer> testList = createList_size3();
        testList.add(25);
        testList.add(25);
        testList.add(6);
        testList.add(25);
        final int initialCapacity = 7;
        final int capacity = 4;
        final Object removeElement = 25;
        List<Integer> newList = createList_size3();
        newList.add(6);

        assertEquals(testList.size(), initialCapacity);
        testList.remove(removeElement);
        assertEquals(testList.size(), capacity);
        assertEquals(newList, testList);
    }

    @Test
    void addAll() {
        List<Integer> testList = createList_size3();
        List<Integer> mergeSet = of(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        List<Integer> resultLis = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
        final int capacity = 40;

        testList.addAll(mergeSet);
        assertEquals(testList.size(), capacity);
        assertEquals(testList, resultLis);
    }

    @Test
    void sort() {
        List<Integer> testList = new CustomArrayList<>();
        testList.add(5);
        testList.add(3);
        testList.add(1);
        testList.add(4);
        testList.add(2);
        List<Integer> resultList = List.of(1, 2, 3, 4, 5);

        testList.sort(Comparator.naturalOrder());
        assertEquals(testList, resultList);
    }

    @Test
    void sort_twoElement() {
        List<Integer> testList = new CustomArrayList<>();
        testList.add(2);
        testList.add(1);
        List<Integer> resultList = List.of(1, 2);

        testList.sort(Comparator.naturalOrder());
        assertEquals(testList, resultList);
        System.out.println(testList);
    }

    @Test
    void sort_oneElement() {
        List<Integer> testList = new CustomArrayList<>();
        testList.add(2);
        List<Integer> resultList = List.of(2);

        testList.sort(Comparator.naturalOrder());
        assertEquals(testList, resultList);
        System.out.println(testList);
    }

    @Test
    void clear() {
        List<Integer> testList = createList_size3();
        int initialCapacity = 3;
        int capacity = 0;


        assertEquals(testList.size(), initialCapacity);
        testList.clear();
        assertEquals(testList.size(), capacity);
        assertEquals(testList, new CustomArrayList<>());
    }

    @Test
    void get() {
    }

    @Test
    void testAdd() {
    }


    private List<Integer> createList_size3() {
        List<Integer> list = new CustomArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }
}