package pl.bozek.dungeon.map.generator.room;

import pl.bozek.dungeon.map.array.Float2D;
import pl.bozek.dungeon.system.map.random.GenerateRandomMathDungeonSystem;
import pl.bozek.dungeon.map.RoomInfo;

public interface RoomType {
    /** @param room should be filled.
     * @param grid should contain the room in its selected position.
     * @param value value with which the room should be filled. */
    void carve(Room room, Float2D grid, float value);

    /** @param room is about to be filled.
     * @return true if this type can handle this room. Returns false if the room has invalid properties and cannot be
     *         properly created with this type. */
    boolean isValid(Room room);

    /** Wraps around an existing type, allowing to slightly modify its behavior. A common usage can be changing of tile
     * value in carve method to a custom one, allowing different room types to use different tile sets, for example.
     * Extend this class and override isValid method if you want to add custom conditions.
     *
     * @author MJ */
    public static class Interceptor implements RoomType {
        protected final RoomType type;
        protected final float value;

        /** @param type wrapped type. Will delegate method calls to this type. Cannot be null.
         * @param value custom value passed to carving method. */
        public Interceptor(final RoomType type, final float value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public void carve(final Room room, final Float2D grid, final float value) {
            type.carve(room, grid, this.value);
        }

        @Override
        public boolean isValid(final Room room) {
            return type.isValid(room);
        }
    }

    /** Contains default implementations of {@link RoomType}.
     *
     * @author MJ */
    public static enum DefaultRoomType implements RoomType {
        /** Fills all of room's cells. Default behavior if room types are not used. Works with any room size. */
        SQUARE {
            @Override
            public void carve(final Room room, final Float2D grid, final float value) {
                room.fill(grid, value);
                RoomInfo info = new RoomInfo(room.getX(), room.getY(), room.getWidth(), room.getHeight());
                info.setRoom(room.fillRoom(value));
                GenerateRandomMathDungeonSystem.rooms.add(info);

            }
        },
        /** Forms a cross-shaped room, dividing the room into 9 (usually) equal parts and removing the corner ones.
         * Requires the room to have at least 3x3 size. Works best with square rooms. */
        CROSS {
            public static final int MIN_SIZE = 3;

            @Override
            public void carve(final Room room, final Float2D grid, final float value) {
                final int offsetX = room.getWidth() / 3;
                final int offsetY = room.getHeight() / 3;

                RoomInfo info = new RoomInfo(room.getX(), room.getY(), room.getWidth(), room.getHeight());


                for (int x = 0, width = room.getWidth(); x < width; x++) {
                    for (int y = offsetY, height = room.getHeight() - offsetY; y < height; y++) {
                        grid.set(x + room.getX(), y + room.getY(), value);
                        info.getRoom().set(x, y, value);
                    }
                }
                for (int x = offsetX, width = room.getWidth() - offsetX; x < width; x++) {
                    for (int y = 0, height = room.getHeight(); y < height; y++) {
                        grid.set(x + room.getX(), y + room.getY(), value);
                        info.getRoom().set(x, y, value);
                    }
                }
                GenerateRandomMathDungeonSystem.rooms.add(info);
            }

            @Override
            public boolean isValid(final Room room) {
                return room.getWidth() >= MIN_SIZE && room.getHeight() >= MIN_SIZE;
            }
        };

        @Override
        public boolean isValid(final Room room) {
            return true;

        }
    }
}
