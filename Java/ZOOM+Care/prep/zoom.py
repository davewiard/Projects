import sys
import json
import time

#secs_per_day = 60 * 60 * 24
#millis = int(round((time.time() + secs_per_day) * 1000))
millis = int(round(time.time() * 1000))

if __name__ == '__main__':
  with open(sys.argv[1]) as infile:
    data = json.load(infile)

  data['scheduleDate'] = millis

  with open(sys.argv[1], 'w') as outfile:
    json.dump(data, outfile, indent=2)
