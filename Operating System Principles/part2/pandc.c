#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <semaphore.h>

//global variables
int *buffer;
int bufferIndex;
int numOfElements;
int itemsProduced;
int itemsConsumed;
int Ptime;
int Ctime;
int global_counter;
int *producerArray;
int *consumerArray;
int producerIndex;
int consumerIndex;
pthread_mutex_t lock;
sem_t full;
sem_t empty;

//functions to be implemented
void producer(int *id);
void consumer(int *id);
void timestamp();
int compare( const void* a, const void* b);
int match(int sizeOfProducer, int sizeOfConsumer);
void print_and_match_array(int sizeOfProducer, int sizeOfConsumer);

int main(int argc, char *argv[]) {

    //parse command line args
    if(argc == 7) {
        int numProducer, numConsumer;

        //print the command line arguments
        printf("Number of elements: %s\n", argv[1]);
        printf("Number of producers: %s\n", argv[2]);
        printf("Number of consumers: %s\n", argv[3]);
        printf("Number of items each producer produces: %s\n", argv[4]);
        printf("Ptime: %s\n", argv[5]);
        printf("Ctime: %s\n", argv[6]);

        //inititalize all the variables
        bufferIndex = 0;
        numOfElements = atoi(argv[1]);
        numProducer = atoi(argv[2]);
        numConsumer = atoi(argv[3]);
        itemsProduced = atoi(argv[4]);
        itemsConsumed = (numProducer * itemsProduced / numConsumer);
        Ptime = atoi(argv[5]);
        Ctime = atoi(argv[6]);
        buffer = (int*)malloc(sizeof(int) * numOfElements);
        producerArray = (int*)malloc(sizeof(int) * (numProducer * itemsProduced));
        consumerArray = (int*)malloc(sizeof(int) * (numConsumer * itemsConsumed));

        //initialize the semaphores and mutex
        //full = dispatch_semaphore_create(0);
        //empty = dispatch_semaphore_create(numOfElements);
        sem_init(&full, 0, 0);
        sem_init(&empty, 0, numOfElements);
        if(pthread_mutex_init(&lock, NULL) != 0) {
            printf("mutex init failed\n");
            return 1;
        }

        //initialize the threads
        pthread_t *tid_1 = (pthread_t*)malloc(sizeof(pthread_t) * numProducer);
        pthread_t *tid_2 = (pthread_t*)malloc(sizeof(pthread_t) * numConsumer);
        pthread_attr_t attr;
        pthread_attr_init(&attr);

        //count and print timestamp
        timestamp();
        time_t begin = time(NULL);

        //spawn off threads
        int i, j;
        int id_1[numProducer], id_2[numConsumer];
        for(i = 0; i < numProducer; i++) {
            id_1[i] = i;
            pthread_create(&tid_1[i], &attr, (void*)producer, &id_1[i]);
        }
        for(j = 0; j < numConsumer; j++) {
            id_2[j] = j;
            pthread_create(&tid_2[j], &attr, (void*)consumer, &id_2[j]);
        }
        for(i = 0; i < numProducer; i++) {
            pthread_join(tid_1[i], NULL);
        }
        for(j = 0; j < numConsumer; j++) {
            pthread_join(tid_2[j], NULL);
        }

        //count and print timestamp
        timestamp();
        print_and_match_array(numProducer * itemsProduced, numConsumer * itemsConsumed);
        time_t end = time(NULL);

        //print run time
        printf("Program ends. Runtime: %ld seconds\n", end - begin);

        //destroy semaphores and mutex
        //dispatch_release(full);
        //dispatch_release(empty);
        sem_destroy(&full);
        sem_destroy(&empty);
        pthread_mutex_destroy(&lock);
        free(buffer);
        free(tid_1);
        free(tid_2);
    }
    else {
        printf("Please enter 6 arguments.\n");
    }
    return 0;
}

void timestamp() {
    time_t ltime; /* calendar time */
    ltime = time(NULL); /* get current cal time */
    printf("Timestamp: %s\n", asctime(localtime(&ltime)));
}

void producer(int *id) {

    int counter = 0, product;

    //produce items
    while(counter < itemsProduced) {
        //lock
        //dispatch_semaphore_wait(empty, DISPATCH_TIME_FOREVER);
        sem_wait(&empty);
        pthread_mutex_lock(&lock);

        //produce and print item
        product = global_counter++;
        printf("Producer #%d produces: %d\n", *id , product);
        producerArray[producerIndex++] = product;

        //enqueue item in buffer
        buffer[bufferIndex++] = product;
        counter++;

        //sleep
        sleep(Ptime);

        //unlock
        pthread_mutex_unlock(&lock);
        sem_post(&full);
        //dispatch_semaphore_signal(full);
    }

    pthread_exit(0);
}

void consumer(int *id) {

    int counter = 0, consume;

    //consume items
    while(counter < itemsConsumed) {
        //lock
        //dispatch_semaphore_wait(full, DISPATCH_TIME_FOREVER);
        sem_wait(&full);
        pthread_mutex_lock(&lock);

        //consume and print item
        consume = buffer[--bufferIndex];
        printf("Consumer #%d consumes: %d\n", *id, consume);
        consumerArray[consumerIndex++] = consume;

        //dequeue item in buffer
        buffer[bufferIndex] = 0;
        counter++;

        //sleep
        sleep(Ctime);

        //unlock
        pthread_mutex_unlock(&lock);
        sem_post(&empty);
        //dispatch_semaphore_signal(empty);
    }

    pthread_exit(0);
}

int compare( const void* a, const void* b) {
    return ( *(int*)a - *(int*)b );
}

int match(int sizeOfProducer, int sizeOfConsumer) {
    if(sizeOfProducer != sizeOfConsumer) {
        return 0;
    }
    for(int i = 0; i < sizeOfProducer; i++) {
        if(producerArray[i] != consumerArray[i]) {
            return 0;
        }
    }
    return 1;
}

void print_and_match_array(int sizeOfProducer, int sizeOfConsumer) {
    //sort producerArray and consumerArray
    qsort(producerArray, sizeOfProducer, sizeof(int), compare);
    qsort(consumerArray, sizeOfConsumer, sizeof(int), compare);

    //print producerArray and consumerArray
    printf("Producer Array: ");
    for(int i = 0 ; i < sizeOfProducer; i++) {
        printf("%d ", producerArray[i]);
    }
    printf("\nConsumer Array: ");
    for(int i = 0 ; i < sizeOfConsumer; i++) {
        printf("%d ", consumerArray[i]);
    }
    printf("\n");

    //check if producerArray and consumerArray match
    if(match(sizeOfProducer, sizeOfConsumer)) {
        printf("Consumer and Producer Arrays Match!\n");
    } else {
        printf("Consumer and Producer Arrays Don't Match...\n");
    }
    printf("\n");
}