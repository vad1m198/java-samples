package ru.vmerkotan.manager;

import org.junit.Test;
import ru.vmerkotan.server.actions.ChangeDirAction;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ActionsManager class.
 * Created by vmerkotan on 12/29/2016.
 */
public class ActionsManagerTest {
    /**
     * Holds Internal ActionsManager instance.
     */
    private ActionsManager manager = new ActionsManager();

    /**
     * Test addAction method.
     */
    @Test
    public void whenAddActionThenLengthShouldBeIncreased() {
        int count = 15;
        for (int i = 0; i < count; i++) {
            manager.addAction(new ChangeDirAction("cd", "changeDirectory", null, null));
        }
        assertThat(manager.getAllActions().length, is(count));
    }

    /**
     * test executeByKey method. When key is invalid throw InvalidActionKeyException
     * @throws IOException when error appear.
     */
    @Test(expected = InvalidActionKeyException.class)
    public void whenKeyIsNotRecognizedThenThrow() throws IOException {
        manager.addAction(new ChangeDirAction("cd", "changeDirectory", null, null));
        manager.executeByKey("invalid params");
    }
}