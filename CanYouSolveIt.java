import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Can you solve it?
 * https://www.hackerearth.com/practice/data-structures/arrays/1-d/practice-problems/algorithm/can-you-solve-it/
 */
public class CanYouSolveIt {

    /**
     * Find the maximum value of the following expression:
     * |arr[i] - arr[j]| + |[i -j]|
     * 
     * First pass (searching for inspiration OR brute force).
     * Execution time: O(n^2)
     */
    static int maxValue1(int[] arr, int n) {

        // **** sanity checks ****
        if (arr == null || n == 1)
            return 0;

        // **** initialization ****
        int maxVal = -1;

        // **** traverse arr[] with first element - O(n) ****
        for (int i = 0; i < n - 1; i++) {

            // **** traverse arr[] with second element - O(n) ****
            for (int j = i + 1; j < n; j++)
                maxVal = Math.max(maxVal, Math.abs(arr[i] - arr[j]) + Math.abs(i - j));
        }

        // **** return max val ****
        return maxVal;
    }

    /**
     * Find the maximum value of the following expression:
     * |arr[i] - arr[j]| + |[i -j]|
     * 
     * Second pass.
     * 
     * Inspiration from:
     * https://www.geeksforgeeks.org/maximum-value-arri-arrj-j/
     * 
     * Execution time: O(n)
     */
    static int maxValue(int[] arr, int n) {

        // **** sanity checks ****
        if (arr == null || n == 1)
            return 0;

        // **** initialization ****
        int[] arrA = new int[arr.length];
        int[] arrB = new int[arr.length];

        // **** populate arrA[] and arrB[] - O(n)****
        for (int i = 0; i < n; i++) {
            arrA[i] = arr[i] + i;
            arrB[i] = arr[i] - i;
        }

        // **** for starters ****
        int minA = arrA[0];
        int maxA = arrB[0];

        // **** find max and min in arrA[] - O(n) ****
        for (int i = 1; i < n; i++) {

            // **** update maxA (if needed) ****
            if (arrA[i] > maxA)
                maxA = arrA[i];

            // **** update minA (if needed) ****
            if (arrA[i] < minA)
                minA = arrA[i];
        }

        // **** determine difference ****
        int diff1 = (maxA - minA);

        // **** for starters ****
        int minB = arrB[0];
        int maxB = arrB[0];

        // **** find max and min in arrB[] - O(n) ****
        for (int i = 1; i < n; i++) {

            // **** update maxB (if needed) ****
            if (arrB[i] > maxB)
                maxB = arrB[i];

            // **** update minB (if needed) ****
            if (arrB[i] < minB)
                minB = arrB[i];
        }

        // **** determine difference ****
        int diff2 = (maxB - minB);

        // **** return answer ****
        return Math.max(diff1, diff2);
    }

    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // **** read number of test cases ****
        int N = Integer.parseInt(br.readLine().trim());

        // **** loop once per test case ****
        for (int i = 0; i < N; i++) {

            // **** skip array length ****
            br.readLine();

            // **** create and populate arr[] ****
            int[] arr = Arrays.stream(br.readLine().trim().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

            // **** compute and display result (two approaches) ****
            System.out.println("main <<< result: " + maxValue1(arr, arr.length));
            System.out.println("main <<< result: " + maxValue(arr, arr.length));
        }

        // **** close buffered reader ****
        br.close();
    }
}