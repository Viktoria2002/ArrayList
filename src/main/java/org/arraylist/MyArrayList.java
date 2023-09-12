package org.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

/**
 * MyArrayList is a class that implements the MyList interface and represents a dynamic array that can hold objects of type T.
 * @param <T> the type of objects to be stored in the array
 */
public class MyArrayList<T> implements MyList<T> {
    /** The default capacity of the underlying array. */
    private static final int DEFAULT_CAPACITY = 10;
    /** The array that stores the elements of the MyArrayList. */
    private Object[] elementData;
    /** The number of elements currently stored in the MyArrayList. */
    private int size;

    /**
     * This constructor creates an instance of MyArrayList with a default capacity of an array inside.
     */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * This constructor creates an instance of MyArrayList with a specific capacity of an array inside.
     * @param initialCapacity - the initial capacity of the list
     * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    /**
     * Adds the specified element to the end of the list.
     * @param element the element to be added
     */
    public void add(T element) {
        grow(size + 1);
        elementData[size] = element;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     * @param index   the index at which the element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        grow(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Adds all elements from the specified collection to the end of the list.
     * @param collection the collection containing the elements to be added
     */
    public void addAll(Collection<? extends T> collection) {
        Object[] objects = collection.toArray();
        int collectionSize = objects.length;
        if (collectionSize != 0) {
            grow(size + collectionSize);
            System.arraycopy(objects, 0, elementData, size, collectionSize);
            size += collectionSize;
        }
    }

    /**
     * Replaces the element at the specified position in the list with the specified element.
     * @param index   the index of the element to be replaced
     * @param element the replacement element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        elementData[index] = element;
    }

    /**
     * Removes the element at the specified position in the list.
     * @param index the index of the element to be removed
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removedElement;
    }

    /**
     * Returns the element at the specified position in the list.
     * @param index the index of the element to retrieve
     * @return the element at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    /**
     * Returns true if the list contains the specified element, false otherwise.
     * @param element the element to be checked for containment in the list
     * @return true if the list contains the specified element, false otherwise
     */
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all the elements from this list.
     */
    public void clear() {
        size = 0;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Sorts the elements in the list using the Quicksort algorithm with the specified comparator.
     * @param comparator the comparator to determine the order of the elements.
     */
    public void sort(Comparator<? super T> comparator) {
        quicksort(0, size - 1, comparator);
    }

    /**
     * Recursively performs the Quicksort algorithm on a portion of the list.
     * @param low        the starting index of the portion to be sorted.
     * @param high       the ending index of the portion to be sorted.
     * @param comparator the comparator to determine the order of the elements.
     */
    private void quicksort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quicksort(low, pivotIndex - 1, comparator);
            quicksort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Partitions the list by selecting a pivot element and rearranging the elements accordingly.
     * @param low        the starting index of the partition.
     * @param high       the ending index of the partition.
     * @param comparator the comparator to determine the order of the elements.
     * @return the final index of the pivot element after partitioning.
     */
    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = get(high);
        int i = low;
        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                swap(i, j);
                i++;
            }
        }
        swap(i, high);
        return i;
    }

    /**
     * Swaps the elements at the specified indices in the list.
     * @param i the index of the first element to be swapped.
     * @param j the index of the second element to be swapped.
     */
    private void swap(int i, int j) {
        Object temp = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = temp;
    }

    /**
     * Increases the capacity of the list if necessary, ensuring that it can accommodate a minimum number of elements.
     * @param minCapacity the desired minimum capacity.
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
