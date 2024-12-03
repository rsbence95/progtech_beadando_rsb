package com.progtech_beadando_rsb;

import com.progtech_beadando_rsb.model.Board;
import com.progtech_beadando_rsb.model.Player;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final String fileName = "game_state.txt";

    public Game(int rows, int cols, String playerName) {
        this.board = new Board(rows, cols);
        this.player1 = new Player(playerName, 'S');
        this.player2 = new Player("Gep", 'P');
    }

    public void start() {

        board.loadFromFile(fileName);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Udvozollek a jatekban!");


        System.out.print("Add meg a neved: ");
        String playerName = scanner.nextLine();
        player1.setName(playerName);

        boolean isPlayer1Turn = true;

        while (true) {
            Player currentPlayer = isPlayer1Turn ? player1 : player2;
            System.out.println(currentPlayer.getName() + " te vagy a soros.");

            int column;
            if (isPlayer1Turn) {
                System.out.print("Valassz oszlopot (1-" + board.getCols() + "): ");
                column = scanner.nextInt() - 1;
            } else {
                column = new Random().nextInt(board.getCols());
                System.out.println("A gep oszlopot valasztott: " + (column + 1));
            }

            if (!board.placeToken(column, currentPlayer.getToken())) {
                System.out.println("Ervenytelen lepes. Probald ujra.");
                continue;
            }

            board.display();

            if (board.checkWin(currentPlayer.getToken())) {
                System.out.println(currentPlayer.getName() + " Gyoztes!");
                break;
            }

            isPlayer1Turn = !isPlayer1Turn;
        }


        board.saveToFile(fileName);
        scanner.close();
    }
}
