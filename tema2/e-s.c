#include <stdio.h>
#include <stdlib.h>
#include <math.h>

// Operador de Dirección (&): Este nos permite acceder a la dirección de memoria de una variable.
// Operador de Indirección (*): Además de que nos permite declarar un tipo de dato puntero, también nos permite ver el VALOR que está en la dirección asignada.

int main(void)
{
    int valor = 69;
    printf("El numero favorito de Santi-Kun es = %d\n", valor);

    char cadena[20] = "hola me llamo Yago";
    printf("Valor de cadena = %s\n", cadena);

    int v1 = 3;
    int v1_2 = 392;
    double v2 = 5.3;

    printf("%5d%f \n", v1, v2);
    printf("%05d%f \n", v1, v2); // muestra cero en los espacios vacios
    printf("%x \n", v1_2);       // hexadecimal

    // str almacena un puntero con una direccion de memoria al array de char
    char str[80];

    sprintf(str, "El valor de PI es: %.50f", M_PI); // .<seguido de numero> --> numero de digitos que seran escritos
    puts(str);

    // ejemplo con scanf - sumador (de tres valores)
    // double suma, v1_3;
    // suma = 0;
    // int total = 0;

    // while (total != 3)
    // {
    //     scanf("%lf", &v1_3);
    //     printf("\t%.2f\n", suma += v1_3);
    //     total++;
    // }

    // ejemplo con getchar / putchar (igual que scanf / printf) - imprime char hasta que el siguiente sea f
    // char c;
    // while ((c = getchar()) != 'f')
    // {
    //     putchar(c);
    // }

    return EXIT_SUCCESS;
}