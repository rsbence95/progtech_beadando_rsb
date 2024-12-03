package com.progtech_beadando_rsb.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testPlaceToken() {
        Board board = new Board(6, 7);
        assertTrue(board.placeToken(1, 'S'));
        assertFalse(board.placeToken(8, 'S'));
    }

    @Test
    public void testSaveAndLoadBoard() {
        Board board = new Board(6, 7);
        board.placeToken(1, 'S');
        board.saveToFile("test_board.txt");

        Board newBoard = new Board(6, 7);
        newBoard.loadFromFile("test_board.txt");


        assertEquals(board.getCols(), newBoard.getCols());
    }
}
