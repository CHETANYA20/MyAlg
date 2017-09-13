package practice.greedy;

/**
 * Created by yuantian on 6/8/17.
 */

import java.util.*;

public class CuttingBoards {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        while (q-- > 0) {
            int m = in.nextInt(), n = in.nextInt();
            long[] x = new long[m];
            long[] y = new long[n];
            for (int i = 0; i < m - 1; i++)
                x[i] = in.nextInt();
            for (int i = 0; i < n - 1; i++)
                y[i] = in.nextInt();
            Arrays.sort(x);
            Arrays.sort(y);
            for (int i = 1; i < m; i++)
                x[i] += x[i - 1];
            for (int i = 1; i < n; i++)
                y[i] += y[i - 1];

            int xi = m - 1, yi = n - 1;
            long total = x[m - 1] + y[n - 1];
            while (xi > 0 && yi > 0) {
                if (x[xi] < y[yi] || (x[xi] == y[yi] && x[xi - 1] >= y[yi - 1])) {
                    total += x[xi];
                    yi--;
                } else {
                    total += y[yi];
                    xi--;
                }
                total %= 1000000007;
            }
            System.out.println(total);
        }
    }
}

/*

Test case:

5
87 89
13 35 72 48 54 72 13 53 77 42 1 15 93 73 64 21 39 57 25 37 1 14 35 51 28 63 25 79 62 62 18 27 97 90 75 3 14 41 56 44 83 10 59 29 83 24 50 75 33 27 12 34 41 0 85 70 63 63 1 77 25 19 56 75 61 32 78 27 73 87 71 8 97 83 37 32 59 39 7 92 18 72 26 60 72 64
82 87 27 35 16 4 54 72 79 67 4 10 94 29 49 18 90 98 1 79 30 12 19 38 4 37 10 82 49 34 46 83 21 25 18 37 82 24 61 13 91 18 75 86 47 24 56 37 22 9 17 53 21 88 43 77 77 5 59 79 39 58 62 12 35 33 1 69 57 62 83 49 80 58 87 28 35 43 17 57 52 86 62 25 26 57 2 56
37 3
61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61 61
71 71
15 9
82 66 76 40 29 11 41 9 22 28 89 57 23 59
66 27 45 29 52 24 86 54
69 17
1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
54 54 54 54 54 54 54 54 54 54 54 54 54 54 54 54
66 22
98 99 86 26 39 15 38 80 25 60 60 66 69 83 25 35 62 71 64 14 47 3 68 27 56 35 94 10 26 10 30 24 9 17 3 0 32 93 80 9 5 92 76 74 27 1 9 89 24 26 3 71 81 23 50 37 11 44 47 89 7 30 66 68 47
69 69 31 62 1 41 67 94 69 41 73 22 2 63 47 28 18 70 61 94 73

Output:

241607
6730
4736
2020
47159

 */