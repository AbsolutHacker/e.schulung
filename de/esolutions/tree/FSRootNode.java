package de.esolutions.tree;

import java.io.File;

public class FSRootNode extends FSDirectory {
    public FSRootNode(File dir) throws IgnoredDirectory {
        super(dir, null);
    }
    @Override
    public String getPath() {
        return getName();
    }
    @Override
    public NodeContainer getParent() {
        return this;
    }
}
