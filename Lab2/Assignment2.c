/*
  Calin Capitanu
  19 - September 2019
  Input: Integers as: size fo array and then the array itself
  Output: The array sorted in descending order

 */


#include <stdio.h>

void swap(int *arr, int i, int j){
  int inter = arr[i];
  arr[i] = arr[j];
  arr[j] = inter;
}
void sort(int *arr,int size){
  int i,j;
  for(i = 1; i < size; i++){
    for(j = i; j > 0; j--)
      if(arr[j] < arr[j-1])
	swap(arr, j-1 , j);
 
  }
  

}
void main(){
  int size;
  scanf("%d", &size);
  int arr[size], i = 0;
  for(; i < size; i++)
    scanf("%d", &arr[i]);
  for(i = 0; i < size; i++){
    arr[i] *= -1;
  }
  sort(arr,size);
  for(i = 0; i < size; i++){
    arr[i] *= -1;

  }
  for(i = 0; i < size; i++){
    if(i == size - 1){
      printf("[%d]", arr[i]);
      break;
    }
    printf("[%d], ", arr[i]);}
  printf("\n");
    
  
  

}
