//
// Fibonacci sequence calculated iteratively using bottom up
// This makes the calculation ultra fast
//

#include <stdio.h>
#include <stdlib.h>

long fib(long n);

//
// main program entry point
//
int main(int argc, char **argv)
{
  long n = 1000;
  printf("fib = %ld\n", fib(n));
}


//
// Fibonacci sequence calculated iteratively using bottom up
//
long fib(long n)
{
  long prev1 = 1;
  long prev2 = 1;
  long result = 0;

  if (n == 0 || n == 1)
    return 1;

  for (long i = 2; i < n; i++) {
    result = prev1 + prev2;
    prev1 = prev2;
    prev2 = result;
  }

  return result;
}
