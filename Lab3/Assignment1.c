/*
  Calin Capitanu 
  23 September 2019
  Input: A text file
  Output: The same text file but every symbol other that ' ', '\n' or letters are replaced with ' '
 */


#include <stdio.h>
#include <ctype.h>

void main(){
  char t;
  while((t = getchar()) != EOF){
    if(!isalpha(t) && t != ' ' && t != '\n')
      putchar(' ');
    else
      putchar(t);
  }
}
