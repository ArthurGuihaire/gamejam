#include <raylib.h>

int main() {
    InitWindow(800, 600, "Hello");
    SetTargetFPS(60);

    while (!WindowShouldClose()) {
        BeginDrawing();
        EndDrawing();
    }
}
