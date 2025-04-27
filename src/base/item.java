package base;

public class item{
    String name;
    int rarity;
	boolean event;
	int pity=0;
    public item(String name,boolean event,int rarity){
    	this.name=name;
    	this.event=event;
    	this.rarity=rarity;
    }
	public String getName() {
		return this.name;
	}
    public int getRarity() {
    	return this.rarity;
    }
	
}
