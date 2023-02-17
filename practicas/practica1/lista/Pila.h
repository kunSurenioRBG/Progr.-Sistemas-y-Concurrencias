/*
 * Pila.h
 *
 *  Created on: 15 feb. 2023
 *      Author: mmar
 */

#ifndef PILA_H_
#define PILA_H_
typedef struct Nodo * Pila; // pila es un puntero a un registro llamado 'Nodo'. 
struct Nodo{ 
	int valor;
	Pila sig;
};

void crear(Pila *p);
void mostrar(Pila p);
int pilaVacia(Pila p);
void insertar(Pila *p, int v);
int extraer(Pila *p);
void borrar(Pila *p);


#endif /* PILA_H_ */
