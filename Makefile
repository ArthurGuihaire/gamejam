link_flags := -lraylib -L/usr/local/lib -lraylib -lglfw -lrt -lm -ldl
compile_flags := -Wall -I/usr/local/include

default: main.o
	g++ main.o $(link_flags) -o main

%.o: %.c
	g++ $(compile_flags) main.c -o main.o
