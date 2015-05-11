package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


class Score {
	String name;
	String classNumber;
	int mark;
}

public class ScoreStatistic {
	
	public Map<String,Integer> statistic(List<Score> scores){
		Map<String,Integer> mapscore = new HashMap<String,Integer>();
		Map<String,Integer> mapnum = new HashMap<String,Integer>();
		for(int i=0;i<scores.size();i++){
			Score s = scores.get(i);
			if(mapscore.get(s.classNumber) == null) mapscore.put(s.classNumber, 0);
			mapscore.put(s.classNumber, mapscore.get(s.classNumber)+s.mark);
			
			if(mapnum.get(s.classNumber) == null) mapnum.put(s.classNumber, 0);
			mapnum.put(s.classNumber, mapnum.get(s.classNumber).intValue()+1);
		}
		Iterator<String> itscore = mapscore.keySet().iterator();
		while(itscore.hasNext()){
			String s = itscore.next();
			mapscore.put(s, mapscore.get(s)/mapnum.get(s));
			System.out.println(s+"\t"+mapscore.get(s));
		}
		return mapscore;
	}
	
	public static void main(String[] args) {
		ScoreStatistic ss = new ScoreStatistic();
		Map<String,Integer> mapscore = new HashMap<String,Integer>();
		List<Score> scores = new ArrayList<Score>(10);
		Random r = new Random();
		
		for(int i=0;i<10;i++){
			Score s = new Score();
			s.name = "kiki" + i;
			s.classNumber = "¼Æ¿Æ120" + (r.nextInt(4)+1);
			s.mark = i*10;
			scores.add(s);
			System.out.println(s.name+"\t"+s.mark+"\t"+s.classNumber);
		}
		mapscore = ss.statistic(scores);

	}

}
