#include "Lista.h"
#include "stdio.h"
#include "stdlib.h"

void crearLista(Lista *l)
{
    *l = NULL;
} // crea una lista vacía

void mostrarLista(Lista p)
{
    // implementacion con una pila auxiliar
    Lista aux = p;
    while (aux != NULL)
    {
        printf("%d ", aux->elem);
        aux = aux->sig;
    }
}

void recorrerLista(Lista l)
{
    Lista ptr;
    ptr = l; // ptr apunta al primer nodo de la lista

    while (ptr != NULL)
    {
        printf("%d", ptr->elem);
        ptr = ptr->sig;
    }
}

int listaVacia(Lista l)
{
    return l == NULL;
} // devuelve 0 sii la lista está vacía

void insertarLista(Lista *l, int elem)
{
    Lista nuevoNodo; // Para crear el nuevo nodo
    Lista ant, ptr;  // Para posicionarnos donde insertar

    // Creamos el nuevo nodo
    nuevoNodo = (Lista)malloc(sizeof(struct NodoLista)); // asigno memoria al nodo y parseo a tipo Lista
    nuevoNodo->elem = elem;                              // asigno valor

    // Buscamos donde insertar
    // 1- La lista l esta vacia
    if (*l == NULL)
    {
        nuevoNodo->sig = NULL; // el nodo no se enlaza a ninguno
        *l = nuevoNodo;        // la lista se inicializa con el nuevoNodo
    }
    else if (nuevoNodo->elem <= (*l)->elem)
    {
        // 2- Insertar al principio
        nuevoNodo->sig = *l; // nuevoNodo apunta
        *l = nuevoNodo;      // lista pone como primer nodo a nuevoNodo
    }
    else
    {
        // 3- Insertar en medio o final
        ant = *l;        // apunta al primer nodo de la lista
        ptr = (*l)->sig; // apunta al siguiente (delante de ant)
        while ((ptr != NULL) && (nuevoNodo->elem > ptr->elem))
        {
            ant = ptr;
            ptr = ptr->sig;
        }
        nuevoNodo->sig = ptr;
        ant->sig = nuevoNodo;
    }

    /*
    Lista nueva = (Lista)malloc(sizeof(struct NodoLista));

    nueva->elem = elem; //valor del nodo nuevo

    if (nueva == NULL)
    {
        printf("Error insertar - no hay memoria \n");
        return;
    }

    Lista actual = *l;
    Lista anterior = NULL;

    while (actual != NULL && actual->elem > elem)
    {
        anterior = actual;
        actual = actual->sig;
    }

    if (anterior == NULL) // para insertar en la primera posicion del array
    {
        nueva->sig = actual;
        *l = nueva;
    }
    else
    {
        // insercion final o intermedio
        anterior->sig = nueva;
        nueva->sig = actual;
    }
    */

} // inserta el elemento elem en la lista l de forma que quede ordenada de forma creciente

int extraerLista(Lista *l, int elem)
{
    Lista ptr;
    Lista ant;

    ptr = *l; // primer nodo de la lista
    ant = NULL;

    while (ptr != NULL && ptr->elem < elem)
    {
        ant = ptr;
        ptr = ptr->sig;
    }

    if (ptr == NULL || ptr->elem != elem)
    {
        // si el nodo actual esta vacio o no coincide con el elem que queremos sacar
        return 0;
    }
    else
    {
        if (ant == NULL)
        {
            *l = ptr->sig; // para el caso que sea el primer nodo
        }
        else
        {
            ant->sig = ptr->sig;
        }
        free(ptr); // libero memoria del nodo
        return 1;
    }

} // elimina de la lista el elemento elem. Devuelve 1 si se ha podido eliminar y 0, si elem no estaba en la lista.

int borrarLista(Lista *l)
{
    Lista aux;
    while (*l != NULL)
    {
        aux = *l;
        *l = (*l)->sig; // *l = aux->sig;
        free(aux);
    }
} // elimina todos los nodos de la lista y la deja vacía
