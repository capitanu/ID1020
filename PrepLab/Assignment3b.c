//Calin Capitanu
// 30 - August - 2019
// This short program is going to print an array of elements in reverse order
// Input: Takes the length of the array first and then the array itself from stdin
// Output: Prints only the array in reverse order than the input. Printed to stdout
// Tests are conducted from the main method itself
// Does not exclude errors such as negative length of array. Works only for proper inputs


#include <stdio.h>
#include <stdlib.h>


void main(){
  printf("Input the length of the array: ");
  int len;
  scanf("%d", &len);
  int *arr = malloc(4*len);
  int i;
  for(i = 0; i < len; i++)
    scanf("%d", &arr[i]);
  for(i = len - 1; i >= 0; i--)
    printf("%d\n", arr[i]);
  }
