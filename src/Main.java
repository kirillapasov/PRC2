public class Main {
    public static void main(String[] args) {
        String message = "A B C D E F G H I K L M N O P Q R S T V X Y Z A B C D E F G H I K L M N O P Q R S T V X Y Z A B C D E F G H I K L M N O P Q R S T V X Y Z";
        CardanoGrid grid1 = new CardanoGrid();
        System.out.println(grid1.parser(message));
        System.out.println("////////////////////////////////");
        System.out.println(grid1.encrypt(message));

        }
    }
