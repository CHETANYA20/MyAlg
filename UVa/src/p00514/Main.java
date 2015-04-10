package p00514;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/9/15
 * Time: 11:19 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Stack;

class Main {
    static void go() {
        int n;
        Stack<Integer> S = new Stack<>();
        while ((n = in.nextInt()) != 0) {
            int k;
            int[] b = new int[n];
            while ((k = in.nextInt()) != 0) {
                b[0] = k;
                for (int i = 1; i < n; i++)
                    b[i] = in.nextInt();
                S.clear();
                int iA = 1, iB = 0;
                while (iA <= n && iB < n) {
                    if (iA == b[iB]) {
                        iA++;
                        iB++;
                    } else {
                        if (!S.empty() && b[iB] == S.peek()) {
                            S.pop();
                            iB++;
                        } else {
                            S.push(iA);
                            iA++;
                        }
                    }
                }
                while (!S.empty()) {
                    if (b[iB] == S.peek()) {
                        iB++;
                        S.pop();
                    } else {
                        break;
                    }
                }
                if (!S.empty()) {
                    out.println("No");
                } else {
                    out.println("Yes");
                }
            }
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