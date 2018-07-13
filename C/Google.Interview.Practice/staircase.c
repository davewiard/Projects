//
// Amazon question about calculating how many ways can be had to reach
// the top of a staircase when only 1 or 2 steps can be taken at each
// step. Essentially, this is a reverse Fibonacci sequence.
//
// https://www.youtube.com/watch?v=5o-kdjv7FD0
//

#include <stdio.h>
#include <stdlib.h>
#include <readline/readline.h>

int num_ways(int n);
int num_ways_bottom_up(int n);
int num_ways_X(int n, int x[], int x_count);
int num_ways_X_bottom_up(int n, int x[], int x_count);


/**
 * 
 */
int main(int argc, char **argv)
{
  int n = 4;
  int x[3] = {1, 3, 5};
  int result;
  
  result = num_ways(n);
  printf("num_ways = %d\n", result);

  result = num_ways_bottom_up(n);
  printf("num_ways_bottom_up = %d\n", result);

  result = num_ways_X(n, x, 3);
  printf("num_ways_X = %d\n", result);

  result = num_ways_X_bottom_up(n, x, 3);
  printf("num_ways_X_bottom_up = %d\n", result);
}


/**
 * Calculate number of ways to reach the top of the staircase
 * in O(n log n) time and O(1) space
 */
int num_ways(int n)
{
  if (n == 0 || n == 1)
    return 1;

  return num_ways(n-1) + num_ways(n-2);
}


/**
 * Calculate number of ways to reach the top of the staircase
 * in O(n) time and O(1) space
 */
int num_ways_bottom_up(int n)
{
  int prev1 = 1;
  int prev2 = 1;
  int num_ways = 0;

  // 0 or 1 step should always take only one step
  if (n == 0 || n == 1)
    return 1;

  // loop from 2 up to n keeping number of ways always
  // equal to previous 2 values
  for (int i = 2; i <= n; i++) {
    num_ways = prev1 + prev2;
    prev1 = prev2;
    prev2 = num_ways;
  }

  return num_ways;
}


/**
 * 
 */
int num_ways_X(int n, int x[], int x_count)
{
  int total = 0;

  if (n == 0)
    return 1;

  for (int i = 0; i < x_count; i++) {
    if ((n - i) >= 0)
      total += num_ways_X(n - x[i], x, x_count);
  }

  return total;
}


/**
 * 
 */
int num_ways_X_bottom_up(int n, int x[], int x_count)
{
  int *nums;

  if (n == 0 || n == 1)
    return 1;

  nums = malloc(sizeof(int) * (n + 1));
  nums[0] = 1;
  nums[1] = 1;
  for (int i = 2; i <= n; i++) {
    int total = 0;
    for (int j = 0; j < x_count; j++) {
      if (i - x[j] >= 0)
        total += nums[i - x[j]];
    }
    nums[i] = total;
  }

  for (int i = 0; i < n+1; i++) {
    printf("nums[%d] = %d\n", i, nums[i]);
  }

  return nums[n];
}
