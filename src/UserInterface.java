import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

public class UserInterface extends JFrame {
    private int[][] map;
    private final int blockSize = 50;
    private Node source, target;

    public UserInterface(int[][] map) {
        this.map = map;

        this.setTitle("Path Finder");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 528);
        this.setResizable(false);
        this.addMouseListener(new MouseInputListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouseClicked");

                switch (e.getButton()) {
                    case MouseEvent.BUTTON1: {
                        int i;
                        int j;

                        i = (e.getY() - 28) / blockSize;
                        j = (e.getX()) / blockSize;

                        // check if coordinates are valid
                        try {
                            if (map[i][j] != 1) {
                                if (source != null) {
                                    map[source.yCoord][source.xCoord] = 0;
                                }
                                source = new Node(j, i);
                                map[i][j] = 3;
                                repaint();
                            } else {
                                // show an error message
                                JOptionPane.showMessageDialog(UserInterface.this, "Duvarı seçemezsin!", "Hata",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (Exception exception) {
                        }

                    }
                        break;
                    case MouseEvent.BUTTON3: {
                        int i;
                        int j;

                        i = (e.getY() - 28) / blockSize;
                        j = (e.getX()) / blockSize;

                        try {
                            if (map[i][j] != 1) {
                                if (target != null) {
                                    map[target.yCoord][target.xCoord] = 0;
                                }
                                target = new Node(j, i);
                                map[i][j] = 4;
                                repaint();
                            } else {
                                // show an error message
                                JOptionPane.showMessageDialog(UserInterface.this, "Duvarı seçemezsin!", "Hata",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (Exception exception) {
                        }
                    }
                        break;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    resetMap();
                    PathFinder.findPath(map, source, target);
                    repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawLabyrinth(g);
    }

    public void drawLabyrinth(Graphics g) {
        // draw relevant graphics for each block
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 1:
                        drawWall(g, i, j);
                        break;
                    case 2:
                        drawRoute(g, i, j);
                        break;
                    case 3:
                        drawStartPoint(g, i, j);
                        break;
                    case 4:
                        drawEndPoint(g, i, j);
                        break;
                    default:
                        // System.out.print(" ");
                        // drawPath
                        break;
                }
            }
        }

    }

    public void drawWall(Graphics g, int i, int j) {
        int x;
        int y;

        x = j * blockSize;
        y = i * blockSize + 28;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, blockSize, blockSize);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, blockSize, blockSize);
    }

    public void drawRoute(Graphics g, int i, int j) {
        int x;
        int y;

        x = j * blockSize;
        y = i * blockSize + 28;
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, blockSize, blockSize);
    }

    public void drawStartPoint(Graphics g, int i, int j) {
        int x;
        int y;

        x = j * blockSize;
        y = i * blockSize + 28;
        g.setColor(Color.RED);
        g.fillRect(x, y, blockSize, blockSize);
    }

    public void drawEndPoint(Graphics g, int i, int j) {
        int x;
        int y;

        x = j * blockSize;
        y = i * blockSize + 28;
        g.setColor(Color.RED);
        g.fillRect(x, y, blockSize, blockSize);
    }

    public void resetMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > 1) {
                    map[i][j] = 0;
                }
            }
        }
    }
}
