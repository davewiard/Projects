#include <stdio.h>
#include <stdbool.h>


bool pair_found_sorted(const int arr[], const int arr_length, const int sum_to_find);


//
// program entry point
//
int main(int argc, char *argv[])
{
  int arr[] = { 1, 4, 2, 4 };
  int sum = 8;

  bool found = pair_found_sorted(arr, (sizeof(arr) / sizeof(arr[0])), sum);
  printf("Pair found? %s\n", (found) ? "Yes" : "No");

  return 0;
}


//
// Pseudo code to determine if the current index has a "compliment" which will add
// up to 'sum_to_find' has been seen in the array already. I use pseudo-code because
// the idea is to use a hash set which C does not have natively and the code is
// too large to implement here.
//
bool pair_found_unsorted(const int arr[], const int arr_length, const int sum_to_find)
{
  // hashset compliments;
  // for (int i = 0; i < arr_length; i++) {
  //   int compliment = sum_to_find - arr[i];
  //   if (compliments.hasValue(compliment)) {
  //     return true;
  //   }

  //   compliments.add(compliment);
  // }

  // return false;
}


//
// Determine if any pair of integers in 'arr' can be added to equal 'sum_to_find'.
// This solution works well for an input that is guaranteed to be sorted.
//
bool pair_found_sorted(const int arr[], const int arr_length, const int sum_to_find)
{
  int low = 0;
  int high = arr_length - 1;

  while (low < high) {
    int sum = arr[low] + arr[high];
    if (sum == sum_to_find) {
      return true;  // pair found that adds up to requested sum
    } else if (sum > sum_to_find) {
      high--;
    } else {
      low++;
    }
  }

  return false;
}