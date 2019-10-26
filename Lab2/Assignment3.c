/*
  Calin Capitanu
  19 - September 2019
  Input: Integers as: size of array and then the array itself
  Output: The number of swaps in an array

 */


#include <stdio.h>
int nrswaps = 0;
void swap(int *arr, int i, int j){
  int inter = arr[i];
  arr[i] = arr[j];
  arr[j] = inter;
  nrswaps++; //just count in a global variable
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

  printf("The number of swaps: %d\n", nrswaps);
  
  

}
