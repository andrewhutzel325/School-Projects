
/****************************************************************
 * Name        : Insert Name hereee                             *
 * Class       : CSC 415                                        *
 * Date  	   : March, 5 2018                                  *
 * Description :  Writting a simple bash shell program          *
 * 	        	  that will execute simple commands. The main   *
 *                goal of the assignment is working with        *
 *                fork, pipes and exec system calls. P.s. I don't work ;)            *
 ****************************************************************/

#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <signal.h>
#include <wait.h>

#define BUFFERSIZE 256
#define PROMPT "myShell >> "
#define PROMPTSIZE sizeof(PROMPT)
#define ARGVMAX 64
//#define PIPECNTMAX 10

/*
 * Function will take argv
 */
void execute_Command(char*myargv[],int myargc,int process){
    char *directory;
    if(arg_passed(myargv,"&",myargc)>=0) {
        //meat
        process_input(myargv, myargc);
    }else if(arg_passed(myargv,"|",myargc)>=0){
        piped_input(myargv,myargc);
    }else if(arg_passed(myargv,"<",myargc)>=0){
        input_redirect(myargv,myargc);
    }else if(arg_passed(myargv,">",myargc)>=0){
        output_redirect(myargv,myargc);
    }else if(arg_passed(myargv,">>",myargc)>=0) {
        output_append(myargv, myargc);
    }else if(arg_passed(myargv,"cd",myargc)>=0) {
        directory=myargv[1];
        chdir(directory);
        if(chdir(directory)==-1){
           // printf(stderr, "Directory didn't work as intended, try again \n");

    }
    }else{
        //If none of the commands match, run execute.
        execute(myargv,process);
    }

}

void execute(char*myargv[],int argc){
    //printf("Process executed.\n");
    //Let the forking begin...
    pid_t pid = fork();
    //utilize example given in class
    if(pid<0){
        printf("I broke something again");
    }else if(pid==0){
        if(argc)
            setpgid(pid,0);
        /*
         * Understanding what execvp does:
         * First argument contain name of a file to be executed.
         * Second argument contains a pointer to array for char strings.
         * KEY, NEED ** HERE! Why does * work then?
         */
        execvp(myargv[0],myargv);
        fprintf(stderr,"Improperly executed, %s\n",myargv[0]);
        exit(-1);
    }else{
        //Parent needs to wait for the child process
        if(argc)
            waitpid(pid,NULL,WNOHANG);
        else
            waitpid(pid,NULL,0);
    }
}


//Done, needs slight revisions...
void process_input(char *myargv[],int size){
    //printf("Process input.\n");
  if(arg_passed(myargv,"&",size) != size - 1) {
    //check if & is placed at the end of command
    printf("Command needs to be at end of line.\n");
  } else {
    //Command will run if & is in correct place...
    myargv[size - 1] = NULL;
    --size;
    execute_Command(myargv, size, 1);
  }
}

void output_redirect(char*myargv[],int process){
    printf("Output redirect...\n");
    char*left_argv[arg_passed(myargv,">",process)+1];
    int left_argc=arg_passed(myargv,">",process);
    //Error checking
    if(process <3 || arg_passed(myargv,">",process)!= process-2){
        fprintf(stderr,"Did not redirect properly, > is placed wrong.\n");
        return;
    }

    for(int i=0;i<left_argc;i++){
        left_argv[i]=myargv[i];
    }
    left_argv[left_argc]=NULL;
    //Open the file, using the same flags and slightly different chmod from
    //project 1 we apply this here.
    int output=open(myargv[process-1], O_RDWR | O_CREAT | O_TRUNC, 0644);
    if(output<0){
        //Something went wrong, EOF?
        fprintf(stderr,"Failed to create, open, or write.");
    }
    else{
        //Takes fd (don't forget to close) and fd destination.
        dup2(output,STDOUT_FILENO);
        execute_Command(left_argv,left_argc,0);
        close(output);
        dup2(dup(STDOUT_FILENO),STDOUT_FILENO);
    }
    //Otherwise left args and argc ???

}

void output_append(char*myargv[],int process){
    printf("Output appending...\n");
        printf("Output redirect...\n");
    char*left_argv[arg_passed(myargv,">>",process)+1];
    int left_argc=arg_passed(myargv,">>",process);
    //Error checking
    if(process <3 || arg_passed(myargv,">",process)!= process-2){
        fprintf(stderr,"Did not redirect properly, >> is placed wrong.\n");
        return;
    }

    for(int i=0;i<left_argc;i++){
        left_argv[i]=myargv[i];
    }
    left_argv[left_argc]=NULL;
    //Open the file, using the same flags and slightly different chmod from
    //project 1 we apply this here.
    int output=open(myargv[process-1], O_RDWR | O_CREAT | O_APPEND, 0644);
    if(output<0){
        //Something went wrong, EOF?
        fprintf(stderr,"Failed to create, open, or write.");
    }
    else{
        //Takes fd (don't forget to close) and fd destination.
        dup2(output,STDOUT_FILENO);
        execute_Command(left_argv,left_argc,0);
        close(output);
        dup2(dup(STDOUT_FILENO),STDOUT_FILENO);
    }
    //Otherwise left args and argc ???

}

void input_redirect(char*myargv[],int process){
    printf("Input redirection...\n");
    char *left_arg[arg_passed(myargv,"<",process)];
    int left_argc=arg_passed(myargv,"<",process);
    if(process < 3 || arg_passed(myargv,"<",process)){
        fprintf(stderr,"Redirection needs to be placed correctly.\n");
        return;
    }

    for(int i=0;i<left_argc;i++){
        left_arg[i]=myargv[i];
    }
    left_arg[left_argc]=NULL;

    //same stuff as before....
    int input=open(myargv[process-1],O_RDONLY);
    if(input<0){
        fprintf(stderr,"Failed to properly open the file");
    }else{
        dup2(input,STDOUT_FILENO);
        execute_Command(left_arg,left_argc,0);
        close(input);
        dup2(dup(STDIN_FILENO),STDIN_FILENO);
    }
}

void piped_input(char*argv[],int process) {
    printf("Piped input...\n");
    //Shell2, final shell utilized through pipes here...
    //Declare all the variables here.
    char *left_arg[BUFFERSIZE];
    int left_argc = 0, pipe_index = -1, fds[2] = {0}, fd_in = -1, fd_out = -1, counter = 0;
    pid_t pid;
    pid = fork();

    //Check for pipe.
   for(int i=0;i<process;i++){
       if(strcmp(process-1==i||argv[i],"|")==0){
           left_argc=0;
           if(pipe_index>0){
               //Update...
               counter=pipe_index+1;
           }else{
               //First pipe found!
               counter=0;
           }
           if(i<process-1){
               //index update @ i
               pipe_index=i;

               for(;counter<i;counter++){
                   left_arg[left_argc++]=argv[counter];
               }
               left_arg[left_argc]=NULL;
               //Creates a new pipe.
               pipe(fds);
               fd_out=fds[1];
           }else{
               for(;counter<i;counter++){
                   left_arg[left_argc++]=argv[counter];
               }
               left_arg[left_argc]=NULL;
               fd_out=-1;
           }
           if(pid<0){
               printf("Process wasn't created, something happened!\n");
           }else if(pid==0){
               execute_Command(left_arg,left_argc,0);
               exit(1);
           }else{
               waitpid(pid,NULL,0);
               close(fd_out);
               close(fd_in);
               fd_in=fds[0];
           }

       }
   }
}

int arg_passed(char* myargv[],char * symbol,int myargc){
    //printf("Arg passsed\n");
    //We now will process through the argv in search of the symbol.
    //We need to be careful not to process char by char.
    for(int i=0;i<myargc;i++){
        if(strcmp(myargv[i],symbol)==0){
            return i;
        }
    }
    return -1;
}

int main(int argc,char *argv)
{
/*
 * Shell0, init variables, enter inifite loop until stdin
 * detects EOF, each iteration prompts 4 input and echo.
 */
    //init
    signal(SIGCHLD,SIG_IGN);
    int myargc=0;
    char input[BUFFERSIZE];
    char *myargv[BUFFERSIZE];
    char *token;
    char *directory;
    int end=0;
    //Begin reading user input until CTRL-D or exit is entered
    //Always true, so no statement needed for while...
    while(1){
        printf("%s",PROMPT);
        //EOF caught here essentially...

        if(fgets(input,BUFFERSIZE,stdin)==NULL)
            break;
        myargc=0;
        //Terminator appeneded to input.
        input[strlen(input)-1]='\0';
        //Tokenize input, pass into myargv...
        token=strtok(input," ");
        while(token!=NULL){
            myargv[myargc++]=token;
            //Retokenize...
            token=strtok(NULL, " ");
        }
        if(myargc>0){
            //We are checking to see if the first command
            //is exit.
            if(strcmp(myargv[0],"exit")==0){
                //printf("Yas");
                break;
            }else if(strcmp(myargv[0],"echo")==0) {
                //printf("Whats argc=%d\n",myargc);

                printf("%s",PROMPT);
                for(int i=1;i<myargc;i++){
                    printf("%s ",myargv[i]);
                }
                printf("\n");
            }else{
                myargv[myargc] = NULL;
                //executecmds, process starts at zero...
                execute_Command(myargv,myargc,0);
            }
        }
    }

return 0;
}
