package org.example;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player playerX;
    private Player playerO;
    private Scanner scanner;

     public Game() {
        scanner = new Scanner(System.in);
        board = new Board();
        playerX = new Player('X', scanner);
        playerO = new Player('O', scanner);
    }
    
    public void start() {
        System.out.println("Welcome to TicTacToe!\n");

        boolean playAgain;
        do {
            board.reset();
            board.print();
             Player currentPlayer = playerX;
            boolean gameEnded = false;

            while (!gameEnded) {
                 int move = currentPlayer.getMove(board);
                board.makeMove(move, currentPlayer.getSymbol());
                board.print();

                if (board.checkWinner(currentPlayer.getSymbol())) {
                    System.out.println("\n Player " + currentPlayer.getSymbol() + " wins! \n");
                    gameEnded = true;
                } else if (board.isFull()) {
                    System.out.println("\nIt's a tie!");
                    gameEnded = true;
                } else {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            }
            playAgain = askPlayAgain();
        } while (playAgain);

         System.out.println("\nThanks for playing!");
        scanner.close();
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
