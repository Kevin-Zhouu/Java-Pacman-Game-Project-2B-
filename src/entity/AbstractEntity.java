package entity;
import util.Drawable;
import util.Vector2;
import bagel.*;

public abstract class AbstractEntity implements Drawable {
    // the current position of the entity
    private Vector2 position;
    private Vector2 initialPosition;
    // determine whether the entity should be rendered
    private boolean visible;
    /*
        instantiate a new entity
        @param position the position of the entity
     */
    public AbstractEntity(Vector2 position) {
        this.position = position;
        this.initialPosition = position.copy();
        this.visible = true;
    }
    /*
        Get the current position of an entity
        @return Vector2 the current position
     */
    public Vector2 getPosition() {
        return position;
    }
    /*
       Set the current position of an entity
       @param position the new position of an entity
       @return void
    */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /*
        Get the current visibility of an entity
        @return boolean the current visibility
    */
    public boolean isVisible() {
        return visible;
    }
    /*
      Set the current visibility of an entity
      @param visible the new visibility of an entity
      @return void
   */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    /*
    get the current image of the entity
    @return Image the image of the entity
     */
    public abstract Image getImage();

    /*
        reset the position of an object
     */
    public void resetPosition(){
        this.position = initialPosition.copy();
    }
    /*
         set the initial position of an object
     */
    public void setInitialPosition(Vector2 initialPosition) {
        this.initialPosition = initialPosition;
    }
}
