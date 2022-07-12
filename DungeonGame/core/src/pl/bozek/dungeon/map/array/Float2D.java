package pl.bozek.dungeon.map.array;

public class Float2D extends Array2D{

    private final float[] array;

    public Float2D(final int dimension){
        this(dimension, dimension);
    }

    public Float2D(final int width, final int height){
        this(new float[width*height], width, height);
    }

    public Float2D(final float array[], final int width, final int height){
        super(width, height);
        this.array = array;
        if(array.length != width * height){
            throw new IllegalArgumentException(
                    "To small or too big array to store in"
            );
        }
    }

    public float get(final int x, final int y) {
        return array[toIndex(x, y)];
    }

    public float set(final int x, final int y, final float value) {
        return array[toIndex(x, y)] = value;
    }

    public float add(final int x, final int y, final float value) {
        return array[toIndex(x, y)] += value;
    }

    public float subtract(final int x, final int y, final float value) {
        return array[toIndex(x, y)] -= value;
    }

    public float multiply(final int x, final int y, final float value) {
        return array[toIndex(x, y)] *= value;
    }

    public float divide(final int x, final int y, final float value) {
        return array[toIndex(x, y)] /= value;
    }

    public Float2D set(final float value) {
        for (int index = 0, length = array.length; index < length; index++) {
            array[index] = value;
        }
        return this;
    }





}
