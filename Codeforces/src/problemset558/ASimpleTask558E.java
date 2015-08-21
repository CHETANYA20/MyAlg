package problemset558;

/**
 * Created by yuantian on 8/21/15.
 */

import java.util.*;
import java.io.*;

public class ASimpleTask558E {

    static int[][][] segTree = null;

    static void add(int[][] st, int index, int l, int r, int ql, int qr) {
        if (ql > r || qr < l)
            return;

        if (l == r) {
            st[index][0] = 1;
            return;
        }

        if (ql <= l && qr >= r) {
            st[index][1] = 2;
            st[index][0] = r - l + 1;
            return;
        }

        int next = index << 1;
        int mid = (l + r) / 2;
        if (st[index][1] != 0) {
            pushDown(st, next, l, mid, st[index][1]);
            pushDown(st, next + 1, mid + 1, r, st[index][1]);
            st[index][1] = 0;
        }
        add(st, next, l, mid, ql, qr);
        add(st, next + 1, mid + 1, r, ql, qr);
        st[index][0] = st[next][0] + st[next + 1][0];
    }

    static void pushDown(int[][] st, int index, int l, int r, int p) {
        if (l != r)
            st[index][1] = p;
        if (p == 1) {
            st[index][0] = 0;
        } else {
            st[index][0] = r - l + 1;
        }
    }

    static int query(int[][] st, int index, int l, int r, int ql, int qr) {
        if (ql > r || qr < l || st[index][0] == 0)
            return 0;

        int next = index << 1;
        int mid = (l + r) / 2;
        if (st[index][1] != 0) {
            pushDown(st, next, l, mid, st[index][1]);
            pushDown(st, next + 1, mid + 1, r, st[index][1]);
            st[index][1] = 0;
        }

        if (ql <= l && qr >= r) {
            int ret = st[index][0];
            st[index][0] = 0;
            if (l != r) st[index][1] = 1;
            return ret;
        }

        int ret = query(st, next, l, mid, ql, qr) + query(st, next + 1, mid + 1, r, ql, qr);
        st[index][0] = st[next][0] + st[next + 1][0];
        return ret;
    }

    static void go() {
        int n = in.nextInt();
        int q = in.nextInt();

        segTree = new int[26][4 * n][2];
        String str = in.nextString();
        for (int i = 0; i < n; i++) {
            add(segTree[str.charAt(i) - 'a'], 1, 1, n, i + 1, i + 1);
        }

        int[] count = new int[26];
        for (int i = 0; i < q; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            int op = in.nextInt();
            for (int j = 0; j < 26; j++) {
                count[j] = query(segTree[j], 1, 1, n, l, r);
            }
            if (op == 1) { // inc
                int p = l;
                for (int j = 0; j < 26; j++) {
                    if (count[j] == 0) continue;
                    add(segTree[j], 1, 1, n, p, p + count[j] - 1);
                    p += count[j];
                }
            } else {
                int p = l;
                for (int j = 25; j >= 0; j--) {
                    if (count[j] == 0) continue;
                    add(segTree[j], 1, 1, n, p, p + count[j] - 1);
                    p += count[j];
                }
            }
        }

        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (query(segTree[j], 1, 1, n, i + 1, i + 1) > 0) {
                    ans[i] = (char) ('a' + j);
                    break;
                }
            }
        }

        out.println(ans);
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