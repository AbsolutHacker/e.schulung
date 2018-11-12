package de.esolutions.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FSDirectory extends FSNode implements NodeContainer {
    List<Node> children;

    public FSDirectory(File dir, NodeContainer parent) {
        super(dir, parent);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Directory node '" + dir.getName() + "' must be a directory.");
        }
        children = new ArrayList<>();
        File[] files = dir.listFiles();
        for (File f : files) {
            if (!f.isDirectory()) {
                children.add(new FSNode(f, this));
            } else {
                if (!Arrays.asList(f.list()).contains(IGNOREFILE)) {
                    children.add(new FSDirectory(f, this));
                }
            }
        }
    }

    @Override
    public List<Node> getChildNodes() {
        return children;
    }
}
