#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Tipos enumerados
enum color {ROJO, VERDE, AZUL}; //Asigna nombres a valores int constates, por defecto empieza en 0 (ROJO==0 ... VERDE==2)
//enum color {ROJO=2, VERDE=1, AZUL=3}; //Tambien podemos asignar los valores que queramos

//Tipos registro /struct
//El nombre del tipo es struct Punto
struct Punto{
	  int x,y;
}; 

/* typedef crea un alias para un tipo
   Ahora "struct Punto" y "TPunto" son el mismo tipo */
typedef struct Punto TPunto;

//El nombre del tipo es struct pp y tambien podemos usar TPunto2
typedef struct pp{
	int c1,c2;
} TPunto2; 

struct Punto3{
		int x[2];
};

struct Rectangulo {
	  TPunto p1, p2;
	  enum color borde;
};
   
struct Persona{
		char nombre[20];
		char apellido[20];
		
};
union contacto {
    	int tlf;
    	char email[20];
    	char postal[50];
    };
int main(int argc, char *argv[]){
  //struct Punto y TPunto son el mismo tipo
	struct Punto ptA = {1,2};
	TPunto ptB = {3,3};
	TPunto2 ptBB = {4,4};
	
  setvbuf(stdout, NULL, _IONBF, 0);  
  //se pueden asignar todos los miembros si las variables son del mismo tipo
  ptB = ptA;

  /* ptBB = ptB; es un error ya que TPunto2 y TPunto son tipos diferentes (aunque ambos son struct con dos enteros)*/
  
  /*Podemos acceder a los diferentes miembros con la notacion 
  nombre_var.nombre_miembro */
  ptB.x=0; 
  
  printf("Punto A: (%d,%d)\n", ptA.x, ptA.y);
  printf("Punto B: (%d,%d)\n", ptB.x, ptB.y);
  printf("Punto BB: (%d,%d)\n", ptBB.c1, ptBB.c2);

  /*El tamaño de un struct es la suma de los tamaños de sus miembros*/
  printf("sizeof(TPunto): %d\n", sizeof(TPunto));
  /*La posicion de memoria de la variable registro coincide con la posicion 
  de su primer miembro */
  printf("Posicion de memoria de ptA %p\n",&ptA);
  printf("Posicion de memoria de ptA.x %p\n",&(ptA.x));
  printf("Posicion de memoria de ptA.y %p\n",&(ptA.y));

  /* No se pueden comparar variables de tipo struct de este modo
  if(pt1!=pt2) 
  {
    ptB = ptA;
  }

  */
    /**************** STRUCT CON MIEMBROS DE TIPO ARRAY ****************************************/

/* Recordatorio
struct Punto3{
		int x[2];
}*/ 
struct Punto3 ptC={{1,4}},ptD;
	   // Los struct si se pueden asignar-> si un miembro es array tambien lo asigna! 
    // aunque los array por si solos no pueden asignarse
    ptD = ptC; 
    //ptD.x = ptC.x; //NO es posible
    //ptD.x[0]=0;
    printf("Punto C: (%d,%d)\n", ptC.x[0], ptC.x[1]);
    printf("Punto D: (%d,%d)\n", ptD.x[0], ptD.x[1]);

    /******************** STRUCT CON MIEMBROS DE TIPO CADENA DE CARACTERES************************************/
    
  /* Recordatorio  
	struct Persona{
		char nombre[20];
		char apellido[20];
	} 
  */
  struct Persona pe1={"Pepe", "Perez"}, pe2;

    
    pe2 = pe1;

    printf("Persona 1 nombre: %s apellido: %s &p: %p &(p.nombre[0]): %p\n", pe1.nombre, pe1.apellido, &(pe1), pe1.nombre);
    // El contenido de pe1 se ha copiad en la posición de memoria de pe2, incluido los arrays!!
    printf("Persona 2 nombre: %s apellido: %s &p: %p &(p.nombre[0]): %p\n", pe2.nombre, pe2.apellido,  &(pe2), pe2.nombre);

    //pe2.apellido = "Romero"; //La asignación de strings no es posible
    strcpy(pe2.apellido, "Romero");

    printf("Persona 1 %s %s longitud apellido %d\n", pe1.nombre, pe1.apellido, (int)strlen(pe1.apellido));
    printf("Persona 2 %s %s longitud apellido %d\n", pe2.nombre, pe2.apellido, (int)strlen(pe2.apellido));

    printf("Comparacion apellidos %d\n",(int)strcmp(pe1.apellido,pe2.apellido));
  
  /**************** STRUCT CON MIEMBROS DE ENUMERADO ****************************************/
 /* Recordatorio   
	struct Rectangulo {
	  TPunto p1, p2;
	  enum color borde;
	}*/ 
  struct Rectangulo r1;
	
	  r1.p1 = ptA;
    r1.p2 = ptB;
    r1.borde = AZUL;

    /* Mostrar los miembros del rectangulo*/
    printf("Rectagulo: p1 = (%d,%d) p2 = (%d,%d) color: %d == ", r1.p1.x, r1.p1.y,  r1.p2.x, r1.p2.y, r1.borde);
      switch(r1.borde)
      {
        case ROJO:
        {
          printf("ROJO\n");
          break;
        }
        case VERDE:
        {
          printf("VERDE\n");
          break;
        }
        case AZUL:
        {
          printf("AZUL\n");
          break;
        }
      }
  
    /******************** UNIONES *******************************/
    
   /* Recordatorio 
    union contacto {
    	int tlf;
    	char email[20];
    	char postal[50];
    }*/
    union contacto a1, a2;

      a1.tlf=951952000; //'d'
      printf("a1 tlf: %d email: %s postal: %s size of union: %d\n", a1.tlf, a1.email, a1.postal, (int)sizeof(a1));
      strcpy(a1.email, "laurapanizo@uma.es");
      printf("a1 tlf: %d email: %s postal: %s size of union: %d\n", a1.tlf, a1.email, a1.postal, (int)sizeof(a1));
      
      a2 = a1; //Pueden asignarse

      /*if(a2== a1) // no se pueden comparar uniones
      {
    	  printf("valores iguales");
      }*/

      printf("a2 tlf: %d email: %s postal: %s\n", a2.tlf, a2.email, a2.postal);
   /******************** STRUCT CON MIEMBROS DE TIPO UNION *******************************/   
    /* Un ejemplo para usarlo
    struct Persona2{
    	char nombre[20];
    	char apellido[20];
    	union contacto contacto;
    	short modo; // necesita saber cual es el modo de contacto que eligio para determinar cual es el miembro de la union valido
    } ;
    */
}
