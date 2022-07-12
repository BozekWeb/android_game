package pl.bozek.dungeon.system.inputHandle;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.Array;


import java.util.HashMap;
import java.util.Map;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;

import pl.bozek.dungeon.component.element.FreeMoveHudComponent;
import pl.bozek.dungeon.component.element.RectangleBoundComponent;
import pl.bozek.dungeon.component.element.ValueComponent;
import pl.bozek.dungeon.component.tag.ClickableComponent;


public class HudInputHandler extends InputBasic {

    private static final Family CLICKABLE_HUD_ELEMENT = Family.all(
            ClickableComponent.class
    ).get();

    private Array<Entity> hud = new Array<>();

    public HudInputHandler(OrthographicCamera hudCamera, PooledEngine engine){
        super(hudCamera, engine);
        ImmutableArray<Entity> hudElements = engine.getEntitiesFor(CLICKABLE_HUD_ELEMENT);
        hud.addAll(hudElements.toArray());
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean clicked = false;

        if (CurrentData.PLAYER_CAN_CLICK) {
            for (Entity hudElement : hud) {
                RectangleBoundComponent bound = Mappers.RECTANGLE_BOUND.get( hudElement );
                ValueComponent value = Mappers.VALUE.get( hudElement );
                FreeMoveHudComponent freeMoveHudComponent = Mappers.FREE_MOVE_HUD.get( hudElement );


                    Vector3 clickPosition = getCamera().unproject( new Vector3( Gdx.input.getX( pointer ), Gdx.input.getY( pointer ), 0 ) );

                    if (bound.rectangle.contains( clickPosition.x, clickPosition.y )) {


                                if(value.value == 1 && freeMoveHudComponent.freeMove == CurrentData.FREE_MOVEMENT){

                                    btnclickLogin();
                                    Gdx.app.exit();

                                    Gdx.app.debug( "HudInputHandler", "Click on change attack type button" );
                                    if(CurrentData.PLAYER_ATTACK_TYPE == 1){
                                        CurrentData.PLAYER_ATTACK_TYPE = 2;
                                        Gdx.app.debug( "HudInputHandler", "Current attack type: " + CurrentData.PLAYER_ATTACK_TYPE );
                                    } else {
                                        CurrentData.PLAYER_ATTACK_TYPE = 1;
                                        Gdx.app.debug( "HudInputHandler", "Current attack type: " + CurrentData.PLAYER_ATTACK_TYPE );
                                    }
                                }

                            clicked = true;
                    }
            }
        }
        return clicked;
    }


    private void btnclickLogin() {

        String URL = "http://localhost/dungeon_game/login.php";

        final String username = "DUPA".trim();
        final String password = "DUPA".trim();

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("username", username);
        parameters.put("password", password);
        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.setHeader("Upgrade", "HTTP/1.1, HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11");
        httpRequest.setUrl(URL);
        httpRequest.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        httpRequest.setTimeOut(6000);


        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String status = httpResponse.getResultAsString().trim();
                Gdx.app.debug("Return result by the server=", status);

                if(status.contains("success"))
                    System.out.println("BRAWO");
            }

            @Override
            public void failed(Throwable t) {

                String status = "failed";
                Gdx.app.debug("Connection failed due to the next error:", t.getMessage());
            }

            @Override
            public void cancelled() {
            }

        });


        Gdx.app.debug("Exiting", "From login button function");

    }
}



