#include <stdio.h>
#include <limits.h> //define los limites de char, int, short
#include <float.h>  //define los limites de float 

int main(void){
	int i;
	double d1=7.299999944444, d2;
	float f1,f2;

	setvbuf(stdout, NULL, _IONBF, 0);

	printf ("char   : %lu bytes \t min=%d max=%d\n", sizeof(char), CHAR_MIN, CHAR_MAX) ;
	printf ("int    : %lu bytes \t min=%d max=%d\n", sizeof(int), INT_MIN, INT_MAX) ;
	printf ("float  : %lu bytes \t min=%E max=%E\n", sizeof(float), FLT_MIN, FLT_MAX) ;
	printf ("double : %lu bytes \t min=%E max=%E\n", sizeof(double), DBL_MIN, DBL_MAX) ;
	printf ("short int : %lu bytes \t min=%d max=%d\n", sizeof(short), SHRT_MIN, SHRT_MAX) ;
	printf ("long int : %lu bytes \t min=%ld max=%ld\n", sizeof(long), LONG_MIN, LONG_MAX) ;
	printf ("long double : %lu bytes\n", sizeof(long double)) ;
	printf ("int * : %lu bytes\n", sizeof(int *)) ; // Un puntero (da igual a que) almacena una direccion de memoria

	// Las variables en C no se inicializan
	printf("Variable i (int) sin inicializar: %d\n", i);
	printf("Variable f1 (float) sin inicializar: %g\n",f1);
	printf("Variable d2 (double) sin inicializar: %g\n",d2);

	return 0;
}
