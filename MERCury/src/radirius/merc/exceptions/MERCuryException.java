package radirius.merc.exceptions;

/**
 * A general exception for MERCury.
 * 
 * @author wessles
 */

@SuppressWarnings("serial")
public class MERCuryException extends Exception {
    public MERCuryException(String reason) {
        super(reason);
    }
}
