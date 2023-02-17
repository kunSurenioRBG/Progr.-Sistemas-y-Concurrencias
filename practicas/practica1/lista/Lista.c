#include "Lista.h"
#include "stdio.h"
#include "stdlib.h"

void crearLista(Lista *l){
    *l = NULL;
} //crea una lista vacía

void recorrerLista(Lista l){

} //escribe en la pantalla los elementos de la lista

void recorrerListaR(Lista l){

}

int listaVacia(Lista l){
    return l == NULL;
} //devuelve 0 sii la lista está vacía

void insertarLista(Lista *l,int elem){
    Lista nueva = (Lista)malloc(sizeof(struct NodoLista));
    
    if (nueva == NULL)
    {
        printf("Insertar error - no hay memoria \n");
        return;
    }

    nueva->elem = elem;
    Lista actual = *l;
    Lista anterior = NULL;

    while (actual != NULL && actual->elem > elem)
    {
        anterior = actual;
        actual = actual->sig;
    }

    if (anterior == NULL) //para insertar en la primera posicion del array
    {
        nueva->sig = actual;
        *l = nueva;
    } else
    {
        //insercion final o intermedio
        anterior->sig = nueva;
        nueva->sig = actual;
    }

} // inserta el elemento elem en la lista l de forma que quede ordenada de forma creciente

void insertarListaR(Lista *l,int elem){

}

int extraerLista(Lista *l,int elem){

} // elimina de la lista el elemento elem. Devuelve 1 si se ha podido eliminar y 0, si elem no estaba en la lista.

int extraerListaR(Lista *l,int elem){

}

int borrarLista(Lista *l){

} //elimina todos los nodos de la lista y la deja vacía
	
int borrarListaR(Lista *l){

}