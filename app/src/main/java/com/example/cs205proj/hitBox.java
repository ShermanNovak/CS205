package com.example.cs205proj;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

public class hitBox extends Entity {
    private Rect rect;
    private Player thePlayer;
    private Joystick theJoyStick;

    public hitBox(Player player) {
        super();
        thePlayer = player;
        rect = new Rect(x, y, x + width, y + height); // create rectangle object for drawing and collision
        this.x = thePlayer.getX();
        this.y = thePlayer.getY();
        this.width = 100;
        this.height = 100;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public void draw(Canvas canvas, Paint paint) {
        // player is currently a circle
        paint.setColor(Color.BLUE);
        canvas.drawRect(rect, paint);
    }

    public void update(Joystick joystick) { // updates the hitbox position to correspond to being next to player
        float[] offsets = joystick.getJoystickOffset();

        double joystickOffsetX = offsets[0];
        double joystickOffsetY = offsets[1];
    
        // Calculate angle of the joystick offset vector
        double angle = Math.atan2(joystickOffsetY, joystickOffsetX);
    
        // Calculate hitbox position based on joystick offset and angle
        int finalOffsetX = (int) (Math.cos(angle) * 50);
        int finalOffsetY = (int) (Math.sin(angle) * 50);

        if (finalOffsetX < 0){
            finalOffsetX -= 100;
        }
        if (finalOffsetY < 0){
            finalOffsetY -= 100;
        }
    
        // Calculate hitbox position relative to player's position
        int offsetX = thePlayer.getX() + finalOffsetX;
        int offsetY = thePlayer.getY() + finalOffsetY;
    
        // Update hitbox position
        this.x = offsetX;
        this.y = offsetY;
        rect.set(x, y, x + width, y + height);
    }
}