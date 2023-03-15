/*
 * driverPlanta.c
 *
 *  Created on: 9 abr. 2021
 *      Author: mmar
 */

/*NOTA IMPORTANTIIIISIMA
#include "Planta.h -> ES LO QUE SIEMPRE SE DEBE PONER
pero los de vscode son especiales y el compilador falla si llamo el .h*/
#include "Planta.h"
#include <stdio.h>

int main(){

	ListaHab lh;
	crear(&lh);
	nuevoCliente(&lh,3,"Pepa Ruiz",25);
	nuevoCliente(&lh,5,"Juan Lena",22);
	nuevoCliente(&lh,1,"Mara Jaime",25);
	nuevoCliente(&lh,2,"Susana Cintra",23);
	nuevoCliente(&lh,1,"Mara Jaime",26);
	nuevoCliente(&lh,4,"Carmen Pasa",26);
	nuevoCliente(&lh,7,"Felipe Moreno",26);
	imprimir(lh);
	printf("\n\nAhora borramos los que salen el día 26\n\n");
	borrarFechaSalida(&lh,26);
	imprimir(lh);
	printf("\n\nAhora borramos toda la lista\n\n");
	borrar(&lh);
	imprimir(lh);
	return 1;
}
