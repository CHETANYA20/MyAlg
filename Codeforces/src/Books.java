/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class Books {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int t = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = in.nextInt();

        int max = 0;
        int total = 0;
        int len = 0;
        int head = 0;
        for (int i : a) {
            total += i;
            len++;
            while (total > t) {
                total -= a[head++];
                len--;
            }

            max = Math.max(max, len);
        }

        System.out.println(max);
    }

}
