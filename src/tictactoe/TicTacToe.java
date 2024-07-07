/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tictactoe;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.util.Scanner;

public class TicTacToe {
    private final char[][] board;
    private char currentPlayer;

    // Constructor to initialize the game
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        currentPlayer = 'O';
        initializeBoard();
    }

    // Method to initialize the board with empty spaces
    public final void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Method to print the current board
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Method to check if the board is full
    public boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    // Method to check if there is a winner
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    // Helper methods for checkForWin
    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    // Method to switch player turns
    public void changePlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } 
        else if(currentPlayer == 'O') {
            currentPlayer = 'X';
        } 
        
    }

    // Method to make a move
    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        try (Scanner scanner = new Scanner(System.in)) {
            int row, col;
            while (!game.isBoardFull() && !game.checkForWin()) {
                game.printBoard();
                System.out.println("Player " + game.currentPlayer + ", enter your move (row [1-3] column [1-3]): ");
                
                // Adjusting for 0-based index internally, but 1-based for user input
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
                
                if (game.makeMove(row, col)) {
                    game.changePlayer();
                } else {
                    System.out.println("Invalid move! Try again.");
                }
            }   game.printBoard();
            if (game.checkForWin()) {
                System.out.println("Player " + game.currentPlayer + " wins!");
            } else {
                System.out.println("It's a draw!");
            }
        }
    }
}

