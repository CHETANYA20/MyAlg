package p11926;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/8/15
 * Time: 10:50 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

class Main {
    static void go() {
        int n, m;
        while (true) {
            n = in.nextInt();
            m = in.nextInt();
            if (n == 0 && m == 0)
                break;

            int[] map = new int[1000001];
            for (int i = 0; i < n; i++) {
                map[in.nextInt()]++;
                int y = in.nextInt();
                if (y <= 1000000) map[y]--;
            }

            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int inv = in.nextInt();
                while (x <= 1000000) {
                    map[x]++;
                    if (y <= 1000000) map[y]--;
                    x += inv;
                    y += inv;
                }
            }

            int count = 0;
            boolean ok = true;
            for (int x : map) {
                count += x;
                if (count > 1) {
                    ok = false;
                    break;
                }
            }
            out.println(ok ? "NO CONFLICT" : "CONFLICT");
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