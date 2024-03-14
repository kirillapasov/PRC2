import java.util.Random;

public class CardanoGrid {
     private int[][] matrix = generateMatrix(8, 8);
     private int[] keys = {4, 11, 17, 26, 7, 16, 21, 31, 36, 41, 43, 50, 40, 45, 47, 64};

    public String parser(String input){
        input = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if(input.length() > 64){
            input = input.substring(0, 64);
        }
        while (input.length() < 64){
            input += generateRandomChar();
        }
        return input.toLowerCase();

    }
    private char generateRandomChar() {
        Random random = new Random();
        char randomChar = (char) (random.nextInt(26) + 'a');
        return randomChar;
    }
    private int[][] generateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int counter = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = counter++;
            }
        }
        return matrix;
    }
}
