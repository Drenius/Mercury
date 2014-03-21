package com.teama.merc.gui;

import com.teama.merc.fmwk.Runner;
import com.teama.merc.gfx.Color;
import com.teama.merc.gfx.Graphics;
import com.teama.merc.gfx.Texture;

/**
 * @from MERCury in package com.teama.merc.gui;
 * @authors Jeviny
 * @website www.wessles.com
 * @license (C) Mar 2, 2014 www.wessles.com This file, and all others of the
 *          project 'MERCury' are licensed under WTFPL license. You can find the
 *          license itself at http://www.wtfpl.net/about/.
 */

public class Button extends Component
{
    public Texture left, right, body;
    
    public Button(String txt, Texture left, Texture right, Texture body, float x, float y, float w, float h)
    {
        super(txt, x, y, w, h, true, true);
        this.left = left;
        this.right = right;
        this.body = body;
    }
    
    public Button(String txt, Texture left, Texture right, Texture body, float x, float y)
    {
        this(txt, left, right, body, x, y, Runner.getInstance().getGraphics().getFont().getWidth(txt.toCharArray()), Runner.getInstance().getGraphics().getFont().getLineHeight());
    }
    
    public void render(Graphics g)
    {
        if (left != null)
            g.drawTexture(left, x, y);
        
        if (body != null)
            g.drawTexture(body, 0, 0, g.getFont().getWidth(txt.toCharArray()), body.getTextureHeight(), x + left.getTextureWidth(), y);
        
        if (right != null)
            g.drawTexture(right, x + left.getTextureWidth() + w, y);
        
        renderContent(g);
    }
    
    public void renderContent(Graphics g)
    {
        float tx = 0, ty = 0;
        
        if (cx)
            tx = w / 2 - g.getFont().getWidth(txt.toCharArray()) / 2;
        
        if (cy)
            ty = h / 2 - g.getFont().getHeight() / 4;
        
        g.setColor(Color.black);
        g.drawString(x + tx + left.getTextureWidth(), y + ty, txt);
        g.setColor(Color.white);
    }
}
