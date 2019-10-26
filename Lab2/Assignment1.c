/*
  Calin Capitanu
  19 - September 2019
  Input: Integers as: size of array and then array
  Output: The array as it is being sorted

 */

#include <stdio.h>

void swap(int *arr, int i, int j){
  int inter = arr[i];
  arr[i] = arr[j];
  arr[j] = inter;
}

void main(){
  int size;
  scanf("%d", &size);
  int arr[size], i = 0;
  for(; i < size; i++)
    scanf("%d", &arr[i]);
  int j;
  for(i = 1; i < size; i++){
    for(j = i; j > 0; j--)
      if(arr[j] < arr[j-1])
	swap(arr, j-1 , j);
    for(j = 0; j < size; j++){
      if(j == size - 1){
	printf("[%d]", arr[j]);
	break;
      }
      printf("[%d], ", arr[j]);}
    printf("\n");     
    
  }
  
  
  

}
