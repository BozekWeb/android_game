package pl.bozek.dungeon.system.enemy;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.ColorComponent;
import pl.bozek.dungeon.component.element.EnemyStatisticsComponent;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.MovementComponent;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.element.PositionComponent;
import pl.bozek.dungeon.config.GameConfig;
import pl.bozek.dungeon.system.turnBase.basic.IsEnemyTurnEnd;

public class EnemySpeedManipulationSystem extends EntitySystem {

    public EnemySpeedManipulationSystem(){}

    @Override
    public void update(float deltaTime) {
        if (CurrentData.ENEMY_TURN && CurrentData.ENEMY_CAN_MOVE) {
            Entity enemy = CurrentData.ENEMY_ACTION_ARRAY.get( 0 );
            PathComponent enemyPath = Mappers.PATH.get( enemy );

            EnemyStatisticsComponent enemyStatistics = Mappers.ENEMY_STATISTICS.get( enemy );

            if (enemyPath.path.size() > 0 && enemyStatistics.enemyStatistics.getCurrentActionPoint() > 0) {
                PositionComponent enemyPosition = Mappers.POSITION.get( enemy );
                MovementComponent enemyMovement = Mappers.MOVEMENT.get( enemy );
                MatrixPositionComponent enemyMatrix = Mappers.MATRIX_POSITION.get( enemy );

                ColorComponent colorComponent = Mappers.COLOR.get( enemy );
                colorComponent.color = new Color( Color.BLACK );

                int nextX = enemyPath.path.get( 0 ).getRow();
                int nextY = enemyPath.path.get( 0 ).getCol();
                float nextXR = (nextX * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;
                float nextYR = (nextY * GameConfig.MAP_DIMENSION) * GameConfig.RATIO_FOR_MAP;

                if (enemyPath.path.size() == 1) {
                    Gdx.app.debug( "EnemySpeedManipulation","Enemy declare attack after move");
                    EnemyAttackSystem.enemySingleAttack( (PooledEngine) getEngine() );
                    CurrentData.ENEMY_ACTION_ARRAY.removeIndex( 0 );
                    CurrentData.ENEMY_CAN_MOVE = false;
                    CurrentData.ENEMY_FIND_PATH = false;

                    IsEnemyTurnEnd.isAllEnemyMove( (PooledEngine) getEngine() );


                } else {

                    if (enemyMatrix.x - 1 == nextX) {
                        if (enemyPosition.x != nextXR) {
                            if (Math.abs( enemyPosition.x - nextXR ) <= GameConfig.MAP_DIMENSION / 16) {
                                enemyMovement.xSpeed = -Math.abs( enemyPosition.x - nextXR );
                            } else {
                                enemyMovement.xSpeed = -GameConfig.MAP_DIMENSION / 16;
                            }
                        } else {
                            enemyMovement.xSpeed = 0f;
                        }

                    } else if (enemyMatrix.x + 1 == nextX) {
                        if (enemyPosition.x != nextXR) {
                            if (Math.abs( enemyPosition.x - nextXR ) <= GameConfig.MAP_DIMENSION / 16) {
                                enemyMovement.xSpeed = Math.abs( enemyPosition.x - nextXR );
                            } else {
                                enemyMovement.xSpeed = GameConfig.MAP_DIMENSION / 16;
                            }
                        } else {
                            enemyMovement.xSpeed = 0f;
                        }

                    } else if (enemyMatrix.y + 1 == nextY) {
                        if (enemyPosition.y != nextYR) {
                            if (Math.abs( enemyPosition.y - nextYR ) <= GameConfig.MAP_DIMENSION / 16) {
                                enemyMovement.ySpeed = Math.abs( enemyPosition.y - nextYR );
                            } else {
                                enemyMovement.ySpeed = GameConfig.MAP_DIMENSION / 16;
                            }
                        } else {
                            enemyMovement.ySpeed = 0f;
                        }

                    } else if (enemyMatrix.y - 1 == nextY) {
                        if (enemyPosition.y != nextYR) {
                            if (Math.abs( enemyPosition.y - nextYR ) <= GameConfig.MAP_DIMENSION / 16) {
                                enemyMovement.ySpeed = -Math.abs( enemyPosition.y - nextYR );
                            } else {
                                enemyMovement.ySpeed = -GameConfig.MAP_DIMENSION / 16;
                            }
                        } else {
                            enemyMovement.ySpeed = 0f;
                        }
                    }
                }


                if (enemyPosition.x == nextXR && enemyPosition.y == nextYR) {
                    enemyMatrix.x = nextX;
                    enemyMatrix.y = nextY;
                    enemyStatistics.enemyStatistics.setCurrentActionPoint( enemyStatistics.enemyStatistics.getCurrentActionPoint() - 1 );
                    enemyPath.path.remove( 0 );

                //    PathUtils.isEnemySaw( nextX, nextY, (PooledEngine) getEngine() );
                }

            } else {

                Gdx.app.debug( "EnemySpeedManipulation","Enemy cannot move/attack: No action points");
                CurrentData.ENEMY_ACTION_ARRAY.removeIndex( 0 );
                CurrentData.ENEMY_CAN_MOVE = false;
                CurrentData.ENEMY_FIND_PATH = false;

                IsEnemyTurnEnd.isAllEnemyMove( (PooledEngine) getEngine() );
            }
        }
    }
}
