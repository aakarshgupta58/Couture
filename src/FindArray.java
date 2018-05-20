import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by gaakarsh on 20/05/18.
 */
public class FindArray {

    static int MAX = 100;
    static int dp[][] = new int[MAX][MAX];
    static int dp1[][][] = new int[MAX][MAX][3];

    // function to check all possible combinations
    // of removal and return the minimum one
    static int countRemovals(Integer a[], int i, int j, int k) {
        // base case when all elements are removed
        if (i >= j)
            return 0;

            // if condition is satisfied, no more
            // removals are required
        else if ((a[j] - a[i]) <= k)
            return 0;

            // if the state has already been visited
        else if (dp[i][j] != -1)
            return dp[i][j];

            // when Amax-Amin>k
        else if ((a[j] - a[i]) >= k) {

            // minimum is taken of the removal
            // of minimum element or removal
            // of the maximum element
            int first = countRemovals(a, i + 1, j, k);
            int second = countRemovals(a, i, j - 1, k);
            if (first < second) {

                dp1[i][j][0] = i + 1; // From where to
                dp1[i][j][1] = j;   // Till where
                dp1[i][j][2] = i;   // Which one to remove
            } else {
                dp1[i][j][0] = i;   // From where to
                dp1[i][j][1] = j - 1; // Till where
                dp1[i][j][2] = j;   // Which one to remove
            }
            dp[i][j] = 1 + Math.min(first, second);
        }
        return dp[i][j];
    }

    // To sort the array and return the answer
    static Integer[] removals(Integer a[], int n, int k) {
        // sort the array
        Arrays.sort(a);
        // fill all stated with -1
        // when only one element
        for (int[] rows : dp)
            Arrays.fill(rows, -1);
        for (int[][] rows : dp1){
            for (int[] col : rows)
                Arrays.fill(col, -1);
        }

        if (n == 1)
            return a;
        else {
            int ans = countRemovals(a, 0, n - 1, k);
            int d1 = 0, d2 = n - 1;
            int[] indexes = new int[n];
            while (dp1[d1][d2][0] != -1) {
                indexes[dp1[d1][d2][2]] = -1;
                d1 = dp1[d1][d2][0];
                d2 = dp1[d1][d2][1];

            }
            ArrayList<Integer> ls = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if (indexes[i] != -1) {
                    ls.add(a[i]);
                }
            }

            System.out.println("Number of Removals: " + ans);

            Integer[] newArr = ls.toArray(new Integer[ls.size()]);

            return newArr;
        }
    }

    // Driver code
    public static void main(String[] args) {


        Scanner s = new Scanner(System.in);
        System.out.println("Please provide the Arrays Length");
        int count = s.nextInt();
        s.nextLine(); // throw away the newline.

        Integer [] a = new Integer[count];
        System.out.println("Please provide the numbers one by one");
        Scanner numScanner = new Scanner(s.nextLine());
        for (int i = 0; i < count; i++) {
            if (numScanner.hasNextInt()) {
                a[i] = numScanner.nextInt();
            } else {
                System.out.println("You didn't provide enough numbers");
                break;
            }
        }
       // Integer a[] = { 34, 656, 565, 412, 67, 23, 900, 6 };
        int n = a.length;
        int k = 200;

        Integer[] ans = removals(a, n, k);

        System.out.println("New Array: " + Arrays.deepToString(ans));
    }
}
