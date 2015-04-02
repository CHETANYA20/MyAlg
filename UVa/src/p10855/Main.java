package p10855;

/**
 * Created by yuantian on 4/2/15.
 */

/*

*/

import java.util.*;
import java.io.*;

class Main {
    static void go() {
        int N = 0, n = 0;
        while ((N = in.nextInt()) != 0) {
            n = in.nextInt();
            char[][] S = new char[N][];
            for (int i = 0; i < N; i++)
                S[i] = in.nextString().toCharArray();
            char[][] s = new char[n][];
            for (int i = 0; i < n; i++)
                s[i] = in.nextString().toCharArray();

            int ans = match(S, s, N, n);
            out.print(ans);
            rotate(S);
            ans = match(S, s, N, n);
            out.print(" " + ans);
            rotate(S);
            ans = match(S, s, N, n);
            out.print(" " + ans);
            rotate(S);
            ans = match(S, s, N, n);
            out.println(" " + ans);
        }
    }

    static void rotate(char[][] S) {
        int n = S.length;

        int max = n / 2;
        for (int l = 0; l < max; l++) {
            int end = n - l - 1;
            for (int i = l; i < end; i++) {
                char ch = S[l][i];
                S[l][i] = S[i][n - l - 1];
                S[i][n - l - 1] = S[n - l - 1][n - i - 1];
                S[n - l - 1][n - i - 1] = S[n - i - 1][l];
                S[n - i - 1][l] = ch;
            }
        }
    }

    static int match(char[][] S, char[][] s, int N, int n) {
        int count = 0;
        String s1 = new String(s[0]);
        for (int i = 0; i <= N - n; i++) {
            int find = 0;
            String str = new String(S[i]);
            while ((find = str.indexOf(s1, find)) >= 0) {
                if (match(S, i, find, s))
                    count++;
                find++;
            }
        }
        return count;
    }

    static boolean match(char[][] S, int row, int col, char[][] s) {
        int n = s.length;
        for (int r1 = 1, r2 = row + 1; r1 < n; r1++, r2++) {
            for (int c1 = 0, c2 = col; c1 < n; c1++, c2++) {
                if (S[r2][c2] != s[r1][c1])
                    return false;
            }
        }
        return true;
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