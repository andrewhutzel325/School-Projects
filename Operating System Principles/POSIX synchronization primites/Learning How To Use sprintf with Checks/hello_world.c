#include <stdio.h>
#include <unistd.h>
#define NAME ("Insert Name here")

int main(){
    char no[256];
    int bytes_formatted=-1;
    int bytes_printed=-1;
    bytes_formatted=sprintf(no,"CSC 415, This program written by %s",NAME);
    if(bytes_formatted<0){
        perror("error running sprintf..");
    }
    bytes_printed=write(STDOUT_FILENO,no,bytes_formatted);
    if(bytes_printed<0){
        perror("Error printing to FD..");
    }
    return 0; 
}
