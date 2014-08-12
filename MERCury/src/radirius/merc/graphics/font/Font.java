package radirius.merc.graphics.font;

import radirius.merc.graphics.Texture;
import radirius.merc.resource.Resource;

/**
 * An abstraction for fonts.
 * 
 * @author wessles
 */
public interface Font extends Resource {
    /**
     * Derive another differently sized instance of this font. Very resource
     * heavy, so only call this once (NOT every single frame)
     * 
     * @return A newly sized font!
     */
    public Font deriveFont(float size);
    
    /**
     * Derive another differently sized instance of this font. Very resource
     * heavy, so only call this once (NOT every single frame)
     * 
     * @return A newly sized font!
     */
    public Font deriveFont(int style);
    
    /** The size of the font. */
    public float getSize();
    
    /**
     * @return The height of the font.
     */
    public float getHeight();
    
    /**
     * @return The width of a given string in I, the font.
     */
    public float getWidth(String what);
    
    /**
     * @return The maximum width that a given string of length len could be.
     */
    public float getMaxWidth(int len);
    
    /**
     * @return The average width of all number/letter characters, multiplied by
     *         len.
     */
    public float getAverageWidth(int len);
    
    /**
     * @return The overall texture used for rendering the font.
     */
    public Texture getFontTexture();
}
