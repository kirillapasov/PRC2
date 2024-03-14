import java.util.Random;

public class CardanoGrid {
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
}
