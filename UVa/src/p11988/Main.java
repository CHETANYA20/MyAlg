package p11988;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/9/15
 * Time: 10:54 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;


import java.util.*;
import java.io.*;

class Main {
    static void go() {
        while(true) {
            String line = null;
            try {
                line = "[" + in.nextString();
            } catch (Exception e) {
                break;
            }
            int n = line.length();
            char[] ans = new char[n];
            int l = 0, r = n - 1;
            int idx = n - 1, preE = n, preS = n;
            for (; idx >= 0; idx--) {
                if (line.charAt(idx) == ']') {
                    preE = idx;
                } else if (line.charAt(idx) == '[') {
                    for (int i = idx; i < preE; i++) {
                        ans[l++] = line.charAt(i);
                    }
                    for (int i = preS - 1; i >= preE; i--) {
                        ans[r--] = line.charAt(i);
                    }
                    preS = idx;
                    preE = idx;
                }
            }
            for (char c : ans) {
                if (c != '[' && c != ']')
                    out.print(c);
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
                    throw new InputMismatchException();
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