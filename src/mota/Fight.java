package mota;

import java.io.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.Timer;

import static mota.MapData.LevelMap;

import static mota.GameCenter.GAME_PIX_72;
import static mota.GameCenter.Misaka;
import static mota.MonsterData.monsterMap;

public class Fight {
	private static final Font BATTLE_FONT = new Font("Serif", 0, 35);
	public static JLayeredPane battleLPane = new JLayeredPane();
	private JLabel battleBackground;
	private JLabel monsterImg;
	private JLabel monsterHitpoint = new JLabel();
	private JLabel monsterAttack = new JLabel();
	private JLabel monsterDefened = new JLabel();
	private JLabel misakaHitpoint = new JLabel();
	private JLabel misakaAttack = new JLabel();
	private JLabel misakaDefened = new JLabel();
	private Monster monster;
	private static boolean canSendMessage = false;
	private int HitPoint;

	public Fight(int id, int x, int y) {
		monster = new Monster();
		monster = monsterMap.get(id);
		HitPoint = monster.getHp();

		if (Misaka.getAttackPower() <= monster.getDefend()) {
			new GetThingsMessage().RefuseToFight();	
			return;
		}
		int attackTimes = (int) Math.ceil(monster.getHp() / (Misaka.getAttackPower() - monster.getDefend()));
		int tempHit = Misaka.getHitPoint();
		if(id == 50)
			tempHit = tempHit*3/4;
		if(id == 57)
			tempHit = tempHit*2/3;
		if (attackTimes * (monster.getAttack() - Misaka.getDefensivePower()) >= tempHit) {
			new GetThingsMessage().RefuseToFight();		
			return;
		}
		if(id == 50)
			Misaka.setHitPoint(-Misaka.getHitPoint()/4);
		if(id == 57)
			Misaka.setHitPoint(-Misaka.getHitPoint()/3);
		battleBackground = new JLabel(new ImageIcon(Map.battleBgImg));
		monsterImg = new JLabel(new ImageIcon(GameCenter.imgSource.get(id)));

		battleLPane.setLayout(null);
		battleLPane.setBounds(27, GAME_PIX_72 * 2, 1242, 541);
		battleBackground.setBounds(0, 0, 1242, 541);
		battleLPane.setVisible(true);
		battleLPane.add(battleBackground, 1, 0);
		battleLPane.setOpaque(true);
		battleLPane.setVisible(false);

		int tmp = -50;
		monsterHitpoint.setBounds(400, 37 + tmp, 300, 300);
		monsterHitpoint.setFont(BATTLE_FONT);
		monsterHitpoint.setForeground(Color.WHITE);

		monsterAttack.setBounds(400, 157 + tmp, 300, 300);
		monsterAttack.setFont(BATTLE_FONT);
		monsterAttack.setForeground(Color.WHITE);

		monsterDefened.setBounds(400, 291 + tmp, 300, 300);
		monsterDefened.setFont(BATTLE_FONT);
		monsterDefened.setForeground(Color.WHITE);

		misakaHitpoint.setBounds(785, 37 + tmp, 300, 300);
		misakaHitpoint.setFont(BATTLE_FONT);
		misakaHitpoint.setForeground(Color.WHITE);

		misakaAttack.setBounds(785, 157 + tmp, 300, 300);
		misakaAttack.setFont(BATTLE_FONT);
		misakaAttack.setForeground(Color.WHITE);

		misakaDefened.setBounds(785, 291 + tmp, 300, 300);
		misakaDefened.setFont(BATTLE_FONT);
		misakaDefened.setForeground(Color.WHITE);

		battleLPane.add(monsterHitpoint, 2, 0);
		battleLPane.add(monsterAttack, 3, 0);
		battleLPane.add(monsterDefened, 4, 0);
		battleLPane.add(misakaHitpoint, 5, 0);
		battleLPane.add(misakaAttack, 6, 0);
		battleLPane.add(misakaDefened, 7, 0);

		monsterImg.setBounds(100, 120, 72, 72);
		battleLPane.add(monsterImg, 8, 0);

		monsterHitpoint.setText(monster.getHp() + "");
		monsterAttack.setText(monster.getAttack() + "");
		monsterDefened.setText(monster.getDefend() + "");

		misakaHitpoint.setText(GameCenter.Misaka.getHitPoint() + "");
		misakaAttack.setText(GameCenter.Misaka.getAttackPower() + "");
		misakaDefened.setText(GameCenter.Misaka.getDefensivePower() + "");

		battleLPane.setVisible(true);

		canSendMessage = false;

		GameCenter.inConversation = true;
		// System.out.println(attackTimes);
		int timeFrame = attackTimes <= 30 ? 400 : 20000 / attackTimes;
		new Timer(timeFrame, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				attack();
				// System.exit(0);

				monsterHitpoint.setText(HitPoint + "");
				misakaHitpoint.setText(GameCenter.Misaka.getHitPoint() + "");
				GameCenter.gameFrame.repaint();

				if (HitPoint <= 0) {

					battleLPane.setVisible(false);
					SendMessage.displayMessage("获得金币数 " + monster.getExp() + " 经验值 " + monster.getMoney() + " ！");

					GameCenter.inConversation = false;
					Misaka.setExperience(monster.getExp());
					Misaka.setCoin(monster.getMoney());
					battleLPane.remove(monsterImg);
					battleLPane.remove(monsterHitpoint);
					battleLPane.remove(monsterAttack);
					battleLPane.remove(monsterDefened);
					battleLPane.remove(misakaHitpoint);
					battleLPane.remove(misakaAttack);
					battleLPane.remove(misakaDefened);
					if(GameCenter.currentFloor == 21 && id == 59) {
						LevelMap[GameCenter.currentFloor][x][y] = 13;
						Misaka.setKillTheBossInTwentyOne(true);
					}else
						LevelMap[GameCenter.currentFloor][x][y] = 0;
					// GameCenter.Misaka.move(y, x);
					((Timer) ex.getSource()).stop();
				}
			}
		}).start();
		

		return;
	}

	private void attack() {
		HitPoint -= (Misaka.getAttackPower() - monster.getDefend());
		if (HitPoint <= 0) {
			return;
		}
		if (monster.getAttack() <= Misaka.getDefensivePower())
			return;
		Misaka.setHitPoint(-(monster.getAttack() - Misaka.getDefensivePower()));
	}

}
