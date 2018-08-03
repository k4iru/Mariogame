package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Mariogame;

public abstract class InteractiveTileObject {

    // variables
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public InteractiveTileObject(World world, TiledMap map, Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;

        // initialize
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        //shape does not move
        bdef.type = BodyDef.BodyType.StaticBody;

        //sets the size of the body, coin and brick send their bounds through the super constructor
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Mariogame.PPM, (bounds.getY() + bounds.getHeight() / 2) / Mariogame.PPM);

        // creates the body in the world
        body = world.createBody(bdef);

        //sets the shape as a box
        shape.setAsBox(bounds.getWidth() /2 / Mariogame.PPM, bounds.getHeight() /2 / Mariogame.PPM );
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onHeadHit();
    public void setCategoryFilter(short filterBit){
        // sets the fixture as a coin, brick, mario
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){

        //gets the cell of the fixture
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x * Mariogame.PPM / 16), (int)(body.getPosition().y * Mariogame.PPM/16));
    }

}
