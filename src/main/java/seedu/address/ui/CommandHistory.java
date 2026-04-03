package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

public class CommandHistory {

    private final List<String> commandHistory = new ArrayList<>();
    private int historyIndex = 0;
    private String currentInput = "";

    public CommandHistory() {

    }

    /**
     * Adds a command to the history. If the command is a duplicate of an existing command, the existing command
     * is removed and the new command is added to the end of the history.
     */
    public void addCommand(String command) {
        assert command != null;
        if(isDuplicateCommand(command)) {
            removeAllDuplicateCommand(command);
        }
        commandHistory.add(command);
        historyIndex = commandHistory.size();
        currentInput="";

    }

    private void removeAllDuplicateCommand(String command) {
        commandHistory.removeIf(c -> c.equalsIgnoreCase(command));
    }

    private boolean isDuplicateCommand(String command) {
        return commandHistory.contains(command);
    }


    /**
     * Returns the previous command in the history. If there is no previous command, returns the current input.
     */
    public String getPreviousCommand(String currentInput) {
        assert currentInput != null;
        if (commandHistory.isEmpty()) {
            return currentInput;
        }

        // set current input
        if (historyIndex == commandHistory.size()) {
            this.currentInput = currentInput;
        }

        if (historyIndex > 0) {
            historyIndex -= 1;
        }

        return commandHistory.get(historyIndex);
    }

    /**
     * Returns the next command in the history. If there is no next command, returns the current input.
     */
    public String getNextCommand() {
        if (commandHistory.isEmpty()) {
            return currentInput;
        }

        if (historyIndex < commandHistory.size()) {
            historyIndex += 1;
        }

        if (historyIndex == commandHistory.size()) {
            return currentInput;
        } else {
            return commandHistory.get(historyIndex);
        }
    }

}
