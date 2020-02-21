package mota;

public class Monster {
	 private int id;         // Deprecated
     private int hitPoint;         // 生命值
     private int attackPower;     // 攻击力
     private int defensivePower;     // 防御力
     private int Coin;      // 金钱
     private int Experience;        // 经验
     private String Name;    // 怪物名

     public Monster() {
		// TODO Auto-generated constructor stub
	}
     public Monster(int id, int hp, int attack, int defend, int money, int exp, String name) {
         this.id=id;
         this.hitPoint = hp;
         this.attackPower = attack;
         this.defensivePower = defend;
         this.Coin = money;
         this.Experience = exp;
         this.Name = name;
     }

     public int getHp() {
      	 return hitPoint;
     }

     public int getAttack() {
    	 return attackPower;
     }

     public int getDefend() {
         return defensivePower;
     }

     public int getMoney() {
    	 return Coin;
     }

     public int getExp() {
    	 return Experience;
     }

     public String getName() {
    	 return Name;
     }

	public int getId() {
		return id;
	}
	public void setHp(int hp) {
		this.hitPoint +=hp;
	}

}
