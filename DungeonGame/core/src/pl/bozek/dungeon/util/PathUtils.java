package pl.bozek.dungeon.util;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import java.util.List;
import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.FindEnemyComponent;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.tag.EnemyComponent;
import pl.bozek.dungeon.config.DungeonConfig;
import pl.bozek.dungeon.system.map.random.GenerateMapTilesSystem;
import pl.bozek.dungeon.util.astar.Astar;
import pl.bozek.dungeon.util.astar.Node;

public class PathUtils {


    private static Family ENEMY_FAMILY = Family.all(
            EnemyComponent.class
    ).get();


    public static boolean isEnemySaw(int x, int y, PooledEngine engine) {

        boolean check = false;
        ImmutableArray<Entity> enemies = engine.getEntitiesFor( ENEMY_FAMILY );

        for (Entity enemy : enemies) {
            MatrixPositionComponent matrixPositionComponent = Mappers.MATRIX_POSITION.get( enemy );
            FindEnemyComponent find = Mappers.FIND_ENEMY.get( enemy );

            if (!find.isEnemyFound) {
                float dX = Math.abs( x - matrixPositionComponent.x );
                float dY = Math.abs( y - matrixPositionComponent.y );

                if (dX <= 2 && dY <= 5) {
                    MatrixPositionComponent enemyMatrix = Mappers.MATRIX_POSITION.get( enemy );

                    Node initialNode = new Node( enemyMatrix.x, enemyMatrix.y );
                    Node finalNode = new Node( x, y );

                    int rows = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;
                    int cols = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;

                    Astar aStar = new Astar( rows, cols, initialNode, finalNode );

                    int[][] blockArray = GenerateMapTilesSystem.getBlocks();
                    aStar.setBlocks( blockArray );

                    List<Node> path = aStar.findPath();


                    if (path.size() < 5 && path.size() > 0) {
                        Gdx.app.debug( "PathUtils", "Enemy saw player: (" + enemyMatrix.x + ", " + enemyMatrix.y + ")" );
                        find.isEnemyFound = true;
                        check = true;
                    }
                }
            }
        }

        return check;
    }

    public static List<Node> getPlayerPath(Entity player, PooledEngine engine) {
        ImmutableArray<Entity> enemies = engine.getEntitiesFor( ENEMY_FAMILY );
        MatrixPositionComponent playerMatrix = Mappers.MATRIX_POSITION.get( player );

        Node initialNode = new Node( playerMatrix.x, playerMatrix.y );
        Node finalNode = new Node( CurrentData.PLAYER_FINAL_NODE_X, CurrentData.PLAYER_FINAL_NODE_Y );
        int rows = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;
        int cols = DungeonConfig.DUNGEON_SIZE + DungeonConfig.OFFSET_SIZE;
        Astar aStar = new Astar( rows, cols, initialNode, finalNode );

        int[][] blockArray = getBlockArrayForPlayer( finalNode, enemies );

        aStar.setBlocks( blockArray );

        return aStar.findPath();
    }

    private static int[][] getBlockArrayForPlayer(Node finalNode, ImmutableArray<Entity> enemies) {
        int[][] tileBlocks = GenerateMapTilesSystem.getBlocks();
        int[][] finalBlock;

        if (enemies.size() < 1) {
            Gdx.app.debug("PathUtils", "No more enemy in this level" );
            return tileBlocks;
        } else {


            int[][] enemyBlock = new int[enemies.size()][2];

            Gdx.app.debug("PathUtils","enemies.size() => " + enemies.size());
            Gdx.app.debug("PathUtils","enemyBlock.length() => " + enemyBlock.length);

            for (int i = 0; i < enemyBlock.length; i++) {
                MatrixPositionComponent matrixEnemy = Mappers.MATRIX_POSITION.get( enemies.get( i ) );

                if (matrixEnemy.x == finalNode.getRow() && matrixEnemy.y == finalNode.getCol()) {
                    Gdx.app.debug( "PathUtils", "Enemy stay on finalNode" );
                    CurrentData.CLICKED_ENEMY = enemies.get( i );
                } else {
                    enemyBlock[i][0] = matrixEnemy.x;
                    enemyBlock[i][1] = matrixEnemy.y;
                }
            }

            finalBlock = new int[tileBlocks.length + enemyBlock.length][2];

            for (int i = 0; i < finalBlock.length; i++) {

                if (i < tileBlocks.length) {
                    finalBlock[i][0] = tileBlocks[i][0];
                    finalBlock[i][1] = tileBlocks[i][1];
                } else {
                    finalBlock[i][0] = enemyBlock[i - tileBlocks.length][0];
                    finalBlock[i][1] = enemyBlock[i - tileBlocks.length][1];
                }
            }

            return finalBlock;
        }
    }

    public static int[][] getBlockArrayForEnemy(Engine engine, Entity thisEnemy) {
        ImmutableArray<Entity> enemies = engine.getEntitiesFor( ENEMY_FAMILY );

        int[][] tileBlocks = GenerateMapTilesSystem.getBlocks();
        int[][] finalBlock;

        if (enemies.size() <= 1) {
            Gdx.app.debug( "PathUtils", "One or less enemy: block only with walls" );
            return tileBlocks;
        } else {
            int[][] enemyBlock = new int[enemies.size() - 1][2];
            MatrixPositionComponent thisEnemyMatrix = Mappers.MATRIX_POSITION.get( thisEnemy );
            int check = 0;

            for(Entity enemy : enemies){
                MatrixPositionComponent enemyMatrix = Mappers.MATRIX_POSITION.get( enemy );

                if (enemyMatrix.x == thisEnemyMatrix.x && enemyMatrix.y == thisEnemyMatrix.y) {
                    Gdx.app.debug( "PathUtils", "Try add current enemy" );
                } else{
                    enemyBlock[check][0] = enemyMatrix.x;
                    enemyBlock[check][1] = enemyMatrix.y;
                    check++;
                }
            }

            Gdx.app.debug( "PathUtils", "enemyBlock.size: " + enemyBlock.length );

            finalBlock = new int[tileBlocks.length + enemyBlock.length][2];

            for (int i = 0; i < finalBlock.length; i++) {

                if (i < tileBlocks.length) {
                    finalBlock[i][0] = tileBlocks[i][0];
                    finalBlock[i][1] = tileBlocks[i][1];
                } else {
                    finalBlock[i][0] = enemyBlock[i - tileBlocks.length][0];
                    finalBlock[i][1] = enemyBlock[i - tileBlocks.length][1];
                }
            }
            return finalBlock;
        }

    }







    private PathUtils(){}


}
