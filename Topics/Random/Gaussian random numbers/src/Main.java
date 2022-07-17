import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int k = scanner.nextInt();
        int n = scanner.nextInt();
        float m = scanner.nextFloat();
        boolean notFindAnswer = true;

        searchSeed:
        while (notFindAnswer) {
            Random random = new Random(k);

            for (int i = 0; i < n; i++) {
                if (random.nextGaussian() > m) {
                    k += 1;
                    continue searchSeed;
                }
            }
            notFindAnswer = false;
        }

        System.out.print(k);



    }
}