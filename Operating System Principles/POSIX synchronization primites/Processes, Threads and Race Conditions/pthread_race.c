#include <stdio.h>
#include <pthread.h>
#define num 10

struct timespec ts={1,0};
void *thread_output(void *arg);
int global_variable=0;
int main() {
    pthread_t tid;
    for(int i=0;i<10;i++){
        pthread_create(&tid,NULL,thread_output,(void *)i);
    }

    pthread_join(tid,NULL);
    fprintf(stderr,"Global Num:%d\n",global_variable);
    return 0;
}

void *thread_output(void *arg){
    int local=0;
    int id=(int)arg;
    fprintf(stderr,"hello I'm thread #%d\n",id);
    local=num;
    fprintf(stderr,"Thread id:%d local thread variable:%d\n",id,local);
    nanosleep(&ts,NULL);

    local+=10;
    fprintf(stderr,"Thread id:%d new local thread variable: %d\n",id,local);
    nanosleep(&ts,NULL);

    global_variable=local;


    pthread_exit(0);

}