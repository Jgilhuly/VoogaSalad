package events;

import gameworld.GameWorld;


public class ConstantSpacingWave extends Wave {

    private int myFramesBetweenEnemies;
    private int myFramesSinceLastRelease;

    public ConstantSpacingWave (double secBetweenObjects, GameObjectQueue objects, GameWorld world) {
        super(objects, world);
        myFramesBetweenEnemies = SecondsToFrames.getFramesForSeconds(secBetweenObjects);
        myFramesSinceLastRelease = 0;
    }

    @Override
    public void update () {
        if (myFramesSinceLastRelease == myFramesBetweenEnemies) {
            // release enemy
            releaseObject();
            // reset frame counter
            myFramesSinceLastRelease = 0;
        }
        else {
            myFramesSinceLastRelease++;
        }

    }
}
