package de.esolutions.tree;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FSDirectory extends FSNode implements NodeContainer {
    List<Node> children;

    public FSDirectory(File dir, NodeContainer parent) throws IgnoredDirectory {
        super(dir, parent);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Directory node '" + dir.getName() + "' must be a directory.");
        }
        children = new ArrayList<>();
        File[] files = dir.listFiles();
        if (Arrays.asList(dir.list()).contains("_ignore")) {
            throw new IgnoredDirectory();
        }
        for (File f : files) {
            if (!f.isDirectory()) {
                children.add(new FSNode(f, this));
            } else {
                try {
                    children.add(new FSDirectory(f, this));
                } catch (IgnoredDirectory e) {
                    continue;
                }
            }
        }
    }

    @Override
    public List<Node> getChildNodes() {
        return children;
    }

    public class IgnoredDirectory extends Exception {
        @Override
        public synchronized Throwable fillInStackTrace() {
            return this;
        }
    }
}
