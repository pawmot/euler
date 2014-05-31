package com.pawmot.euler.utils.graphs;

import java.util.*;

public class DirectedGraph {
    List<Map<Integer, Integer>> adjLists = new ArrayList<>();

    public int addNewVertex() {
        adjLists.add(new HashMap<>());
        return adjLists.size() - 1;
    }

    public void addNewEdge(int b, int e, int w) {
        this.adjLists.get(b).put(e, w);
    }

    public Set<Integer> getAdjacent(int v) {
        return adjLists.get(v).keySet();
    }

    public int getWeight(int b, int e) {
        return adjLists.get(b).get(e);
    }
}
