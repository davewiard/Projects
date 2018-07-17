//
// calculate the number of ways to decode the input string data
// if '1' => 'a', '2' => 'b', ... '26' => 'z'.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int num_ways(char data[], int data_length);

//
// program entry point
//
int main(int argc, char const *argv[])
{
    char data[] = "11111";

    int result = num_ways(data, sizeof(data) - 1);
    printf("num_ways = %d\n", result);

    return 0;
}

//
// Recursively step through the input data. Look at the input
// length, first character, and first two characters to decide
// what to do. With each pass, shorten the input data string
//
// NOTE:
// This implementation is using straight recursion which is an O(n^2)
// solution. A much better way would be using dynamic programming
// techniques to store calculated values in a hash table with data_length
// as the key to retrieve the value instead of calculating it multiple
// time. Using dynamic programming ("memoization") would result in an
// O(n) solution.
//
int num_ways(char data[], int data_length)
{
    int total_ways = 0;

    // if length of data is < 1, return 0
    if (data_length < 2)
        return 1;

    // no character maps to '0' value 
    if (data[0] == '0')
        return 0;

    // allocate and null-out an array to hold number
    char *number1_str = malloc(sizeof(char) * 3);
    char *number2_str = malloc(sizeof(char) * 3);
    for (int i = 0; i < 3; i++) {
        number1_str[i] = '\0';
        number2_str[i] = '\0';
    }

    // convert first 2 characters to int to check if in
    // range of [a=1 - z=26]
    strncpy(number2_str, data, 2);  // ignore return value
    int number2 = atoi(number2_str);

    free(number1_str);
    free(number2_str);

    if (number2 > 26) {
        total_ways = num_ways(&data[1], data_length - 1);
    } else {
        total_ways = num_ways(&data[1], data_length - 1) +
                     num_ways(&data[2], data_length - 2);
    }

    return total_ways;
}
