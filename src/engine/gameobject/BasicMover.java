package engine.gameobject;

import engine.fieldsetting.Settable;
import engine.pathfinding.EndOfPathException;


@Settable
public abstract class BasicMover implements Mover {
    double inherentSpeed;
    double speedModifier;
    double myDistance;
    boolean frozen;
    
    public BasicMover() {
        inherentSpeed=0;
        speedModifier=0;
        myDistance=0;
        frozen=false;
    }

    public BasicMover(double speed){
            myDistance = 0;
            inherentSpeed = speed;
            frozen = false;
            speedModifier = 1;
    }
    
    @Override
    public abstract PointSimple move (PointSimple current) throws EndOfPathException;
    /**
     * This switch statement is not worth having polymorphism/using a state pattern.
     * No incompatible extensions will be made.
     */

    @Settable
    @Override
    public void setSpeed (double speed) {
        inherentSpeed = speed;
    }
    
    public void setFreeze (boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * 
     * @param percentage i.e. .90 for 90% speedup
     */
    public void speedBuff (double percentage) {
        speedModifier = speedModifier + percentage;
    }

    public abstract Mover clone();

    protected double currentSpeed () {
        if (frozen != true)
            return inherentSpeed * speedModifier;
        return 0;
    }
}
