package org.example;

import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testGetSymbolReturnsCorrectSymbol() {
        Player p = new Player('X', new Scanner(System.in));
        assertEquals('X', p.getSymbol());
    }
}