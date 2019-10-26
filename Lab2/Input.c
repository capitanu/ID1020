/*
  Calin Capitanu
  19 September 2019
  Input: Size of the array and max value
  Output: An array of the specified size with random numbers from 0 to specified max size
  The code uses random instead of rand because rand is supposedly a bad random generator (first few bits rarely differ so there is a high chance that you get a specific range of number always)
  Seed in this case is going to be system time for more randomness.
  
  
 */


#include <stdio.h>
#include <stdlib.h>
#include <time.h>
void main(){
  long size, max, i;
  scanf("%ld", &size);
  scanf("%ld", &max);
  printf("%ld\n", size);
  srandom(time(NULL));
  for(i = 0; i < size; i++)
    printf("%ld\n", (long)((max + 1) * ((double) random()/ ((double)RAND_MAX + 1))));

}
