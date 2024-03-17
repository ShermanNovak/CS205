package com.example.cs205proj;

public class Entity {
    int x;
    int y;
    int width;
    int height;
    int velocityX;
    int velocityY;

    public boolean collides(Entity target) {
        return !(this.x + this.width < target.x || this.x > target.x + target.width || this.y + this.height < target.y || this.y > target.y + target.height);
    }
}