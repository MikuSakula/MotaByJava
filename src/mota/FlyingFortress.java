package mota;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static mota.MapData.*;
import static mota.GameCenter.*;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class FlyingFortress {
	public static JLayeredPane flyFortressLPane = new JLayeredPane();         // 跳跃信息面板
	public static JLabel titleLPane ;
	public static JLabel titleMsg ;

    static {
        // 初始化 跳跃信息面板
        flyFortressLPane.setLayout(null);
        flyFortressLPane.setBounds(7 * GAME_PIX_72, 2 * GAME_PIX_72, GAME_PIX_72 * 9, GAME_PIX_72 * 9);
        flyFortressLPane.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        flyFortressLPane.setBackground(Color.BLACK);
        flyFortressLPane.setOpaque(true);
        flyFortressLPane.setVisible(false);
        titleLPane = new JLabel("飞行堡垒");
        titleLPane.setFont(new Font("Serif", 0, 40));
        titleLPane.setForeground(Color.WHITE);
        titleLPane.setBounds(250,50,200,50);
        flyFortressLPane.add(titleLPane);
        
        titleMsg = new JLabel("按<W>和<S>移动光标，<SPACE>确定，ESC退出");
        titleMsg.setFont(new Font("Serif", 0, 20));
        titleMsg.setForeground(Color.WHITE);
        titleMsg.setBounds(115,565,500,50);
        flyFortressLPane.add(titleMsg);
    }

    static JLabel[] choices = new JLabel[21];
    public static void displayJump() {
    	Misaka.setCanUseFlyingFortress(false);
        for (int x = 0; x < Math.min(8, maxFloor); x++) {
        	JLabel temp;
        	if( x==0) {
        		 temp = new JLabel("▶第 " + x + " 层");
        	}
        	else {
        		temp = new JLabel("▷第 " + x + " 层");
        	}
            temp.setFont(new Font("Serif", 0, 30));
            temp.setForeground(Color.WHITE);
            temp.setBounds(50, 150 + 50 * x, 200, 50);
            flyFortressLPane.add(temp);
            choices[x] = temp;
        }
        if (maxFloor >= 8)
            for (int x = 8; x < 16; x++) {
                JLabel temp = new JLabel("▷第 " + x + " 层");
                temp.setFont(new Font("Serif", 0, 30));
                temp.setForeground(Color.WHITE);
                temp.setBounds(250, 150 + 50 * (x - 8), 200, 50);
                flyFortressLPane.add(temp);
                choices[x] = temp;
            }
        if (maxFloor >= 16)
            for (int x = 16; x < 21; x++) {
                JLabel temp = new JLabel("▷第 " + x + " 层");
                temp.setFont(new Font("Serif", 0, 30));
                temp.setForeground(Color.WHITE);
                temp.setBounds(450, 150 + 50 * (x - 16), 200, 50);
                flyFortressLPane.add(temp);
                choices[x] = temp;
            }
        

        flyFortressLPane.setVisible(true);
     //   choices[0].setText(choices[0].getText().replaceAll("▶", "▷"));


        gameFrame.addKeyListener(new KeyListener() {
            int selection = 0;

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (selection != 20 && e.getKeyCode() == e.VK_S && choices[selection + 1] != null) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    selection = selection + 1;
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    gameFrame.repaint();
                }
                if (selection != 0 && e.getKeyCode() == e.VK_W) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    selection = selection - 1;
                    choices[selection].setText(choices[selection].getText().replaceAll("▷", "▶"));
                    gameFrame.repaint();
                }
                if (e.getKeyCode() == e.VK_SPACE) {
                    choices[selection].setText(choices[selection].getText().replaceAll("▶", "▷"));
                    Misaka.move(initPos[selection][0], initPos[selection][1]);
                    currentFloor = selection;
                    gameFrame.repaint();
                    inConversation = false;
                    flyFortressLPane.removeAll();
                    flyFortressLPane.setVisible(false);
                    gameFrame.removeKeyListener(this);
                    Misaka.setCanUseFlyingFortress(true);
                }
                if(e.getKeyCode() == e.VK_ESCAPE) {
                	inConversation = false;
                    flyFortressLPane.removeAll();
                    flyFortressLPane.setVisible(false);
                    gameFrame.removeKeyListener(this);
                    Misaka.setCanUseFlyingFortress(true);
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
