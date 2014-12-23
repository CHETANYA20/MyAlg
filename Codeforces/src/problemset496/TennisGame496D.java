package problemset496;

/**
 * Created by yuantian on 12/19/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class TennisGame496D {
    static class Pair {
        int s, t;

        public Pair(int t, int s) {
            this.t = t;
            this.s = s;
        }
    }

    static void go() {
        int n = in.nextInt();
        int[] a = in.nextIntArray(n);
        //long start = System.currentTimeMillis();
        /*
        int n = 100000;
        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = 1;
        */

        int[][] score = new int[n][2];
        int[][] map = new int[100001][2];
        int winner = a[n - 1] - 1;
        int loser = 1 - winner;
        score[0][a[0] - 1] = 1;
        map[1][a[0] - 1] = 1;
        for (int i = 1; i < n; i++) {
            int j = a[i] - 1;
            score[i][j] = score[i - 1][j] + 1;
            map[score[i][j]][j] = i + 1;
            score[i][1 - j] = score[i - 1][1 - j];
        }

        ArrayList<Pair> ans = new ArrayList<>();
        int max1 = score[n - 1][winner], max2 = score[n - 1][loser];
        if (max1 > max2) {
            ans.add(new Pair(1, max1));
        }
        for (int i = max1 / 2; i >= 1; i--) {
            int index, s = 0, t1 = 0, t2 = 0;
            if (map[i][winner] < map[i][loser] || map[i][loser] == 0) {
                index = map[i][winner] - 1;
                s = score[index][winner];
                t1 = 1;
            } else {
                index = map[i][loser] - 1;
                s = score[index][loser];
                t2 = 1;
            }
            while (index < n - 1) {
                int next1 = score[index][winner] + s;
                int next2 = score[index][loser] + s;
                int i1, i2;
                if (next1 <= max1) {
                    i1 = map[next1][winner] - 1;
                } else {
                    break;
                }
                i2 = next2 <= max2 ? map[next2][loser] - 1 : n;

                if (i1 > i2) {
                    t2++;
                    index = i2;
                } else if (i1 < i2) {
                    t1++;
                    index = i1;
                    if (i1 == n - 1 && t1 > t2) {
                        ans.add(new Pair(t1, s));
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        out.println(ans.size());
        Collections.sort(ans, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.t == o2.t)
                    return o1.s - o2.s;
                return o1.t - o2.t;
            }
        });
        for (int i = 0; i < ans.size(); i++) {
            out.println(ans.get(i).t + " " + ans.get(i).s);
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