package de.esolutions.tree;

import java.io.File;

public class FSNode implements Node {
    private final String name;
    private final NodeContainer parent;
    public FSNode(File f, NodeContainer d) {
        this.name = f.getName();
        this.parent = d;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public NodeContainer getParent() {
        return parent;
    }

    @Override
    public String getPath() {
        return parent.getPath() + PATH_SEPARATOR + getName();
    }
}
