package com.example.cs205proj;

import java.util.*;
import static java.lang.Thread.sleep;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class Enemy extends Entity implements Runnable {
    Random random = new Random();
    int distanceThreshold = random.nextInt(150) + random.nextInt(300);  //enemies dont converge into one
    int MAX_SPEED = 10;
    int VISIBILITY = (int)Double.POSITIVE_INFINITY;
    boolean isAlive = true;
    int health = 100;
    private final Player player;
    private final Score score;
    long movementTimer;
    int movementDuration;
    Animation animation;
    int walkSpeed = 1;
    String[] directions = {"left", "right", "up", "down"};

    public Enemy(int x, int y, Player player, Score score, Animation animation) {
        super();
        this.x = x;
        this.y = y;
        this.width = 120;
        this.height = 200;
        this.velocityX = 0;
        this.velocityY = 0;
        this.player = player;
        this.score = score;
        this.animation = animation;
    }
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(animation.getCurrentFrame(), null, new Rect(x, y, x + width, y + height), paint);
   }

    public void update(long deltaTime, Rect display) {
        if (movementTimer > movementDuration) {
            movementTimer = 0;
            movementDuration = (new Random().nextInt(5) + 1) * 500;
            direction = directions[new Random().nextInt(directions.length)];
        }

        movementTimer += deltaTime;

        if (direction.equals("left")) {
            x = (int) Math.max(display.left + width, x - walkSpeed * deltaTime);
        } else if (direction.equals("right")) {
            x = (int) Math.min(display.right - width, x + walkSpeed * deltaTime);
        } else if (direction.equals("up")) {
            y = (int) Math.max(display.top + height, y - walkSpeed * deltaTime);
        } else if (direction.equals("down")) {
            y = (int) Math.min(display.bottom - height, y + walkSpeed * deltaTime);
        }
    }

    @Override
    public void run() {
        while (isAlive) {
//            System.out.println(Math.abs(player.x - this.x)+ " ," + Math.abs(player.y - this.y));
            if (Math.abs(player.x - this.x) < 200 && Math.abs(player.y - this.y) < 200){
                isAlive = false;
            }
        }
        score.increment();
    }
}
