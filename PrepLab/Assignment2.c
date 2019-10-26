// Calin Capitanu
// 30 - August - 2019
// Replaces each 'a' with 'X' read from a file names 'task1' and prints that to the stdout
// Other characters are printed as they are
// Input: text file (linux created - no .txt) containing a string of characters
// Output: all characters as they are except for 'a' which is replaced by 'X', to stdout
// The test is basically included in the functionality of the function itself

#include <stdio.h> 

void main(){
  
  FILE *fl;
  fl = fopen("task1","r");
  int c = getc(fl);
  while(!feof(fl))
    {
      if(c == 'a')
	printf("X");
      else
	printf("%c", c);
      c = getc(fl);
    }
  fclose(fl);

}

