package p11235;

/**
 * Created by yuantian on 4/30/15.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Main {
    static void go() {
        int n, q;
        while ((n = in.nextInt()) != 0) {
            q = in.nextInt();
            //int[] a = in.nextIntArray(n);

            // pre-process
            int[] st = new int[n];
            int pre = -100000000;
            int count = 1;
            for (int i = 0; i < n; i++) {
                int x = in.nextInt();
                if (x != pre) {
                    count = 1;
                    pre = x;
                }
                st[i] = count++;
            }

            int[] index = new int[n];
            index[n - 1] = n;
            int last = n;
            for (int i = n - 2; i >= 0; i--) {
                if (st[i] >= st[i + 1]) {
                    last = i + 1;
                }
                index[i] = last;
            }

            // build DP
            int len = 32 - Integer.numberOfLeadingZeros(n);
            int[][] dp = new int[n][len];
            for (int i = 0; i < n; i++) {
                dp[i][0] = i;
            }
            for (int i = 1; i < len; i++) {
                for (int j = 0; j + (1 << (i - 1)) < n; j++) {
                    if (st[dp[j][i - 1]] > st[dp[j + (1 << (i - 1))][i - 1]]) {
                        dp[j][i] = dp[j][i - 1];
                    } else {
                        dp[j][i] = dp[j + (1 << (i - 1))][i - 1];
                    }
                }
            }

            // answer queries
            while (q-- > 0) {
                int l = in.nextInt() - 1, r = in.nextInt() - 1;
                int from = index[l];
                if (from > r) {
                    out.println(r - l + 1);
                    continue;
                }

                int c1 = index[l] - l;

                // search range(from, r)
                int k = 31 - Integer.numberOfLeadingZeros(r - from + 1);
                int c2 = st[dp[from][k]];
                int c3 = st[dp[r - (1 << k) + 1][k]];
                out.println(Math.max(c1, Math.max(c2, c3)));
            }
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

        public String nextLine() {
            StringBuilder sb = new StringBuilder(1024);
            int c = read();
            while (!(c == '\n' || c == '\r' || c == -1)) {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
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