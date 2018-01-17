package problemset915;


/*

*/

import java.util.*;
import java.io.*;

public class D {
    private static boolean onCycle = false;

    static void go() {
        int n = in.nextInt(), m = in.nextInt();
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++)
            g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1;
            g[a].add(b);
            //g[b].add(a);
        }

        List<Integer> cycle = new ArrayList<>();
        int[] status = new int[n];
        for (int i = 0; i < n; i++) {
            if (status[i] == 0) {
                onCycle = false;
                dfs(g, i, status, cycle, -1);
                if (cycle.size() > 0)
                    break;
            }
        }
        if (cycle.size() == 0) {
            out.println("YES");
            return;
        }

        cycle.add(cycle.get(0));
        outter:
        for (int i = cycle.size() - 1; i > 0; i--) {
            int from = cycle.get(i), to = cycle.get(i - 1);
            status = new int[n];
            List<Integer> c = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (status[j] == 0) {
                    dfs(g, j, status, c, from * 1000 + to);
                    if (c.size() > 0)
                        continue outter;
                }
            }
            out.println("YES");
            return;
        }
        out.println("NO");
    }

    static boolean dfs(List<Integer>[] g, int x, int[] status, List<Integer> cycle, int ignore) {
        status[x] = 1;
        for (int next : g[x]) {
            if (x * 1000 + next == ignore)
                continue;
            if (status[next] == 1) {
                cycle.add(next);
                cycle.add(x);
                onCycle = true;
                return true;
            } else if (status[next] == 0) {
                if (dfs(g, next, status, cycle, ignore) && onCycle) {
                    if (x == cycle.get(0))
                        onCycle = false;
                    else
                        cycle.add(x);

                    return true;
                }
            }
        }

        status[x] = 2;
        return false;
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