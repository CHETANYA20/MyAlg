package problemset938;


/*

 */

import java.util.*;
import java.io.*;

public class WordCorrection938A {
    final static Set<Character> vow = new HashSet<>();

    static {
        vow.add('a');
        vow.add('e');
        vow.add('i');
        vow.add('o');
        vow.add('u');
        vow.add('y');
    }

    static void go() {
        int n = in.nextInt();
        String str = in.nextString();
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            if (vow.contains(str.charAt(i)) && vow.contains(str.charAt(i - 1)))
                continue;
            sb.append(str.charAt(i));
        }
        out.println(sb.toString());
    }

    static InputReader in;
    static PrintWriter out;

    public static void main(String[] args) {
        if (!System.getenv().containsKey("RUN_LOCAL")) {
            in = new InputReader(System.in);
            out = new PrintWriter(System.out);

            go();

            out.close();
        } else {
            String testFiles = System.getenv().get("TEST_FILES");
            if (testFiles == null) {
                System.out.println("Test files are not specified!");
                return;
            }
            out = new PrintWriter(System.out);
            try {
                for (String fileName : testFiles.split("\\n")) {
                    File testFile = new File(fileName);
                    in = new InputReader(new FileInputStream(testFile));

                    go();
                    out.flush();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Test file can not be found!");
            } finally {
                out.close();
            }
        }
    }

    static class InputReader {
        private InputStream stream;
        private byte[]      buf = new byte[1024];
        private int         curChar;
        private int         numChars;

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
            return (int)nextLong();
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
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }

        public char nextChar() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            return (char)c;
        }

        public String nextString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder sb = new StringBuilder(1024);
            do {
                sb.append((char)c);
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
                ca[i] = (char)c;
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
