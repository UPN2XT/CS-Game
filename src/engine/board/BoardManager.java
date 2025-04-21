package engine.board;

import exception.*;
import model.player.Marble;

import java.util.ArrayList;

/**
 * The BoardManager interface represents the contract for board management operations.
 */
public interface BoardManager {
    /**
     * Gets the split distance for the board.
     *
     * @return the split distance
     */
    public int getSplitDistance();

    /**
     * Moves a marble by the specified number of steps.
     *
     * @param marble the marble to move
     * @param steps the number of steps to move
     * @param destroy whether to destroy marbles in the way
     * @throws IllegalMovementException if the move is not allowed
     * @throws IllegalDestroyException if a marble cannot be destroyed
     */
    public void moveBy(Marble marble, int steps, boolean destroy) throws
            IllegalMovementException, IllegalDestroyException;

    /**
     * Swaps the positions of two marbles.
     *
     * @param marble1 the first marble to swap
     * @param marble2 the second marble to swap
     * @throws IllegalSwapException if the swap is not allowed
     */
    public void swap(Marble marble1, Marble marble2) throws IllegalSwapException;

    /**
     * Destroys a marble from the board.
     *
     * @param marble the marble to destroy
     * @throws IllegalDestroyException if the marble cannot be destroyed
     */
    public void destroyMarble(Marble marble) throws IllegalDestroyException;

    /**
     * Sends a marble back to its base.
     *
     * @param marble the marble to send to base
     * @throws CannotFieldException if the marble cannot be placed in the base
     * @throws IllegalDestroyException if the marble cannot be removed from its current position
     */
    public void sendToBase(Marble marble) throws CannotFieldException, IllegalDestroyException;

    /**
     * Sends a marble to a safe zone.
     *
     * @param marble the marble to send to safe zone
     * @throws InvalidMarbleException if the marble cannot be placed in the safe zone
     */
    public void sendToSafe(Marble marble) throws InvalidMarbleException;

    /**
     * Gets a list of marbles that can perform actions.
     *
     * @return list of actionable marbles
     */
    public ArrayList<Marble> getActionableMarbles();
}