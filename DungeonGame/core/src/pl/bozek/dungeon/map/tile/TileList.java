package pl.bozek.dungeon.map.tile;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import pl.bozek.dungeon.assets.RegionNames;

public class TileList {

    private static Array<BasicTile> TILE_LIST;

    //Checked U D L R
    /** Right Side Walls */
    private final static BasicTile RIGHT_WALL_1 =
            new BasicTile(new int[]{0, 0, 1}, new int[]{0, 0, 1}, new int[]{0, 0, 0}, new int[]{1, 1, 1}, 1, RegionNames.WALL_1);

    private final static BasicTile RIGHT_WALL_2 =
            new BasicTile(new int[]{0, 0, 1}, new int[]{0, 0, 1}, new int[]{0, 0, 0}, new int[]{1, 1, 1}, 1, RegionNames.WALL_2);

    /** Left Side Walls */
    private final static BasicTile LEFT_WALL_1 =
            new BasicTile(new int[]{1, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{0, 0, 0}, 1, RegionNames.WALL_3);

    private final static BasicTile LEFT_WALL_2 =
            new BasicTile(new int[]{1, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{0, 0, 0}, 1, RegionNames.WALL_4);

    /** Left-Right Side Walls */
    private final static BasicTile RIGHT_LEFT_WALL_1 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_5);

    private final static BasicTile RIGHT_LEFT_WALL_2 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_6);

    private final static BasicTile RIGHT_LEFT_WALL_3 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_7);

    private final static BasicTile RIGHT_LEFT_WALL_4 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_8);

    /** Up Side Walls */

    //Checked U D L R

    /**
     *  Do konstruktora podajemy:
     *      4 parametry int[3], odpowiadające kolejno górnej, dolnej, lewej i prawej krawędzi
     *      rodzaj puzzla, 1 - ściana, 0 - podłoga
     *      nazwe tekstury
     * */
    private final static BasicTile UP_WALL_1 =
            new BasicTile(new int[]{1, 1, 1}, new int[]{1, 1, 1},
                    new int[]{1, 1, 1}, new int[]{1, 1, 1},
                    1, RegionNames.WALL_9);

    private final static BasicTile UP_WALL_2 =
            new BasicTile(new int[]{1, 1, 1}, new int[]{1, 1, 1},
                    new int[]{1, 1, 1}, new int[]{1, 1, 1},
                    1, RegionNames.WALL_10);

    private final static BasicTile UP_WALL_3 =
            new BasicTile(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_11);

    private final static BasicTile UP_WALL_4 =
            new BasicTile(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_12);

    /** Small Wall Corners */
    private final static BasicTile SMALL_RIGHT_CORNER =
            new BasicTile(new int[]{0, 0, 1}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, 1, RegionNames.WALL_13);

    private final static BasicTile SMALL_LEFT_CORNER =
            new BasicTile(new int[]{1, 0, 0}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{0, 0, 0}, 1, RegionNames.WALL_15);

    private final static BasicTile SMALL_DOUBLE_CORNER =
            new BasicTile(new int[]{1, 0, 1}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 0, 0}, 1, RegionNames.WALL_14);

    /** Down Side Wall */
    private final static BasicTile DOWN_WALL_1 =
            new BasicTile(new int[]{1, 1, 1}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 0, 0}, 1, RegionNames.WALL_16);

    private final static BasicTile DOWN_WALL_2 =
            new BasicTile(new int[]{1, 1, 1}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 0, 0}, 1, RegionNames.WALL_17);

    /** Big Corners and no down wall */
    private final static BasicTile LEFT_BIG_CORNER =
            new BasicTile(new int[]{1, 1, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{1, 0, 0}, 1, RegionNames.WALL_18);

    private final static BasicTile RIGHT_BIG_CORNER =
            new BasicTile(new int[]{1, 1, 1}, new int[]{0, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, 1, RegionNames.WALL_19);

    private final static BasicTile NO_DOWN =
            new BasicTile(new int[]{1, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, 1, RegionNames.WALL_20);

    /** Big wall and small corner */
    private final static BasicTile RIGHT_SMALL_LEFT_1 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{0, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, 1, RegionNames.WALL_21);

    private final static BasicTile RIGHT_SMALL_LEFT_2 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{0, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, 1, RegionNames.WALL_22);

    private final static BasicTile LEFT_SMALL_RIGHT_1 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{1, 0, 0}, 1, RegionNames.WALL_23);

    private final static BasicTile LEFT_SMALL_RIGHT_2 =
            new BasicTile(new int[]{1, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{1, 0, 0}, 1, RegionNames.WALL_24);

    /** Empty wall */
    private final static BasicTile EMPTY_WALL =
            new BasicTile(new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, 1, RegionNames.WALL_25);

    /** All floors */
    private final static BasicTile FLOOR_1 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_1);

    private final static BasicTile FLOOR_2 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_2);

    private final static BasicTile FLOOR_3 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_3);

    private final static BasicTile FLOOR_4 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_4);

    private final static BasicTile FLOOR_5 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_5);

    private final static BasicTile FLOOR_6 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_6);

    private final static BasicTile FLOOR_7 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_7);

    private final static BasicTile FLOOR_8 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_8);

    private final static BasicTile FLOOR_9 =
            new BasicTile(new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, new int[]{2, 2, 2}, 0, RegionNames.FLOOR_9);





    public static void initTileList(){
        TILE_LIST = new Array<BasicTile>();

        TILE_LIST.add(RIGHT_WALL_1);
        TILE_LIST.add(RIGHT_WALL_2);
        TILE_LIST.add(LEFT_WALL_1);
        TILE_LIST.add(LEFT_WALL_2);
        TILE_LIST.add(RIGHT_LEFT_WALL_1);
        TILE_LIST.add(RIGHT_LEFT_WALL_2);
        TILE_LIST.add(RIGHT_LEFT_WALL_3);
        TILE_LIST.add(RIGHT_LEFT_WALL_4);
        TILE_LIST.add(UP_WALL_1);
        TILE_LIST.add(UP_WALL_2);
        TILE_LIST.add(UP_WALL_3);
        TILE_LIST.add(UP_WALL_4);
        TILE_LIST.add(SMALL_LEFT_CORNER);
        TILE_LIST.add(SMALL_RIGHT_CORNER);
        TILE_LIST.add(SMALL_DOUBLE_CORNER);
        TILE_LIST.add(DOWN_WALL_1);
        TILE_LIST.add(DOWN_WALL_2);
        TILE_LIST.add(LEFT_BIG_CORNER);
        TILE_LIST.add(RIGHT_BIG_CORNER);
        TILE_LIST.add(NO_DOWN);
        TILE_LIST.add(RIGHT_SMALL_LEFT_1);
        TILE_LIST.add(RIGHT_SMALL_LEFT_2);
        TILE_LIST.add(LEFT_SMALL_RIGHT_1);
        TILE_LIST.add(LEFT_SMALL_RIGHT_2);
        TILE_LIST.add(EMPTY_WALL);

        TILE_LIST.add(FLOOR_1);
        TILE_LIST.add(FLOOR_2);
        TILE_LIST.add(FLOOR_3);
        TILE_LIST.add(FLOOR_4);
        TILE_LIST.add(FLOOR_5);
        TILE_LIST.add(FLOOR_6);
        TILE_LIST.add(FLOOR_7);
        TILE_LIST.add(FLOOR_8);
        TILE_LIST.add(FLOOR_9);
    }

    public static BasicTile getRandomFloor(){
        Array<BasicTile> floors = new Array<BasicTile>();

        for( BasicTile tile : TILE_LIST){
            if(tile.getType() == 0) floors.add(tile);
        }
        return floors.get(MathUtils.random(floors.size - 1));
    }


    public static BasicTile getRandomWall(int[] up, int[] down, int[] left, int[] right){
        Array<BasicTile> walls = new Array<BasicTile>();



        for( BasicTile tile : TILE_LIST){

            if(tile.isUpSame(up) && tile.isDownSame(down) && tile.isLeftSame(left) && tile.isRightSame(right)){
                walls.add(tile);
            }
        }
        if(walls.isEmpty()) return getEmptyWall();
        return walls.get(MathUtils.random(walls.size -1));

    }


    public static BasicTile getEmptyWall() {
        return EMPTY_WALL;
    }

}
