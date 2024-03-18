import java.util.Random;

public class CardanoGrid {
    private final int[] keys = {4, 11, 17, 26, 7, 16, 21, 31, 36, 41, 43, 50, 40, 45, 47, 64};
    private int counterEncrypt = 0;
    private int counterDecrypt = 0;



    public String encrypt(String message) {
        String parsingMessage = parser(message);
        char[][] mask = parseStringToMatrix(parsingMessage);
        char[][] encryptMatrix = getEncryptMatrix(mask);
        String encryptedMessage = parseMatrixToString(encryptMatrix);
        return encryptedMessage;

    }
    //Todo создать метод дешифровки
    public String decrypt(String message) {
        char[][] mask = parseStringToMatrix(message);
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

    private void sectorLoop(int iLower, int iUpper, int jLower, int jUpper, int[][] integerMatrix, char[][] cryptoMatrix, char[][] strMatrix) {
        for (int i = iLower; i < iUpper; i++)
            for (int j = jLower; j < jUpper; j++)
                for (int key : keys)
                    if (integerMatrix[i][j] == key) {
                        cryptoMatrix[i][j] = strMatrix[counterEncrypt / 8][counterEncrypt % 8];
                        counterEncrypt++;
                    }
    }

    private char[][] getEncryptMatrix(char[][] strMatrix) {
        int[][] integerMatrix = createMatrix();
        char[][] cryptoMatrix = new char[8][8];
        for (int rotation = 0; rotation < 4; rotation++) {
            sectorLoop(0, 4, 0, 4, integerMatrix, cryptoMatrix, strMatrix);
            sectorLoop(0, 4, 4, 8, integerMatrix, cryptoMatrix, strMatrix);
            sectorLoop(4, 8, 0, 4, integerMatrix, cryptoMatrix, strMatrix);
            sectorLoop(4, 8, 4, 8, integerMatrix, cryptoMatrix, strMatrix);
            integerMatrix = rightRotateMatrix(integerMatrix);
        }
        return cryptoMatrix;
    }


    private void sectorLoopDecrypt(int iLower, int iUpper, int jLower, int jUpper, int[][] integerMatrix, char[][] cryptoMatrix, char[][] strMatrix) {
        for (int i = iLower; i < iUpper; i++)
            for (int j = jLower; j < jUpper; j++)
                for (int key : keys)
                    if (integerMatrix[i][j] == key) {
                        strMatrix[i][j] = cryptoMatrix[counterDecrypt / 8][counterDecrypt % 8];
                        counterDecrypt++;
                    }
    }

    private char[][] getDecryptMatrix(char[][] cryptoMatrix) {
        char[][] strMatrix = new char[8][8];
        int[][] integerMatrix = createMatrix();
        leftRotateMatrix(integerMatrix);
        for (int rotation = 0; rotation < 4; rotation++) {
            sectorLoopDecrypt(4, 8, 4, 8, integerMatrix, cryptoMatrix, strMatrix);
            sectorLoopDecrypt(4, 8, 0, 4, integerMatrix, cryptoMatrix, strMatrix);
            sectorLoopDecrypt(0, 4, 4, 8, integerMatrix, cryptoMatrix, strMatrix);
            sectorLoopDecrypt(0, 4, 0, 4, integerMatrix, cryptoMatrix, strMatrix);
            integerMatrix = leftRotateMatrix(integerMatrix);
        }
        return strMatrix;
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

    private int[][] rightRotateMatrix(int[][] matrix) {
        int[][] rotatedMatrix = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rotatedMatrix[j][7 - i] = matrix[i][j]; // изменен индекс 8 - 1 на 7 - i
            }
        }
        return rotatedMatrix;
    }

    private int[][] leftRotateMatrix(int[][] matrix) {
        int[][] rotatedMatrix = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rotatedMatrix[7 - j][i] = matrix[i][j]; // изменен индекс 8 - 1 на 7 - j
            }
        }
        return rotatedMatrix;
    }
}
