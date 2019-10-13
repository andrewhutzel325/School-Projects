
#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/time.h>


//Global Values, sem, locks, and struts located here
int *buffer;
int bufferIndex;
int elements;
int global_counter;
//Presumably where init would go
pthread_mutex_t lock;
sem_t full;
sem_t empty;
struct Producer producer1;
struct Consumer consumer1;
struct timespec ts={1,0};
//End of global var declare

struct Producer{
    int *p_array;
    int p_index;
    int p_time;
    int p_items;
    int p_num;
};

struct Consumer{
    int *c_array;
    int c_index;
    int c_time;
    int c_items;
    int c_num;
};


void producer(int *id) {
    //Two local values declared
    int counter = 0, product;
    //Loop until we react end of items
     while(counter<producer1.p_items){
         //Semaphore and mutex lock...
        sem_wait(&empty);
        pthread_mutex_lock(&lock);
         //Pass global counter +1 to product (Post fix)
        product = global_counter++;
       fprintf(stderr,"%d was produced by producer->%d\n",product,*id);
        producer1.p_array[producer1.p_index++]=product;
        //enqueue
        buffer[bufferIndex++] = product;
         //Increment, we have produced one product and are moving
         //onto the next one.
        counter++;
         //Put thread to sleep for ptime
        sleep(producer1.p_time);
         //Releasing mutex lock and semaphore...
        pthread_mutex_unlock(&lock);
        sem_post(&full);
    }
    pthread_exit(0);
}

void consumer(int *id) {
    //Local values declared...
    int counter = 0, consume=0;
    //Loop until all products are consumed
     while(counter<consumer1.c_items){
         //Semaphore and mutex lock...
        sem_wait(&full);
        pthread_mutex_lock(&lock);
         //Product was consumed...
        consume = buffer[--bufferIndex];
         fprintf(stderr,"%d was consumed by consumer->%d\n",consume,*id);
        //Assign consumed product to consumer struct!
         consumer1.c_array[consumer1.c_index++]=consume;
        //dequeue, we resolve the space to zero.
        buffer[bufferIndex] = 0;
         //We have consumed one product!
        counter++;
         //Sleep for consume time.
        sleep(consumer1.c_time);
         //Release mutex lock and semaphore
        pthread_mutex_unlock(&lock);
        sem_post(&empty);
    }
    pthread_exit(0);
}


//http://www.cplusplus.com/reference/cstdlib/qsort/
//I know you disapprove when we show where we lifted some code, but I'm doing this so I can look this up
//in like 3 months.
int compare( const void* a, const void* b) {
    return ( *(int*)a - *(int*)b );
}

void check_array(int consumer_idx, int producer_idx) {
    //sorting both arrays, qsort seems to be the most optimized.
    qsort(producer1.p_array, producer_idx, sizeof(int), compare);
    qsort(consumer1.c_array, consumer_idx, sizeof(int), compare);

    fprintf(stderr,"Producer Consumer\n ");
    for (int i = 0; i < producer_idx; i++) {
        fprintf(stderr,"%d\t\t|%d\n ", producer1.p_array[i],consumer1.c_array[i]);
    }
    if(consumer_idx != producer_idx) {
        fprintf(stderr,"Size of arrays do not match.");
    } else {
        for (int i = 0; i < producer_idx; i++) {
            if (producer1.p_array[i] != consumer1.c_array[i]) {
                fprintf(stderr, "Arrays do not match!");
                break;
            }
        }
        fprintf(stderr,"Arrays do match.");
    }


}

//76
//7 5 3 15 1 1 (You need to make sure you're rounding these off properly!

int main(int argc, char *argv[]) {
        //Show command line arguments, revise and do fprintf one line!
        fprintf(stderr,"Elements: %s\nProducers:%s\nConsumers:%s\nProducer will produce:%s\nPtime:%s\nCtime:%s\n", argv[1],argv[2],argv[3],argv[4],argv[5],argv[6]);

        //Parse string arguments into numbers, also
        //declare all variables. Excluding adjusted for rare fix.
        //time_t start,end;
        bufferIndex = 0;
        char *ptr;
        //https://stackoverflow.com/questions/5248915/execution-time-of-c-program
        //https://www.tutorialspoint.com/c_standard_library/c_function_strtol.htm
        struct timeval tv1,tv2;
        elements = strtol(argv[1],&ptr,10); // 5
        producer1.p_num=strtol(argv[2],&ptr,10); //2
        consumer1.c_num=strtol(argv[3],&ptr,10); //3
        producer1.p_items= strtol(argv[4],&ptr,10); // 14, Now we have an issue, resolve by adjusting down.
        producer1.p_time=strtol(argv[5],&ptr,10); // 1
        consumer1.c_time=strtol(argv[6],&ptr,10); // 1
        buffer = (int*)malloc(sizeof(int) * elements);

        //We need to check and see if there is an even number of consumers and producers
        if(producer1.p_num*producer1.p_items%consumer1.c_num ==0){
        consumer1.c_items=(producer1.p_num * producer1.p_items / consumer1.c_num);}
        else{
            int adjusted=producer1.p_num*producer1.p_items;
            while(((adjusted)%consumer1.c_num)!=0){
                adjusted--;
            }
            consumer1.c_items=(producer1.p_num * producer1.p_items / consumer1.c_num);
            fprintf(stderr,"Consumer items adjusted to %d\n",adjusted);
        }

        producer1.p_array= (int*)malloc(sizeof(int)* (producer1.p_num * producer1.p_items));
        consumer1.c_array=(int*)malloc(sizeof(int)*(consumer1.c_num * consumer1.c_items));

        //Initalize an unnamed semaphore
        //http://www.csc.villanova.edu/~mdamian/threads/posixsem.html
        //Reference for me
        //Should this be a function? The pdf states enqueue, dequeue, and init?
        sem_init(&full, 0, 0);
        sem_init(&empty, 0, elements);

        if(pthread_mutex_init(&lock, NULL) != 0) {
            fprintf(stderr,"Mutex error\n");
            return -1;
        }

        pthread_t *tid_0 = (pthread_t*)malloc(sizeof(pthread_t)* producer1.p_num);
        pthread_t *tid_1 = (pthread_t*)malloc(sizeof(pthread_t)*consumer1.c_num);
        pthread_attr_t attribute;
        pthread_attr_init(&attribute);

        time_t result = time(NULL);
        fprintf(stderr,"\nThe current time is %s\n",asctime(gmtime(&result)));
        //Time starts here...
         gettimeofday(&tv1,NULL);
        //Creating Threads and joining them, a contiuation of p4...
        int producer_0[producer1.p_num], consumer_1[consumer1.c_num];
        for(int i=0;i<producer1.p_num;i++){
            producer_0[i]=i;
            pthread_create(&tid_0[i],&attribute,(void*)producer,&producer_0[i]);
        }
        for(int i=0;i<consumer1.c_num;i++){
            consumer_1[i]=i;
            pthread_create(&tid_1[i],&attribute,(void*)consumer,&consumer_1[i]);
        }
        //Joining... duh.
        for(int i=0;i<producer1.p_num;i++){
            pthread_join(tid_0[i],NULL);
            fprintf(stderr,"Producer Thread joined:%d\n",i);
        }

        for(int i=0;i<consumer1.c_num;i++){
            pthread_join(tid_1[i],NULL);
            fprintf(stderr,"Consumer Thread joined:%d\n",i);
        }

        time_t result2 = time(NULL);
        fprintf(stderr,"\nThe current time is %s\n",asctime(gmtime(&result2)));

        check_array(producer1.p_num*producer1.p_items,consumer1.c_num*consumer1.c_items);
        //Time ends here
        gettimeofday(&tv2,NULL);
        fprintf(stderr,"\nTotal run time: %f",((double)(tv2.tv_usec-tv1.tv_usec)/1000000 +(double)(tv2.tv_sec-tv1.tv_sec)));
        //Clean up...
        sem_destroy(&full);
        sem_destroy(&empty);
        pthread_mutex_destroy(&lock);
    return 0;
}

