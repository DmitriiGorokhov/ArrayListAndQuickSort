package com.gorokhov;

import org.junit.jupiter.api.Test;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayListTest {

    @Test
    public void newListShouldHaveZeroSize() {
        CustomArrayList<String> list = new CustomArrayList<>();
        assertEquals(0, list.size());
    }

    @Test
    public void newListShouldHaveDefaultTenCapacity() {
        CustomArrayList<String> list = new CustomArrayList<>();
        assertEquals(10, list.getArray().length);
    }

    @Test
    public void newListShouldHaveTheSpecifiedInitialCapacity() {
        int initCapacity = 1837;
        CustomArrayList<String> list = new CustomArrayList<>(initCapacity);
        assertEquals(initCapacity, list.getArray().length);
    }

    @Test
    public void capacityShouldNotHaveNegativeCapacity() {
        int initCapacity = -63;
        assertThrows(IllegalArgumentException.class, () -> new CustomArrayList<>(initCapacity));
    }

    @Test
    public void addingElementShouldWork() {
        String string = "Random string";
        CustomArrayList<String> list = new CustomArrayList<>();
        assertTrue(list.add(string));
    }

    @Test
    public void addingManyElementsShouldWork() {
        double num1 = 1.7435;
        double num2 = 28.93;
        double num3 = -925.06;
        CustomArrayList<Double> list = new CustomArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        assertEquals(num2, list.get(1));
    }

    @Test
    public void insertingTheElementShouldCauseAShiftOfSubsequent() {
        String firstStr = "First string";
        String secondStr = "Second string";
        String insertedStr = "Inserted string";
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(firstStr);
        list.add(secondStr);
        list.add(1, insertedStr);
        assertEquals(secondStr, list.get(2));
    }

    @Test
    public void theListShouldAutomaticallyGrowIfThereIsNotEnoughFreeSpace() {
        int num1 = 234;
        int num2 = 85534;
        int num3 = -13413;
        CustomArrayList<Integer> list = new CustomArrayList<>(2);
        list.add(num1);
        list.add(num2);
        assertTrue(list.add(num3));
    }

    @Test
    public void gettingElementShouldWork() {
        long num = 2134456756L;
        CustomArrayList<Long> list = new CustomArrayList<>();
        assertTrue(list.add(num));
    }

    @Test
    public void newElementShouldBePlaced() {
        String oldStr = "Old string";
        String newStr = "New string";
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(oldStr);
        list.set(0, newStr);
        assertEquals(newStr, list.get(0));
    }

    @Test
    public void oldElementShouldBeReturned() {
        String oldStr = "Old string";
        String newStr = "New string";
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(oldStr);
        assertEquals(oldStr, list.set(0, newStr));
    }

    @Test
    public void indexShouldBeWithinFilledArrayCells() {
        int num1 = 2635242;
        int num2 = 908;
        int num3 = -3453;
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(7));
    }

    @Test
    public void theElementShouldBeFound() {
        int num1 = 81;
        int num2 = -7181;
        int num3 = 6041;
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        assertTrue(list.isExist(num3));
    }

    @Test
    public void theIndexShouldBeReceived() {
        String firstStr = "First string";
        String secondStr = "Second string";
        String thirdStr = "Third string";
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(firstStr);
        list.add(secondStr);
        list.add(thirdStr);
        assertEquals(0, list.indexOf(firstStr));
    }

    @Test
    public void removingElementShouldWork() {
        String string = "String to delete";
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add(string);
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    public void removingAllElementsShouldWork() {
        byte b1 = 51;
        byte b2 = -107;
        byte b3 = 3;
        byte b4 = 94;
        byte b5 = -45;
        CustomArrayList<Byte> list = new CustomArrayList<>();
        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);
        list.removeAll();
        assertTrue(list.isEmpty());
    }

    @Test
    public void removingShouldReturnElement() {
        float num = -4579.654f;
        CustomArrayList<Float> list = new CustomArrayList<>();
        list.add(num);
        assertEquals(num, list.remove(0));
    }

    @Test
    public void removingByObjectShouldWork() {
        double num1 = 8213.123;
        double num2 = -7209.75;
        double num3 = 314.9;
        CustomArrayList<Double> list = new CustomArrayList<>();
        list.add(num1);
        list.add(num2);
        list.add(num3);
        assertTrue(list.remove(num2));
    }

    @Test
    public void deletingTheElementShouldShiftSubsequentElementsToTheLeftByOneStep() {
        int zero = 0;
        int one = 1;
        int two = 2;
        int three = 3;
        int four = 4;
        Integer[] expected = {0, 1, 3, 4, null};
        CustomArrayList<Integer> list = new CustomArrayList<>(5);
        list.add(zero);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.remove(2);
        assertArrayEquals(expected, list.getArray());
    }

    @Test
    public void listsShouldBeEquals() {
        String name = "Rick";
        String surname = "Morty";
        String hobby = "Basketball";
        CustomArrayList<String> firstList = new CustomArrayList<>();
        CustomArrayList<String> secondList = new CustomArrayList<>();
        firstList.add(name);
        firstList.add(surname);
        firstList.add(hobby);
        secondList.add(name);
        secondList.add(surname);
        secondList.add(hobby);
        assertEquals(firstList, secondList);
    }

    @Test
    public void hashcodeForListsShouldBeEquals() {
        String name = "Micky";
        String surname = "Mouse";
        String hobby = "Tennis";
        CustomArrayList<String> firstList = new CustomArrayList<>();
        CustomArrayList<String> secondList = new CustomArrayList<>();
        firstList.add(name);
        firstList.add(surname);
        firstList.add(hobby);
        secondList.add(name);
        secondList.add(surname);
        secondList.add(hobby);
        assertEquals(firstList.hashCode(), secondList.hashCode());
    }

    @Test
    public void theStringRepresentationOfTheListShouldBeDefined() {
        String name = "Donald";
        String surname = "Duck";
        String hobby = "Money";
        String expected = "CustomArrayList{data=[Donald, Duck, Money], size=3}";
        CustomArrayList<String> list = new CustomArrayList<>(3);
        list.add(name);
        list.add(surname);
        list.add(hobby);
        assertEquals(expected, list.toString());
    }

    @Test
    public void theListShouldBeSortedInNaturalOrder() {
        int num1 = 95;
        int num2 = -8240;
        int num3 = 1;
        int num4 = 215;
        int num5 = -633;
        CustomArrayList<Integer> list = new CustomArrayList<>(5);
        list.add(num1);
        list.add(num2);
        list.add(num3);
        list.add(num4);
        list.add(num5);
        CustomArrayList.quickSort(list);
        Integer[] expected = {num2, num5, num3, num1, num4};
        assertArrayEquals(expected, list.getArray());
    }

    @Test
    public void theListShouldBeSortedByComparatorOrder() {
        String str1 = "Age";
        String str2 = "Mango";
        String str3 = "Elephant";
        String str4 = "Tiger";
        String str5 = "Ambient";
        CustomArrayList<String> list = new CustomArrayList<>(5);
        list.add(str1);
        list.add(str2);
        list.add(str3);
        list.add(str4);
        list.add(str5);
        CustomArrayList.quickSort(list, Comparator.reverseOrder());
        String[] expected = {str4, str2, str3, str5, str1};
        assertArrayEquals(expected, list.getArray());
    }

    @Test
    public void theIncompleteListShouldBeSorted() {
        int num1 = 128;
        int num2 = -740;
        int num3 = 15;
        CustomArrayList<Integer> list = new CustomArrayList<>(5);
        list.add(num1);
        list.add(num2);
        list.add(num3);
        CustomArrayList.quickSort(list);
        Integer[] expected = {num2, num3, num1, null, null};
        assertArrayEquals(expected, list.getArray());
    }
}