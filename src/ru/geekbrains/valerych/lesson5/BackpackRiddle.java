package ru.geekbrains.valerych.lesson5;

public class BackpackRiddle {
    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item("Книга", 1, 600),
                new Item("Бинокль", 2, 5000),
                new Item("Аптечка", 4, 1500),
                new Item("Ноутбук", 2, 40000),
                new Item("Котелок", 1, 500)
        };

        BackpackRiddleSolver solver = new BackpackRiddleSolver(5);
        solver.pack(items);
        System.out.println(solver);
    }
}
