package ru.geekbrains.valerych.lesson5;

import java.util.Arrays;

public class BackpackRiddleSolver {

    private int volume;
    private int currentCost = 0;
    private Item[] packedItems;

    public BackpackRiddleSolver(int volume) {
        this.volume = volume;
    }

    public Item[] pack(Item[] items) {
        packedItems = new Item[items.length];
        tryToPack(items);
        return packedItems;
    }

    private void tryToPack(Item[] items) {
        if (items.length == 0) {
            return;
        }

        for (int i = 0; i < items.length; i++) {
            checkSolve(items);
            tryToPack(Arrays.copyOf(items, items.length - 1));
            Item tmp = items[0];
            for (int j = 0; j < items.length - 1; j++) {
                items[j] = items[j + 1];
            }
            items[items.length - 1] = tmp;
        }
    }

    private void checkSolve(Item[] items) {
        int weight = 0;
        int cost = 0;
        for (Item item : items) {
            weight += item.getWeight();
            cost += item.getCost();
        }
        if (cost > currentCost && weight <= volume) {
            currentCost = cost;
            packedItems = items.clone();
        }
    }

    public String toString(){
        StringBuilder solve = new StringBuilder();
        solve
                .append("В рюкзак надо взять:");

        for (Item item : packedItems) {
            solve
                    .append("\n")
                    .append(item.getName())
                    .append("\n\tВес: ")
                    .append(item.getWeight())
                    .append("\n\tСтоимость: ")
                    .append(item.getCost());
        }
        return solve.toString();
    }
}
