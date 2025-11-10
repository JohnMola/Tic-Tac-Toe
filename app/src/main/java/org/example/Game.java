package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Board board;
    private Scanner scanner;
    private Player playerX;
    private Player playerO;
    private Player startingPlayer;
    private int xWins = 0;
    private int oWins = 0;
    private int ties  = 0;

     public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
        playerX = new Player('X', scanner);
        playerO = new Player('O', scanner);
        startingPlayer = playerX;
    }
    
    public void start() {
        System.out.println("Welcome to TicTacToe!\n");

        boolean playAgain;
        do{
            board.reset();
            board.print();
            Player currentPlayer = startingPlayer;
            boolean gameEnded = false;
            Player loser = null;

            while (!gameEnded) {
                int move = currentPlayer.getMove(board);
                board.makeMove(move, currentPlayer.getSymbol());
                board.print();

                if (board.checkWinner(currentPlayer.getSymbol())) {
                    System.out.println("\n Player " + currentPlayer.getSymbol() + " wins! \n");
                    gameEnded = true;
                    if (currentPlayer == playerX) xWins++; else oWins++;
                    loser = (currentPlayer == playerX) ? playerO : playerX;
                    gameEnded = true;

                } else if (board.isFull()) {
                    System.out.println("\nIt's a tie!");
                    ties++;
                    loser = null;
                    gameEnded = true;

                } else {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            }
            if (loser != null) {
                startingPlayer = loser;
            }
            
            playAgain = askPlayAgain();
            printScoreboard();

            if (playAgain) {
            System.out.println("Press Enter to start the next round...");
            scanner.nextLine();
            }


        } while (playAgain);

        System.out.println("\nThanks for playing!");
        saveLogToDisk();
        scanner.close();
    }

    private void printScoreboard() {
        System.out.println("========== SCOREBOARD ==========");
        System.out.println("X wins : " + xWins);
        System.out.println("O wins : " + oWins);
        System.out.println("Ties   : " + ties);
        System.out.println("================================\n");
    }

    private void saveLogToDisk() {
        try (FileWriter fw = new FileWriter("gameLog.txt")) {
            fw.write("Final Game Wins!\n");
            fw.write("----------------\n");
            fw.write("X wins : " + xWins + "\n");
            fw.write("O wins : " + oWins + "\n");
            fw.write("Ties   : " + ties + "\n");
        } catch (IOException e) {
            System.out.println("Error writing game log to disk: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean askPlayAgain() {
        while (true) {
             System.out.print("\nDo you want to play again? (Y, N): ");
             String response = scanner.nextLine().trim();

            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                return false;
            } else {
                 System.out.println( "Invalid input. Please enter 'Y' to play again or 'N' to exit.");
            }
        }
    }
}
