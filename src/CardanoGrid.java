import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CardanoGrid {
    private int[] keysArr = {4, 11, 17, 26, 7, 16, 21, 31, 36, 41, 43, 50, 40, 45, 47, 64};


    public void encrypt(String message){
        printMatrix(parseStringToMatrix(parser(message)));
        System.out.println("//////////////////////////////////////");
        printMatrix( getMatrix( parseStringToMatrix( parser(message))));

    }

    private String parser(String input) {
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (input.length() > 64) {
            input = input.substring(0, 64);
        }
        while (input.length() < 64) {
            input += generateRandomChar();
        }
        return input.toLowerCase();

    }
    private char[][] getMatrix(char[][] strMatrix){
        int[][] integerMatrix = createMatrix();
        char[][] cryptoMatrix = new char[8][8];
        ArrayList keys = new ArrayList<>();
        keys.add(keysArr);
        for (int a = 0; a < 3; a++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    //TODO Изменить условие добавления эллемента
                    if (true){
                        char temp = strMatrix[i][j];
                        cryptoMatrix[i][j] = temp;
                    }
                }
            }
            rotateMatrix(integerMatrix);
        }
        return cryptoMatrix;
    }

    public char[][] parseStringToMatrix(String str) {
        char[][] matrix = new char[8][8];
        int index = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = str.charAt(index++);
            }
        }

        return matrix;
    }
    private int[][] createMatrix() {
        int[][] matrix = new int[8][8];
        int value = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }


    private char generateRandomChar() {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a');
        return randomChar;
    }
    private int[][] rotateMatrix(int[][] matrix) {
        int[][] rotatedMatrix = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rotatedMatrix[j][8 - 1 - i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }


    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

    }
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
