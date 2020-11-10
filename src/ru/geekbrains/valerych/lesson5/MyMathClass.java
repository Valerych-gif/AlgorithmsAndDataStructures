package ru.geekbrains.valerych.lesson5;

public class MyMathClass {
    public static void main(String[] args) {
        int[][] data = new int[][]
                {
                        {2, 5},
                        {3, 6},
                        {7, 3},
                        {5, 2},
                        {12, 3}
                };

        for (int[] pair : data) {
            System.out.printf("Число %d в степени %d равно %d\n", pair[0], pair[1], exp(pair[0], pair[1]));
        }

    }

    private static int exp(int number, int n) {
        if (n == 1) {
            return number;
        }

        if (n == 0) {
            return 1;
        }

        return number * exp(number, --n);
    }
}
