#!/usr/bin/env python

def hasPairWithSum(data, sumToFind):
  '''
  Determine if the current value has a "compliment" which will add up to sumToFind.
  '''
  comp = set()
  for value in data:
    if value in comp:
      print('pair: value {}, comp {}'.format(value, sumToFind - value))
      return True
    
    comp.add(sumToFind - value)

  return False


if __name__ == '__main__':
  data = [1, 2, 4, 6]
  sumToFind = 8
  found = hasPairWithSum(data, sumToFind)
  print('found = {}'.format(found))
