package radirius.merc.tests;

import radirius.merc.framework.Core;
import radirius.merc.framework.Runner;
import radirius.merc.graphics.Animation;
import radirius.merc.graphics.Color;
import radirius.merc.graphics.Graphics;
import radirius.merc.graphics.SpriteSheet;
import radirius.merc.graphics.Texture;
import radirius.merc.resource.Loader;

/**
 * @author opiop65 (base code), wessles (modification)
 */

public class SpriteSheetTest extends Core {
  
  private Runner rnr = Runner.getInstance();
  private SpriteSheet sheet;
  private Animation anm;
  
  public SpriteSheetTest(String name) {
    super(name);
    rnr.init(this, 800, 600);
    rnr.run();
  }
  
  @Override
  public void init() {
    sheet = SpriteSheet.loadSpriteSheet(Texture.loadTexture(Loader.streamFromClasspath("radirius/merc/tests/tiles.png")), 16, 16);
    anm = new Animation(300, sheet, 0, 6);
  }
  
  @Override
  public void update(float delta) {
  }
  
  @Override
  public void render(Graphics g) {
    g.setBackground(Color.marble);
    g.drawTexture(sheet.getBaseTexture(), 0, 0);
    anm.render(10, 10, g);
    anm.nextFrame();
  }
  
  @Override
  public void cleanup() {
  }
  
  public static void main(String[] args) {
    new SpriteSheetTest("SpriteSheet Test");
  }
}