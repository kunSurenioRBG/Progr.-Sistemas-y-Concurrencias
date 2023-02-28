#include <stdio.h>
#include "arbolbb.h"
// Crea la estructura utilizada para gestionar la memoria disponible.
void Crear(T_Arbol *arbol)
{
    *arbol = NULL;
}

// DUDA (recursividad del arbol)
//  Destruye la estructura utilizada.
void Destruir(T_Arbol *arbol)
{
    if (*arbol != NULL)
    {
        T_Arbol der = (*arbol)->der;
        Destruir(&((*arbol)->izq));
        Destruir(&der);
        free(*arbol);
    }
}

// Inserta num en el arbol
void Insertar(T_Arbol *arbol, unsigned num)
{
    if (*arbol != NULL)
    {
        *arbol = (T_Arbol)malloc(sizeof(struct T_Nodo));
        if (*arbol == NULL)
        {
            perror("Error al insertar");
        }
        else
        {
            (*arbol)->dato = num;
            (*arbol)->der = NULL;
            (*arbol)->izq = NULL;
        }
    }
    else // caso recursivo
    {
        if (((*arbol)->dato > num))
        {
            Insertar(&((*arbol)->izq), num);
        }
        else if (((*arbol)->dato < num))
        {
            Insertar(&((*arbol)->der), num);
        }
    }
}
// Muestra el contenido del �rbol en InOrden
void Mostrar(T_Arbol arbol)
{
    if (arbol != NULL)
    {
        Mostrar(arbol->izq);
        printf("%u", arbol->dato);
        Mostrar(arbol->der);
    }
}
// Guarda en disco el contenido del arbol
void Salvar(T_Arbol arbol, FILE *fichero)
{
    if (arbol != NULL)
    {
        Salvar(arbol->izq, fichero);
        fwrite(&arbol->dato, sizeof(int), 1, fichero);
        Salvar(arbol->der, fichero);
    }
}
