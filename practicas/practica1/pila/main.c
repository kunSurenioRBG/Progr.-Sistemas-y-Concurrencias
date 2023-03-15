#include "Pila.h"
#include <stdio.h>

int main(){
    Pila p1;
    crear(&p1);

    printf("Mostrando pila vacia \n");

    mostrar(p1);

    if (pilaVacia(p1))
    {
        printf("La pila esta vacia \n");
    } else
    {
        printf("La pila no esta vacia \n");
    }

    //insertamos valores a la pila
    insertar(&p1, 5);
    insertar(&p1, 7);
    insertar(&p1, 9);
    insertar(&p1, 2);

    mostrar(p1);

    int valor = extraer(&p1);
    printf("Pila despues de extraer el %d\n", valor);
    mostrar(p1);


    borrar(&p1);
    printf("Borrando pila... \n");
    mostrar(p1);

    return 0;
}