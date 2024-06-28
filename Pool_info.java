package base;

import java.util.ArrayList;


public class Pool_info {
	itembracket[] bracket;
	int max_rarity;
    String name;
	private int draw_count[];
	private int totalcount;
	public int gettotalcount() {
		return totalcount;}
	public void settotalcount(int t) {
		this.totalcount=t;}
public Pool_info(int max_rarity,String name) throws Exception {
	this.max_rarity=max_rarity;
	this.name=name;
	this.bracket=new itembracket[max_rarity];
	this.draw_count=new int[max_rarity];
}
public int get_maxrarity() {
	return this.max_rarity;
}
public itembracket[] getAllbrackets(){
	return this.bracket;
	
}
public static Pool_info convert_pool(String p) throws Exception {
	String[] temp_1=p.split("####");
	String[] basic=temp_1[0].split("##");
	Pool_info poo=new Pool_info(Integer.parseInt(basic[1]),basic[0]);
	poo.input_info(temp_1[1].split("###"));
    return poo;
}
public void setDrawcount(int[] draw_count) {
	this.draw_count=draw_count;
}public int[] getDrawcount() {
	return this.draw_count;
}
public void input_info(String[] item_str) throws Exception {
	 for(int i=0; i<item_str.length;i++) {
		 String[] temp=item_str[i].split("\n");
		String[] bracket_info= temp[0].split("#");
		 if(bracket_info.length==3) {
			this.bracket[Integer.parseInt(bracket_info[0])-1]=new itembracket(bracket_info[2],Integer.parseInt(bracket_info[0]),Double.parseDouble(bracket_info[1]));
		 }	 if(bracket_info.length==5) {
				this.bracket[Integer.parseInt(bracket_info[0])-1]=new itembracket(bracket_info[4],Integer.parseInt(bracket_info[0]),Double.parseDouble(bracket_info[1]),Integer.parseInt(bracket_info[2]),Integer.parseInt(bracket_info[3]));
			 }else if(bracket_info.length!=3&&bracket_info.length!=5) {
				throw new InvalidBracketInfoException();
			 }
		 for(int j=1;j<temp.length;j++) {
			if(temp[j].endsWith("#event")) {bracket[Integer.parseInt(bracket_info[0])-1].add(new item(temp[j].split("#event")[0],true,Integer.parseInt(bracket_info[0])));
			continue;}
			this.bracket[Integer.parseInt(bracket_info[0])-1].add(new item(temp[j],false,Integer.parseInt(bracket_info[0])));
		 }
		 
	 }

}
public item pool() {
	itembracket bra=new itembracket("",0,0);
    double[] temp_prob=new double[max_rarity];
    for(int i=0;i<max_rarity;i++) {
    	temp_prob[i]=bracket[i].probability;
    }
    for(int i=max_rarity-1;i>=0;i--) {
    	temp_prob=bracket[i].gurantee_adjustment(draw_count[i], temp_prob, i);
    }
    double[] temp_cul=new double[max_rarity+1];
    temp_cul[0]=0;
    temp_cul[max_rarity]=100;
    for(int i=0;i<max_rarity-1;i++) {
    	temp_cul[i+1]=temp_cul[i]+temp_prob[i];
    }
    double p=Math.random()*100;
    for(int i=1;i<max_rarity+1;i++) {
     if(p>temp_cul[i-1]&& p<temp_cul[i]) { bra=bracket[i-1]; draw_count[i-1]=0; continue;} 
     draw_count[i-1]+=1;
    }
    this.totalcount+=1;
	return bra.pick(max_rarity, 1);
}
@Override
public String toString() {
	StringBuilder temp=new StringBuilder();
	temp.append("Gotcha pool name: "+this.name+"\n");
	temp.append("Number of level of rarity:"+Integer.toString(this.max_rarity)+"\n");
	for(int i=0;i<bracket.length;i++) {
		temp.append(bracket[i].print_itembracket());
	}
	return temp.toString();
}
}
