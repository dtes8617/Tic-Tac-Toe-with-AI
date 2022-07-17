package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the cells: ");
//        String initSetup = scanner.next();
        char[][] matrix = new char[3][3];
        String status = "Game not finished";
        String level = "easy";
//        int countX = 0;
//        int countO = 0;

//        for (int i = 0; i < initSetup.length(); i++) {
//            int rowIndex = i / 3;
//            int columnIndex = i % 3;
//
//            if (!Objects.equals('_',initSetup.charAt(i))) {
//                matrix[rowIndex][columnIndex] = initSetup.charAt(i);
//                if (matrix[rowIndex][columnIndex] == 'O') {
//                    countO += 1;
//                } else {
//                    countX += 1;
//                }
//            } else {
//                matrix[rowIndex][columnIndex] = ' ';
//            }
//        }
        for (int i = 0; i < 3; i++) {
            Arrays.fill(matrix[i], ' ');
        }
        printStatus(matrix);

        while (status == "Game not finished") {
            matrix = takeNextMove(scanner, matrix);
            printStatus(matrix);
            status = checkResult(matrix);

            if (status == "Game not finished") {
                matrix = aiTakeAction(matrix, level);
                printStatus(matrix);
                status = checkResult(matrix);
            } else {
                break;
            }
        }
    }

    private static char[][] aiTakeAction(char[][] matrix, String level) {
        System.out.printf("Making move level \"%s\"%n", level);
        Random random = new Random();
        boolean stepSearching = true;
        int rowPicked = 0;
        int columnPicked = 0;

        if (level == "easy") {
            while (stepSearching) {
                rowPicked = random.nextInt(3);
                columnPicked = random.nextInt(3);

                if (matrix[rowPicked][columnPicked] == ' ') {
                    stepSearching = false;
                }
            }
        }

        matrix[rowPicked][columnPicked] = 'O';
        return matrix;
    }

    private static char[][] takeNextMove(Scanner scanner, char[][] matrix) {
        int rowPicked, columnPicked;

        while (true) {
            System.out.println("Enter the coordinates: ");
            if (scanner.hasNextInt()) {
                rowPicked = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.next();
                continue;
            }

            if (scanner.hasNextInt()) {
                columnPicked = scanner.nextInt();
            } else {
                System.out.println("You should enter numbers!");
                scanner.next();
                continue;
            }

            if (rowPicked <= 0 || rowPicked > 3 || columnPicked <= 0 || columnPicked > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (matrix[rowPicked-1][columnPicked-1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            break;
        }

        matrix[rowPicked-1][columnPicked-1] = 'X';
        return matrix;
    }

    private static String checkResult(char[][] matrix) {
        char[] rowWin = new char[] {'U', 'U', 'U'};
        char[] colWin = new char[] {'U', 'U', 'U'};
        char[] diagWin = new char[] {'U', 'U'};
        boolean gameFinished = true;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == ' ') {
                    gameFinished = false;
                }
                if (rowWin[i] == 'U') {
                    rowWin[i] = matrix[i][j];
                } else if (rowWin[i] != matrix[i][j]) {
                    rowWin[i] = 'N';
                } else if (j==2 && matrix[i][j] != ' ') {
                    System.out.printf("%s wins\n", matrix[i][j]);
                    return String.format("%s wins", matrix[i][j]);
                }

                if (colWin[j] == 'U') {
                    colWin[j] = matrix[i][j];
                } else if (colWin[j] != matrix[i][j]) {
                    colWin[j] = 'N';
                } else if (i==2 && matrix[i][j] != ' ') {
                    System.out.printf("%s wins\n", matrix[i][j]);
                    return String.format("%s wins", matrix[i][j]);
                }

                if (i == j) {
                    if (diagWin[0] == 'U') {
                        diagWin[0] = matrix[i][j];
                    } else if (diagWin[0] != matrix[i][j]) {
                        diagWin[0] = 'N';
                    } else if (i==2 && matrix[i][j] != ' ') {
                        System.out.printf("%s wins\n", matrix[i][j]);
                        return String.format("%s wins", matrix[i][j]);
                    }
                }

                if (i == (2-j)) {
                    if (diagWin[1] == 'U') {
                        diagWin[1] = matrix[i][j];
                    } else if (diagWin[1] != matrix[i][j]) {
                        diagWin[1] = 'N';
                    } else if (i==2 && matrix[i][j] != ' ') {
                        System.out.printf("%s wins\n", matrix[i][j]);
                        return String.format("%s wins", matrix[i][j]);
                    }
                }
            }
        }
        if (gameFinished){
            System.out.print("Draw\n");
            return "Draw";
        } else {
            System.out.print("Game not finished\n");
            return "Game not finished";
        }

    }

    private static void printStatus(char[][] matrix) {
        System.out.println("---------");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == 0) { System.out.print("| "); }
                System.out.printf("%s ", matrix[i][j]);
                if (j==2) { System.out.print("|\n"); }
            }
        }
        System.out.println("---------");
    }
}
