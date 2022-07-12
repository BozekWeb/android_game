package pl.bozek.dungeon.system.databse;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Interpolation;

import java.util.List;
import java.util.Arrays;

import javax.xml.crypto.Data;

import pl.bozek.dungeon.DungeonGame;
import pl.bozek.dungeon.common.PlayerData;
import pl.bozek.dungeon.database.DataBase;
import pl.bozek.dungeon.screen.menu.MainMenuScreen;

public class WaitLoginRespondSystem extends EntitySystem {

    public static boolean LOGIN_START = false;
    public static int USER_ID = -1;
    public static String username;
    public static String password;

    DungeonGame game;

    public WaitLoginRespondSystem(DungeonGame game){
        System.out.println("Wait Respond System Start");

        this.game = game;


    }

    @Override
    public void update(float deltaTime) {
        super.update( deltaTime );


        //Logowanie, Zapis ID do zmiennej
        if(LOGIN_START){
            if(!DataBase.waitRespond){
                DataBase.waitRespond = true;
                DataBase.loginPlayer( username, password );
            } else {
                if (DataBase.respondStatus){
                    LOGIN_START = false;
                    DataBase.waitRespond = false;
                    DataBase.respondStatus = false;
                    List<String> statusList = Arrays.asList( DataBase.status.split( "," ) );
                    USER_ID = Integer.valueOf(statusList.get( 0 ));

                } else if (DataBase.status.contains( "-1" )){
                    DataBase.waitRespond = false;
                    DataBase.respondStatus = false;
                    LOGIN_START = false;
                }
            }
        }

        if(USER_ID != -1){
            if(!DataBase.waitRespond){
                DataBase.waitRespond = true;
                DataBase.getUserCharacter( USER_ID );
            } else {
                if (DataBase.respondStatus){
                    USER_ID = -1;
                    DataBase.waitRespond = false;
                    DataBase.respondStatus = false;

                    List<String> statusList = Arrays.asList( DataBase.status.split( "," ) );
                    System.out.println(statusList.size());

                    PlayerData.GeneratePlayer( statusList );
                    game.setScreen( new MainMenuScreen( game ) );


                } else if (DataBase.status.contains( "-1" )){
                    USER_ID = -1;
                    DataBase.waitRespond = false;
                    DataBase.respondStatus = false;
                }
            }




        }






    }
}
