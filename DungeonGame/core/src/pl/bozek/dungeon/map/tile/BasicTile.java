package pl.bozek.dungeon.map.tile;

public class BasicTile {

    private int[] up;
    private int[] down;
    private int[] left;
    private int[] right;

    private String regionName;

    private int type;

    public BasicTile(int[] up, int[] down, int[] left, int[] right, int type, String regionName) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.type = type;
        this.regionName = regionName;
    }

    public int[] getUp() {
        return up;
    }

    public int[] getDown() {
        return down;
    }

    public int[] getLeft() {
        return left;
    }

    public int[] getRight() {
        return right;
    }

    public String getRegionName() {
        return regionName;
    }

    public int getType() {
        return type;
    }

    boolean isLeftSame(int[] right){
        int count = 0;
        for(int i = 0; i < 3; i++){
            if(this.left[i] == right[i]){
                count++;
            }
        } return count == 3;
    }

    boolean isRightSame(int[] left){
        int count = 0;
        for(int i = 0; i < 3; i++){
            if(this.right[i] == left[i]){
                count++;
            }
        } return count == 3;
    }

    boolean isUpSame(int[] down){
        int count = 0;
        for(int i = 0; i < 3; i++){
            if(this.up[i] == down[i]){
                count++;
            }
        } return count == 3;
    }

    boolean isDownSame(int[] up){
        int count = 0;
        for(int i = 0; i < 3; i++){
            if(this.down[i] == up[i]){
                count++;
            }
        } return count == 3;
    }



}
