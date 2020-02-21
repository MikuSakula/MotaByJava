package mota;



import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import static mota.Map.*;
import static mota.GameCenter.*;

public class SendMessage {
	public static JLayeredPane msgLPane = new JLayeredPane();           // 提示信息面板
    public static JLabel msgLabel = new JLabel();

    static {
        // 初始化 信息面板
        msgLPane.setLayout(null);
        msgLPane.setBounds(10, 270, GAME_PIX_72 * 18 - 20, 150);
        msgLabel.setBounds(0, 0, GAME_PIX_72 * 18 - 20, 150);
        msgLabel.setForeground(Color.WHITE);
        msgLabel.setFont(new Font("Serif", 0, 50));
        msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel msgBackground = new JLabel(new ImageIcon(Map.blankBgImg));
        msgBackground.setLayout(null);
        msgBackground.setBounds(0, 0, GAME_PIX_72 * 18 - 20, 150);
        msgBackground.setBorder(BorderFactory.createLineBorder(new Color(204, 102, 0), 8, true));
        msgLPane.add(msgBackground, 1, 0);
        msgLPane.add(msgLabel, 2, 0);
        msgLPane.setOpaque(true);
        msgLPane.setVisible(false);
    }

    public static void displayMessage(String message) {
        msgLPane.setVisible(true);
        inConversation = true;
        try {
			new Robot().delay(200);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Timer animat = new Timer(500, new ActionListener() {
            int count = 0;
            int flag = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(flag == 0 && (message.indexOf('L')!=-1||message.indexOf('J')!=-1)) {
            		count = -3;
            		flag = 1;
            	}
            	else if(flag == 0 && message.indexOf("星光神琅")!=-1) {
            		count = -1;
            		flag = 1;
            	}
                count++;
                if (count == 2) {
                    msgLPane.setVisible(false);
                    inConversation = false;
                    ((Timer) e.getSource()).stop();
                }
                msgLabel.setText(message);
                gameFrame.repaint();
            }
        });
        animat.setInitialDelay(0);
        animat.start();
//        try {
//			Thread.sleep(50);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
    }
}
