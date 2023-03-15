#include "Lista.h"
#include <stdio.h>

int main(){
    Lista p1;
    crearLista(&p1);

    printf("Mostrando lista vacia \n");

    mostrarLista(p1);

    if (listaVacia(p1))
    {
        printf("La lista esta vacia \n");
    } else
    {
        printf("La lista no esta vacia \n");
    }

    //insertamos valores a la pila
    insertarLista(&p1, 5);
    insertarLista(&p1, 7);
    insertarLista(&p1, 9);
    insertarLista(&p1, 2);

    mostrarLista(p1);

    int valor = extraerLista(&p1);
    printf("Lista despues de extraer el %d\n", valor);
    mostrarLista(p1);


    borrarLista(&p1);
    printf("Borrando lista... \n");
    mostrarLista(p1);

    return 0;
}