public class Main {
    public static void main(String[] args) {
        CardanoGrid c = new CardanoGrid();
        String govno =  c.parser("ADKSHAAAAAYAGHOULDIMAPENISLOX");

        c.printMatrix( c.getMatrix( c.parseStringToMatrix(govno)));

        }
    }
