package model;

//class implementing score values related to mood and how it may change as mood changes
public class MoodScore {
    private int initialMoodNum;
    private int finalMoodNum;

    private int moodNumDifference;

    // EFFECTS: mood score class constructor.
    public MoodScore() {
        initialMoodNum = 0;
        moodNumDifference = 0;
        finalMoodNum = 0;
    }

    // EFFECTS: returns difference in mood score value (can be positive or negative).
    public int getMoodNumDifference() {
        return moodNumDifference;
    }

    // EFFECTS: sets value of initial mood score.
    public void setInitialMoodNum(int i) {
        initialMoodNum = i;
    }

    //REQUIRES: initial and final mood scores to be set to integers  > 0, <= 10
    //MODIFIES: this
    // EFFECTS: calculates difference between mood scores, returns true if value is < 0 (indicating positive change)
    public boolean calculateMoodNumDifference() {
        moodNumDifference = initialMoodNum - finalMoodNum;
        if (moodNumDifference < 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: sets value of final mood score.
    public void setFinalMoodNum(int i) {
        finalMoodNum = i;
    }


}
