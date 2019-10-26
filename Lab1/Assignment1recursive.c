// Calin Capitanu
// 7 - Septmeber - 2019
/*      Input: A string of characters from stdin
	Output: Same string to stdout but in reverse order
	This program prints a string of chars in reverse order using recursion
 */


#include <stdio.h>

void recursive(){
  char c = getchar();
  if(c == '\n') return;
  else recursive();
  putchar(c);

}


void main(){
  recursive();
  putchar('\n');

}
