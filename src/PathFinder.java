import java.util.ArrayList;

public class PathFinder {
    public static void findPath(int[][] map, Node source, Node target) {
        int[][] points = new int[map.length][map.length];

        // mark all the points over the map
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                points[i][j] = Integer.MAX_VALUE;
            }
        }

        // start searching
        boolean isPathFound = false;
        ArrayList<Node> openNodes = new ArrayList<Node>();

        points[source.yCoord][source.xCoord] = 0;
        openNodes.add(new Node(source.xCoord, source.yCoord));

        while (!isPathFound) {
            Node currentNode = openNodes.get(0);
            int currentDistance = points[currentNode.yCoord][currentNode.xCoord];

            try {
                // check neigbour nodes
                if (map[currentNode.yCoord][currentNode.xCoord + 1] == 0) {
                    if (points[currentNode.yCoord][currentNode.xCoord + 1] == Integer.MAX_VALUE) {
                        openNodes.add(new Node(currentNode.xCoord + 1, currentNode.yCoord));
                    }
                }
            } catch (Exception e) {

            }
            try {
                if (map[currentNode.yCoord][currentNode.xCoord - 1] == 0) {
                    if (points[currentNode.yCoord][currentNode.xCoord - 1] == Integer.MAX_VALUE) {
                        openNodes.add(new Node(currentNode.xCoord - 1, currentNode.yCoord));
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
            }

            try {
                if (map[currentNode.yCoord + 1][currentNode.xCoord] == 0) {
                    if (points[currentNode.yCoord + 1][currentNode.xCoord] == Integer.MAX_VALUE) {
                        openNodes.add(new Node(currentNode.xCoord, currentNode.yCoord + 1));
                    }
                }

            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                if (map[currentNode.yCoord - 1][currentNode.xCoord] == 0) {
                    if (points[currentNode.yCoord - 1][currentNode.xCoord] == Integer.MAX_VALUE) {
                        openNodes.add(new Node(currentNode.xCoord, currentNode.yCoord - 1));
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            openNodes.remove(0);

            if (openNodes.isEmpty()) {
                isPathFound = true;
            } else {
                // evaluate all the open nodes
                for (Node node : openNodes) {
                    if (points[node.yCoord][node.xCoord] == Integer.MAX_VALUE) {
                        points[node.yCoord][node.xCoord] = currentDistance + 1;
                    }
                }
            }
        }

        // print the route
        boolean isAllNodesMarked = false;
        Node currentNode = new Node(target.xCoord, target.yCoord);
        map[currentNode.yCoord][currentNode.xCoord] = 4;

        while (!isAllNodesMarked) {
            int currentPoint = points[currentNode.yCoord][currentNode.xCoord];

            try {
                if (points[currentNode.yCoord][currentNode.xCoord + 1] < currentPoint) {
                    currentNode = new Node(currentNode.xCoord + 1, currentNode.yCoord);
                    map[currentNode.yCoord][currentNode.xCoord] = 2;
                }
            } catch (Exception e) {

            }
            try {
                if (points[currentNode.yCoord][currentNode.xCoord - 1] < currentPoint) {
                    currentNode = new Node(currentNode.xCoord - 1, currentNode.yCoord);
                    map[currentNode.yCoord][currentNode.xCoord] = 2;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            try {
                if (points[currentNode.yCoord + 1][currentNode.xCoord] < currentPoint) {
                    currentNode = new Node(currentNode.xCoord, currentNode.yCoord + 1);
                    map[currentNode.yCoord][currentNode.xCoord] = 2;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                if (points[currentNode.yCoord - 1][currentNode.xCoord] < currentPoint) {
                    currentNode = new Node(currentNode.xCoord, currentNode.yCoord - 1);
                    map[currentNode.yCoord][currentNode.xCoord] = 2;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            map[currentNode.yCoord][currentNode.xCoord] = 2;

            if (currentNode.xCoord == source.xCoord && currentNode.yCoord == source.yCoord) {
                isAllNodesMarked = true;
                map[currentNode.yCoord][currentNode.xCoord] = 3;
            }
        }
    }
}
