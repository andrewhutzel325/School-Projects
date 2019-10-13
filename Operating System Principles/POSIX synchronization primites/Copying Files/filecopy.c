#include <stdio.h>
#include <stdlib.h>
//#include <sys/types.h>
//#include <sys/stat.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#define NAME ("Insert Name Here")
int main(){
    int fd_OG,fd_CPY,bytes_counter=0;
    char buffer[27], fileName[256], fileCopy[256];
    ssize_t return_input;

    //Write prompt to screen
    printf("Welcome to the File Copy Program by %s!\n",NAME);
    //Acquire input file name
    printf("Enter the name of the file to copy from:");
    scanf("%s",fileName);
    //Accept input,using linux flags
    fd_OG=open(fileName,O_RDONLY);
    if(-1==fd_OG){
        printf("\n open() failed with an error... [%s]",strerror(errno));
        exit(1);
    }
    //Acquire output file name
    printf("Enter the name of the file to copy to:");
    scanf("%s",fileCopy);
    //Accept input
    fd_CPY=open(fileCopy,O_WRONLY | O_CREAT | O_EXCL, 777);
    if(-1==fd_CPY){
         printf("\n open() failed with an error... [%s]",strerror(errno));
        exit(1);
    }
    //Write until read fails,loop
    while((return_input=read(fd_OG,buffer,27))>0){
        write(fd_CPY,buffer,return_input);
        bytes_counter+=return_input;
    }
    //Close output file
    close(fd_OG);
    close(fd_CPY);
    //Write completion message to the screen
    printf("File Copy Successful,%i bytes copied",bytes_counter);
    //Terminate normally
    return 0;
}
