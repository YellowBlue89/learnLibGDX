package com.mygdx.game.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.MainLearn;

public class Learn1 extends BaseScreen {

    private World world;
    private Box2DDebugRenderer renderer;

    public Learn1(MainLearn game) {
        super(game);
        Vector2 gravity = new Vector2(0, -9.8f);
        world = new World(gravity, true);
        renderer = new Box2DDebugRenderer();
        createBall();
    }

    private void createBall() {
        BodyDef bd = new BodyDef();
        bd.position.set(4, 4.5f);
        bd.type = BodyDef.BodyType.DynamicBody;

        CircleShape shape = new CircleShape();
        shape.setRadius(.25f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;

        Body body = world.createBody(bd);
        body.createFixture(fixDef);
    }

    @Override
    public void update(float delta) {
        world.step(delta, 8, 6);
    }

    @Override
    public void draw(float delta) {
        oCamUI.update();
        spriteBatch.setProjectionMatrix(oCamUI.combined);

        spriteBatch.begin();
        Assets.font.draw(spriteBatch, "Fps: " + Gdx.graphics.getFramesPerSecond(), 0, 20);
        spriteBatch.end();

        oCamBox2D.update();;
        renderer.render(world, oCamBox2D.combined);
    }

}
