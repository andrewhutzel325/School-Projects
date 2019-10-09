Name  : Andrew Hutzel
Date  : April 14th, 2018
Class : CSC 415.02

Compile Instructions:
gcc pandc.c

Run Instructions:

./a.out

Project Description:

    The purpose of this project is to implment the consumer producer problem presented in the book.
We need a bounded buffer queue of N elements, P producer threads, and C consumer threads.
These three variables will be command line arguments as well as X, Ptime, and Ctime.

Producers should Enqueue X different numbers onto the queue which means:
Sleeping for Ptime cycles inbetween each call to Enqueue.

Each Consumer thread should Dequeue P*X/C, check for even divisiblity, but mainly:
Items from the queue (based off sleeping for Ctime cycles inbetween each call to dequeue).

The main program should do the following:

Initalize the bounded buffere queue
Print a timestamp
Spawn off C consumer threads and P producer threads
Wait for all the threads to finish and then print off another timestamp
Duration of execution!

