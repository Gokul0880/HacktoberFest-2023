import java.util.Scanner;

public class MaxInArray {

    // Method to find the maximum number in the array
    public static int findMax(int[] arr) {
        int max = arr[0]; // Assume the first element is the largest
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // Update max if a larger element is found
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        // Input the elements of the array
        System.out.println("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Find the maximum number
        int maxNumber = findMax(arr);

        // Output the result
        System.out.println("The maximum number in the array is: " + maxNumber);
    }
}
