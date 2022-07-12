package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import java.util.List;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.common.EntityFactory;
import pl.bozek.dungeon.common.PlayerData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.DungeonConfig;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.system.map.random.GenerateMapTilesSystem;
import pl.bozek.dungeon.system.map.random.GenerateRandomMathDungeonSystem;
import pl.bozek.dungeon.map.RoomInfo;
import pl.bozek.dungeon.util.astar.Astar;
import pl.bozek.dungeon.util.astar.Node;


public class PlayerDungeonSpawnLocationSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();


    public PlayerDungeonSpawnLocationSystem(PooledEngine engine, AssetManager assetManager, OrthographicCamera camera){
        EntityFactory entityFactory = new EntityFactory(engine, assetManager);
        CurrentData.PLAYER_SPAWN_ROOM = MathUtils.random(GenerateRandomMathDungeonSystem.rooms.size-1);
        RoomInfo room = GenerateRandomMathDungeonSystem.rooms.get(CurrentData.PLAYER_SPAWN_ROOM );

        CurrentData.PLAYER_END_ROOM = getLongestPath(room);
        RoomInfo roomEnd = GenerateRandomMathDungeonSystem.rooms.get(CurrentData.PLAYER_END_ROOM );

        entityFactory.addPlayer(room.getCenter()[0], room.getCenter()[1], PlayerData.playerStatistics );

        entityFactory.addStairs( (roomEnd.getCenter()[0]), roomEnd.getCenter()[1] );


        ImmutableArray<Entity> players = engine.getEntitiesFor(PLAYER_FAMILY);
        for(Entity player : players) {
            PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( player );
            CurrentData.CURRENT_GAME_DEEP = playerStatistics.playerStatistics.getDeep();
        }

        float x = camera.position.x;
        float y = camera.position.y;

        camera.position.x =  (x + room.getCenter()[0] * GameConfig.MAP_DIMENSION +
                GameConfig.MAP_DIMENSION/2) * GameConfig.RATIO_FOR_MAP;
        camera.position.y = (y + room.getCenter()[1] * GameConfig.MAP_DIMENSION_FINAL);
        camera.update();
    }


    private int getLongestPath(RoomInfo room){

        int x = -1;
        int pathSize = 0;

        Node initialNode = new Node( room.getCenter()[0], room.getCenter()[1]);

        for(int i = 0; i < GenerateRandomMathDungeonSystem.rooms.size; i++){
            if(i != CurrentData.PLAYER_SPAWN_ROOM){
                RoomInfo cRoom = GenerateRandomMathDungeonSystem.rooms.get( i );
                Node finalNode = new Node(cRoom.getCenter()[0], cRoom.getCenter()[1]  );

                int rows = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;
                int cols = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;

                Astar aStar = new Astar(rows, cols, initialNode, finalNode);

                int[][] blockArray = GenerateMapTilesSystem.getBlocks();
                aStar.setBlocks(blockArray);

                List<Node> path = aStar.findPath();
                if(path.size() > pathSize){
                    pathSize = path.size();
                    x = i;
                }
            }
        }
        Gdx.app.debug( "PlayerSpawnSystem", "getLongestPath() => "+x );
        return x;
    }




}
