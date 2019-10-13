Name  : Insert Name Here
Date  : 3/25/2018
Class : CSC415 Project 4

Compile Instructions:

gcc pthread_race.c

Run Instructions:

./a.out is all you need to run the program.

Project Description:

Annotated output of program:

hello I'm thread #1
hello I'm thread #2
Thread id:2 local thread variable:10 //Race condition
Thread id:1 local thread variable:10
hello I'm thread #0
hello I'm thread #4
hello I'm thread #8
hello I'm thread #3
hello I'm thread #9
Thread id:3 local thread variable:10
Thread id:9 local thread variable:10
hello I'm thread #7
Thread id:8 local thread variable:10
hello I'm thread #6
Thread id:7 local thread variable:10
Thread id:6 local thread variable:10
hello I'm thread #5
Thread id:4 local thread variable:10
Thread id:5 local thread variable:10
Thread id:0 local thread variable:10
Thread id:1 new local thread variable: 20 //Race condition, the local variable is incremented twice.
Thread id:2 new local thread variable: 20
Thread id:7 new local thread variable: 20
Thread id:6 new local thread variable: 20
Thread id:9 new local thread variable: 20
Thread id:8 new local thread variable: 20
Thread id:3 new local thread variable: 20
Thread id:5 new local thread variable: 20
Thread id:0 new local thread variable: 20
Thread id:4 new local thread variable: 20
Global Num:20                           // Global value is always 20, why?, because over written by local value...
