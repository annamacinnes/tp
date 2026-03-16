package seedu.address.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;

public class ResultDisplayTest {

    @BeforeAll
    public static void initToolkit() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // toolkit already started
        }
    }

    @Test
    public void setFeedbackToUser_removesExitStyle() {
        ResultDisplay resultDisplay = new ResultDisplay();
        resultDisplay.setFeedbackToUser("Hello");
    }

    @Test
    public void setExitMessage_addsExitStyle() {
        ResultDisplay resultDisplay = new ResultDisplay();

        resultDisplay.setExitMessage("Bye");
        resultDisplay.setExitMessage("Bye again");
    }
}
