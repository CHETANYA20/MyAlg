package problemset486;

/**
 * Created by yuantian on 11/13/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class ValidSets486D {
    static int P = (int) 1e9 + 7;
    static List<Integer>[] tree = null;
    static int[] wt = null;
    static boolean[] visited = null;
    static int d = 0;

    static void go() {
        d = in.nextInt();
        int n = in.nextInt();
        wt = new int[n];
        int[][] nwt = new int[n][2];
        for (int i = 0; i < n; i++) {
            wt[i] = in.nextInt();
            nwt[i][0] = i;
            nwt[i][1] = wt[i];
        }

        tree = new List[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<Integer>();

        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree[from].add(to);
            tree[to].add(from);
        }

        Arrays.sort(nwt, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        visited = new boolean[n];
        long count = 0;
        for (int[] node : nwt) {
            visited[node[0]] = true;
            count += dfs(node[0], node[1]);
        }

        out.println(count % P);
    }

    static long dfs(int from, int min) {
        long ret = 1;
        for (int next : tree[from]) {
            if (!visited[next] && wt[next] <= min + d && wt[next] >= min) {
                visited[next] = true;
                ret *= 1 + dfs(next, min);
                ret %= P;
                visited[next] = false;
            }
        }
        return ret;
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