package de.esolutions.tree;

import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class FileTreeTest {
    @Test
    void getSourceTree() {
        new FileTree("src").printFileTree();
    }
    @Test
    void openNonexistentPath() {
        assertThrows(NoSuchFileException.class, () -> new FileTree("michgibtsgarnicht"));
    }
}