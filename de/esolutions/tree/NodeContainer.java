package de.esolutions.tree;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public interface NodeContainer extends Node, Iterable<Node> {
    String IGNOREFILE = "_ignore";
    /**
     *
     * @return a list of this @code{NodeContainer}'s children
     */
    List<Node> getChildNodes();
    default Iterator<Node> iterator() {
        return getChildNodes().iterator();
    }
    default void forEachInBreadth(Consumer<Node> consumer) {
        for (Node n : getChildNodes()) {
            if (n instanceof NodeContainer) {
                // add children to remaining queue
            }
            consumer.accept(n);
        }
    }
}
