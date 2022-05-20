import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Tictactoe extends JFrame implements ActionListener{

    public static JFrame Tboard_frame, GameOver_frame;
    public static JButton [][]Tboard_keys = new JButton[3][3];
    public static JPanel Tboard_panel, label_panel;
    public static JLabel label;
    public static Font font = new Font("DialogInput", Font.BOLD, 50);
    public static Font key_font = new Font("DialogInput", Font.BOLD, 120);
    public static final String X_KEY = "X", O_KEY = "O", winning_move = "";
    public static int moveCounter = 0;
    public static boolean P1_turn = true, P2_turn = false, game_over = false;


    public static void main(String args[]){
        Tictactoe tictactoe = new Tictactoe();

        Tboard_frame = new JFrame("TIC-TAC-TOE");
        Tboard_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tboard_frame.setSize(500,600);
        Tboard_frame.setLayout(null);
        label = new JLabel("TIC-TAC-TOE");

        label_panel = new JPanel();
        label_panel.setBounds(0,0,500,100);
        label_panel.setBackground(Color.CYAN);

        label.setBounds(150,-50,450,100);
        label.setFont(font);

        label_panel.add(label);

        Tboard_panel = new JPanel();
        Tboard_panel.setBounds(15,100,450,450);
        Tboard_panel.setLayout(new GridLayout(3,3,2,2));
        Tboard_panel.setBackground(Color.black);


        for(int  i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Tboard_keys[i][j] = new JButton(" ");
                Tboard_keys[i][j].setFocusable(false);
                Tboard_keys[i][j].setFont(key_font);
                Tboard_keys[i][j].addActionListener(tictactoe);
                Tboard_panel.add(Tboard_keys[i][j]);
            }
        }

        Tboard_frame.add(label_panel);
        Tboard_frame.add(Tboard_panel);

        GameOver_frame = new JFrame("TIC-TAC-TOE");
        GameOver_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameOver_frame.setSize(500,600);
        GameOver_frame.setLayout(null);
        GameOver_frame.setVisible(false);

        Tboard_frame.setVisible(true);
    }

//    Tictactoe(){
//
//    }
    private static void get_keyMove (ActionEvent e, String key){
        for (int  i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(e.getSource() == Tboard_keys[i][j]){
                    Tboard_keys[i][j].setText(key);
                }
            }
        }
        moveCounter++;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        game_over = false;
        do{
            if(P1_turn & !P2_turn){
                try {
                    get_keyMove(e,X_KEY);
                    P1_turn = false;
                    P2_turn = true;
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                notifyAll();
                //winning move checker
            }
            else if(!P1_turn & P2_turn){
                try {
                    get_keyMove(e,O_KEY);
                    P2_turn = false;
                    P1_turn = true;
                    System.out.println(game_over+ " " + moveCounter);
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                notifyAll();
                //winning move checker
            }
            if(moveCounter == 9){
                game_over = true;
                //break;
                System.out.println(game_over+ " " + moveCounter);
            }
        }while(!game_over);

        System.out.println("GAME OVER!!"+ " " + moveCounter);
        Tboard_frame.dispose();
        GameOver_frame.setVisible(true);

    }

}
