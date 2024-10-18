import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {
    private static final int TILE_SIZE = 20;
    private static final int GRID_SIZE = 20;
    private static final int SCREEN_WIDTH = TILE_SIZE * GRID_SIZE;
    private static final int SCREEN_HEIGHT = TILE_SIZE * GRID_SIZE;
    
    private LinkedList<Point> snake;
    private Point food;
    private int direction;
    private boolean gameOver;
    private Timer timer;

    // Directions
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    public SnakeGame() {
        snake = new LinkedList<>();
        snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2)); // Starting position
        direction = RIGHT;
        generateFood();
        gameOver = false;
        
        // Timer to control game speed
        timer = new Timer(100, this);
        timer.start();

        // Setting up the panel
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    // Generate random position for food
    private void generateFood() {
        Random rand = new Random();
        food = new Point(rand.nextInt(GRID_SIZE), rand.nextInt(GRID_SIZE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Draw snake
        g.setColor(Color.GREEN);
        for (Point point : snake) {
            g.fillRect(point.x * TILE_SIZE, point.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw Game Over text if game is over
        if (gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", SCREEN_WIDTH / 4, SCREEN_HEIGHT / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        // Move the snake
        Point head = snake.getFirst();
        Point newHead = new Point(head);

        switch (direction) {
            case UP:
                newHead.y--;
                break;
            case RIGHT:
                newHead.x++;
                break;
            case DOWN:
                newHead.y++;
                break;
            case LEFT:
                newHead.x--;
                break;
        }

        // Check if snake hits the wall or itself
        if (newHead.x < 0 || newHead.x >= GRID_SIZE || newHead.y < 0 || newHead.y >= GRID_SIZE || snake.contains(newHead)) {
            gameOver = true;
            repaint();
            return;
        }

        // Add new head to the snake
        snake.addFirst(newHead);

        // Check if snake eats the food
        if (newHead.equals(food)) {
            generateFood(); // Generate new food
        } else {
            snake.removeLast(); // Remove tail if no food eaten
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP && direction != DOWN) {
            direction = UP;
        } else if (key == KeyEvent.VK_RIGHT && direction != LEFT) {
            direction = RIGHT;
        } else if (key == KeyEvent.VK_DOWN && direction != UP) {
            direction = DOWN;
        } else if (key == KeyEvent.VK_LEFT && direction != RIGHT) {
            direction = LEFT;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // Main method to start the game
    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake Game");
        SnakeGame game = new SnakeGame();
        
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
