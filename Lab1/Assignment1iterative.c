// Calin Capitanu
//  7 - September - 2019
/*    Input: String of characters from stdin
      Output: Same string but reversed to stdout
      The program uses a stack (in form of an array) in order to save all the characters and then print them in reverse order


*/
#include <stdio.h>

char arr[50];

int hasNext(int i){
  return arr[i+1];
}
int next(int i){
  return arr[i++];
}

void printArrRev(int j){
  for(;j >= 0; j--)
    putchar(arr[j]);

}

void main(){
  char c = getchar();
  int i = 0;
  while(c != '\n'){
    arr[i++] = c;
    c = getchar();
  }
  printArrRev(--i);
  putchar('\n');

}
