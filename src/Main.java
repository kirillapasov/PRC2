public class Main {
    public static void main(String[] args) {
        CardanoGrid c = new CardanoGrid();
        String mes = c.parser("GOVNOPENISYAYCAADKSHDIMAPEENIS");
        char[][] ch1Arr = c.parseStringToMatrix(mes);
        c.printMatrix(ch1Arr);
        c.rotateMatrix(ch1Arr);
        System.out.println("???????????????????????????");
        c.printMatrix(ch1Arr);


        }
    }
