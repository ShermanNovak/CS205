package com.example.cs205proj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Background {
    private final Bitmap spriteSheet;
    private final Bitmap mrtSprite;
    private final Bitmap chairsSprite;
    private final Bitmap computerSprite;
    private final Bitmap tableSprite;
    private final Rect srcRect;
    private final int tileSize;
    int mapWidth = 4000;
    int mapHeight = 2000;
    Rect goalRect;

    public Background(Context context, int tileSize) {
        spriteSheet = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile1);
        mrtSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.train);
        chairsSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.chairs);
        computerSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.computer);
        tableSprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.table);
        this.tileSize = tileSize;
        int left = 0;
        int top = 0;
        int right = tileSize;
        int bottom = tileSize;
        srcRect = new Rect(left, top, right, bottom);
        goalRect = new Rect(mapWidth - 29 - 64*4, mapHeight - 85, mapWidth, mapHeight);
    }

    public void draw(Canvas canvas, Paint paint, int offsetX, int offsetY) {
        // Calculate the number of tiles needed to cover the screen
        int numTilesX = (mapWidth + tileSize - 1) / tileSize;
        int numTilesY = (mapHeight + tileSize - 1) / tileSize;

        // Draw the tiles to cover the entire screen
        for (int i = 0; i < numTilesX; i++) {
            for (int j = 0; j < numTilesY; j++) {
                int left = offsetX + i * tileSize;
                int top = offsetY + j * tileSize;
                int right = left + tileSize;
                int bottom = top + tileSize;
                Rect destRect = new Rect(left, top, right, bottom);
                canvas.drawBitmap(spriteSheet, srcRect, destRect, paint);
            }
        }

        // draw train
        canvas.drawBitmap(mrtSprite, offsetX, offsetY - 248, paint);

        // draw chairs
        int numColumnChairs = mapWidth / (1000);
        int numRowChairs = mapHeight / (500);

        for (int i = 0; i < numColumnChairs; i++) {
            for (int j = 1; j < numRowChairs; j++) {
                canvas.drawBitmap(chairsSprite, offsetX + i * 1000, offsetY + j * 500, paint);
            }
        }

        // draw end goal: control centre
        canvas.drawBitmap(tableSprite, offsetX + numTilesX * tileSize - 64 * 4, offsetY + numTilesY * tileSize - 28 * 7, paint);
        canvas.drawBitmap(computerSprite, offsetX + numTilesX * tileSize - 64 * 4, offsetY + numTilesY * tileSize - 28 * 9, paint);

//        goalRect.set(offsetX + numTilesX * tileSize - 64 * 4, offsetY + numTilesY * tileSize - 28 * 7, offsetX + numTilesX * tileSize, offsetY + numTilesY * tileSize);
//         paint.setColor(Color.RED);
//         canvas.drawRect(goalRect, paint);
    }

    public Rect getGoalRect() {
        return this.goalRect;
    }
}