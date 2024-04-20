package Website;

public class Nnatural_num {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < 100; i++) {

            sum = (i * (i + 1)) / 2;
            System.out.println(sum);
        }
    }
}
