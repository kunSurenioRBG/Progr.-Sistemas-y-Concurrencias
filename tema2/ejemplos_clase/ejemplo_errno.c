#include <stdio.h>
#include <errno.h> // incluir para la gestion de errores
#include <string.h>
#include <stdlib.h>

//extern int errno ; // esta variable esta en la libreria errno.h

/* Algunos valores y significados de la variable errno
 value       		Error
	1             Operation not permitted 
	2             No such file or directory
	3             No such process
	4             Interrupted system call
	5             I/O error
	6             No such device or address
	7             Argument list too long
	8             Exec format error
	9             Bad file number
	10            No child processes
	11            Try again
	12            Out of memory
	13            Permission denied
*/
int main () {
	
   FILE * pf;
   int errnum;
   
   setvbuf(stdout, NULL, _IONBF, 0); //evitar buffering en stdout --eclipse para windows
   pf = fopen ("unexist.txt", "rb");
	
   if (pf == NULL) {
   
      errnum = errno;
      printf("(con printf + errono)Valor de errno: %d\n", errno);
      perror("(con perror) Error abriendo fichero");
      printf("(con printf+strerror) Error opening file: %s\n", strerror( errnum ));
      fprintf(stderr,"(con fprintf) Error opening file: %s\n", strerror( errnum ));
      
      exit(-1); //return -1;
   } else {
      fclose (pf);
      printf("Closing file\n");
   }
   return 0;
}
