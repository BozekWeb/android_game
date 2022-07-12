package pl.bozek.dungeon.system.map.random;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;

import pl.bozek.dungeon.common.MapFactory;
import pl.bozek.dungeon.config.DungeonConfig;
import pl.bozek.dungeon.map.array.Float2D;
import pl.bozek.dungeon.map.array.Object2dArray;
import pl.bozek.dungeon.map.tile.BasicTile;
import pl.bozek.dungeon.map.tile.TileList;
import pl.bozek.dungeon.util.ConsoleUtils;

public class GenerateMapTilesSystem extends GenerateRandomMathDungeonSystem {

    private static final int OFFSET_SIZE = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;
    private static Float2D offsetMap = new Float2D(OFFSET_SIZE);
    private final MapFactory mapFactory;

    private Object2dArray<BasicTile> tiles = new Object2dArray<BasicTile>(OFFSET_SIZE, OFFSET_SIZE) {
        @Override
        protected BasicTile[] getArray(int size) {
            return new BasicTile[size];
        }
    };

    public GenerateMapTilesSystem(PooledEngine engine, AssetManager assetManager){
        super();
        mapFactory = new MapFactory(engine, assetManager);
        TileList.initTileList();
        initOffset();
        initTiles();
        generateWalls();
        checkMap();
        checkMap();
        addTiles();


        printDungeon();
        printRooms();

    }

    private void initOffset(){
        offsetMap.set(1);
        for(int i = 1; i < OFFSET_SIZE - 1; i++){
            for(int j = 1; j < OFFSET_SIZE - 1; j++){
                offsetMap.set(i, j, getDungeonLayout().get(i - 1, j - 1));
            }
        }
    }

    private void initTiles(){
        for(int x = 0; x < OFFSET_SIZE; x++){
            for(int y = 0; y < OFFSET_SIZE; y++){

                if(offsetMap.get(x, y) == 0){
                    tiles.set(x, y, TileList.getRandomFloor());
                } else{
                    tiles.set(x, y, TileList.getEmptyWall());
                }

            }
        }
    }

    private void generateWalls(){

        for (int x = 1; x < OFFSET_SIZE - 1; x++) {
            for (int y = 1; y < OFFSET_SIZE - 1; y++) {



                if(tiles.get(x, y).getType() == 1) {
                  if(tiles.get(x + 1, y).getType() == 0 && tiles.get(x - 1, y).getType() == 0
                          && tiles.get(x, y + 1).getType() == 0 && tiles.get(x, y - 1).getType() == 0){
                        //System.out.println("UP WALL");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    }else if(tiles.get(x + 1, y).getType() == 0 && tiles.get(x - 1, y).getType() == 0
                            && tiles.get(x, y + 1).getType() == 0){
                        //System.out.println("NO-DOWN");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x + 1, y).getType() == 0 && tiles.get(x - 1, y).getType() == 0
                            && tiles.get(x, y - 1).getType() == 0){
                        //System.out.println("UP WALL");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x, y + 1).getType() == 0 && tiles.get(x, y - 1).getType() == 0
                            && tiles.get(x + 1, y).getType() == 0){
                        //System.out.println("UP WALL");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x, y + 1).getType() == 0 && tiles.get(x, y - 1).getType() == 0
                            && tiles.get(x - 1, y).getType() == 0){
                        //System.out.println("UP WALL");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x + 1, y).getType() == 0 && tiles.get(x - 1, y).getType() == 0) {
                        //System.out.println("RIGHT-LEFT WALL");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 0, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x + 1, y).getType() == 0 && tiles.get(x, y + 1).getType() == 0) {
                        //System.out.println("Right Big Corner");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{0, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x - 1, y).getType() == 0 && tiles.get(x, y + 1).getType() == 0) {
                        //System.out.println("Left Big Corner");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{1, 0, 0}));

                    } else if(tiles.get(x, y - 1).getType() == 0 && tiles.get(x, y + 1).getType() == 0) {
                      //System.out.println("UP WALL");
                      tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x, y - 1).getType() == 0) {
                      //System.out.println("UP WALL");
                      tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));

                    } else if(tiles.get(x, y + 1).getType() == 0) {
                        //System.out.println("Down Side wall");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 0, 0}));

                    } else if (tiles.get(x + 1, y).getType() == 0) {
                        //System.out.println("Right Wall");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{0, 0, 1}, new int[]{0, 0, 1}, new int[]{0, 0, 0}, new int[]{1, 1, 1}));

                    } else if (tiles.get(x - 1, y).getType() == 0) {
                        //System.out.println("Left Wall");
                        tiles.set(x, y, TileList.getRandomWall(new int[]{1, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{0, 0, 0}));
                    }



                }
            }
        }



    }

    private void checkMap() {
        for (int i = 1; i < OFFSET_SIZE - 1; i++) {
            for (int j = 1; j < OFFSET_SIZE - 1; j++) {
                if (tiles.get(i, j).getType() == 1) {

                    int u_1 = tiles.get(i, j + 1).getDown()[0];
                    int u_2 = tiles.get(i, j + 1).getDown()[1];
                    int u_3 = tiles.get(i, j + 1).getDown()[2];

                    int r_1 = tiles.get(i + 1, j).getLeft()[0];
                    int r_2 = tiles.get(i + 1, j).getLeft()[1];
                    int r_3 = tiles.get(i + 1, j).getLeft()[2];

                    int d_1 = tiles.get(i, j - 1).getUp()[0];
                    int d_2 = tiles.get(i, j - 1).getUp()[1];
                    int d_3 = tiles.get(i, j - 1).getUp()[2];

                    int l_1 = tiles.get(i - 1, j).getRight()[0];
                    int l_2 = tiles.get(i - 1, j).getRight()[1];
                    int l_3 = tiles.get(i - 1, j).getRight()[2];


                    if (isLeftSmallCorner(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Small Left Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 0, 0}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{0, 0, 0}));
                    }

                    if (isRightSmallCorner(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Small Right Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{0, 0, 1}, new int[]{0, 0, 0}, new int[]{0, 0, 0}, new int[]{1, 0, 0}));
                    }

                    if (isDoubleSmallCorner(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Small Double Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 0, 1}, new int[]{0, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 0, 0}));
                    }

                    if (isRightLeftWall(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Right Left Wall, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 0, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));
                    }

                    if (isLeftWall(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Left Wall, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 0, 0}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{0, 0, 0}));
                    }

                    if (isRightWall(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Right Wall, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{0, 0, 1}, new int[]{0, 0, 1}, new int[]{0, 0, 0}, new int[]{1, 1, 1}));
                    }

                    if (isNoDown(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("No Down, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 0, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}));
                    }

                    if (isBigLeftCorner(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Big Left Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{1, 0, 0}));
                    }

                    if (isBigRightCorner(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Big Right Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 1, 1}, new int[]{0, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}));
                    }

                    if (isLeftSmallRight(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Left Wall - Small Right Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}, new int[]{1, 0, 0}));
                    }

                    if (isRightSmallLeft(u_1, u_2, u_3, r_1, r_2, r_3, d_1, d_2, d_3, l_1, l_2, l_3)) {
                        //System.out.println("Right Wall - Small Left Corner, (x, y) = (" + i + ", " + j + ")");
                        tiles.set(i, j, TileList.getRandomWall(new int[]{1, 0, 1}, new int[]{0, 0, 1}, new int[]{1, 0, 0}, new int[]{1, 1, 1}));
                    }
                }
            }
        }
    }

    private void addTiles(){
        for(int x = 0; x < OFFSET_SIZE; x++){
            for(int y = 0; y < OFFSET_SIZE; y++){

                mapFactory.addTile(x, y, tiles.get(x,y).getType(), tiles.get(x, y).getRegionName());

            }
        }
    }

    private boolean isLeftSmallCorner(int u_1, int u_2, int u_3,
                                      int r_1, int r_2, int r_3,
                                      int d_1, int d_2, int d_3,
                                      int l_1, int l_2, int l_3){

        return (u_1 == 1) && (u_2 == 0) && (u_3 == 0) &&
                (r_1 == 0) && (r_2 == 0) && (r_3 == 0) &&
                (d_1 == 0 || d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 0 || d_3 == 1) &&
                (l_1 == 1) && (l_2 == 0) && (l_3 == 0);

    }

    public static int[][] getBlocks(){
        int count = 0;

        for(int i = 0; i < OFFSET_SIZE; i++){
            for(int j = 0; j < OFFSET_SIZE; j++){
                if(offsetMap.get(i, j) == 1){
                    count++;
                }
            }
        }

        int[][] block = new int[count][2];

        int check = 0;
        while(check <count)
            for(int i = 0; i < OFFSET_SIZE; i++){
                for(int j = 0; j < OFFSET_SIZE; j++){
                    if(offsetMap.get(i, j) == 1){
                        block[check][0] = i;
                        block[check][1] = j;
                        check++;
                    }
                }
        }
        return block;
    }



    private boolean isRightSmallCorner(int u_1, int u_2, int u_3,
                                       int r_1, int r_2, int r_3,
                                       int d_1, int d_2, int d_3,
                                       int l_1, int l_2, int l_3){

        return (u_1 == 0) && (u_2 == 0) && (u_3 == 1) &&
                (r_1 == 1) && (r_2 == 0) && (r_3 == 0) &&
                (d_1 == 0 || d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 0 || d_3 == 1) &&
                (l_1 == 0) && (l_2 == 0) && (l_3 == 0);

    }

    private boolean isDoubleSmallCorner(int u_1, int u_2, int u_3,
                                        int r_1, int r_2, int r_3,
                                        int d_1, int d_2, int d_3,
                                        int l_1, int l_2, int l_3){

        return (u_1 == 1) && (u_2 == 0) && (u_3 == 1) &&
                (r_1 == 1) && (r_2 == 0) && (r_3 == 0) &&
                (d_1 == 0 || d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 0 || d_3 == 1) &&
                (l_1 == 1) && (l_2 == 0) && (l_3 == 0);

    }

    private boolean isRightLeftWall(int u_1, int u_2, int u_3,
                                    int r_1, int r_2, int r_3,
                                    int d_1, int d_2, int d_3,
                                    int l_1, int l_2, int l_3){

        return (u_1 == 1 || u_1 == 0) && (u_2 == 0) && (u_3 == 1 || u_3 == 0) &&
                (r_1 == 1 || r_1 == 2) && (r_2 == 1 || r_2 == 2) && (r_3 == 1 || r_3 == 2) &&
                (d_1 == 1) && (d_2 == 1 || d_2 == 0) && (d_3 == 1) &&
                (l_1 == 1 || l_1 == 2) && (l_2 == 1 || l_2 == 2) && (l_3 == 1 || l_3 == 2);

    }

    private boolean isLeftWall(int u_1, int u_2, int u_3,
                               int r_1, int r_2, int r_3,
                               int d_1, int d_2, int d_3,
                               int l_1, int l_2, int l_3){

        return (u_1 == 1 || u_1 == 0) && (u_2 == 0) && (u_3 == 0) &&
                (r_1 == 0) && (r_2 == 0) && (r_3 == 0) &&
                (d_1 == 1) && (d_2 == 1 || d_2 == 0) && (d_3 == 1 || d_3 == 0) &&
                (l_1 == 1 || l_1 == 2) && (l_2 == 1 || l_2 == 2) && (l_3 == 1 || l_3 == 2);

    }

    private boolean isRightWall(int u_1, int u_2, int u_3,
                                int r_1, int r_2, int r_3,
                                int d_1, int d_2, int d_3,
                                int l_1, int l_2, int l_3){

        return (u_1 == 0) && (u_2 == 0) && (u_3 == 1 || u_3 == 0) &&
                (r_1 == 1 || r_1 == 2) && (r_2 == 1 || r_2 == 2) && (r_3 == 1 || r_3 == 2) &&
                (d_1 == 0 || d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 1) &&
                (l_1 == 0) && (l_2 == 0) && (l_3 == 0);

    }

    private boolean isNoDown(int u_1, int u_2, int u_3,
                             int r_1, int r_2, int r_3,
                             int d_1, int d_2, int d_3,
                             int l_1, int l_2, int l_3){

        return (u_1 == 2) && (u_2 == 2) && (u_3 == 2) &&
                (r_1 == 1 || r_1 == 2) && (r_2 == 1 || r_2 == 2) && (r_3 == 1 || r_3 == 2) &&
                (d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 1) &&
                (l_1 == 1 || l_1 == 2) && (l_2 == 1 || l_2 == 2) && (l_3 == 1 || l_3 == 2);

    }

    private boolean isBigLeftCorner(int u_1, int u_2, int u_3,
                                    int r_1, int r_2, int r_3,
                                    int d_1, int d_2, int d_3,
                                    int l_1, int l_2, int l_3){

        return (u_1 == 2) && (u_2 == 2) && (u_3 == 2) &&
                (r_1 == 1) && (r_2 == 0) && (r_3 == 0) &&
                (d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 0 || d_3 == 1) &&
                (l_1 == 1 || l_1 == 2) && (l_2 == 1 || l_2 == 2) && (l_3 == 1 || l_3 == 2);

    }

    private boolean isBigRightCorner(int u_1, int u_2, int u_3,
                                     int r_1, int r_2, int r_3,
                                     int d_1, int d_2, int d_3,
                                     int l_1, int l_2, int l_3){

        return (u_1 == 2) && (u_2 == 2) && (u_3 == 2) &&
                (r_1 == 1 || r_1 == 2) && (r_2 == 1 || r_2 == 2) && (r_3 == 1 || r_3 == 2) &&
                (d_1 == 0 || d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 1) &&
                (l_1 == 1) && (l_2 == 0) && (l_3 == 0);

    }

    private boolean isLeftSmallRight(int u_1, int u_2, int u_3,
                                     int r_1, int r_2, int r_3,
                                     int d_1, int d_2, int d_3,
                                     int l_1, int l_2, int l_3){

        return (u_1 == 1 || u_1 == 0) && (u_2 == 0) && (u_3 == 1) &&
                (r_1 == 1) && (r_2 == 0) && (r_3 == 0) &&
                (d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 0 || d_3 == 1) &&
                (l_1 == 1 || l_1 == 2) && (l_2 == 1 || l_2 == 2) && (l_3 == 1 || l_3 == 2);

    }

    private boolean isRightSmallLeft(int u_1, int u_2, int u_3,
                                     int r_1, int r_2, int r_3,
                                     int d_1, int d_2, int d_3,
                                     int l_1, int l_2, int l_3){

        return (u_1 == 1) && (u_2 == 0) && (u_3 == 1 || u_3 == 0) &&
                (r_1 == 1 || r_1 == 2) && (r_2 == 1 || r_2 == 2) && (r_3 == 1 || r_3 == 2) &&
                (d_1 == 0 || d_1 == 1) && (d_2 == 0 || d_2 == 1) && (d_3 == 1) &&
                (l_1 == 1) && (l_2 == 0) && (l_3 == 0);

    }


    private void printOffsetMap(){
        StringBuilder consoleArray = new StringBuilder();
        for (int x = 0; x < offsetMap.getWidth(); x++) {
            for (int y = 0; y < offsetMap.getHeight(); y++) {

                if(offsetMap.get(x, y) == 1)
                    consoleArray.append(ConsoleUtils.ANSI_RED).append(offsetMap.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

                if(offsetMap.get(x, y) == 9)
                    consoleArray.append(ConsoleUtils.ANSI_YELLOW).append(offsetMap.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

                if(offsetMap.get(x, y) == 0)
                    consoleArray.append(ConsoleUtils.ANSI_BLUE).append(offsetMap.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

            }

            consoleArray.append("\n");

        }
        System.out.println(consoleArray);
    }

    public static Float2D getOffsetMap() {
        return offsetMap;
    }
}
