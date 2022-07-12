package pl.bozek.dungeon.system.inputHandle;

import com.badlogic.ashley.core.PooledEngine;

import com.badlogic.gdx.InputProcessor;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputBasic implements InputProcessor {

    private OrthographicCamera camera;
    private PooledEngine engine;

    InputBasic(OrthographicCamera camera, PooledEngine engine){
        this.camera = camera;
        this.engine = engine;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public PooledEngine getEngine() {
        return engine;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
