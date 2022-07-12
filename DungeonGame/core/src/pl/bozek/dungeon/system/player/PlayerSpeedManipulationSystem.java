package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import pl.bozek.dungeon.assets.AssetDescriptors;
import pl.bozek.dungeon.assets.RegionNames;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.AnimatedTextureComponent;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.element.PlayerStatisticsComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.system.graphics.GraphicsUtility;
import pl.bozek.dungeon.util.PathUtils;

public class PlayerSpeedManipulationSystem  extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    private static final Family ENEMY_FAMILY = Family.all(
            EnemyComponent.class
    ).get();

    private TextureAtlas playerAtlas;

    public PlayerSpeedManipulationSystem(AssetManager assetManager){
        super(PLAYER_FAMILY);
        this.playerAtlas = assetManager.get( AssetDescriptors.ENTITY_TILESET );
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(CurrentData.PLAYER_TURN_START && CurrentData.PLAYER_CAN_MOVE && !CurrentData.FREE_MOVEMENT){
            PathComponent playerPath = Mappers.PATH.get( entity );

            if(playerPath.path.size() > 0) {

                PositionComponent playerPosition = Mappers.POSITION.get( entity );
                MovementComponent playerMovement = Mappers.MOVEMENT.get( entity );
                MatrixPositionComponent playerMatrix = Mappers.MATRIX_POSITION.get( entity );

                int nextX = playerPath.path.get( 0 ).getRow();
                int nextY = playerPath.path.get( 0 ).getCol();
                float nextXR = (nextX * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;
                float nextYR = (nextY * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;

                boolean isEnemyLast = isEnemyLast( nextX, nextY );

                if(isEnemyLast){
                    AnimatedTextureComponent playerTexture = Mappers.A_TEXTURE.get( entity );

                    playerMovement.ySpeed = 0;
                    playerMovement.xSpeed = 0;
                    playerPosition.x = (playerMatrix.x * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;
                    playerPosition.y = (playerMatrix.y * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;

                    if(playerMatrix.x - 1 == nextX){
                        GraphicsUtility.setAnimation( RegionNames.knight_m_idle_left, playerTexture, playerAtlas);
                    } else if (playerMatrix.x + 1 == nextX) {
                        GraphicsUtility.setAnimation( RegionNames.knight_m_idle_right, playerTexture, playerAtlas);
                    } else if(playerMatrix.y + 1 == nextY){
                        GraphicsUtility.setAnimation( RegionNames.knight_m_idle_up, playerTexture, playerAtlas);
                    } else if(playerMatrix.y - 1 == nextY){
                        GraphicsUtility.setAnimation( RegionNames.knight_m_idle_down, playerTexture, playerAtlas);
                    }

                    CurrentData.PLAYER_CAN_MOVE = false;
                    CurrentData.PLAYER_TURN_START = false;
                    CurrentData.PLAYER_CAN_CLICK = true;

                } else {
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
                }


                if(playerPosition.x == nextXR && playerPosition.y == nextYR) {
                    PlayerStatisticsComponent playerStatistics = Mappers.PLAYER_STATISTICS.get( entity );
                    playerStatistics.playerStatistics.setCurrentActionPoint(playerStatistics.playerStatistics.getCurrentActionPoint()-1);
                    playerMatrix.x = nextX;
                    playerMatrix.y = nextY;
                    playerPath.path.remove( 0 );

                    PathUtils.isEnemySaw( nextX, nextY , (PooledEngine) getEngine());

                    if(playerStatistics.playerStatistics.getCurrentActionPoint() == 0){
                        CurrentData.PLAYER_CAN_MOVE = false;
                        CurrentData.PLAYER_TURN_START = false;
                        CurrentData.PLAYER_CAN_CLICK = false;
                    }

                }

            } else {
                CurrentData.PLAYER_CAN_MOVE = false;
                CurrentData.PLAYER_TURN_START = false;
                CurrentData.PLAYER_CAN_CLICK = true;
            }
        }
    }

    private boolean isEnemyLast(int nextX, int nextY){
        boolean isEnemyLast = false;
        ImmutableArray<Entity> enemies = getEngine().getEntitiesFor(ENEMY_FAMILY);
        for (Entity enemy : enemies) {
            MatrixPositionComponent enemyMatrix = Mappers.MATRIX_POSITION.get(enemy);
            if (nextX == enemyMatrix.x && nextY == enemyMatrix.y) {
                isEnemyLast = true;
                break;
            }
        }
        return isEnemyLast;
    }




}
