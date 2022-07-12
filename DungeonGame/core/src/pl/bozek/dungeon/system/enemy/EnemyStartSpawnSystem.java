package pl.bozek.dungeon.system.enemy;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.common.EntityFactory;
import pl.bozek.dungeon.map.RoomInfo;
import pl.bozek.dungeon.map.array.Float2D;
import pl.bozek.dungeon.system.map.random.GenerateRandomMathDungeonSystem;

public class EnemyStartSpawnSystem {

    public EnemyStartSpawnSystem(PooledEngine engine, AssetManager assetManager) {

        EntityFactory factory = new EntityFactory(engine, assetManager);
        int check = 0;

        for(int i = 0; i < GenerateRandomMathDungeonSystem.rooms.size; i++) {
            if (i != CurrentData.PLAYER_SPAWN_ROOM && i != CurrentData.PLAYER_END_ROOM) {
                RoomInfo room = GenerateRandomMathDungeonSystem.rooms.get(i);
                int roomPositionX = room.getX();
                int roomPositionY = room.getY();
                int roomWidth = room.getWidth();
                int roomHeight = room.getHeight();
                int roomPossibleMovementTile = 0;

                for (int x = 0; x < room.getWidth(); x++) {
                    for (int y = 0; y < room.getHeight(); y++) {
                        if (room.getRoom().get(x, y) == 0) roomPossibleMovementTile++;
                    }
                }

                int startEnemyAmountInRoom;

                if(roomPossibleMovementTile < 17){
                    startEnemyAmountInRoom = 2;
                } else if(roomPossibleMovementTile < 28){
                    startEnemyAmountInRoom = 3;
                } else if(roomPossibleMovementTile < 39){
                    startEnemyAmountInRoom = 4;
                } else{
                    startEnemyAmountInRoom = 5;
                }

                Gdx.app.debug("EnemyStartSpawn","RoomSize: " + roomHeight*roomWidth + ", RoomPossible: " + roomPossibleMovementTile + ", StartEnemy: " + startEnemyAmountInRoom);

                int randomPositionX;
                int randomPositionY;

                Float2D spawnLocation = new Float2D(room.getWidth(), room.getHeight());
                spawnLocation.set(1);

                do {
                    randomPositionX = MathUtils.random(roomWidth);
                    randomPositionY = MathUtils.random(roomHeight);
                    randomPositionX = MathUtils.clamp(randomPositionX, 0, roomWidth - 1);
                    randomPositionY = MathUtils.clamp(randomPositionY, 0, roomHeight - 1);

                    if (room.getRoom().get(randomPositionX, randomPositionY) == 0) {
                        if (spawnLocation.get(randomPositionX, randomPositionY) == 1) {
                            spawnLocation.set(randomPositionX, randomPositionY, 0);
                            Gdx.app.debug("EnemyStartSpawn", "Enemy: (" + (roomPositionX + randomPositionX + 1) + ", " + (roomPositionY + randomPositionY + 1 )+ ")" );

                            Color color;

                            if(check == 0){
                                color = new Color( Color.YELLOW );
                            } else if(check == 1){
                                color = new Color( Color.GREEN );
                            } else if(check == 2){
                                color = new Color( Color.BLACK );
                            } else if(check == 3){
                                color = new Color( Color.BLUE );
                            } else if(check == 4){
                                color = new Color( Color.GRAY );
                            } else if(check == 5){
                                color = new Color( Color.WHITE );
                            } else if(check == 6){
                                color = new Color( Color.CORAL );
                            } else if(check == 7){
                                color = new Color( Color.ORANGE );
                            } else {
                                color = new Color( Color.PINK );
                            }

                            check++;

                            factory.addEnemy(roomPositionX + randomPositionX + 1, roomPositionY + randomPositionY + 1, color);
                            startEnemyAmountInRoom--;
                        }
                    }
                } while (startEnemyAmountInRoom > 0);
            }
        }
    }
}

