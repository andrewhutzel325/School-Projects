# csc415-p2

Documentation:

1)Command using to build my project

gcc filecopy.c

2)Command to run my project

./a.out

3)What my code does

My program copies one file and creates another. The concept is to
simply apply ctrl+c and ctrl+v but to a program.

As it stands right now, my program prompts the user for the file the user
want to copy and then it allows the user to give a name to the copied file. Once both
inputs are filled the bytes from the original file are copied to the
new file.

4)Annotated output of strace of your program
```
brk(NULL)                               = 0x1f3e000
access("/etc/ld.so.nohwcap", F_OK)      = -1 ENOENT (No such file or directory)
access("/etc/ld.so.preload", R_OK)      = -1 ENOENT (No such file or directory)
open("/etc/ld.so.cache", O_RDONLY|O_CLOEXEC) = 3
fstat(3, {st_mode=S_IFREG|0644, st_size=109520, ...}) = 0
mmap(NULL, 109520, PROT_READ, MAP_PRIVATE, 3, 0) = 0x7f6651766000
close(3)                                = 0
access("/etc/ld.so.nohwcap", F_OK)      = -1 ENOENT (No such file or directory)
open("/lib/x86_64-linux-gnu/libc.so.6", O_RDONLY|O_CLOEXEC) = 3
read(3, "\177ELF\2\1\1\3\0\0\0\0\0\0\0\0\3\0>\0\1\0\0\0P\t\2\0\0\0\0\0"..., 832) = 832
fstat(3, {st_mode=S_IFREG|0755, st_size=1868984, ...}) = 0
mmap(NULL, 4096, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS, -1, 0) = 0x7f6651765000
mmap(NULL, 3971488, PROT_READ|PROT_EXEC, MAP_PRIVATE|MAP_DENYWRITE, 3, 0) = 0x7f6651192000
mprotect(0x7f6651352000, 2097152, PROT_NONE) = 0
mmap(0x7f6651552000, 24576, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_FIXED|MAP_DENYWRITE, 3, 0x1c0000) = 0x7f6651552000
mmap(0x7f6651558000, 14752, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_FIXED|MAP_ANONYMOUS, -1, 0) = 0x7f6651558000
close(3)                                = 0
mmap(NULL, 4096, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS, -1, 0) = 0x7f6651764000
mmap(NULL, 4096, PROT_READ|PROT_WRITE, MAP_PRIVATE|MAP_ANONYMOUS, -1, 0) = 0x7f6651763000
arch_prctl(ARCH_SET_FS, 0x7f6651764700) = 0
mprotect(0x7f6651552000, 16384, PROT_READ) = 0
mprotect(0x600000, 4096, PROT_READ)     = 0
mprotect(0x7f6651781000, 4096, PROT_READ) = 0
munmap(0x7f6651766000, 109520)          = 0
fstat(1, {st_mode=S_IFCHR|0620, st_rdev=makedev(136, 21), ...}) = 0
brk(NULL)                               = 0x1f3e000
brk(0x1f5f000)                          = 0x1f5f000
write(1, "Welcome to the File Copy Program"..., 51Welcome to the File Copy Program by Andrew Hutzel! // Program prompt
) = 51
fstat(0, {st_mode=S_IFCHR|0620, st_rdev=makedev(136, 21), ...}) = 0
write(1, "Enter the name of the file to co"..., 40Enter the name of the file to copy from:) = 40 //Program prompt for input
read(0, test.txt // Program takes input
"test.txt\n", 1024)             = 9
open("test.txt", O_RDONLY)              = 3 // File defined as READ ONLY, we only copy from original...
write(1, "Enter the name of the file to co"..., 38Enter the name of the file to copy to:) = 38
read(0, text2.txt // File that is going to be copied to...
"text2.txt\n", 1024)            = 10
open("text2.txt", O_WRONLY|O_CREAT|O_EXCL, 01411) = 4 // Three seperate flags with chmod 777, to ensure proper writing...
read(3, "Testing my file copier...\n", 27) = 26
write(4, "Testing my file copier...\n", 26) = 26
read(3, "", 27)                         = 0
close(3)                                = 0 // Closing both files...
close(4)                                = 0
write(1, "File Copy Successful,26 bytes co"..., 36File Copy Successful,26 bytes copied) = 36 //Output when successful...
lseek(0, -1, SEEK_CUR)                  = -1 ESPIPE (Illegal seek)
exit_group(0)                           = ?
+++ exited with 0 +++
File Copy Successful,26 bytes copied% time     seconds  usecs/call     calls    errors syscall
------ ----------- ----------- --------- --------- ----------------
  0.00    0.000000           0         5           read
  0.00    0.000000           0         5           write
  0.00    0.000000           0         4           open
  0.00    0.000000           0         4           close
  0.00    0.000000           0         4           fstat
  0.00    0.000000           0         1         1 lseek
  0.00    0.000000           0         7           mmap
  0.00    0.000000           0         4           mprotect
  0.00    0.000000           0         1           munmap
  0.00    0.000000           0         3           brk
  0.00    0.000000           0         3         3 access
  0.00    0.000000           0         1           execve
  0.00    0.000000           0         1           arch_prctl
------ ----------- ----------- --------- --------- ----------------
100.00    0.000000                    43         4 total
``` 


```
$ diff -s text.txt text2.txt
Files text.txt and text2.txt are identical
```

List of all of the system calls using strace and then strace -s. Last but not least we run linux's diff function to check
to see how different the two text files are. We ultimately find that they are identical.
