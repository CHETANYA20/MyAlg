package problemset731;

/**
 * Created by yuantian on 11/18/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Socks731C_ds {
    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] colors = in.nextIntArray(n);
        int[] ds = new int[n];
        Arrays.fill(ds, -1);
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1, r = in.nextInt() - 1;
            merge(ds, l, r);
        }
        HashMap<Long, Integer> map = new HashMap<>();
        HashSet<Integer> groups = new HashSet<>();
        for (int i = 0; i < n; i++) {
            long group = getDS(ds, i);
            groups.add((int) group);
            int color = colors[i];
            long key = (group << 32) | color;
            if (map.containsKey(key))
                map.put(key, map.get(key) + 1);
            else
                map.put(key, 1);
        }
        int[] max = new int[n];
        for (long key : map.keySet()) {
            int group = (int) (key >> 32);
            max[group] = Math.max(max[group], map.get(key));
        }
        int ans = 0;
        for (int i : groups) {
            ans += -ds[i] - max[i];
        }
        out.println(ans);
    }

    static void merge(int[] ds, int a, int b) {
        int x = getDS(ds, a);
        int y = getDS(ds, b);
        if (x != y) {
            if (ds[x] <= ds[y]) {
                ds[x] += ds[y];
                ds[y] = x;
            } else {
                ds[y] += ds[x];
                ds[x] = y;
            }
        }
    }

    static int getDS(int[] ds, int i) {
        if (ds[i] < 0) return i;
        return ds[i] = getDS(ds, ds[i]);
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

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char) c;
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