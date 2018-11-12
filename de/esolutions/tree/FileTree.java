package de.esolutions.tree;

import java.io.File;

public class FileTree {
    FSRootNode root;
    private String prefix;

    public FileTree() {
        this(".");
    }
    public FileTree(String path) {
        try {
            root = new FSRootNode(new File(path));
            prefix = "";
        } catch (FSDirectory.IgnoredDirectory e) {

        }
    }
    public void printFileTree() {
            printTree(root);
    }
    private void printTree(Node c) {
        if (c == null) return;
        if (c instanceof NodeContainer) {
            System.out.println(prefix + c.getName() + "/ {");
            prefix += "  ";
            for (Node n: ((NodeContainer) c)) {
                printTree(n);
            }
            prefix = prefix.substring(0, prefix.length() - 2);
            System.out.println(prefix + "} [" + c.getName() + "]");
        } else {
            System.out.println(prefix + " - " + c.getName());
        }
    }
}
