package pl.bozek.dungeon.map.generator.room;

import pl.bozek.dungeon.map.array.Float2D;
import pl.bozek.dungeon.map.array.Integer2D;

public class Room {
    private final int x, y;
    private final int width, height;

    public Room(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

        /** @param room another room instance.
         * @return true if the two rooms overlap with each other. */
        public boolean overlaps(final Room room) {
            return x < room.x + room.width && x + width > room.x && y < room.y + room.height && y + height > room.y;
        }

        /** @param grid its cells will be modified.
         * @param value will be used to fill all cells contained by the room. */
        public void fill(final Float2D grid, final float value) {
            for (int x = this.x, sizeX = this.x + width; x < sizeX; x++) {
                for (int y = this.y, sizeY = this.y + height; y < sizeY; y++) {
                    grid.set(x, y, value);
                }
            }
        }


    public Float2D fillRoom(final float value) {
            Float2D room = new Float2D(width, height);

        for (int x = 0, sizeX = 0 + width; x < sizeX; x++) {
            for (int y = 0, sizeY = 0 + height; y < sizeY; y++) {
                room.set(x, y, value);
            }
        }

        return room;
    }



        /** @param grid its cells will be modified.
         * @param value will be used to fill all cells contained by the room. */
        public void fill(final Integer2D grid, final int value) {
            for (int x = this.x, sizeX = this.x + width; x < sizeX; x++) {
                for (int y = this.y, sizeY = this.y + height; y < sizeY; y++) {
                    grid.setValue(x, y, value);
                }
            }
        }

        /** @param x column index.
         * @param y row index.
         * @return true if the passed position is on the bounds of the room. */
        public boolean isBorder(final int x, final int y) {
            return this.x == x || this.y == y || this.x + width - 1 == x || this.y + height - 1 == y;
        }

        /** @return column index of the room's start. */
        public int getX() {
            return x;
        }

        /** @return row index of the room's start. */
        public int getY() {
            return y;
        }

        /** @return amount of columns taken by the room. */
        public int getWidth() {
            return width;
        }

        /** @return amount of rows taken by the room. */
        public int getHeight() {
            return height;
        }

        @Override // Auto-generated.
        public String toString() {
            return "Room [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
        }
    }



