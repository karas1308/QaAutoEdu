package games;
public class Main {
    String str = "";

    public static void main(String[] args) {
//        for (int i = 1; ; i++) {
//            System.out.println("Введите число от 1 до 9. Для выхода введите q");
//            Scanner in = new Scanner(System.in); //Ведите имя
//            String str = in.nextLine();
//            if (str.equals("q")) {
//                System.out.print("Пока пока :(");
//                break;
//            }
//            int n = Integer.parseInt(str);
//            if (n < 1 || n > 9) {
//            } else if (n > 0 && n < 10)
//                Matrix.matrix(n);
//        }


        int n = 4;
        Snail.calculateSnail(n);
    }
}