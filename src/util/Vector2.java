package util;
public class Vector2 {
    /*
        unit vector for left direction
     */
    public static final Vector2 LEFT_DIRECTION = new Vector2(-1,0);
    /*
       unit vector for right direction
    */
    public static final Vector2 RIGHT_DIRECTION = new Vector2(1,0);
    /*
       unit vector for up direction
    */
    public static final Vector2 UP_DIRECTION = new Vector2(0,-1);
    /*
       unit vector for down direction
    */
    public static final Vector2 DOWN_DIRECTION = new Vector2(0,1);
    //x and y coordinates
    private double x;
    private double y;
    /*
    instantiate a new vector
    @param double x x value of a vector
    @param double y y value of the vector
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /*
     * increment value of x
     */
    public void increaseX(double dx){
        this.x = this.x + dx;
    }
    /*
     * increment value of y
     */
    public void increaseY(double dy){
        this.y = this.y + dy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Perform the scalar product of a vector
     * @param v1 the vector being operated
     * @param c the constant for scalar multiplication
     * @return Vector2 the result of vector multiplication
     */
    public static Vector2 scalarProduct(Vector2 v1, double c){
        Vector2 newVector = new Vector2(v1.x, v1.y);
        newVector.x *= c;
        newVector.y *= c;
        return newVector;
    }


    /**
     * perform vector addition operation
     * @param v1 the first vector
     * @param v2 the second vector
     * @return Vector2 the result vector
     */
    public static Vector2 addition(Vector2 v1, Vector2 v2){
        Vector2 newVector = new Vector2(v1.x, v1.y);
        newVector.x += v2.getX();
        newVector.y += v2.getY();
        return newVector;
    }
    /*
    create a copy of the current vector
    @return Vector2 new vector
     */
    public Vector2 copy(){
        return new Vector2(this.x, this.y);
    }
}
