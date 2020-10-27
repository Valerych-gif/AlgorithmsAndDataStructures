package ru.geekbrains.valerych.lesson2;

import java.util.Random;

public class TestArray {

    private static final int SIZE_OF_ARRAY = 100_000;
    private static final Integer RANGE_OF_INTEGER_VALUE = 1_000_000;

    private static long startTimerValue, stopTimerValue;

    public static void main(String[] args) {
        ArrayImpl<Integer> arrayForSelectSort = createLargeIntegerArray();
        ArrayImpl<Integer> arrayForInsertSort = new ArrayImpl<>(arrayForSelectSort);
        ArrayImpl<Integer> arrayForBubbleSort = new ArrayImpl<>(arrayForSelectSort);

        showSelectSortDuration(arrayForSelectSort);
        showInsertSortDuration(arrayForInsertSort);
        showBubbleSortDuration(arrayForBubbleSort);
    }


    private static ArrayImpl<Integer> createLargeIntegerArray() {
        ArrayImpl<Integer> array = new ArrayImpl<>(SIZE_OF_ARRAY);
        Random random = new Random();

        for (int i = 0; i < SIZE_OF_ARRAY; i++) {
            array.add(random.nextInt(RANGE_OF_INTEGER_VALUE));
        }
        return array;
    }

    private static void showSelectSortDuration(ArrayImpl<Integer> arrayForSelectSort) {
        startTimerValue = System.currentTimeMillis();
        arrayForSelectSort.sortSelect();
        stopTimerValue = System.currentTimeMillis();
        System.out.println("Сортировка выбором произведена за " + (stopTimerValue - startTimerValue) + " мс");
    }

    private static void showInsertSortDuration(ArrayImpl<Integer> arrayForInsertSort) {
        startTimerValue = System.currentTimeMillis();
        arrayForInsertSort.sortInsert();
        stopTimerValue = System.currentTimeMillis();
        System.out.println("Сортировка вставкой произведена за " + (stopTimerValue - startTimerValue) + " мс");
    }

    private static void showBubbleSortDuration(ArrayImpl<Integer> arrayForBubbleSort) {
        startTimerValue = System.currentTimeMillis();
        arrayForBubbleSort.sortBubble();
        stopTimerValue = System.currentTimeMillis();
        System.out.println("Сортировка пузырьком произведена за " + (stopTimerValue - startTimerValue) + " мс");
    }
}
