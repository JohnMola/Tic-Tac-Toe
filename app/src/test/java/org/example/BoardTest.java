package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    void testResetSetsNumbers1to9() {
        board.reset();
        for (int i = 1; i <= 9; i++) {
            assertEquals((char) ('0' + i), getCell(board, i));
        }
    }

    @Test
    void testValidMoveReturnsTrueWhenEmpty() {
        assertTrue(board.isValidMove(1));
    }

    @Test
    void testValidMoveReturnsFalseWhenOccupied() {
        board.makeMove(1, 'X');
        assertFalse(board.isValidMove(1));
    }

    @Test
    void testMakeMovePlacesSymbol() {
        board.makeMove(5, 'O');
        assertEquals('O', getCell(board, 5));
    }

    @Test
    void testIsFullReturnsTrueWhenBoardFull() {
        for (int i = 1; i <= 9; i++) {
            board.makeMove(i, (i % 2 == 0) ? 'X' : 'O');
        }
        assertTrue(board.isFull());
    }

    @Test
    void testCheckWinnerForRow() {
        board.makeMove(1, 'X');
        board.makeMove(2, 'X');
        board.makeMove(3, 'X');
        assertTrue(board.checkWinner('X'));
    }

    // Helper using reflection to peek into private cells
    private char getCell(Board board, int index) {
        try {
            var field = Board.class.getDeclaredField("cells");
            field.setAccessible(true);
            char[] cells = (char[]) field.get(board);
            return cells[index];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}