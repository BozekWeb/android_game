package pl.bozek.dungeon.system.enemy;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.element.PathComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.config.DungeonConfig;
import pl.bozek.dungeon.system.turnBase.basic.IsEnemyTurnEnd;
import pl.bozek.dungeon.util.PathUtils;
import pl.bozek.dungeon.util.astar.Astar;
import pl.bozek.dungeon.util.astar.Node;

public class EnemyFindPathSystem extends EntitySystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();

    public EnemyFindPathSystem(){}

    @Override
    public void update(float deltaTime) {
        if(CurrentData.ENEMY_TURN_START && !CurrentData.ENEMY_FIND_PATH && CurrentData.ENEMY_ACTION_ARRAY.size > 0){
            ImmutableArray<Entity> players = getEngine().getEntitiesFor(PLAYER_FAMILY);
            Gdx.app.debug( "EnemyFindPath", "Enemy in action array: " + CurrentData.ENEMY_ACTION_ARRAY.size );
            for (int i = 0; i < players.size(); i++) {
                MatrixPositionComponent playerMatrix = Mappers.MATRIX_POSITION.get( players.get( i ) );

                Entity enemy = CurrentData.ENEMY_ACTION_ARRAY.get( 0 );
                PathComponent finalPath = Mappers.PATH.get( enemy );
                MatrixPositionComponent enemyMatrix = Mappers.MATRIX_POSITION.get( enemy );

                Node initialNode = new Node(enemyMatrix.x, enemyMatrix.y);
                Node finalNode = new Node(playerMatrix.x, playerMatrix.y);

                int rows = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;
                int cols = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;

                Astar aStar = new Astar(rows, cols, initialNode, finalNode);

                int[][] blockArray = PathUtils.getBlockArrayForEnemy(getEngine(), enemy);
                aStar.setBlocks(blockArray);
                finalPath.path = aStar.findPath();

                if(finalPath.path.size() > 0){
                    finalPath.path.remove( 0 );
                }


                Gdx.app.debug( "EnemyFindPath","Path size to player: " + finalPath.path.size());

                if(finalPath.path.size() == 1){
                    Gdx.app.debug( "EnemyFindPath","Enemy declare attack");

                    EnemyAttackSystem.enemySingleAttack( (PooledEngine) getEngine() );
                    CurrentData.ENEMY_ACTION_ARRAY.removeIndex( 0 );
                    IsEnemyTurnEnd.isAllEnemyMove( (PooledEngine) getEngine() );



                } else if(finalPath.path.size() > 0){
                    Gdx.app.debug( "EnemyFindPath","Enemy move to player");
                    CurrentData.ENEMY_FIND_PATH = true;
                    CurrentData.ENEMY_CAN_MOVE = true;
                    IsEnemyTurnEnd.isAllEnemyMove( (PooledEngine) getEngine() );

                } else {
                    Gdx.app.debug( "EnemyFindPath","Enemy cannot move");
                    CurrentData.ENEMY_ACTION_ARRAY.removeIndex( 0 );
                    IsEnemyTurnEnd.isAllEnemyMove( (PooledEngine) getEngine() );
                }



            }
        }
    }
}
