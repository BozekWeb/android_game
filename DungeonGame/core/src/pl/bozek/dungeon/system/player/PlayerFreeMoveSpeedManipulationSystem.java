package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.util.PathUtils;

public class PlayerFreeMoveSpeedManipulationSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public PlayerFreeMoveSpeedManipulationSystem(){
        super(PLAYER_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        if(CurrentData.PLAYER_TURN_START && CurrentData.PLAYER_CAN_MOVE && CurrentData.FREE_MOVEMENT){
            PathComponent playerPath = Mappers.PATH.get( entity );

            if(playerPath.path.size() > 0){
                PositionComponent playerPosition = Mappers.POSITION.get(entity);
                MovementComponent playerMovement = Mappers.MOVEMENT.get(entity);
                MatrixPositionComponent playerMatrix = Mappers.MATRIX_POSITION.get(entity);

                int nextX = playerPath.path.get( 0 ).getRow();
                int nextY = playerPath.path.get( 0 ).getCol();
                float nextXR = (nextX * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;
                float nextYR = (nextY * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;

                if (playerMatrix.x - 1 == nextX) {
                    if (playerPosition.x != nextXR) {
                        if (Math.abs( playerPosition.x - nextXR ) <= GameConfig.MAP_DIMENSION / 16) {
                            playerMovement.xSpeed = -Math.abs( playerPosition.x - nextXR );
                        } else {
                            playerMovement.xSpeed = -GameConfig.MAP_DIMENSION / 16;
                        }
                    } else {
                        playerMovement.xSpeed = 0f;
                    }

                } else if (playerMatrix.x + 1 == nextX) {
                    if (playerPosition.x != nextXR) {
                        if (Math.abs( playerPosition.x - nextXR ) <= GameConfig.MAP_DIMENSION / 16) {
                            playerMovement.xSpeed = Math.abs( playerPosition.x - nextXR );
                        } else {
                            playerMovement.xSpeed = GameConfig.MAP_DIMENSION / 16;
                        }
                    } else {
                        playerMovement.xSpeed = 0f;
                    }

                } else if (playerMatrix.y + 1 == nextY) {
                    if (playerPosition.y != nextYR) {

                        if (Math.abs( playerPosition.y - nextYR ) <= GameConfig.MAP_DIMENSION / 16) {
                            playerMovement.ySpeed = Math.abs( playerPosition.y - nextYR );
                        } else {
                            playerMovement.ySpeed = GameConfig.MAP_DIMENSION / 16;
                        }
                    } else {
                        playerMovement.ySpeed = 0f;
                    }

                } else if (playerMatrix.y - 1 == nextY) {
                    if (playerPosition.y != nextYR) {
                        if (Math.abs( playerPosition.y - nextYR ) <= GameConfig.MAP_DIMENSION / 16) {
                            playerMovement.ySpeed = -Math.abs( playerPosition.y - nextYR );
                        } else {
                            playerMovement.ySpeed = -GameConfig.MAP_DIMENSION / 16;
                        }
                    } else {
                        playerMovement.ySpeed = 0f;
                    }
                }


                if(playerPosition.x == nextXR && playerPosition.y == nextYR) {

                    playerMatrix.x = nextX;
                    playerMatrix.y = nextY;
                    playerPath.path.remove( 0 );

                    CurrentData.BREAK_FREE_MOVEMENT = PathUtils.isEnemySaw( nextX, nextY , (PooledEngine) getEngine());
                }

            } else {
                CurrentData.PLAYER_CAN_MOVE = false;
                CurrentData.PLAYER_TURN_START = false;
                CurrentData.PLAYER_CAN_CLICK = true;
            }
        }
    }
}
