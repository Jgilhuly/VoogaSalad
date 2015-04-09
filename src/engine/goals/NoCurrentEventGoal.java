package engine.goals;

import java.util.Observable;
import engine.game.StoryBoard;

public class NoCurrentEventGoal extends Goal {

    private StoryBoard myStoryBoard;
    
    public NoCurrentEventGoal () {
        initialize(new StoryBoard());
    }

    public NoCurrentEventGoal (StoryBoard storyBoard) {
        initialize(storyBoard);
    }
    
    private void initialize(StoryBoard storyBoard) {
        myStoryBoard=storyBoard;
    }
    
    @Override
    public boolean isSatisfied () {
        return !(myStoryBoard.eventInProgress());
    }
    
    @Override
    public void update (Observable o, Object arg) {
        // doesn't observe anything
        
    }

}
