package org.arraylist;

import java.util.Collection;
import java.util.Comparator;

/**
 * The MyList interface represents a generic list that stores elements of type T.
 * It provides various operations to manipulate and access the elements in the list.
 */
public interface MyList<T> {
    /**
     * Adds the specified element to the end of the list.
     * @param element the element to be added
     */
    void add(T element);

    /**
     * Inserts the specified element at the specified position in the list.
     * @param index   the index at which the element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    void add(int index, T element);

    /**
     * Adds all elements from the specified collection to the end of the list.
     * @param collection the collection containing the elements to be added
     */
    void addAll(Collection<? extends T> collection);

    /**
     * Returns the element at the specified position in the list.
     * @param index the index of the element to retrieve
     * @return the element at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    T get(int index);

    /**
     * Removes the element at the specified position in the list.
     * @param index the index of the element to be removed
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    T remove(int index);

    /**
     * Replaces the element at the specified position in the list with the specified element.
     * @param index   the index of the element to be replaced
     * @param element the replacement element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    void set(int index, T element);

    /**
     * Returns true if the list contains the specified element, false otherwise.
     * @param element the element to be checked for containment in the list
     * @return true if the list contains the specified element, false otherwise
     */
    boolean contains(T element);

    /**
     * Removes all elements from the list.
     */
    void clear();

    /**
     * Sorts the elements in the list according to the order induced by the specified
     * comparator. The elements are rearranged in ascending order.
     * @param comparator the comparator to determine the order of the elements
     */
    void sort(Comparator<? super T> comparator);

    /**
     * Returns true if the list is empty, false otherwise.
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    int size();
}
