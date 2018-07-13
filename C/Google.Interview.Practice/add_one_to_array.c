#include <stdio.h>
#include <stdlib.h>

int *add_one(int *input, int *length);

//
// main program entry point
//
int main(int argc, char **argv)
{
  int input[] = { 9, 9 };
  int length = sizeof(input)/sizeof(input[0]);

  for (int i = 0; i < length; i++) {
    printf("input[%d] = %d\n", i, input[i]);
  }
  printf("\n");

  int *result = add_one(input, &length);
  for (int i = 0; i < length; i++) {
    printf("result[%d] = %d\n", i, result[i]);
  }

  free(result);
}


//
// [1, 2, 4] -> [1, 2, 5]
// [1, 9, 9] -> [2, 0, 0]
// [9, 9, 9] -> [1, 0, 0, 0]
//
int *add_one(int *input, int *length)
{
  int carry = 1;
  int *result = malloc(sizeof(int) * (*length));

  for (int i = *length - 1; i >= 0; i--) {
    int sum = input[i] + carry;
    carry = (sum == 10) ? 1 : 0;
    result[i] = sum % 10;
    if (carry == 0)
      break;
  }

  // carry will be 1 if input was [9, 9, 9]
  // need a longer output array
  if (carry == 1) {
    free(result);
    result = malloc(sizeof(int) * (*length + 1));
    for (int i = 1; i < *length + 1; i++) {
      result[i] = 0;
    }
    result[0] = 1;
    ++(*length);
  }

  return result;
}
