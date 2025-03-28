package bullerun;

import java.util.LinkedList;

class BinomialHeap {
    public static class Node {
        int key, degree;
        Node parent, child, sibling;

        Node(int key) {
            this.key = key;
            this.degree = 0;
            this.parent = this.child = this.sibling = null;
        }
    }

    private LinkedList<Node> heap;

    public BinomialHeap() {
        heap = new LinkedList<>();
    }

    private Node mergeTrees(Node b1, Node b2) {
        if (b1.key > b2.key) {
            Node temp = b1;
            b1 = b2;
            b2 = temp;
        }
        b2.parent = b1;
        b2.sibling = b1.child;
        b1.child = b2;
        b1.degree++;
        return b1;
    }

    private LinkedList<Node> union(LinkedList<Node> h1, LinkedList<Node> h2) {
        LinkedList<Node> newHeap = new LinkedList<>();
        while (!h1.isEmpty() || !h2.isEmpty()) {
            if (h1.isEmpty()) {
                newHeap.add(h2.removeFirst());
            } else if (h2.isEmpty()) {
                newHeap.add(h1.removeFirst());
            } else {
                if (h1.peek().degree <= h2.peek().degree) {
                    newHeap.add(h1.removeFirst());
                } else {
                    newHeap.add(h2.removeFirst());
                }
            }
        }
        return newHeap;
    }

    private void consolidate() {
        if (heap.isEmpty()) return;
        LinkedList<Node> newHeap = new LinkedList<>();
        Node curr = heap.removeFirst(), next;
        while (!heap.isEmpty()) {
            next = heap.removeFirst();
            if (curr.degree != next.degree || (!heap.isEmpty() && heap.peek().degree == curr.degree)) {
                newHeap.add(curr);
                curr = next;
            } else {
                curr = mergeTrees(curr, next);
            }
        }
        newHeap.add(curr);
        heap = newHeap;
    }

    public void insert(int key) {
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.heap.add(new Node(key));
        heap = union(heap, tempHeap.heap);
        consolidate();
    }

    public int getMin() {
        if (heap.isEmpty()) return -1;
        Node minNode = heap.getFirst();
        for (Node node : heap) {
            if (node.key < minNode.key) minNode = node;
        }
        return minNode.key;
    }

    public int extractMin() {
        if (heap.isEmpty()) return -1;
        Node minNode = heap.getFirst();
        for (Node node : heap) {
            if (node.key < minNode.key) minNode = node;
        }
        heap.remove(minNode);
        LinkedList<Node> childHeap = new LinkedList<>();
        Node child = minNode.child;
        while (child != null) {
            Node next = child.sibling;
            child.sibling = null;
            childHeap.addFirst(child);
            child = next;
        }
        heap = union(heap, childHeap);
        consolidate();
        return minNode.key;
    }

    public LinkedList<Node> getHeap() {
        return heap;
    }
}
