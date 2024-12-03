package com.progtech_beadando_rsb.model;

import java.io.*;

public class Board {
    private final char[][] grid;

    public Board(int rows, int cols) {
        this.grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void display() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean placeToken(int column, char token) {

        if (column < 0 || column >= grid[0].length) {
            System.out.println("Érvénytelen oszlop: " + column);
            return false;
        }


        for (int row = grid.length - 1; row >= 0; row--) {
            if (grid[row][column] == '.') {
                grid[row][column] = token;
                return true;
            }
        }


        System.out.println("Az oszlop tele van, próbálj másikat.");
        return false;
    }


    public boolean checkWin(char token) {

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == token &&
                        grid[row][col + 1] == token &&
                        grid[row][col + 2] == token &&
                        grid[row][col + 3] == token) {
                    return true;
                }
            }
        }


        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length - 3; row++) {
                if (grid[row][col] == token &&
                        grid[row + 1][col] == token &&
                        grid[row + 2][col] == token &&
                        grid[row + 3][col] == token) {
                    return true;
                }
            }
        }


        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 0; col < grid[0].length - 3; col++) {
                if (grid[row][col] == token &&
                        grid[row + 1][col + 1] == token &&
                        grid[row + 2][col + 2] == token &&
                        grid[row + 3][col + 3] == token) {
                    return true;
                }
            }
        }


        for (int row = 0; row < grid.length - 3; row++) {
            for (int col = 3; col < grid[0].length; col++) {
                if (grid[row][col] == token &&
                        grid[row + 1][col - 1] == token &&
                        grid[row + 2][col - 2] == token &&
                        grid[row + 3][col - 3] == token) {
                    return true;
                }
            }
        }


        return false;
    }


    public int getCols() {
        return grid[0].length;
    }


    public void loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("A fájl nem található. Üres állapotot töltök.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < grid.length) {
                for (int col = 0; col < line.length() && col < grid[row].length; col++) {
                    grid[row][col] = line.charAt(col);
                }
                row++;
            }
            System.out.println("Tábla betöltve a fájlból.");
        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasása közben: " + e.getMessage());
        }
    }


    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (char[] row : grid) {
                writer.write(new String(row));
                writer.newLine();
            }
            System.out.println("Tábla elmentve a fájlba.");
        } catch (IOException e) {
            System.out.println("Hiba a fájl mentése közben: " + e.getMessage());
        }
    }
}