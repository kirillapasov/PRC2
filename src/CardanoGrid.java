import java.util.Random;

public class CardanoGrid {
    private final int[] keys = {4, 11, 17, 26, 7, 16, 21, 31, 36, 41, 43, 50, 40, 45, 47, 64};
    private int counter = 0;


    public void encrypt(String message){
        printMatrix(parseStringToMatrix(parser(message)));
        System.out.println("//////////////////////////////////////");
        printMatrix( getMatrix( parseStringToMatrix( parser(message))));

    }

    public String parser(String input) {
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (input.length() > 64) {
            input = input.substring(0, 64);
        }
        while (input.length() < 64) {
            input += generateRandomChar();
        }
        return input.toLowerCase();

    }
    public char[][] getMatrix(char[][] strMatrix){
        int[][] integerMatrix = createMatrix();
        char[][] cryptoMatrix = new char[8][8];
        for (int rotation = 0; rotation < 4; rotation++) {

            //first block
            for (int i1 = 0; i1 < 4; i1++ ){
                for (int j1 = 0; j1 < 4; j1++) {
                    for (int key : keys) {
                        if (integerMatrix[i1][j1] == key) {
                            //Todo Дописать остальные блоки по образцу со счётчиком.
                            int i11 = (counter ) / 8;
                            int j11 = (counter ) % 8;
                            char temp1 = strMatrix[i11][j11] ;
                            cryptoMatrix[i1][j1] = temp1;
                            counter++;
                        }
                    }
                }
            }
            //second block
            for (int i3 = 0; i3 < 4; i3++ ){
                for (int j3 = 4; j3 < 8; j3++) {
                    for (int key : keys) {
                        if (integerMatrix[i3][j3] == key) {
                            counter++;
                            char temp3 = strMatrix[i3][j3] ;
                            cryptoMatrix[i3][j3] = temp3;
                        }
                    }
                }
            }

            //thidrd block
            for (int i2 = 4; i2 < 8; i2++ ){
                for (int j2 = 0; j2 < 4; j2++) {
                    for (int key : keys) {
                        if (integerMatrix[i2][j2] == key) {
                            counter++;
                            char temp2 = strMatrix[i2][j2] ;
                            cryptoMatrix[i2][j2] = temp2;
                        }
                    }
                }
            }

            // fourth block
            for (int i4 = 4; i4 < 8; i4++ ){
                for (int j4 = 4; j4 < 8; j4++) {
                    for (int key : keys) {
                        if (integerMatrix[i4][j4] == key) {
                            counter++;
                            char temp4 = strMatrix[i4][j4] ;
                            cryptoMatrix[i4][j4] = temp4;
                        }
                    }
                }
            }

            integerMatrix = rotateMatrix(integerMatrix);
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
    public int[][] createMatrix() {
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
    private char[][] rotateMatrix(char[][] matrix) {
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                char top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
        return matrix;
    }


    public void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

    }
    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
