/*
 ============================================================================
 Name        : Practica2B.c
 Author      : esc
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "arbolbb.h"

/**
 * Pide un n�mero "tam" al usuario, y
 * crea un fichero binario para escritura con el nombre "nfichero"
 * en que escribe "tam" numeros (unsigned int) aleatorios
 * Se utiliza srand(time(NULL)) para establecer la semilla (de la libreria time.h)
 * y rand()%100 para crear un n�mero aleatorio entre 0 y 99.
 */
void creafichero(char *nfichero)
{
	int tam, num;
	printf("Introduce cuantos numeros quieres generar: \n");
	scanf("%d", &tam); // asumimos que tam > 0

	srand(time(NULL));				 // coge el tiempo del sistema para generar una semilla aleatoria
	FILE *f = fopen(nfichero, "wb"); // abrir fichero, apuntando a el con un puntero. "wb" = para indicar que vamos a escribir sobre el

	// comprobar si el fichero es nulo
	if (f == NULL)
	{
		perror("Error al crear el fichero de numeros aleatorios");
	}
	else
	{
		for (int i = 0; i < tam; i++)
		{
			num = rand() % tam;				 // rand = numero aleatorio entre 0 y tam - 1
			fwrite(&num, sizeof(int), 1, f); // escribir en num, 1 vez en el archivo f
		}
		fclose(f);
	}
}
/**
 * Muestra por pantalla la lista de n�meros (unsigned int) almacenada
 * en el fichero binario "nfichero"
 */
void muestrafichero(char *nfichero)
{
	// NOTA: rb y wb solo es aplicable para archivos binarios
	FILE *f = fopen(nfichero, "rb");

	// compruebo que se abra bien
	if (f == NULL)
	{
		perror("Error al mostrar el fichero");
	}
	else
	{
		int num;
		while (fread(&num, sizeof(int), 1, f))
		{
			printf("%d ", num);
		}
		printf("\n");
		fclose(f);
	}
}

/**
 * Guarda en el arbol "*miarbol" los n�meros almacenados en el fichero binario "nfichero"
 */

void cargaFichero(char *nfichero, T_Arbol *miarbol)
{
	FILE *f = fopen(nfichero, "rb");

	int num;
	while (fread(&num, sizeof(int), 1, f))
	{
		Insertar(miarbol, num);
	}
	fclose(f);
}

int main(void)
{
	char nfichero[50];
	printf("Introduce el nombre del fichero binario:\n");
	fflush(stdout);
	scanf("%s", nfichero);
	fflush(stdin);
	creafichero(nfichero);
	printf("\n Ahora lo leemos y mostramos\n");
	muestrafichero(nfichero);
	fflush(stdout);

	printf("\n Ahora lo cargamos en el arbol\n");
	T_Arbol miarbol;
	Crear(&miarbol);
	cargaFichero(nfichero, &miarbol);
	printf("\n Y lo mostramos ordenado\n");
	Mostrar(miarbol);
	fflush(stdout);
	printf("\n Ahora lo guardamos ordenado\n");
	FILE *fich;
	fich = fopen(nfichero, "wb");
	Salvar(miarbol, fich);
	fclose(fich);
	printf("\n Y lo mostramos ordenado\n");
	muestrafichero(nfichero);
	Destruir(&miarbol);

	return EXIT_SUCCESS;
}
