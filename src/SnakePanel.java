import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.awt.event.*;

public class SnakePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 1024;
    static final int SCREEN_HEIGHT = 768;
    static final int UNIT_SIZE = 25; //Default 25
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 80;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 4;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    boolean play = false;
    JButton playButton;
    JButton end;
    JButton playAgain;
    JLabel label;
    Timer timer;
    JPanel play_button_panel;
    JPanel end_button_panel;
    JPanel title_panel;
    JPanel play_again_panel;
    JPanel panel4;
    JPanel panel5;
    JPanel panel6;
    JPanel panel7;
    JPanel panel8;
    Random random;

    public SnakePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.setLayout(new BorderLayout());

        Border border = BorderFactory.createLineBorder(Color.YELLOW, 6);
        this.setBorder(border);

        //Main panels
        play_button_panel = new JPanel();
        play_button_panel.setBackground(Color.RED);
        end_button_panel = new JPanel();
        title_panel = new JPanel();
        play_again_panel = new JPanel();


        //Sub panels
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();


        //Play button
        playButton = new JButton();
        playButton.addActionListener(this);
        panel5.setPreferredSize(new Dimension(300, 200));
        panel5.setMinimumSize(new Dimension(300, 200));
        panel5.setMaximumSize(new Dimension(300, 200));
        playButton.setPreferredSize(new Dimension(300, 150));
        playButton.setMinimumSize(new Dimension(300, 150));
        playButton.setMaximumSize(new Dimension(300, 150));
       // playButton.setText("Play");
        playButton.setIcon(new ImageIcon("U:/play2.png"));
     //   playButton.setFont(new Font("Play", Font.ITALIC, 20));
        panel5.add(playButton, BorderLayout.CENTER);
        play_button_panel.add(panel5, BorderLayout.CENTER);
     //   play_button_panel.add(playButton);
        play_button_panel.setPreferredSize(new Dimension(70, 250));
        play_button_panel.setMinimumSize(new Dimension(70, 250));
        play_button_panel.setMaximumSize(new Dimension(70, 250));

        play_button_panel.add(title_panel);
        this.add(play_button_panel, BorderLayout.SOUTH);


        //End Button
        end = new JButton("End");
        end.addActionListener(this);
        //end_button_panel.setBackground(Color.WHITE);
        end_button_panel.add(panel4);
        end_button_panel.setPreferredSize(new Dimension(70, 100));
        end_button_panel.setMinimumSize(new Dimension(70, 100));
        end_button_panel.setMaximumSize(new Dimension(70, 100));
       // panel4.setBackground(Color.WHITE);
        panel4.add(end);
        end_button_panel.setLayout(new BorderLayout());
        end_button_panel.add(panel4, BorderLayout.WEST);
        this.add(end_button_panel, BorderLayout.NORTH);
        end.setVisible(false);


        //Play Again Button
        playAgain = new JButton();
        playAgain.setText("Play Again!");
        playAgain.setFont(new Font("Play Again!", Font.ITALIC, 20));
        playAgain.addActionListener(this);
        //play_again_panel.setBackground(Color.GREEN);
        playAgain.setMinimumSize(new Dimension(150, 60));
        playAgain.setPreferredSize(new Dimension(150, 60));
        playAgain.setMaximumSize(new Dimension(150, 60));
        play_again_panel.setMinimumSize(new Dimension(1024, 50));
        play_again_panel.setPreferredSize(new Dimension(1024, 50));
        play_again_panel.setMaximumSize(new Dimension(1024, 50));
        play_again_panel.setVisible(false);
        play_again_panel.setOpaque(false);
       // playAgain.setVisible(false);
        panel8.add(playAgain, BorderLayout.SOUTH);
        panel8.setVisible(false);
        play_again_panel.add(panel8, BorderLayout.WEST);
        this.add(play_again_panel, BorderLayout.WEST);

        //Game title screen
        ImageIcon image = new ImageIcon("U:/textanim-UcIFb.gif");
        label = new JLabel();
        label.setIcon(image);
      //  panel7.add(label, BorderLayout.CENTER);
        title_panel.setBackground(Color.GRAY);
        title_panel.setPreferredSize(new Dimension(1024, 300));
        title_panel.setMinimumSize(new Dimension(1024, 300));
        title_panel.setMaximumSize(new Dimension(1024, 300));
        title_panel.add(label);
        this.add(title_panel, BorderLayout.CENTER);

        //Timer
        timer = new Timer(DELAY, this);

        //Things to do

        //Play again button

        }
    public void startGame(){
        newApple();
        running = true;
        //timer = new Timer(DELAY, this);
        timer.start();
        playAgain.setVisible(false);
        play_again_panel.setVisible(false);
        panel8.setVisible(false);
    }
    public void reset(){
        running = false;
        playAgain.setVisible(true);
        play_again_panel.setVisible(true);
        panel8.setVisible(true);
        timer.restart();
        bodyParts = 4;
        char direction = 'R';
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g) {

        if (running) {
            // grid
           /*for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

            } */
            //Apple
            g.setColor(Color.GREEN);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //Snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    //g.setColor(new Color(45, 180, 0));
                    g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
         /*  g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score:" + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
            */

        }
        else{
            gameOver(g);
        }
    }
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }
    public void Move(){
        for(int i = bodyParts;i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction){
        case 'U':
            y[0] = y[0] - UNIT_SIZE;
            break;
        case 'D':
            y[0] = y[0] + UNIT_SIZE;
            break;
        case 'L':
            x[0] = x[0] - UNIT_SIZE;
            break;
        case 'R':
            x[0] = x[0] + UNIT_SIZE;
            break;
        }
    }
    public void checkApple(){
       if((x[0] == appleX) && (y[0] == appleY)){
           bodyParts++;
           applesEaten++;
           newApple();
       }
    }
    public void checkCollisions(){
        //Checks if head collides with body
        for(int i = bodyParts; i>0; i--){
            if((x[0] == x[i] && (y[0] == y[i]))){
              //  running = false;
                reset();
            }
        }
        //Check if head touches left border
        if(x[0] < 0){
        //    running = false;
            reset();
        }
        //Check if head touches right border
        if(x[0] > SCREEN_WIDTH){
         //   running = false;
            reset();
        }
        //Check if head touches top border
        if(y[0] < 0){
          //  running = false;
            reset();
        }
        //Check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT){
            //running = false;
            reset();
        }
        if(!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //Score
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:" + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.GREEN);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over!", (SCREEN_WIDTH - metrics2.stringWidth("Game Over!"))/2, SCREEN_HEIGHT/2);
       // running = false;
        reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            if (e.getSource() == playButton){
                startGame();
                playButton.setVisible(false);
                play_button_panel.setVisible(false);
                title_panel.setVisible(false);
                this.setBorder(null);
                end_button_panel.setOpaque(false);
                panel4.setOpaque(false);
            }

            if (e.getSource() == end) {
                reset();
                end.setVisible(false);
                end_button_panel.setVisible(false);
            }

            if(e.getSource() == playAgain){

            }


        if(running){
            Move();
            checkApple();
            checkCollisions();
            end.setVisible(true);
        }
        repaint();
    }
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }

}



