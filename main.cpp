#include <raylib.h>

int main() {
    InitWindow(800, 600, "Hello");
    SetTargetFPS(60);
    Rectangle r=Rectangle {10, 10, 100, 100};
    Color c=BLUE;
    while (!WindowShouldClose()) {

        BeginDrawing();
        DrawRectangleRec(r,c);

        EndDrawing();
    }
}
