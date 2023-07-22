package entity;

import bagel.Image;
import util.Collidable;
import util.Vector2;

public abstract class AbstractProp extends AbstractEntity implements Collidable {
    private final int points;

    /**
     * instantiate naprop
     * @param position initial position of the prop
     * @param points point reward of the prop
     */
    public AbstractProp(Vector2 position, int points) {
        super(position);
        this.points = points;
    }
    /*
    get point reward for prop
    @return int points
     */
    public int getPoints() {
        return points;
    }
    /*
    get the image for prop
    @return Image the image object
     */
    public abstract Image getImage();
}
