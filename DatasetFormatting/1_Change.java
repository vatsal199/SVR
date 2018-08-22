import java.util.*;
import java.io.*;
// xlxs to csv https://convertio.co/
//(1) convert all noise into ? and convert to usable CSV formate
public class Change{
	
	public static void main(String args[]){
		//----------Declaration----------------
		String input = "FileName.csv";
		String output = "FileName1.csv";
		//-------------------------------------
		try{
		FileWriter fw = new FileWriter(new File(output));
		Scanner scn = new Scanner(new File(input));
		String temp = scn.nextLine();
		fw.write(temp+"\n");
		while(scn.hasNext()){
			String data[] = scn.nextLine().split(",");
			for(int i=0;i<data.length;i++){
				//Add all noise that you want to remove with OR condition
				if(data[i].length() == 2 || data[i].equals("\"********\"") || 
					data[i].equals("\"#NAME?\"")|| data[i].equals("\"inf\""))
					data[i] = "?";
				else{
					String[] temp1 = data[i].split("\"");
					data[i] = temp1[1];
				}
				if(i==0)
					fw.write(data[i]);
				else
					fw.write(","+data[i]);			
			}
			fw.write("\n");	
		} 
		fw.close();
		scn.close();
	
	}catch(Exception e){
			
			System.out.println(e);
			}
}
}