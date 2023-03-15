/*
 * Planta.c
 *
 *  Created on: 9 abr. 2021
 *      Author:
 */

#include "Planta.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * crea una lista de habitaciones vacia
 */
void crear(ListaHab *lh)
{
    *lh = NULL;
}

/**
 * En esta función se añade a la lista, la habitación con número nh,
 * cliente "nombre" y fecha de salida "fs".
 * Si ya existe una habitación numerada con nh en la lista se actualizan sus campos
 * con los nuevos valores ("nombre" y "fs").
 * Si no existe ninguna habitación con ese número, se inserta un nuevo nodo de manera que
 * la lista siempre esté ordenada con respecto a los números de las habitaciones (de
 * menor a mayor)
 */
void nuevoCliente(ListaHab *lh, unsigned nh, char *nombre, unsigned fs)
{
    ListaHab act = *lh;
    ListaHab ant = NULL;

    // vamos recorriendo todos los nodos de la lista, hasta encontrar
    // uno cuyo numero de habitacion sea menor que nh
    while (act != NULL && act->numHab < nh)
    {
        ant = act;
        act = act->sig;
    }

    if (act != NULL && act->numHab == nh)
    {
        /*NOTA IMPORTANTE
        El motivo de usar strcpy es debido a que los string se escriben directamente en el ejecutable, debido a que el valor del string
        debe ser conocido en tiempo de compilacion. La localizacion del string y de su puntero estan en el ejecutable, ejecutable que una vez
        se inicie sera solo de lectura.
        Por tanto, lo que debemos hacer es reservar espacio de memoria en una zona que no solo sea de lectura (en la heap/monticulo o en el stack/pila de llamada)
        Podemos copiar manualmente los datos, reservando memoria en el heap y luego usar strcpy para copiar el string a esa memoria reservada.*/
        strcpy(act->nombre, nombre); // copia nombre en act->nombre
        act->fechaSalida = fs;
    }
    else
    {
        ListaHab clienteNuevo = (ListaHab)malloc(sizeof(struct Nodo));
        strcpy(clienteNuevo->nombre, nombre);
        clienteNuevo->numHab = nh;
        clienteNuevo->fechaSalida = fs;
        clienteNuevo->sig = act;

        if (ant == NULL)
        {
            *lh = clienteNuevo;
        }
        else
        {
            ant->sig = nuevoCliente;
        }
    }
}

/**
 * Escribe por la pantalla la información de cada una de las habitaciones
 * almacenadas en la lista.
 * El formato de salida debe ser:
 * \t habitacion "nh" ocupada por "nombre" con fecha de salida "fs"
 */
void imprimir(ListaHab lh)
{
    while (lh != NULL)
    {
        printf("\t Habitacion %d ocupada por %s con fecha de salida %d", lh->numHab, lh->nombre, lh->fechaSalida);
        lh = lh->sig;
    }
}

/**
 * Borra todos los nodos de la lista y la deja vacía
 */
void borrar(ListaHab *lh)
{
    ListaHab aux = *lh;
    while ((*lh != NULL))
    {
        aux = *lh;
        *lh = (*lh)->sig;
        free(aux);
    }
}

/**
 * Borra todos las habitaciones cuya fecha de salida es fs.
 */

void borrarFechaSalida(ListaHab *lh, unsigned fs)
{
    ListaHab act = *lh;
    ListaHab ant = NULL;

    while (act != NULL)
    {
        if (act->fechaSalida == fs)
        {
            if (ant == NULL)
            {
                *lh = act->sig;
                free(act);
            }else
            {
                ant->sig = act->sig;
            }
        }
        ant = act;
        act = act->sig;
    }
    
    
}
