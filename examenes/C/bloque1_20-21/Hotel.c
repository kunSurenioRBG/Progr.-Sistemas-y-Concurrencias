/*
 * Hotel.c
 *
 *  Created on: 9 abr. 2021
 *      Author: Santiago Ponce Arrocha
 */

#include "Hotel.h"
#include "Planta.h"

#ifndef HOTEL_C_
#define HOTEL_C_

void crearHotel(ListaHab *h, unsigned NPlantas)
{
    for (int i = 0; i < NPlantas; i++)
    {
        crear(&(h[i]));
    }
}
void nuevoClienteHotel(ListaHab *h, unsigned NPlantas, unsigned nh, char *nombre, unsigned fs)
{
    if (nh / 10 < NPlantas && fs > 0 && fs < 32)
    {
        int planta = nh / 10;
        nuevoCliente(&(h[planta]), nh, nombre, fs);
    }
}
void imprimirHotel(ListaHab *h, unsigned NPlantas)
{
    for (int i = 0; i < NPlantas; i++)
    {
        printf("\n Planta numero %d", i);
        imprimir((h[i])); // no paso por referencia (&) porque no voy a modificar nada
    }
}
void borrarHotel(ListaHab *h, unsigned NPlantas)
{
    for (int i = 0; i < NPlantas; i++)
    {
        borrar(&(h[i]));
    }
}

void borrarFechaSalidaHotel(ListaHab *h, unsigned NPlantas, unsigned fs)
{
    for (int i = 0; i < NPlantas; i++)
    {
        borrarFechaSalida((&(h[i])), fs);
    }
}
#endif /* HOTEL_C_ */
