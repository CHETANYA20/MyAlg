/**
 * Created by yuantian on 5/22/14.
 */
/*
Koga and Ryuho, new generation Athena’s saints, are training to improve their control over the cosmos. According to the ancient Masters, a saint’s power to control the cosmos strengthens, when one allows the energy of the universe to flow within the body and then concentrates it. This energy can even be used to explode the objects.

Today’s training is based on a game, and the goal is to use as little cosmos as possible to win. Two saints play as follows:

Initially there are N piles of stones; pile 1 has 1 stone, pile 2 has 2 stones, and so on. Thus, the i-th pile has i stones. The saints take turns and in each turn, a saint must select a non-empty pile and destroy at least half of the stones in it. The winner is the saint who destroys the last available stone .

For example, from a pile of 7 stones, a saint must destroy at least 4 stones, leaving a single (and possibly empty) pile at most 3 stones. With such game, saints learn how to use the appropriate amount of cosmos in a single strike: too much will destroy more stones than desired, to little won’t be enough. They also improve their battle thinking and strategy skills.

Ryuho suspects that such game is not as random as it appears to be at first glance. He strongly believes that with the correct single blow, you’re assured to win from the very first turn, if you play optimally, no matter how good the other saint plays. Moreover, he is particularly interested in knowing the minimum number of stones he needs to destroy at that first move. Can you help him?

Input Format
First line of the input consists of an integer T, T testcases follow, each in a new line. Each line will contain a single integer N, which describes the number of initial piles as explained above.

Output Format
For each line in the input, output the minimum number of stones Ryuho needs to destroy in his first turn, assuming he starts playing and that both he and Koga play always as well as possible. If this is not possible, just print 0.

Constraints

1 <= T <= 1e6
1 <= N <= 1e9

Sample Input:

5
1
10
6
8
123456
Sample Output:

1
7
2
7
32768
Explanation

For the first testcase, we can see that the saint can destroy the first stone and win the game.
 */

import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.io.IOException;

public class StonesGame {
    static void go() {
        int[][] table = new int[32][33];
        table[1][1] = 1;
        for (int i = 2; i < 32; i++) {
            table[i][1] = 1 << (i - 2);
            for (int j = 2; j <= i; j++)
                table[i][j] = (1 << (i - 1)) - (1 << (i - j)) + 1;

        }

        /*
        for(int[] x : table) {
            for(int y : x)
                System.out.print(" " + y);
            System.out.println();
        }
        */

        /*
        table is like:
          0
          1
          1 2
          2 3 4
          4 5 7 8
          8 9 13 15 16
          16 17 25 29 31 32
          32 33 49 57 61 63 64
          64 65 97 113 121 125 127 128
        */

        int t = in.nextInt();
        while (t-- > 0) {
            long n = in.nextLong();

            if ((n & 1) == 1) {
                out.println(1);
                continue;
            }

            long p = 1;
            int max = 0;
            while (p <= n) {
                p <<= 1;
                max++;
            }

            p >>= 1;
            long res = p - 1; // default
            int target = 1 ^ max;

            //System.out.println("p = " + p + " target =" + target + " max = " + max + " out for now = " + out);
            for (int nim = 2; nim < max; nim++) {
                int j = target ^ nim;
                if (nim > j) {
                    res = Math.min(res, table[nim][nim - j]);
                    break;
                }
            }
            out.println(res);
        }
        out.close();
    }


    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        in = new InputReader(System.in);
        out = new PrintWriter(System.out);

        go();

        out.close();
    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char) c);
                c = read();
            } while (!isSpaceChar(c));
            return sb.toString();
        }

        public static boolean isSpaceChar(int c) {
            switch (c) {
                case -1:
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    return true;
                default:
                    return false;
            }
        }
    }
}

/*
input:
50
58
54
64
10
76
69
78
32
33
2
40
12
29
80
47
24
3
73
11
30
74
57
5
67
52
26
66
31
22
68
41
61
37
48
45
79
15
35
56
70
77
9
65
36
62
50
25
38
27
7
 */

/*
output:
4
4
5
7
5
1
5
4
1
1
4
7
1
5
1
8
1
1
1
8
5
1
1
1
4
8
5
1
8
5
1
1
1
4
1
1
1
1
4
5
1
1
1
4
4
4
1
4
1
1
 */