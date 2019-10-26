/*
  Calin Capitanu
  19 September 2019
  Input: Integers such as: size and array
  Output: none
  This code uses quicksort but instead of going all the way to 1 element, it cuts-off at 10-30 (set manually) and goes to insertion sort
  There is not much difference in time.

 */


#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void swap(int *arr, int i, int j){
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
    
}

void insertionSort(int *arr, int size){
  int i,j;
   for(i = 1; i < size; i++){
    for(j = i; j > 0; j--)
      if(arr[j] < arr[j-1])
	swap(arr, j-1 , j);
   }
}

  
int partition(int *arr, int low, int high){
  int i = low, j = high + 1;
  int v = arr[low];
  while(1){
    while(arr[++i] < v)
      if(i == high) break;
    while(v < arr[--j])
      if(j == low) break;
    if(i >= j) break;
    swap(arr, i, j);
  }
  swap(arr, low, j);
  return j;
}

void shuffle(int *arr, int size){
  if(size < 1)
    return;
  srandom(time(NULL));
  int i,j;
  for( i = 0; i < size; i++){
    j = i + (int) random()/(RAND_MAX / (size - i) + 1);
    swap(arr,i,j);
  }
    
}
  
  

void qSort(int *arr, int low, int high){
  if( high <= low ) return;
  if( high-low <= 30){
    insertionSort(arr, high-low+1);
    return;
  }
  int j = partition(arr, low ,high);
  qSort(arr, low, j - 1);
  qSort(arr, j+1, high);		   

}
void quickSort(int *arr, int low, int high){
  shuffle(arr, high-low+1);
  qSort(arr, low , high);
}

int *aux;

void main(){
  int size;
  scanf("%d", &size);
  int *arr_quick = malloc(size*4);
  int i;
  aux = malloc(4*size);
  for( i = 0; i < size; i++ ){
    scanf("%d", &arr_quick[i]);
  }
  

  time_t seconds = time(NULL);

   /* printf("Sorted with Quick sort: \n");  */
  
  quickSort(arr_quick, 0 , size -1);
  printf("Time for quicksort: %ld\n", time(NULL)-seconds);
  
   /* for(i = 0; i < size - 1; i++)   */
   /* printf("%d, ", arr_quick[i]);   */
    /* printf("%d\n", arr_quick[i]);   */
  free(arr_quick);
  free(aux);
 
}

  
