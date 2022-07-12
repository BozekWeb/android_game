package pl.bozek.dungeon.map;

import pl.bozek.dungeon.map.array.Float2D;

public class RoomInfo {

    private int x;
    private int y;
    private int width;
    private int height;
    private Float2D room;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Float2D getRoom() {
        return room;
    }

    public void setRoom(Float2D room) {
        this.room = room;
    }

    public RoomInfo(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.room = new Float2D(width, height);
        this.room.set(1);
    }


    public int[] getCenter(){
        int[] center = new int[2];
        center[0] = 1 + getX() + getWidth() / 2;
        center[1] = 1 + getY() + getHeight() / 2;


        return center;
    }

}
