/*
  Calin Capitanu
  19 - September 2019
  Input: Integers as: size of array and then the array itself
  Output: The array being sorted, the number of swaps as well as the number of inversions.
  Number of inversions is equal with the number of each smaller element to the right of each element


*/

#include <stdio.h>
int nrswaps = 0;
void swap(int *arr, int i, int j){
  int inter = arr[i];
  arr[i] = arr[j];
  arr[j] = inter;
  nrswaps++;
}

void countInversions(int *arr, int size){
  int i,j,numberOfInversions = 0;
  for(i = 0; i < size; i++){
    for(j = i + 1; j < size; j++)
      if(arr[i] > arr[j]){
	numberOfInversions++;
	printf("[%d, %d] - [%d, %d]\n", i, arr[i], j, arr[j]);

      }
  }
  printf("Number of inversions: %d\n", numberOfInversions);
}

void main(){
  int size;
  scanf("%d", &size);
  int arr[size], i = 0;
  for(; i < size; i++)
    scanf("%d", &arr[i]);
  int j;
  countInversions(arr,size);
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
