package com.fzyszsz.study.tree;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author fuzy
 * create time 19-3-11-下午10:21
 */
@Slf4j
public class MyTree<T extends Comparable<T>> {
    private Node root;
    private int count = 0;
    private Object[] data;
    private int foot = 0;

    public void add(T t) {
        Node node = new Node(t);
        if (root == null) {
            root = node;
            count++;
        } else {
            root.add(node);
            count++;
        }
    }

    public void remove(T t) {
        root.remove(t);
    }

    public void edit(T old, T t) {
        root.edit(old, t);
    }

    public int size() {
        log.info(String.valueOf(root));
        return count;
    }

    public Object[] toArray() {
        if (count == 0) return null;
        data = new Object[count];
        foot = 0;
        root.toArray();
        return data;
    }


    @Data
    @NoArgsConstructor
    class Node {
        private T t;
        private Node left;
        private Node right;

        public Node(T t) {
            this.t = t;
        }

        public int compareTo(Node node) {
            return t.compareTo(node.t);
        }

        public void add(Node node) {
            if (this.compareTo(node) > 0) {
                if (this.left == null) this.left = node;
                else this.left.add(node);
            } else if (this.compareTo(node) < 0) {
                if (this.right == null) this.right = node;
                else this.right.add(node);
            } else {
                System.out.println("Binary Tree has exist this element - " + node);
                count--;
            }
        }

        public void remove(T t) {
            if (this.t.compareTo(t) == 0) {
                this.t = null;
                count--;
            } else if (this.t.compareTo(t) > 0) {
                this.left.remove(t);
            } else if (this.t.compareTo(t) < 0) {
                this.right.remove(t);
            }
        }

        public void toArray() {
            if (this.left != null) this.left.toArray();
            MyTree.this.data[foot++] = this.t;
            if (this.right != null) this.right.toArray();
        }

        public void edit(T old, T t) {
            remove(old);
            MyTree.this.add(t);
        }
    }

    public static void main(String[] args) {
        MyTree<String> tree = new MyTree<>();
        tree.add("a");
        tree.add("b");
        tree.add("c");
        tree.add("d");
        tree.add("e");
        tree.add("e");
        tree.add("f");
        tree.add("g");
        tree.add("h");
        tree.add("i");
        log.info(String.valueOf(tree.size()));
        log.info(Arrays.toString(tree.toArray()));
    }
}

