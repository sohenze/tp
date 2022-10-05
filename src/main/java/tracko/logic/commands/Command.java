package tracko.logic.commands;

import tracko.logic.commands.exceptions.CommandException;
import tracko.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    private boolean isAwaitingInput;
    private boolean isCancelled;

    protected Command() {
        this(false, false);
    }

    protected Command(boolean isAwaitingInput, boolean isCancelled) {
        this.isAwaitingInput = isAwaitingInput;
        this.isCancelled = isCancelled;
    }

    public void setAwaitingInput(boolean isAwaitingInput) {
        this.isAwaitingInput = isAwaitingInput;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean isAwaitingInput() {
        return isAwaitingInput;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute(Model model) throws CommandException;

}
