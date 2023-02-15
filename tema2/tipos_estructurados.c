#include <stdio.h>
void intercambiar (int* pa, int* pb){
    int aux = *pa;
    *pa=*pb;
    *pb=aux;
}

int main(){
    int a = 5;
    int b = 8;

    intercambiar(&a,&b);

    printf("%d,%d\n", a,b);
}