package problemset731;

/**
 * Created by yuantian on 11/17/16.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Socks731C {
    static List<Integer>[] g = null;
    static int[] visited = null;
    static int total = 0;
    static int max = 0;
    static int[] count = null;
    static int[] color = null;

    static void go() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        color = in.nextIntArray(n);

        g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            g[l].add(r);
            g[r].add(l);
        }

        visited = new int[n];
        count = new int[k];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                dfs(i);

                ans += total - max;
                total = 0;
                max = 0;
                //Arrays.fill(count, 0);
                clean(i);
            }
        }
        out.println(ans);
    }

    static void dfs(int idx) {
        visited[idx] = 1;
        total++;
        int x = color[idx] - 1;
        count[x]++;
        max = Math.max(max, count[x]);
        for (int next : g[idx]) {
            if (visited[next] == 0)
                dfs(next);
        }
    }

    static void clean(int idx) {
        visited[idx] = 2;
        int x = color[idx] - 1;
        count[x] = 0;
        for (int next : g[idx]) {
            if (visited[next] == 1)
                clean(next);
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