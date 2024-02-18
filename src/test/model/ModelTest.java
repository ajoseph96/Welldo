package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test area used to test model methods
class ModelTest {
    private HappyTasks happyTest;
    private SadTasks sadTest;
    private MoodScore moodTest;

    @BeforeEach
    void runBefore() {
        happyTest = new HappyTasks();
        moodTest = new MoodScore();
        sadTest = new SadTasks();
    }

    //happy, sad and angrytasks subclasses all start with a suggested task as the first item in their task list.
    @Test
    void testAddTaskListSingle() {
        sadTest.addTaskList("cycling");
        assertEquals("cycling", (sadTest.getTaskList()).get(1));

    }

    @Test
    void testAddTaskListMultiple() {
        happyTest.addTaskList("jumping jacks");
        assertEquals("jumping jacks", (happyTest.getTaskList()).get(1));
        happyTest.addTaskList("hugs");
        assertEquals("hugs", (happyTest.getTaskList()).get(2));
        happyTest.addTaskList("pilates");
        assertEquals("pilates", (happyTest.getTaskList()).get(3));

    }

    @Test
    void testCalculateMoodNumDifferenceImproved() {
        moodTest.setInitialMoodNum(4);
        moodTest.setFinalMoodNum(10);
        assertTrue(moodTest.calculateMoodNumDifference());
        assertEquals(-6, moodTest.getMoodNumDifference());
    }

    @Test
    void testCalculateMoodNumDifferenceNotImproved() {
        moodTest.setInitialMoodNum(10);
        moodTest.setFinalMoodNum(7);
        assertFalse(moodTest.calculateMoodNumDifference());
        assertEquals(3, moodTest.getMoodNumDifference());
        moodTest.setInitialMoodNum(10);
        moodTest.setFinalMoodNum(10);
        assertFalse(moodTest.calculateMoodNumDifference());
        assertEquals(0, moodTest.getMoodNumDifference());
    }

}


