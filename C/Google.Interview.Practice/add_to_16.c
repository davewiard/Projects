#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>


//
// function prototypes
//
int num_sets_rec(int arr[], int total, int i);
int num_sets_dp(int arr[], int total);
int dp(int arr[], int total, int i, int mem);


//
// program entry point
//
int main(int argc, char **argv)
{
  int arr[] = {2, 4, 6, 10};
  int length = sizeof(arr) / sizeof(arr[0]);
  int total = 16;

  int total_num_sets = num_sets_rec(arr, total, length - 1);
  printf("total_num_sets = %d\n", total_num_sets);

  return 0;
}


//
// Returns number of total subsets that add up to "total"
// this is very inefficient, especially for very large arrays
// and large values of "total"
//
int num_sets_rec(int arr[], int total, int i)
{
  if (total == 0) {
    // only subset that adds up to 0 is the empty set
    return 1;
  } else if (total < 0) {
    // no subsets add up to 0 because all array elements are positive
    return 0;
  } else if (i < 0) {
    // index is out of range so we've seen every possible subset already
    return 0;
  } else if (total < arr[i]) {
    // no possible subsets that add up to "total" since the current value
    // is larger than the total we're looking for
    return num_sets_rec(arr, total, i-1);
  } else {
    return num_sets_rec(arr, total - arr[i], i-1) +
           num_sets_rec(arr, total, i-1);
  }
}
