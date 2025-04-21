package engine;

import exception.CannotDiscardException;
import exception.CannotFieldException;
import exception.IllegalDestroyException;
import model.Colour;
import model.player.Marble;

/**
 * The GameManager interface represents the contract for game management operations.
 */
public interface GameManager {
    /**
     * Sends a marble back to its home base.
     *
     * @param marble the marble to send home
     */
    public void sendHome(Marble marble);

    /**
     * Places a marble on the board field.
     *
     * @throws CannotFieldException if the marble cannot be placed on the field
     * @throws IllegalDestroyException if a marble cannot be destroyed during the fielding
     */
    public void fieldMarble() throws CannotFieldException, IllegalDestroyException;

    /**
     * Discards a card of the specified colour.
     *
     * @param colour the colour of the card to discard
     * @throws CannotDiscardException if the card cannot be discarded
     */
    public void discardCard(Colour colour) throws CannotDiscardException;

    /**
     * Discards a card from the current player.
     *
     * @throws CannotDiscardException if the card cannot be discarded
     */
    public void discardCard() throws CannotDiscardException;

    /**
     * Gets the colour of the active player.
     *
     * @return the colour of the active player
     */
    public Colour getActivePlayerColour();

    /**
     * Gets the colour of the next player.
     *
     * @return the colour of the next player
     */
    public Colour getNextPlayerColour();
}