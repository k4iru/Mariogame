package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Mariogame;

public class Brick extends InteractiveTileObject{
    public Brick(World world, TiledMap map, Rectangle bounds){

        // creates a brick fixture
        super(world, map, bounds);
        // sets fixture as a brick
        fixture.setUserData(this);
        // sets the bit value to brick
        setCategoryFilter(Mariogame.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", "Collision");
        // when collision set brick bit to destroyed
        setCategoryFilter(Mariogame.DESTROYED_BIT);

        //sets the brick tile to null, so that it disappears
        getCell().setTile(null);

    }
}
