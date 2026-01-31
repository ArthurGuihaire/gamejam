CXX := g++
CXXFLAGS := -std=c++17 -O2 -Wall

TARGET := game
SRC := main.cpp
OBJ := main.o

UNAME_S := $(shell uname -s)

ifeq ($(UNAME_S),Linux)
	LIBS := -lraylib -lglfw -lrt -lm -ldl
endif

ifeq ($(UNAME_S),MINGW64_NT-*)
	TARGET := game.exe
	LIBS := -lraylib -lopengl32 -lgdi32 -lwinmm
endif

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CXX) $(OBJ) -o $@ $(LIBS)

$(OBJ): $(SRC)
	$(CXX) $(CXXFLAGS) -c $<

clean:
	rm -f $(OBJ) $(TARGET)
