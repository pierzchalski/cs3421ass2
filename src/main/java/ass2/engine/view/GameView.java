package ass2.engine.view;

import ass2.engine.controller.Mouse;
import ass2.engine.model.GameModel;
import com.jogamp.opengl.util.gl2.GLUT;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

/**
 * User: Pierzchalski
 * Date: 09/10/13
 * Package: ass2.engine
 * Project: cs3421ass2
 */
public class GameView implements GLEventListener {

    private GameModel gameModel;
    private Camera camera;

    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.camera = new Camera(gameModel.getPlayer3DPosition(), gameModel.getPlayerLookDirection());
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        //To change body of implemented methods use File | Settings | File Templates.
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glEnable(GL.GL_DEPTH_TEST);

        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);

        gl.glEnable(GL2.GL_NORMALIZE);
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        //To change body of implemented methods use File | Settings | File Templates.
        GL2 gl = glAutoDrawable.getGL().getGL2();
        updateCamera();
        camera.setView(gl);
        Mouse.theMouse.update(gl);
        draw(gl);
    }

    private void updateCamera() {
        camera.setPosition(gameModel.getPlayer3DPosition());
        camera.setLookDirection(gameModel.getPlayerLookDirection());
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
        GL2 gl = glAutoDrawable.getGL().getGL2();
        camera.reshape(gl, x, y, width, height);
        Mouse.theMouse.reshape(gl);
    }

    private static final double[] TEST_COLOR = new double[]{0, 0, 0, 1};

    private void draw(GL2 gl) {
        gl.glColor4dv(TEST_COLOR, 0);
        gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
        gl.glPushMatrix(); {
            gl.glTranslated(2, 1, 0);
            gl.glRotated(30, 1, 1, 0);
            GLUT glut = new GLUT();
            glut.glutSolidCube(1);
        } gl.glPopMatrix();
    }
}
