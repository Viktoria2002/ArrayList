package org.arraylist;

import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Unit test for simple MyArrayList.
 */
public class MyArrayListTest {
    private MyArrayList<Integer> list;

    /**
     * Sets up the test environment before each test case.
     */
    @Before
    public void setUp() {
        list = new MyArrayList<>();
    }

    /**
     * Test case for creating a list with a specific array capacity.
     */
    @Test
    public void testCreateListWithSpecificArrayCapacity() {
        list = new MyArrayList<>(8);

        assertEquals(8, getTestArray().length);
    }

    /**
     * Test case for creating a list with a wrong capacity.
     */
    @Test
    public void testCreateListWithWrongCapacity() {
        assertThrows(IllegalArgumentException.class, () -> list = new MyArrayList<>(-2));
    }

    /**
     * Test case for adding elements to the list.
     */
    @Test
    public void testAdd() {
        list.add(1);
        list.add(2);

        assertEquals(2, list.size());
    }

    /**
     * Test case for adding an element at a specific index in the list.
     */
    @Test
    public void testAddElementByIndex() {
        fillTestArray(15, 69, 58, 78, 68);

        list.add(2, 10);

        Object[] internalArray = getTestArray();

        assertEquals(6, list.size());
        assertEquals(10, internalArray[2]);
    }

    /**
     * Test case for adding an element at a negative index,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testAddElementByNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
    }

    /**
     * Test case for adding an element at an index larger than the list size,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testAddElementByIndexLargerThanListSize() {
        setTestSize(4);

        assertThrows(IndexOutOfBoundsException.class, () -> list.add(5, 10));
    }

    /**
     * Test case for adding an element at an index equal to the list size.
     */
    @Test
    public void testAddElementByIndexEqualToSize() {
        fillTestArray(1, 2, 3, 4, 5);

        list.add(5, 10);

        Object[] internalArray = getTestArray();

        assertEquals(6, list.size());
        assertEquals(10, internalArray[5]);
    }

    /**
     * Test case for adding all elements from a collection to the list.
     */
    @Test
    public void testAddAll() {
        fillTestArray(15, 69, 58, 78);
        List<Integer> elements = Arrays.asList(10, 20, 30);

        list.addAll(elements);

        assertEquals(7, list.size());
    }

    /**
     * Test case for setting an element at a specific index in the list.
     */
    @Test
    public void testSetElementByIndex() {
        fillTestArray(15, 69, 58, 78);
        Object[] internalArray = getTestArray();

        list.set(2, 10);

        assertEquals(10, internalArray[2]);
        assertEquals(4, list.size());
    }

    /**
     * Test case for setting an element at an index that is out of bounds,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testSetAtIndexOutOfBounds() {
        fillTestArray(15, 69, 58, 78);

        assertThrows(IndexOutOfBoundsException.class, () -> list.set(4, 10));
    }

    /**
     * Test case for setting the first element when the list is empty,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testSetFirstElementOnEmptyTree() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 10));
    }

    /**
     * Test case for removing an element at a specific index in the list.
     */
    @Test
    public void testRemoveElementByIndex() {
        fillTestArray(15, 69, 58, 78, 100);
        Object[] internalArray = getTestArray();

        int removedElement = list.remove(2);

        assertEquals(78, internalArray[2]);
        assertEquals(58, removedElement);
        assertEquals(4, list.size());
    }

    /**
     * Test case for removing an element at an index that equals the list size,
     * which shose for removing an element at an index that equals the list size,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testRemoveElementByIndexThrowsExceptionWhenIndexEqualsSize() {
        fillTestArray(15, 69, 58, 78);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(4));
    }

    /**
     * Test case for removing an element at an index that is out of bounds,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testRemoveElementByIndexThrowsExceptionWhenIndexIsOutOfBounds() {
        fillTestArray(15, 69, 58, 78, 100);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(6));
    }

    /**
     * Test case for getting elements by index in the list.
     */
    @Test
    public void testGetElementsByIndex() {
        fillTestArray(10, 15, 20);
        int[] expected = {10, 15, 20};

        for (int i = 0; i < expected.length; i++) {
            assertEquals(Optional.of(expected[i]), Optional.ofNullable(list.get(i)));
        }
        assertEquals(3, list.size());
    }

    /**
     * Tests the sort(Comparator<? super T> comparator) method of MyArrayList.
     * Sorts the list using a comparator and checks the order of the elements.
     */
    @Test
    public void testSort() {
        fillTestArray(15, 10, 20);
        Object[] internalArray = getTestArray();
        int[] expected = {10, 15, 20};

        list.sort(Integer::compareTo);

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], internalArray[i]);
        }
    }

    /**
     * Test case for getting an element at an index that is out of bounds,
     * which should throw an IndexOutOfBoundsException.
     */
    @Test
    public void testGetByIndexThrowsExceptionWhenIndexIsOutOfBounds() {
        fillTestArray(10, 15, 20);

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    /**
     * Test case for checking if the list contains a specific element when the list is empty.
     */
    @Test
    public void testContainsOnEmptyList() {
        assertFalse(list.contains(8));
    }

    /**
     * Test case for checking if the list contains a specific element.
     */
    @Test
    public void testContainsElement() {
        fillTestArray(15, 69, 58, 78, 100);

        assertTrue(list.contains(58));
    }

    /**
     * Test case for checking if the list does not contain a specific element.
     */
    @Test
    public void testContainsNotExistingElement() {
        fillTestArray(15, 69, 58, 78, 100);

        assertFalse(list.contains(200));
    }

    /**
     * Test case for clearing the list, which should make the size of the list zero.
     */
    @Test
    public void testClear() {
        setTestSize(100);

        list.clear();

        assertEquals(0, list.size());
    }

    /**
     * Test case for checking if the list is not empty.
     */
    @Test
    public void testIsNotEmpty() {
        setTestSize(3);

        assertFalse(list.isEmpty());
    }

    /**
     * Test case for checking the size of an empty list.
     */
    @Test
    public void testSizeOfEmptyArrayWrapper() {
        assertEquals(0, list.size());
    }

    /**
     * Test case for checking the size of the list.
     */
    @Test
    public void testSize() {
        setTestSize(3);

        assertEquals(3, list.size());
    }

    /**
     * Sets the size of the test array through reflection.
     * @param size the size to set
     */
    @SneakyThrows
    private void setTestSize(int size) {
        Field sizeField = list.getClass().getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(list, size);
    }

    /**
     * Retrieves the test array using reflection.
     * @return the test array
     */
    @SneakyThrows
    private Object[] getTestArray() {
        Field field = list.getClass().getDeclaredField(getTestArrayName());
        field.setAccessible(true);
        return (Object[]) field.get(list);
    }

    /**
     * Retrieves the name of the test array field from the class.
     * @return the name of the test array field
     */
    private String getTestArrayName() {
        Field[] fields = list.getClass().getDeclaredFields();
        String name = null;
        for (Field field : fields) {
            if (field.getType().isArray()) {
                field.setAccessible(true);
                name = field.getName();
            }
        }
        return name;
    }

    /**
     * Fills the test array with the provided elements.
     * @param elements the elements to fill the test array with
     */
    @SneakyThrows
    private void fillTestArray(Object... elements) {
        Field arrayField = list.getClass().getDeclaredField(getTestArrayName());
        Field sizeField = list.getClass().getDeclaredField("size");
        arrayField.setAccessible(true);
        sizeField.setAccessible(true);
        arrayField.set(list, elements);
        sizeField.set(list, elements.length);
    }
}
