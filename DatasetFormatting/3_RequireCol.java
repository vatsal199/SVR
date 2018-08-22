import java.io.*;
import java.util.*;
//(3)  Separate require column from dataset
public class RequireCol{
	
	public static void main(String args[]){
		
		//----------Declaration----------------
		String input = "FileName2.csv";
		String output = "FileName3.csv";
		//add column index that we want to keep in final dataset
		int require[]={10,11,15,17,21,23,25,26,33};
		int NoofCols = 33; //total no of column available in dataset
		//-------------------------------------
		
		boolean requireFlag[] = new boolean[NoofCols];
		int count=0;
		for(int i=0;i<NoofCols;i++){
			if(i == require[count]-1 && count<require.length){
				requireFlag[i] = true;
				count++;
			}
			else
				requireFlag[i] = false;
		}
		try{
		Scanner scn = new Scanner(new File(input));
		FileWriter fw = new FileWriter(new File(output));
			while(scn.hasNext()){
				String line[] = scn.nextLine().split(",");
				boolean flag = false;
				for(int i=0;i<line.length;i++){
					if(requireFlag[i] == false)
						continue;
					else{
						if(flag == false){
							fw.write(line[i]);
							flag = true;
						}
						else
							fw.write(","+line[i]);
					}	
				}
				fw.write("\n");
				
			}
			scn.close();
			fw.close();
		}catch(Exception e){
			
			System.out.println(e);
			}
		
	}
}