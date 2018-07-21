#!/usr/bin/env python

'''
Given a list of numbers and a number k, return whether any two numbers
from the list add up to k.

For example, given [10, 15, 3, 7] and k of 17, return true since
10 + 7 is 17.

Bonus: Can you do this in one pass?
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
