package taxdoo;

public class Main {
    public static int isPrime(long n) {
        if (n % 2 == 0)
            return 2;

        // iterate from 3 to sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return i;
        }

        return Long.valueOf(n).intValue();
    }


    public static void main(String[] args) {
        System.out.println(isPrime(4));
        System.out.println(isPrime(49));
        System.out.println(isPrime(24));
    }
}
