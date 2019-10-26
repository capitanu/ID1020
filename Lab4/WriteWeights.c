
#include <stdio.h>
#include <stdlib.h>

void main(){
  int f;
  scanf("%d", &f);
  char c = getchar();
  printf("107\n");
  
  while((c = getchar()) != EOF){
    if(c == '\n'){
      putchar(' ');
      printf("%f", ((double)random()) / ((double) RAND_MAX + 1) );
    }
    putchar(c);
  }

}
