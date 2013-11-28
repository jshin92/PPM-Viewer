public class Coordinate {

    private static final int MAGIC_HASH_CONST = 51;

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean equals(Object obj) {
        Coordinate other = (Coordinate) obj;
        return x == other.getX() && y == other.getY();
    }

    // integer hash from http://stackoverflow.com/questions/664014/
    private int int_hash(int x) {
        x = ((x >> 16) ^ x) * 0x45d9f3b;
        x = ((x >> 16) ^ x) * 0x45d9f3b;
        x = ((x >> 16) ^ x);
        return x;
    }

    public int hashCode() {
        return (MAGIC_HASH_CONST + int_hash(x)) * MAGIC_HASH_CONST + int_hash(y);
    }

}
