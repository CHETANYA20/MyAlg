package CodeSprintIndia2014;

/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 9/19/14
 * Time: 10:16 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

/*

https://www.hackerrank.com/contests/csindia/challenges/pin-problem-1

Bidhan and Roy are very close friends. They know every little secret of each other. Today Roy forgot his ATM card's pin code. So, he called Bidhan and asked if he can remember the code. Bidhan does not remember the exact code. But he told Roy that, the pin code is a positive number not bigger than n and it is divisible by m other numbers. Then Bidhan gave Roy the list of m numbers.

Now, Roy has to try all possible combinations for his pin code. How many are possible?

Input Format

The first line of input will contain t, the number of test cases. Each testcase will consist of two lines. The first line will contain n and m. The second line will contain m space separated integers.

Constraints

1≤t≤105
1≤n≤104
1≤m≤10
All of the m integers will be positive and will not exceed 10.

Output Format

For each testcase, print the number of possible pin codes on a separate line.

Sample Input

3
5 1
1
7 2
2 3
10 3
2 2 4
Sample Output

5
1
2
Explanation

Test case 1 : The possible pin codes are, 1,2,3,4 and 5. All of them are divisible by 1.
Test case 2 : The only number smaller than 7 which is divisible by both 2 and 3 is 6.
Test case 3 : The possible pin codes are 4 and 8.
*/

import java.util.*;
import java.io.*;

public class PinProblem1 {
    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int m = in.nextInt();
            int d = in.nextInt();
            for (int i = 1; i < m; i++) {
                int next = in.nextInt();
                int g = gcd(d, next);
                d *= next / g;
            }
            out.println(n / d);

        }
    }

    static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
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