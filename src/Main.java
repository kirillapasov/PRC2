public class Main {
    public static void main(String[] args) {
        CardanoGrid c = new CardanoGrid();
        String govno =  c.parser(" ");
        System.out.println(govno);
        System.out.println("............................");

        c.printMatrix( c.getMatrix( c.parseStringToMatrix(govno)));

        }
    }
