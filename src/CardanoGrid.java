import java.util.Random;

public class CardanoGrid {
    private final int[] keys = {4, 11, 17, 26, 7, 16, 21, 31, 36, 41, 43, 50, 40, 45, 47, 64};
    private int counter = 0;


    public String encrypt(String message) {
        String parsingMessage = parser(message);
        char[][] mask = parseStringToMatrix(parsingMessage);
        char[][] encryptMatrix = getEncryptMatrix(mask);
        String encryptedMessage = parseMatrixToString(encryptMatrix);
        return encryptedMessage;

    }

    //Todo создать метод дешифровки
    public String decrypt(String message){
        String parsingMessage = parser(message);
        char[][] mask = parseStringToMatrix(parsingMessage);
        char[][] decryptMatrix = getDecryptMatrix(mask);
        String decryptedMessage = parseMatrixToString(decryptMatrix);
        return decryptedMessage;
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
    private void zalupa(int iLower, int iUpper, int jLower, int jUpper, int[][] integerMatrix, char[][] cryptoMatrix, char[][] strMatrix){
        for (int i = iLower; i < iUpper; i++)
            for (int j = jLower; j < jUpper; j++)
                for (int key: keys)
                    if (integerMatrix[i][j] == key){
                        cryptoMatrix[i][j] = strMatrix[counter / 8][counter % 8];
                        counter++;
                    }
    }
    //Todo Переписать и оптимизировать алгоритм
    public char[][] getEncryptMatrix(char[][] strMatrix) {
        int[][] integerMatrix = createMatrix();
        char[][] cryptoMatrix = new char[8][8];
        for (int rotation = 0; rotation < 4; rotation++) {

            //first block
            zalupa(0,4,0,4,integerMatrix,cryptoMatrix,strMatrix);
            //second block
            zalupa(0,4,4,8,integerMatrix,cryptoMatrix,strMatrix);
            //third block
            zalupa(4,8,0,4,integerMatrix,cryptoMatrix,strMatrix);
            // fourth block
            zalupa(4,8,4,8,integerMatrix,cryptoMatrix,strMatrix);
            integerMatrix = rotateMatrix(integerMatrix);
        }
        return cryptoMatrix;
    }
    //Todo реализовать метод расшифровки матрицы
    public char[][]  getDecryptMatrix(char[][] strMatrix){
        int[][] integerMatrix = createMatrix();
        char[][] decryptoMatrix = new char[8][8];
        return decryptoMatrix;
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


    public String parseMatrixToString(char[][] encodeMatrix) {
        String encryptedMessage = "";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                encryptedMessage += encodeMatrix[i][j];

            }
        }

        return encryptedMessage;


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
