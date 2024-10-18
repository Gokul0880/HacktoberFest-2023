#include <iostream>
#include <cmath>
using namespace std;

// Function to check if a number is a Kaprekar number in a given base
bool isKaprekar(int number, int base)
{
    // Basic validations
    if (number < 0 || base <= 1) {
        return false; // Base must be greater than 1
    }

    // Square the number
    long long squared = static_cast<long long>(number) * number;

    // Initialize divisor as 1 and find the appropriate divisor based on the base
    long long divisor = 1;
    while (squared / divisor >= base) {
        divisor *= base;
    }

    // Check for Kaprekar condition by splitting the squared number
    while (divisor > 0) {
        long long left = squared / divisor;        // Left part
        long long right = squared % divisor;       // Right part

        // If the sum of left and right equals the original number
        if (left + right == number && right > 0) {
            return true;
        }

        // Reduce divisor to split further
        divisor /= base;
    }

    // Return false if no valid Kaprekar split is found
    return false;
}

int main()
{
    int number, base;
    
    // Input the number and the base
    cout << "Enter a number: ";
    cin >> number;
    cout << "Enter the base: ";
    cin >> base;

    // Check if the number is a Kaprekar number in the specified base
    if (isKaprekar(number, base)) {
        cout << number << " is a Kaprekar number in base " << base << "." << endl;
    } else {
        cout << number << " is not a Kaprekar number in base " << base << "." << endl;
    }

    return 0;
}
