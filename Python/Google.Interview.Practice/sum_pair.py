#!/usr/bin/env python

'''
This problem was given by Google as a sample interview question video but was
also delivered to me by Daily Coding Problem: Problem #1.

The Google video solved this problem in C++ but I chose Python. I have also
now enhanced the script to generate the input list as 100 random numbers
between [1, 100]
'''

import random

def hasPairWithSum(data, sumToFind):
  '''
  Determine if the current value has a "compliment" which will add up to sumToFind.
  '''
  comp = set()
  for value in data:
    if value in comp:
      # pair found, return a tuple of the two values
      return (value, sumToFind - value)
    
    comp.add(sumToFind - value)

  # no pair found
  return None


if __name__ == '__main__':
  data = [random.randint(1, 100) for _ in range(100)]
  print("input =", data)
  sumToFind = 8
  pair = hasPairWithSum(data, sumToFind)
  print('found = {}'.format(pair))
