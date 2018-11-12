package de.esolutions.tree;

public interface Node {
    String PATH_SEPARATOR = "/";
    /**
     *
     * @return this node's name
     */
    String getName();

    /**
     *
     * @return this node's parent @code{NodeContainer}
     */
    NodeContainer getParent();

    /**
     *
     * @return path inside the tree, fully qualified up to its root @code{NodeContainer}, separated by @code{PATH_SEPARATOR}
     */
    String getPath();
}
