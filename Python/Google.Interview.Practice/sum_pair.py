#!/usr/bin/env python

'''
This problem was found online in Google interview question video but was
also delivered to me by Daily Coding Problem: Problem #1.
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
