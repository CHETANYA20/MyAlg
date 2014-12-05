package problemset490;

/**
 * Created by yuantian on 12/4/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class HackingCypher490C {
    static void go() {
        String str = in.nextString();
        int a = in.nextInt();
        int b = in.nextInt();

        boolean[] ok = new boolean[str.length()];
        long x = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            int y = str.charAt(i) - '0';
            x = (x * 10 + y) % a;
            if (x == 0)
                ok[i] = true;
        }

        x = 0;
        int base = 1;
        for (int i = str.length() - 1; i > 0; i--) {
            int y = str.charAt(i) - '0';
            x = (y * base + x) % b;
            if (y != 0 && x == 0 && ok[i - 1]) {
                out.println("YES");
                out.println(str.substring(0, i));
                out.println(str.substring(i));
                return;
            }
            base = (base * 10) % b;
        }
        out.println("NO");
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