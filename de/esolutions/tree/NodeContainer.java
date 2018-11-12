package de.esolutions.tree;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.ArrayList; // <- I don't like this at all, but what the h*

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
    @Override
    default void forEach(Consumer<? super Node> consumer) {
      for (Node n : getChildNodes()) {
        if (n instanceof NodeContainer) {
          ((NodeContainer) n).forEach(consumer);
        } else {
          consumer.accept(n);
        }
      }
    }
    default void forEachInBreadth(Consumer<? super Node> consumer) {
      forEachInBreadth(consumer, getChildNodes());
    }
    default void forEachInBreadth(Consumer<? super Node> consumer, List<? extends Node> queue) {
      // create queue of items to be consumed in the next recursion level,
      // i.e. items on the next deeper tree level
      List<Node> nextQueue;
      try {
        nextQueue = queue.getClass().newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
        nextQueue = new ArrayList<>();
      }
      for (Node n : queue) {
        if (n instanceof NodeContainer) {
          // enqueue NodeContainer n's children for the next lower execution level
          nextQueue.addAll(((NodeContainer) n).getChildNodes());
        } else {
          // consume leaf nodes on the current level
          consumer.accept(n);
        }
      }
      // condition is optional, but saves resources for queue allocation
      if (!nextQueue.isEmpty()) {
        forEachInBreadth(consumer, nextQueue);
      }
    }
}
