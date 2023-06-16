import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame extends JFrame implements ActionListener {
        JButton playAgain;
        SnakePanel game;


    public SnakeGame(){
        /*
        playAgain.addActionListener(this);
        panel.add(playAgain);
        main.setLayout(new BorderLayout());
        main.add(panel, BorderLayout.NORTH);
        this.add(playAgain);

         */
        playAgain = new JButton();
        playAgain.setText("Play Again!");
        playAgain.setForeground(Color.RED);
        playAgain.setSize(100, 50);
        playAgain.setLocation(300, 0);
        playAgain.addActionListener(this);

        game = new SnakePanel();

        this.add(playAgain);
        this.add(game);
        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        playAgain.setVisible(true);
    }

    public void playAgain(){
        this.remove(this);
       // this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        new SnakeGame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == playAgain){
            this.remove(game);
            game = new SnakePanel();
            this.add(game);
            SwingUtilities.updateComponentTreeUI(this);
        }
    }

    /*this.setTitle("SnakeGame");
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.getContentPane().setBackground(Color.black);

    JButton play = new JButton("Play");
    JButton pause = new JButton("||");
    JButton end = new JButton("End");
    JLabel score = new JLabel("Score: ");

    //this.add(play);
    // this.add(pause);
    // this.add(end);
    //this.add(score);

   SnakePanel panel = new SnakePanel();
        this.add(panel);
        panel.setLayout(null);

    SnakePanel panel2 = new SnakePanel();
    SnakePanel panel3 = new SnakePanel();
    SnakePanel panel4 = new SnakePanel();

        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);

        panel2.setLayout(null);
        panel2.add(play);
        panel3.add(pause);
        panel3.add(end);
        panel4.add(score);
        panel2.setLocation(50, 50);

        this.pack();
        this.setVisible(true);
*/
}

