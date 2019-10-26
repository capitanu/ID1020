/*
  Calin Capitanu
  19 - September 2019
  Input: Integers as follows: size of the array and then the array itself
  Output: The array partitioned in 3 ways: first it is negative numbers, then 0's and then positive numbers
  This code traverses the array twice: once for any negative numbers it swaps them into the beggining, saves the index and then it traverses it starting from that index to place the 0's.
*/


#include <stdio.h>

void swap(int *arr, int i, int j){
  int inter = arr[i];
  arr[i] = arr[j];
  arr[j] = inter;
  }

void separate(int *arr, int size){
  int i,i1=0;

  //Loop invariant: On the left of i1, there is always gonna be negative numbers
  for(i = 0; i < size; i++)
    if(arr[i] < 0){
      swap(arr,i,i1);
      i1++;
    }
  //Loop invariant: On the left of i1, there is always gonna be negative numbers

  
  
  //Loop invariant 2: everything on the left of i1 is either negative or 0
  for(i = i1; i < size; i++)
    if(arr[i] == 0){
      swap(arr,i,i1);
      i1++;
	}
  //Loop invariant 2: everything on the left of i1 is either negative or 0  
  

  for(i = 0; i < size-1; i++)
    printf("%d, ", arr[i]);
  printf("%d\n", arr[size-1]);
}

void main(){
  int size;
  printf("Input size: ");
  scanf("%d", &size);
  int arr[size], i = 0;
  for(; i < size; i++){
    scanf("%d", &arr[i]); 
    }
  separate(arr,size);
  
}
