package de.esolutions.tree;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class FileTreeTest {
    @Test
    void getSourceTree() throws NoSuchFileException, IllegalArgumentException {
        new FileTree("de").printFileTree();
    }
    @Test
    void openNonexistentPath() throws NoSuchFileException, IllegalArgumentException {
        assertThrows(NoSuchFileException.class, () -> new FileTree("michgibtsgarnicht"));
    }
    @Test
    void testForEachInBreadth() throws NoSuchFileException, IllegalArgumentException {
        new FileTree().getRoot().forEachInBreadth(fn -> System.out.println(fn.getPath()));
    }
    @Test
    void isRootEqualToItsParent() throws NoSuchFileException, IllegalArgumentException {
        FileTree ft = new FileTree(".");
        assertEquals(ft.getRoot().getPath(), ft.getRoot().getParent().getPath());
    }
}