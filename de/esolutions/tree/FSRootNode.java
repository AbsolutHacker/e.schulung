package de.esolutions.tree;

import java.io.File;
import java.nio.file.NoSuchFileException;

public class FSRootNode extends FSDirectory {
    public FSRootNode(File dir) throws NoSuchFileException, IllegalArgumentException {
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
