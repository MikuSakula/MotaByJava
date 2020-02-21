package mota;
import java.io.*;
import static mota.Map.*;
import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Character {
	private int nowLevel;
	private int Floor;
	private int hitPoint;
	private int attackPower;
	private int defensivePower;
	private int Coin ;
	private int Experience;
	private int yellowKey=10;
	private int blueKey=10;
	private int redKey=10;
	private int posX;
	private int posY;
	private int toward;
	
	private boolean hasTen = true;  // 十字架
	private boolean hasPick = false;  // 星光神琅
	private boolean canOpenTheLockedDoor = false; // 是否能打开二楼的门
	private int changeTheThiefTimes = 1;  // 第几次见小偷（剧情触发）
	private boolean canUseFlyingFortress = true;  // 是否能使用飞行堡垒
	private boolean canUseHolyLightBook   = true;  // 能否使用圣光徽
	private boolean needSayToThief = true;  // 是否能跟小偷说话
	private boolean isMeetTheOldMan = false;  // 是否见过神仙老头（触发剧情）
	private boolean isKillTheBossInTwentyOne = false;
	private boolean isGiveTheTen = false;

	public Character() {	  // 英雄
		this.nowLevel = 1;
		this.hitPoint = 1000;
		this.attackPower = 10000;
		this.defensivePower = 10000;
		this.Coin = 0;
		this.posX = 5;
		this.posY = 9;
		this.toward = 1;
	}
	public void move(int dx,int dy) {
		posX = dx;
		posY = dy;
	}
	public BufferedImage draw() {
        if (toward == 0)
            return Map.playerMap.get(-1);
        if (toward == 1)
            return Map.playerMap.get(-2);
        if (toward == 2)
            return Map.playerMap.get(-3);
        if (toward == 3)
            return Map.playerMap.get(-4);
        return null;
    }
	public  int getFloor() {
		return Floor;
	}
	public  void setFloor(int floor) {
		Floor += floor;
	}
	public  int getHitPoint() {
		return hitPoint;
	}
	public  void setHitPoint(int hitPoint) {
		this.hitPoint += hitPoint;
	}
	public  int getAttackPower() {
		return attackPower;
	}
	public  void setAttackPower(int attackPower) {
		this.attackPower += attackPower;
	}
	public  int getDefensivePower() {
		return defensivePower;
	}
	public void setDefensivePower(int defensivePower) {
		this.defensivePower += defensivePower;
	}
	public int getCoin() {
		return Coin;
	}
	public void setCoin(int coin) {
		Coin += coin;
	}
	public int getExperience() {
		return Experience;
	}
	public void setExperience(int experience) {
		Experience += experience;
	}
	public int getYellowKey() {
		return yellowKey;
	}
	public void setYellowKey(int yellowKey) {
		this.yellowKey += yellowKey;
	}
	public int getBlueKey() {
		return blueKey;
	}
	public void setBlueKey(int blueKey) {
		this.blueKey += blueKey;
	}
	public int getRedKey() {
		return redKey;
	}
	public void setRedKey(int redKey) {
		this.redKey += redKey;
	}
	public int getNowLevel() {
		return nowLevel;
	}
	public  void setNowLevel(int nowLevel) {
		this.nowLevel += nowLevel;
		this.hitPoint += 1000*nowLevel;
		this.attackPower += 5*nowLevel;
		this.defensivePower += 5*nowLevel;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getToword() {
		return toward;
	}
	public void setToword(int toword) {
		this.toward = toword;
	}
	
	public boolean canDown() {
		if(posY + 1 < 11 && posY + 1 >= 0) {
			return true;
		}
		return false;
	}
	public boolean canUp() {
		if(posY - 1 < 11 && posY - 1 >= 0) {
			return true;
		}
		return false;			
	}
	public boolean canLeft() {
		if(posX - 1 < 11 && posX - 1 >= 0) {
			return true;
		}
		return false;		
	}
	public boolean canRight() {
		if(posX + 1 < 11 && posX + 1 >= 0) {
			return true;
		}
		return false;		
	}
	public boolean isHasTen() {
		return hasTen;
	}
	public void setHasTen(boolean hasTen) {
		this.hasTen = hasTen;
	}
	public boolean isHasPick() {
		return hasPick;
	}
	public void setHasPick(boolean hasPick) {
		this.hasPick = hasPick;
	}
	public boolean isCanOpenTheLockedDoor() {
		return canOpenTheLockedDoor;
	}
	public void setCanOpenTheLockedDoor(boolean canOpenTheLockedDoor) {
		this.canOpenTheLockedDoor = canOpenTheLockedDoor;
	}
	public int getChangeTheThiefTimes() {
		return changeTheThiefTimes;
	}
	public void setChangeTheThiefTimes(int changeTheThiefTimes) {
		this.changeTheThiefTimes = changeTheThiefTimes;
	}
	public boolean isCanUseFlyingFortress() {
		return canUseFlyingFortress;
	}
	public void setCanUseFlyingFortress(boolean canUseFlyingFortress) {
		this.canUseFlyingFortress = canUseFlyingFortress;
	}
	public boolean isNeedSayToThief() {
		return needSayToThief;
	}
	public void setNeedSayToThief(boolean needSayToThief) {
		this.needSayToThief = needSayToThief;
	}
	public boolean isMeetTheOldMan() {
		return isMeetTheOldMan;
	}
	public void setMeetTheOldMan(boolean isMeetTheOldMan) {
		this.isMeetTheOldMan = isMeetTheOldMan;
	}
	public boolean isKillTheBossInTwentyOne() {
		return isKillTheBossInTwentyOne;
	}
	public void setKillTheBossInTwentyOne(boolean isKillTheBossInTwentyOne) {
		this.isKillTheBossInTwentyOne = isKillTheBossInTwentyOne;
	}
	public boolean isGiveTheTen() {
		return isGiveTheTen;
	}
	public void setGiveTheTen(boolean isGiveTheTen) {
		this.isGiveTheTen = isGiveTheTen;
	}
	public boolean isCanUseHolyLightBook() {
		return canUseHolyLightBook;
	}
	public void setCanUseHolyLightBook(boolean canUseHolyLightBook) {
		this.canUseHolyLightBook = canUseHolyLightBook;
	}
	
	


}
