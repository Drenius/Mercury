package radirius.merc.tests;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import radirius.merc.framework.Core;
import radirius.merc.framework.Runner;
import radirius.merc.graphics.FrameBuffer;
import radirius.merc.graphics.Graphics;
import radirius.merc.graphics.Shader;
import radirius.merc.graphics.Texture;
import radirius.merc.resource.Loader;

/**
 * @author wessles
 */

public class FboTest extends Core {
  Runner rnr = Runner.getInstance();
  
  public FboTest() {
    super("FBO Test!");
    rnr.init(this, 500, 500);
    rnr.run();
  }
  
  Texture cuteface;
  Shader shad;
  FrameBuffer fbo;
  
  @Override
  public void init() {
    Runner.getInstance().getGraphics().setScale(1.1f);
    
    cuteface = Texture.loadTexture(Loader.streamFromClasspath("radirius/merc/tests/dAWWWW.png"), 45, GL_NEAREST);
    shad = Shader.getShader(Loader.streamFromClasspath("radirius/merc/tests/distort.fs"), Shader.FRAGMENT_SHADER);
    fbo = FrameBuffer.getFrameBuffer();
  }
  
  @Override
  public void update(float delta) {
  }
  
  float x = 0;
  
  @Override
  public void render(Graphics g) {
    fbo.use();
    {
      g.drawTexture(cuteface, x++, x++);
      
      g.getBatcher().flush();
    }
    fbo.release();
    
    g.useShader(shad);
    {
      g.drawTexture(fbo.getTextureObject(), 0, 0);
    }
    g.releaseShaders();
  }
  
  @Override
  public void cleanup() {
  }
  
  public static void main(String[] args) {
    new FboTest();
  }
}
