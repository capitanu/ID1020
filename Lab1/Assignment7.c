// Calin Capitanu
// 7 - Septmeber - 2019
/*      Input: Should be a file towards stdin
	Output: Prints out to stdout if the paranthesis are balanced or not.
	This program only counts and saves all the paranthesis in an array and then pops them when the other sided paranthesis appear and checks if they are the same. Used ADT: stack


 */



#include <stdio.h>

char a[100];
int size = 0;

char pop(){
  return a[--size];
}
void push(char last){
  a[size++] = last;
}


void main(){
  char test,c;
  while((c = getchar()) != EOF){
    if(c == '(' || c == '[' || c == '{'){
      push(c);
    }
    if(c == ')' || c == ']' || c == '}'){
      test = pop();
      switch(c){
      case ')':
	if(test != '('){
	  printf("The code is not balanced\n");
	  return;
	}
	break;
	
      case ']':
	if(test != '['){
	  printf("The code is not balanced\n");
	  return;
	}
	break;
	
      case '}':
	if(test != '{'){
	  printf("The code is not balanced\n");
	  return;
	}
	break;
      default:
	break;
      }
    }
  }
  if(size != 0)
    printf("The code is not balanced\n");
  else
    printf("The code is balanced\n");
}












 
