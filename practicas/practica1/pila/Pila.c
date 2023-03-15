#include "Pila.h"
#include <stdio.h>
#include <stdlib.h>

void crear(Pila *p){
    *p=NULL;
}

void mostrar(Pila p){
    // implementacion con una pila auxiliar
    Pila aux = p;
    while (aux !=NULL)
    {
        printf("%d ", aux->valor);
        aux = aux->sig;
    }
    
    /*while (p !=NULL)
    {
        printf("%d\n", p->valor);
        p = p->sig;
    }*/
}

int pilaVacia(Pila p){
    return p == NULL;
}

void insertar(Pila *p, int v){
    Pila nuevo = (Pila)malloc(sizeof(struct Nodo));
    if (nuevo == NULL)
    {
        printf("Error insertar: memoria insuficiente");
        return;
    }
    nuevo->valor = v;
    nuevo->sig = *p;
    *p = nuevo;
    
}

int extraer(Pila *p){
    if (*p != NULL)
    {
        int valor = (*p)->valor;
        Pila aux = *p;
        *p = (*p)->sig;
        free(aux);
        return valor;
    } else
    {
        printf("No se puede extraer de la pila vacia");
        return -1;
    }
    
    
}

void borrar(Pila *p){
    while (!pilaVacia(*p))
    {
        extraer(p);
    }
    
}