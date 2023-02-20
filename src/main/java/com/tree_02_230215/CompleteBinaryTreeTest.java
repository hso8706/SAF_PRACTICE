package com.tree_02_230215;

public class CompleteBinaryTreeTest {
    public static void main(String[] args) {
        int size = 9;
        CompleteBinaryTree<Character> tree = new CompleteBinaryTree<>(size);

        for (int i = 0; i < size; i++) {
            tree.add((char)(65+i));
        }

//        tree.bfs();
//        tree.bfs2();
        tree.dfsByPreOrder();
//        tree.dfsByInOrder();
//        tree.dfsByPostOrder();
        tree.dfs();
    }
}