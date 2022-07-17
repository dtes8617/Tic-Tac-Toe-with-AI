import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int maxPerSeed;
        int minPerRequest = k;
        int minRequestIndex = a - 1;
        int randomNumber;

        for (int i = a; i < b + 1; i++) {
            Random random = new Random(i);
            maxPerSeed = 0;
            for (int j = 0; j < n; j++) {
                randomNumber = random.nextInt(k);
                maxPerSeed = randomNumber > maxPerSeed ? randomNumber : maxPerSeed;
            }
            minRequestIndex = minPerRequest > maxPerSeed ? i : minRequestIndex;
            minPerRequest = minPerRequest > maxPerSeed ? maxPerSeed : minPerRequest;
        }

        System.out.println(minRequestIndex);
        System.out.println(minPerRequest);


    }
}