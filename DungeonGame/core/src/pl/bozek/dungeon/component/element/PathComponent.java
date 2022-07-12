package pl.bozek.dungeon.component.element;

import com.badlogic.ashley.core.Component;

import java.util.List;

import pl.bozek.dungeon.util.astar.Node;

public class PathComponent implements Component {
    public List<Node> path;
}
