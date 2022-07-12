package pl.bozek.dungeon.common;

import java.util.Arrays;
import java.util.List;
import pl.bozek.dungeon.customType.PlayerStatistics;

public class PlayerData {

    public static int playerID = -1;

    public static PlayerStatistics playerStatistics;
    public static boolean isPlayerLoaded;
    public static boolean isQuest = false;

    public static void GeneratePlayer(){
        isPlayerLoaded = true;
        isQuest = true;


        int pLevel = 1;
        int pDeep = 1;
        int pCurrentExp = 0;
        int pSkillPoint = 0;
        int pMaxHitPoint = 10;
        int pCurrentHitPoint = 10;
        int pMaxActionPoint = 6;
        int pCurrentActionPoint = 6;
        int pDamageMin = 1;
        int pDamageMax = 4;
        int pArmor = 1;

        CurrentData.CURRENT_GAME_DEEP = pDeep;

        playerStatistics = new PlayerStatistics(
                pLevel,
                pDeep,
                pCurrentExp,
                pSkillPoint,
                pMaxHitPoint,
                pCurrentHitPoint,
                pMaxActionPoint,
                pCurrentActionPoint,
                pDamageMin,
                pDamageMax,
                pArmor
        );

    }

    public static void GeneratePlayer(List<String> status){
        isPlayerLoaded = true;
        isQuest = false;

        playerID = Integer.valueOf(status.get( 10 ));

        int pLevel = Integer.valueOf(status.get( 1 ));
        int pDeep = Integer.valueOf(status.get( 2 ));
        int pCurrentExp = Integer.valueOf(status.get( 3 ));
        int pSkillPoint = Integer.valueOf(status.get( 4 ));
        int pMaxHitPoint = Integer.valueOf(status.get( 5 ));
        int pCurrentHitPoint = Integer.valueOf(status.get( 5 ));
        int pMaxActionPoint = Integer.valueOf(status.get( 6 ));
        int pCurrentActionPoint = Integer.valueOf(status.get( 6 ));
        int pDamageMin = Integer.valueOf(status.get( 7 ));
        int pDamageMax = Integer.valueOf(status.get( 8 ));
        int pArmor = Integer.valueOf(status.get( 9 ));

        CurrentData.CURRENT_GAME_DEEP = pDeep;

        playerStatistics = new PlayerStatistics(
                pLevel,
                pDeep,
                pCurrentExp,
                pSkillPoint,
                pMaxHitPoint,
                pCurrentHitPoint,
                pMaxActionPoint,
                pCurrentActionPoint,
                pDamageMin,
                pDamageMax,
                pArmor
        );
    }



}
