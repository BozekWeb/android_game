package pl.bozek.dungeon.database;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;

import org.omg.PortableInterceptor.SUCCESSFUL;

import java.util.HashMap;
import java.util.Map;

import pl.bozek.dungeon.common.CurrentData;

public class DataBase {
    public static String prefixHost ="";

    public static String ERROR;
    public static boolean isLogin = false;


    public static boolean respondStatus = false;
    public static boolean waitRespond = false;
    public static String status = "";


    public static void getUserCharacter(int x){
        status="";
        String URL = prefixHost+"dungeon_game/get_user_character.php";
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("user_id", String.valueOf( x ));
        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.setHeader("Upgrade", "HTTP/1.1, HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11");
        httpRequest.setUrl(URL);
        httpRequest.setContent( HttpParametersUtils.convertHttpParameters(parameters));
        httpRequest.setTimeOut(6000);



        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                status = httpResponse.getResultAsString().trim();
                Gdx.app.debug( "Return result by the server=", status );

                if (status.contains( "-1" )){
                    respondStatus = false;
                } else {
                    respondStatus = true;
                }

            }

            @Override
            public void failed(Throwable t) {

                status = "failed";
                Gdx.app.debug("Connection failed due to the next error:", t.getMessage());
            }

            @Override
            public void cancelled() {
            }

        });


        Gdx.app.debug("Exiting", "Get user character");



    }

    public static void loginPlayer(String username, String password){
        status="";
        String URL = prefixHost+"dungeon_game/login.php";

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("username", username.trim());
        parameters.put("password", password.trim());
        final Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.POST);
        httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpRequest.setHeader("Upgrade", "HTTP/1.1, HTTP/2.0, SHTTP/1.3, IRC/6.9, RTA/x11");
        httpRequest.setUrl(URL);
        httpRequest.setContent( HttpParametersUtils.convertHttpParameters(parameters));
        httpRequest.setTimeOut(6000);



        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                status = httpResponse.getResultAsString().trim();
                Gdx.app.debug( "Return result by the server=", status );

                if (status.contains( "-1" )){
                    respondStatus = false;
                } else {
                    respondStatus = true;
                }

            }

            @Override
            public void failed(Throwable t) {

                status = "failed";
                Gdx.app.debug("Connection failed due to the next error:", t.getMessage());
            }

            @Override
            public void cancelled() {
            }

        });


        Gdx.app.debug("Exiting", "From login button function");



    }

}
