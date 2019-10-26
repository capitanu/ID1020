/*
  Calin Capitanu
  19 September 2019
  Input: Size of the array and then the array
  Output: none
  This code sorts an array with either mergesort or quicksort.

*/


#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void swap(int *arr, int i, int j){
  int temp = arr[i];
  arr[i] = arr[j];
  arr[j] = temp;
    
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
  int j = partition(arr, low ,high);
  qSort(arr, low, j - 1);
  qSort(arr, j+1, high);		   

}
void quickSort(int *arr, int low, int high){
  shuffle(arr, high-low+1);
  qSort(arr, low , high);
}

int *aux;
void merge(int *arr, int low, int mid, int high){
  int i = low, j = mid + 1, k;

  for( k = low; k <= high; k++)
    aux[k] = arr[k];

  for( k = low; k <= high; k++)
    if(i > mid)
      arr[k] = aux[j++];
    else if(j > high)
      arr[k] = aux[i++];
    else if(aux[j] < aux[i])
      arr[k] = aux[j++];
    else
      arr[k] = aux[i++];
}

void mergeSort(int *arr, int low, int high){
  if(high <= low) return;
  int mid = low + (high - low)/2;
  mergeSort(arr, low, mid);
  mergeSort(arr, mid+1, high);
  merge(arr, low, mid, high);
}




void main(){
  int size;
  scanf("%d", &size);
  int *arr_quick = malloc(size*4);
  int *arr_merge = malloc(size*4);
  int i;
  aux = malloc(4*size);
  for( i = 0; i < size; i++ ){
    scanf("%d", &arr_merge[i]);
    arr_quick[i] = arr_merge[i];
  }
  
  

  
  //mergeSort(arr_merge, 0, size - 1);printf("Sorted with Merge Sort: \n");

  /* for(i = 0; i < size - 1; i++)  */
  /* printf("%d, ", arr_merge[i]);  */
  /* printf("%d\n", arr_merge[i]);  */  

  quickSort(arr_quick, 0 , size -1);printf("Sorted with Quick sort \n");
  
  /* for(i = 0; i < size - 1; i++) */
  /*   printf("%d, ", arr_quick[i]); */
  /* printf("%d\n", arr_quick[i]); */
  free(arr_quick);
  free(arr_merge);
  free(aux);
 
}

  
