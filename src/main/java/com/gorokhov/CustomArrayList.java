package com.gorokhov;

import java.util.*;
// Please check the code
/**
 * <p>{@code CustomArrayList} is a dynamic array implementation. It can change its size during
 * the execution of the program, while it is not necessary to specify the dimension
 * when creating the object. Elements can be of any type, including {@code null}.</p>
 *
 * <p>As you add items to the {@code CustomArrayList}, its capacity automatically increases
 * by 1.5 times (capacity is the size of the array used to store the list items).</p>
 *
 * <p>{@code CustomArrayList} is not synchronized.</p>
 *
 * {@code CustomArrayList} provides:
 * <ul>
 *  <li>Fast access to elements by index in constant time O(1);</li>
 *  <li>Access to elements by value in linear time O(n);</li>
 *  <li>Insertion and removal of elements from the beginning and middle of the list
 *      in linear time O(n).</li>
 * </ul>
 *
 * <p>There is a static quicksort method {@code quickSort} that takes a {@code CustomArrayList}
 * object (with elements that implement the {@link Comparable} interface) and optionally
 * a {@link Comparator} object.</p>
 *
 * @param   <E> the type of elements
 *
 * @see     Comparable
 * @see     Comparator
 * @author  Dmitrii Gorokhov
 */
public class CustomArrayList<E> {

    /**
     * An array that holds the elements of the CustomArrayList. The capacity
     * of the CustomArrayList is equal to the length of this array.
     */
    private Object[] data;

    /**
     * Number of elements in CustomArrayList.
     */
    private int size;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates an empty list with an initial capacity of ten.
     */
    public CustomArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Creates an empty list with the specified initial capacity.
     *
     * @param  initCapacity  the initial capacity of the list
     *
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public CustomArrayList(int initCapacity) {
        if (initCapacity >= 0)
            this.data = new Object[initCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: " + initCapacity);
    }

    /**
     * Sorts the specified list into ascending order by quick sort algorithm, according to
     * the {@linkplain Comparable natural ordering} of its elements. All elements in the list
     * must implement the {@link Comparable} interface. Furthermore, all elements in the list
     * must be <i>mutually comparable</i> (that is, {@code e1.compareTo(e2)} must not throw
     * a {@link ClassCastException} for any elements {@code e1} and {@code e2} in the list).
     *
     * <p>The average execution time of quicksort is O(n*log n) exchanges when ordering
     * n elements. Severely degrades in speed (up to O(n^2) in the worst case or close
     * to it, which can happen with bad input data.</p>
     *
     * @param   <E> the class of the objects in the list
     * @param   list the list to be sorted.
     * @throws  ClassCastException if the list contains elements that are not
     *         <i>mutually comparable</i> (for example, strings and integers).
     * @throws  IllegalArgumentException (optional) if the implementation
     *         detects that the natural ordering of the list elements is
     *         found to violate the {@link Comparable} contract
     * @see     Comparable
     */
    public static <E extends Comparable<? super E>> void quickSort(CustomArrayList<E> list) {
        sort(list.getArray(), 0, list.size() - 1, null);
    }

    /**
     * Sorts the specified list by quick sort algorithm, according to the order induced by
     * the specified {@link Comparator}. Furthermore, all elements in the list must be mutually
     * comparable by the specified {@link Comparator} (that is, {@code c.compare(e1, e2)} must
     * not throw a {@link ClassCastException} for any elements {@code e1} and {@code e2} in
     * the list).
     *
     * <p>The average execution time of quicksort is O(n*log n) exchanges when ordering
     * n elements. Severely degrades in speed (up to O(n^2) in the worst case or close
     * to it, which can happen with bad input data.</p>
     *
     * @param   <E> the class of the objects in the list
     * @param   list the list to be sorted.
     * @param   comparator the comparator to determine the order of the array. A {@code null}
     *          value indicates that the elements' {@linkplain Comparable natural ordering}
     *          should be used.
     * @throws ClassCastException if the array contains elements that are not
     *         <i>mutually comparable</i> using the specified comparator.
     * @throws IllegalArgumentException (optional) if the comparator is found to violate
     *         the {@link Comparator} contract
     * @see     Comparator
     */
    public static <E> void quickSort(CustomArrayList<E> list, Comparator<? super E> comparator) {
        sort(list.getArray(), 0, list.size() - 1, comparator);
    }

    /**
     * One of three private methods (the others are {@code calculateWallPosition} and {@code swap})
     * that together implement the quicksort algorithm.
     *
     * <p>This method takes as parameters the {@code array} to be sorted, the {@code first} and
     * the {@code last} index. First, we check the indices and continue only if there are still
     * elements to be sorted. We get the index of the sorted pivot and use it to recursively call
     * {@code calculateWallPosition} method with the same parameters as the sort() method, but
     * with different indices.</p>
     *
     * @param   array the array to be sorted.
     * @param   first the first index of array.
     * @param   last the last index of array.
     * @param   comparator the comparator to determine the order of the array. A {@code null}
     *          value indicates that the elements' {@linkplain Comparable natural ordering}
     *          should be used.
     * @throws ClassCastException if the array contains elements that are not
     *         <i>mutually comparable</i> (for example, strings and integers).
     * @throws IllegalArgumentException (optional) if the comparator is found to violate
     *         the {@link Comparator} contract or if the implementation detects that the natural
     *         ordering of the list elements is found to violate the {@link Comparable} contract
     * @see     Comparable
     * @see     Comparator
     */
    @SuppressWarnings("rawtypes")
    private static void sort(Object[] array, int first, int last, Comparator comparator) {
        if (first < last) {
            int wallIndex = calculateWallPosition(array, first, last, comparator);
            sort(array, first, wallIndex - 1, comparator);
            sort(array, wallIndex + 1, last, comparator);
        }
    }

    /**
     * Two of three private methods (the others are {@code sort} and {@code swap}) that together
     * implement the quicksort algorithm.
     *
     * <p>This method takes the last element as the pivot. Then, checks each element and {@code swaps}
     * it before the pivot if its value is smaller. By the end of the partitioning, all elements
     * less then the pivot are on the left of it and all elements greater then the pivot are on
     * the right of it. The pivot is at its final sorted position and the method returns this
     * position:</p>
     *
     * @param   array the array to be sorted.
     * @param   first the first index of array.
     * @param   last the last index of array.
     * @param   comparator the comparator to determine the order of the array. A {@code null}
     *          value indicates that the elements' {@linkplain Comparable natural ordering}
     *          should be used.
     * @return index of the wall that separates the left subarray and pivot
     * @throws ClassCastException if the array contains elements that are not
     *         <i>mutually comparable</i> (for example, strings and integers).
     * @throws IllegalArgumentException (optional) if the comparator is found to violate
     *         the {@link Comparator} contract or if the implementation detects that the natural
     *         ordering of the list elements is found to violate the {@link Comparable} contract
     * @see     Comparable
     * @see     Comparator
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static int calculateWallPosition(Object[] array, int first, int last, Comparator comparator) {
        Object pivot = array[last];
        int i = first - 1;
        if (comparator != null) {
            for (int j = first; j < last; j++)
                if (comparator.compare(array[j], pivot) <= 0)
                    swap(array, ++i, j);
        } else {
            for (int j = first; j < last; j++)
                if (((Comparable) array[j]).compareTo(pivot) <= 0)
                    swap(array, ++i, j);
        }
        swap(array, i + 1, last);
        return i + 1;
    }

    /**
     * Three of three private methods (the others are {@code sort} and {@code calculateWallPosition})
     * that together implement the quicksort algorithm.
     *
     * <p>This method takes as parameter the {@code array} in which it is necessary to swap elements
     * with indexes {@code i} and {@code j}.</p>
     *
     * @param   array the array to be sorted.
     * @param   i the first index of array.
     * @param   j the last index of array.
     *          value indicates that the elements' {@linkplain Comparable natural ordering}
     *          should be used.
     * @throws ClassCastException if the array contains elements that are not
     *         <i>mutually comparable</i> (for example, strings and integers).
     * @throws ArrayIndexOutOfBoundsException if the indexes {@code i} or {@code j} are either negative
     *          or greater than or equal to the size of the {@code array}.
     */
    private static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Returns the number of elements in this list
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the array that holds the elements
     *
     * @return the array that holds the elements
     */
    public Object[] getArray() {
        return data;
    }

    /**
     * Returns {@code true} if this list contains no elements
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns {@code true} if this list contains the specified element
     *
     * @param   obj checking if this element is in the list
     * @return  {@code true} if this list contains the specified element
     */
    public boolean isExist(Object obj) {
        return indexOf(obj) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if the list does not contain the element
     *
     * @param   obj checking if this element is in the list
     * @return  the index of the first occurrence of the specified element in this list,
     *          or -1 if the list does not contain the element
     */
    public int indexOf(Object obj) {
        if (obj != null) {
            for (int i = 0; i < size; i++)
                if (obj.equals(data[i]))
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (data[i] == null)
                    return i;
        }
        return -1;
    }

    /**
     * Gets the element at the specified position in this list
     *
     * @param  index index of the element
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    /**
     * Replaces the element at the specified {@code index} in this list with the specified {@code element}.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified {@code index}
     * @return the element previously at the specified {@code index}
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) data[index];
        data[index] = element;
        return oldValue;
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element element to be added to this list
     * @return {@code true} if the addition was successful
     */
    public boolean add(E element) {
        checkFreeSpace();
        data[size++] = element;
        return true;
    }

    /**
     * Inserts the specified {@code element} at the specified {@code index} in this list.
     * If there is an element currently at that position, shifts it and any subsequent elements
     * to the right.
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @return {@code true} if the insertion was successful
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public boolean add(int index, E element) {
        checkIndex(index);
        checkFreeSpace();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
        return true;
    }

    /**
     * Removes the element at the specified {@code index} in this list.
     * Shifts any subsequent elements to the left.
     *
     * @param index the index of the element to be removed
     * @return the element was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) data[index];
        shift(index);
        return oldValue;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if present.
     * If the list does not contain an element, it does not change.
     *
     * @param obj element to be removed from this list
     * @return {@code true} if the element was removed from the list, and {@code false}
     *          if the element was not in the list
     */
    public boolean remove(Object obj) {
        int index = 0;
        if (obj != null) {
            while (index < size) {
                if (obj.equals(data[index]))
                    break;
                index++;
            }
        } else {
            while (index < size) {
                if (data[index] == null)
                    break;
                index++;
            }
        }
        if (index == size)
            return false;
        shift(index);
        return true;
    }

    /**
     * Removes all elements from this list.
     */
    public void removeAll() {
        for (int i = 0; i < size; i++)
            data[i] = null;
        size = 0;
    }

    /**
     * Checks if the {@code index} is within the bounds of the range from
     * {@code 0} (inclusive) to {@code size} (inclusive).
     *
     * @param index the index
     * @throws IndexOutOfBoundsException if the {@code index} is out of bounds
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index);
    }

    /**
     * Checks for free space in the list.
     */
    private void checkFreeSpace() {
        if (size == data.length)
            increase();
    }

    /**
     * Increases list capacity by 1.5 times.
     */
    private void increase() {
        int oldCapacity = data.length;
        if (oldCapacity > 0)
            data = Arrays.copyOf(data, oldCapacity * 3 / 2 + 1);
        else
            data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Helper method to shift left by 1 position of all elements starting from position (index + 1)
     *
     * @param index index of the element, all elements to the right of which will be shifted to
     *             the left by 1 position
     */
    private void shift(int index) {
        if (size - 1 > index)
            System.arraycopy(data, index + 1, data, index, size - 1 - index);
        data[--size] = null;
    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param o object to compare for equality
     * @return  Returns true if and only if the specified object is also a list, both lists have
     *          the same size, and all corresponding pairs of elements in the two lists are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArrayList<?> that = (CustomArrayList<?>) o;
        return size == that.size && Arrays.equals(data, that.data);
    }

    /**
     * @return the hash code value for this list.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    /**
     * @return string representation of the list
     */
    @Override
    public String toString() {
        return "CustomArrayList{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }
}