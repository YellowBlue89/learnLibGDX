package com.mygdx.game.tutoriales;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Assets;
import com.mygdx.game.BaseScreen;
import com.mygdx.game.MainLearn;

public class Learn3 extends BaseScreen {

    private World world;
    private Box2DDebugRenderer renderer;

    public Learn3(MainLearn game) {
        super(game);
        Vector2 gravity = new Vector2(0, -9.8f);
        world = new World(gravity, true);
        renderer = new Box2DDebugRenderer();
        createFloor();
        createBox();
    }

    private void createFloor() {
        BodyDef bd = new BodyDef();
        bd.position.set(0, 0.5f);
        bd.type = BodyDef.BodyType.StaticBody;

        EdgeShape shape = new EdgeShape();
        shape.set(0, 0, WORLD_WIDTH, 1.5f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;

        Body body = world.createBody(bd);
        body.createFixture(fixDef);

        shape.dispose();
    }

    private void createBox() {
        BodyDef bd = new BodyDef();
        bd.position.set(7f, 4f);
        bd.type = BodyDef.BodyType.DynamicBody;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(.2f, .2f);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = 1f;
        fixDef.friction = .5f;
        fixDef.restitution = .3f;

        Body body = world.createBody(bd);
        body.createFixture(fixDef);

        shape.dispose();
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

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }

}
