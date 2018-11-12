package de.esolutions.tree;

import java.util.Iterator;
import java.util.List;

public interface NodeContainer extends Node, Iterable<Node> {
    /**
     *
     * @return a list of this @code{NodeContainer}'s children
     */
    List<Node> getChildNodes();
    default Iterator<Node> iterator() {
        return getChildNodes().iterator();
    }
}
