package base;

import java.util.ArrayList;

public class itembracket {
	int rarity; 
	ArrayList<item> items;
	double probability;
	
	String rarity_name;
	int guarantee_num;
	int start_guarantee;
	boolean guarantee;
	itembracket(String rarity_name,int rarity,double prob){
		this.rarity=rarity;
		this.probability=prob;
		this.items=new ArrayList<item>();
		this.guarantee=false;
		this.rarity_name=rarity_name;
}
	itembracket(String rarity_name,int rarity,double prob, int guarantee_num, int start_guarantee){
		this.rarity=rarity;
		this.probability=prob;
		this.items=new ArrayList<item>();
		this.guarantee_num=guarantee_num;
		this.start_guarantee=start_guarantee;
		this.guarantee=true;
		this.rarity_name=rarity_name;
}   
	public String getName() {
		return this.rarity_name;
	}
	public  void add(item it) {
		 items.add(it);
		
	}
	public boolean getGuarantee() {
		return this.guarantee;
	}
	public int getceiling() {
		return guarantee_num;
	}
	public int getstart() {
		return start_guarantee;
	}
	public  double[] gurantee_adjustment(int drawcount,double[] prob,int index){
		if(drawcount>=start_guarantee-1&&guarantee) {
			double temp_newprob=100*(drawcount-this.start_guarantee+2)/(this.guarantee_num-this.start_guarantee+1);
		
			for(int i=0;i<=prob.length-1;i++) {
				if(i==index) {prob[i]=temp_newprob;
					continue;}
				prob[i]=(100-temp_newprob)*prob[i]/100;
			}
		}
		return prob;
	}
   public item pick(int max_rarity,int pity) {
	int pick_index=(int)(Math.random()*(items.size()));
   if(rarity==max_rarity&&items.get(pick_index).event) {
		   items.get(pick_index).pity=0;
		   return items.get(pick_index);
	   }else {
		   for(int i=0;i<items.size();i++) {
			   if(items.get(i).event&&items.get(i).pity>=pity) {
				   items.get(i).pity=0;  return items.get(i); }
		   }
		 double ptemp=Math.random();
		 if(ptemp>0.5) {
			  for(int i=0;i<items.size();i++) {
				   if(items.get(i).event) {
					   items.get(i).pity=0;  return items.get(i); }
		 }
	   }
	 return items.get(pick_index);
   }}
    public String print_itembracket() {
    	StringBuilder temp=new StringBuilder();
    	temp.append("----rarity name: "+this.rarity_name+" --rarity level: "+Integer.toString(this.rarity)+"\n");
    	temp.append("probability: "+Double.toString(this.probability)+"\n");
    	if(guarantee) {temp.append("guarantee ceiling at "+Integer.toString(this.guarantee_num));
    	temp.append(" starting from "+Integer.toString(this.start_guarantee)+"\n");}
    	temp.append("Item list from this rarity level: "+"\n");
    	for(int i=0;i<items.size();i++) {
    		temp.append(this.items.get(i).getName()+"\n");
    	}
    	return temp.toString();
    }
}