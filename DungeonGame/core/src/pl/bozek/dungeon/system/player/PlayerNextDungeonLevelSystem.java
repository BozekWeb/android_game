package pl.bozek.dungeon.system.player;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import pl.bozek.dungeon.common.CurrentData;
import pl.bozek.dungeon.common.PlayerData;
import pl.bozek.dungeon.component.Mappers;
import pl.bozek.dungeon.component.element.MatrixPositionComponent;
import pl.bozek.dungeon.component.tag.PlayerComponent;
import pl.bozek.dungeon.map.RoomInfo;
import pl.bozek.dungeon.system.map.random.GenerateRandomMathDungeonSystem;

public class PlayerNextDungeonLevelSystem extends IteratingSystem {

    private static final Family PLAYER_FAMILY = Family.all(
            PlayerComponent.class
    ).get();


    public PlayerNextDungeonLevelSystem(){
        super(PLAYER_FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        RoomInfo roomEnd = GenerateRandomMathDungeonSystem.rooms.get( CurrentData.PLAYER_END_ROOM );

        MatrixPositionComponent matrix = Mappers.MATRIX_POSITION.get( entity );


        if(roomEnd.getCenter()[0] == matrix.x && roomEnd.getCenter()[1] == matrix.y ){
            PlayerData.playerStatistics.incDeep( 1 );
            CurrentData.PLAYER_DEAD = true;
        }




    }
}
