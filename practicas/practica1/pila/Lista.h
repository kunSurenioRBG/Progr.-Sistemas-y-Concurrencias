/*
 * Lista.h
 *
 *  Created on: 23 mar. 2022
 *      Author: mmar
 */

#ifndef LISTA_H_
#define LISTA_H_

typedef struct NodoLista* Lista;

struct NodoLista{
	int elem;
	Lista sig;
};
void crearLista(Lista *l); //crea una lista vacía

void recorrerLista(Lista l); //escribe en la pantalla los elementos de la lista

void recorrerListaR(Lista l);

int listaVacia(Lista l); //devuelve 0 sii la lista está vacía

void insertarLista(Lista *l,int elem); // inserta el elemento elem en la lista l de forma que quede ordenada de forma creciente

void insertarListaR(Lista *l,int elem);

int extraerLista(Lista *l,int elem); // elimina de la lista el elemento elem. Devuelve 1 si se ha podido eliminar y 0, si elem no estaba en la lista.

int extraerListaR(Lista *l,int elem);

int borrarLista(Lista *l); //elimina todos los nodos de la lista y la deja vacía
	
int borrarListaR(Lista *l);

#endif /* LISTA_H_ */
