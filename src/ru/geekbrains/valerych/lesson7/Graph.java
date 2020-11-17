package ru.geekbrains.valerych.lesson7;

import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Invalid label for vertex");
        }

        adjMat[startIndex][endIndex] = true;
        adjMat[endIndex][startIndex] = true;
    }

    public void addEdges(String startLabel, String secondLabel, String... others) {
        addEdge(startLabel, secondLabel);
        for (String other : others) {
            addEdge(startLabel, other);
        }
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return  i;
            }
        }
        return -1;
    }


    public int getVertexSize() {
        return vertexList.size();
    }

    public void display() {
        for (int i = 0; i < getVertexSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getVertexSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. Depth-first search, DFS
     *
     * @param startLabel
     */
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, stack);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            }
            else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    /**
     * англ. breadth-first search, BFS
     *
     * @param startLabel
     */
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, queue);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            }
            else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    public void printBfsPath(String startLabel, String endLabel){
        Vertex vertex = bfsFind(startLabel, endLabel);
        if (vertex==null){
            return;
        }

        Stack<Vertex> stack = new Stack<>();

        while (vertex.getPreviousVisitedVertex()!=null){
            stack.push(vertex);
            vertex = vertex.getPreviousVisitedVertex();
        }

        System.out.printf("Путь от вершины '%s' до вершины '%s'\n", startLabel, endLabel);
        System.out.print(startLabel);
        while (!stack.empty()){
            System.out.print("->" + stack.pop().getLabel());
        }
        System.out.println();
    }

    private Vertex bfsFind(String startLabel, String endLabel) {
        resetVertexState();
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);
        if (startIndex == -1||endIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, queue, null);
        while (!queue.isEmpty()) {
            Vertex previous = queue.peek();
            vertex = getNearUnvisitedVertex(previous);
            if (vertex != null) {
                visitVertex(vertex, queue, previous);
                if (vertex.getLabel().equals(endLabel)){
                    return vertex;
                }
            }
            else {
                queue.remove();
            }
        }
        return null;
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
            vertex.setPreviousVisitedVertex(null);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getVertexSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).getVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        System.out.println(vertex);
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        System.out.println(vertex);
        queue.add(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Vertex vertex, Queue<Vertex> queue, Vertex previous) {
        queue.add(vertex);
        vertex.setVisited(true);
        vertex.setPreviousVisitedVertex(previous);
    }
}
