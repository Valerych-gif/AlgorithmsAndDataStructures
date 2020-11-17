package ru.geekbrains.valerych.lesson6;

import java.util.Random;

public class Test6 {

    private static final int DEPTH_OF_TREE = 4;
    private static final int TREES_QUANTITY = 20;

    public static void main(String[] args) {
//        testTree();
//        testRemoveElement();
        testBalanceTree();
    }

    private static void testBalanceTree() {
        Tree<Integer> tree;
        int balancedTrees = 0;
        for (int i = 0; i < TREES_QUANTITY; i++) {
            tree = new TreeImpl<>(DEPTH_OF_TREE);

            int attempts = (int) Math.pow(2, DEPTH_OF_TREE) - 1;
            Random random = new Random();
            for (int j = 0; j < attempts; j++) {
                tree.add(random.nextInt(50) - 25);
            }
            if (tree.isBalanced()){
                balancedTrees++;
            }
            System.out.println("Сбаллансировано ли дерево №" + (i+1) + ": " + tree.isBalanced());
        }
        int balancedPercent = balancedTrees*100/TREES_QUANTITY;
        System.out.printf("Сбаллансированных деревьев - %d%%, несбаллансированных - %d%%", balancedPercent, 100 - balancedPercent);

    }

    private static void testRemoveElement() {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(25);
        tree.add(66);
        tree.add(15);
        tree.add(5);
        tree.add(20);
        tree.add(45);
        tree.add(30);
        tree.add(55);
        tree.add(32);

        tree.remove(25);
        tree.display();
    }

    private static void testTree() {
        Tree<Integer> tree = new TreeImpl<>();
        tree.add(60);
        tree.add(50);
        tree.add(66);
        tree.add(40);
        tree.add(55);
        tree.add(70);
        tree.add(31);
        tree.add(45);
        tree.add(67);
        tree.add(81);

        System.out.println("Find 70: " + tree.contains(70));
        System.out.println("Find 700: " + tree.contains(700));

        tree.display();
//        tree.traverse(Tree.TraverseMode.IN_ORDER, System.out::println);
//        tree.traverse(Tree.TraverseMode.PRE_ORDER, System.out::println);
//        tree.traverse(Tree.TraverseMode.POST_ORDER, System.out::println);
    }
}
