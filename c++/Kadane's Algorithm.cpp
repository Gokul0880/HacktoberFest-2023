class Solution {
public:
    // arr: input array
    // n: size of array
    // Function to find the sum of the contiguous subarray with the maximum sum.
    long long maxSubarraySum(int arr[], int n) {
        // Initialize the maximum sum and the current sum.
        long long currentSum = arr[0];
        long long maxSum = arr[0];
        
        // Iterate through the array starting from the second element.
        for (int i = 1; i < n; i++) {
            // If current sum becomes negative, reset it to the current element.
            if (currentSum < 0) {
                currentSum = arr[i];
            } else {
                // Otherwise, add the current element to the current sum.
                currentSum += arr[i];
            }
            
            // Update the maximum sum if the current sum is greater.
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        
        // Return the maximum sum found.
        return maxSum;
    }
};
