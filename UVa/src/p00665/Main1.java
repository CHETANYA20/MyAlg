package p00665;

/**
 * Created by yuantian on 4/1/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main1 {
    static void go() {
        int t = in.nextInt();

        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();

            // bit map
            // bit[x] meaning:
            //   0 => not weighted
            //   1 => weighted at least once, and it's always on the light side
            //   2 => weighted at least once, and it's always on the heavy side
            //   3 => weighted at least once, could be on a balanced side ('=') or at least one
            //            time on the light side and at least one time on the heavy side ( 1 | 2)
            //            if a coin has been on both light and heavy side, it must be a good one.
            int[] bit = new int[n + 1];
            for (int i = 0; i < k; i++) {
                int m = in.nextInt();
                int[] first = in.nextIntArray(m);
                int[] second = in.nextIntArray(m);
                char op = in.nextString().charAt(0);
                int b1 = 0, b2 = 0;
                switch (op) {
                    case '=':
                        b1 = b2 = 3;
                        break;
                    case '<':
                        b1 = 1;
                        b2 = 2;
                        break;
                    case '>':
                        b1 = 2;
                        b2 = 1;
                        break;
                }
                for (int x : first)
                    bit[x] |= b1;
                for (int x : second)
                    bit[x] |= b2;
            }

            // count how many coins have bit map: 0, 1, 2, 3
            int[] c = new int[4];
            for (int b : bit)
                c[b]++;

            // if
            // there is only 1 light ('1') and 0 heavy ('2'),
            // or,
            // there is only 1 heavy ('2') and 0 light ('1'),
            // then we got it.
            if (c[1] + c[2] == 1) {
                for (int i = 1; i <= n; i++) {
                    if (bit[i] == 1 || bit[i] == 2) {
                        out.println(i);
                        break;
                    }
                }
            } else if (c[3] == n - 1) {
                // if we got n-1 good ones, we can also identify the fake one
                for (int i = 1; i <= n; i++) {
                    if (bit[i] == 0) {
                        out.println(i);
                        break;
                    }
                }
            } else {
                out.println(0);
            }

            if (t != 0)
                out.println();
        }
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

        public int[] nextIntArray(int len) {
            int[] ret = new int[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextInt();
            return ret;
        }

        public long[] nextLongArray(int len) {
            long[] ret = new long[len];
            for (int i = 0; i < len; i++)
                ret[i] = nextLong();
            return ret;
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

        public char[] nextCharArray(int n) {
            char[] ca = new char[n];
            for (int i = 0; i < n; i++) {
                int c = read();
                while (isSpaceChar(c))
                    c = read();
                ca[i] = (char) c;
            }
            return ca;
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