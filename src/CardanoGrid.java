import java.util.Random;

public class CardanoGrid {
    private int[] keys = {4, 11, 17, 26, 7, 16, 21, 31, 36, 41, 43, 50, 40, 45, 47, 64};


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

    public void rotateMatrix(char[][] matrix) {
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                char top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top;
            }
        }

    }

    private char generateRandomChar() {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a');
        return randomChar;
    }


    public void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
