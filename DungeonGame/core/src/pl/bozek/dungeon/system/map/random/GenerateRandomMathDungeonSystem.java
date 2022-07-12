package pl.bozek.dungeon.system.map.random;

import com.badlogic.gdx.utils.Array;
import pl.bozek.dungeon.config.DungeonConfig;
import pl.bozek.dungeon.map.RoomInfo;
import pl.bozek.dungeon.map.array.Float2D;
import pl.bozek.dungeon.map.generator.room.RoomType;
import pl.bozek.dungeon.map.generator.room.dungeon.DungeonGenerator;
import pl.bozek.dungeon.util.ConsoleUtils;


public class GenerateRandomMathDungeonSystem {

    private static Float2D dungeonLayout;
    public static Array<RoomInfo> rooms;

    GenerateRandomMathDungeonSystem(){
        dungeonLayout =  new Float2D(DungeonConfig.DUNGEON_SIZE);
        rooms = new Array<>(  );

        final DungeonGenerator dungeonGenerator = new DungeonGenerator();
        dungeonGenerator.setMaxRoomSize(DungeonConfig.MAX_ROOM_SIZE);
        dungeonGenerator.setTolerance(DungeonConfig.ROOM_SIZE_TOLERANCE);
        dungeonGenerator.setMinRoomSize(DungeonConfig.MIN_ROOM_SIZE);
        dungeonGenerator.setRoomGenerationAttempts(DungeonConfig.ATTEMPTS);
        dungeonGenerator.setWindingChance(DungeonConfig.WIND_CHANCE);
        dungeonGenerator.setRandomConnectorChance(DungeonConfig.CONNECT_CHANCE);
        dungeonGenerator.setDeadEndRemovalIterations(DungeonConfig.DEAD_END);
        dungeonGenerator.addRoomTypes(RoomType.DefaultRoomType.values());
        dungeonGenerator.generate(dungeonLayout);
    }

    static void printDungeon(){
        StringBuilder consoleArray = new StringBuilder();
        for (int x = 0; x < dungeonLayout.getWidth(); x++) {
            for (int y = 0; y < dungeonLayout.getHeight(); y++) {

                if(dungeonLayout.get(x, y) == 1.0f)
                    consoleArray.append(ConsoleUtils.ANSI_RED).append(dungeonLayout.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

                if(dungeonLayout.get(x, y) == 0.5f)
                    consoleArray.append(ConsoleUtils.ANSI_YELLOW).append(dungeonLayout.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

                if(dungeonLayout.get(x, y) == 0.0f)
                    consoleArray.append(ConsoleUtils.ANSI_BLUE).append(dungeonLayout.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

            }

            consoleArray.append("\n");

        }

        System.out.println(consoleArray);
    }

    static Float2D getDungeonLayout() {
        return dungeonLayout;
    }


    static void printRooms() {
        Float2D array = new Float2D(DungeonConfig.DUNGEON_SIZE);
        array.set(1);


        for(RoomInfo room : rooms){
            for(int i = 0; i < room.getRoom().getWidth(); i++){
                for(int j = 0; j < room.getRoom().getHeight(); j++){
                    array.set(i + room.getX() , j+room.getY() , room.getRoom().get(i, j));
                }
            }
        }

        StringBuilder consoleArray = new StringBuilder();
        for (int x = 0; x < array.getWidth(); x++) {
            for (int y = 0; y < array.getHeight(); y++) {

                if(array.get(x, y) == 1.0f)
                    consoleArray.append(ConsoleUtils.ANSI_RED).append(array.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

                if(array.get(x, y) == 0.5f)
                    consoleArray.append(ConsoleUtils.ANSI_YELLOW).append(array.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

                if(array.get(x, y) == 0.0f)
                    consoleArray.append(ConsoleUtils.ANSI_BLUE).append(array.get(x, y)).append(" ").append(ConsoleUtils.ANSI_RESET);

            }

            consoleArray.append("\n");

        }

        System.out.println(consoleArray);




    }
}
