#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>
#include <sys/time.h>

#define num 10


/**
 * Can use the following struct for
 * the nanosleep function.
 * the first value is sleep time in seconds
 * the second vaue is sleep time in nanoseconds.
 *
 * struct timespec ts = {2, 0 };
 *
 * you can call nano sleep as nanosleep(&ts, NULL);
 *
 * https://www.geeksforgeeks.org/mutex-lock-for-linux-thread-synchronization/
 *
*/

struct timespec ts={1,0};
void *thread_output(void *arg);
int global_variable=0;
pthread_mutex_t lock;


int main() {
    pthread_t tid[num];
        for (int i = 0; i < num; i++) {
            pthread_create(&tid[i], NULL, thread_output, (void *)(intptr_t) i);
        }
        for(int i=0;i<num;i++) {
            pthread_join(tid[i], NULL);
         }
    pthread_mutex_destroy(&lock);
    fprintf(stderr, "Global Num:%d\n", global_variable);
    return 0;
}

void *thread_output(void *arg){
    int local;
    int id=(int)(intptr_t)arg;
    pthread_mutex_lock(&lock);
    fprintf(stderr,"hello I'm thread #%d\n",id);
    //nanosleep(&ts,NULL);
    local=global_variable;
    fprintf(stderr,"Thread id:%d local thread variable:%d\n",id,local);
    local=10;
    global_variable+=local;
    fprintf(stderr,"Thread id:%d new local thread variable: %d\n",id,global_variable);
    nanosleep(&ts,NULL);
    pthread_mutex_unlock(&lock);

}
