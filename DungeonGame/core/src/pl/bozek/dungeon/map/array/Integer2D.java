package pl.bozek.dungeon.map.array;

public class Integer2D extends Array2D {

    private final int[] array;

    public Integer2D(final int width, final int height){
        super(width, height);
        array = new int[width*height];
    }

    public int getValue(final int x, final int y){
        return array[toIndex(x, y)];
    }

    public void setValue(final int x, final int y, final int value){
        array[toIndex(x, y)] = value;
    }


}
