import java.util.*;
import java.io.*;
//(5)  Normalize dataset in 0 to 1
public class Normalize{
	public static void main(String args[]){
		
		//----------Declaration----------------
		String input = "FileName.csv";
		String output = "FileName1.csv";
		//Column index that we want to skip from normalization process
		double skip[] = {};
		
		//minimum value column wise
		double minArray[] = {-80,-83,-98,-193,-8367,0,0,-832,-814};
		//maximum value column wise
		double maxArray[] = {119,160,98,203,0,47659,1001,82241,5935};
		//-------------------------------------
		
		double subArray[] = new double[minArray.length];
		boolean requireFlag[] = new boolean[minArray.length];
		int count=0;
		for(int i=0;i<minArray.length;i++){
			if(skip.length>0 && i == skip[count]-1 && count<skip.length ){
				requireFlag[i] = true;
				count++;
			}
			else
				requireFlag[i] = false;
		}
		for(int i=0;i<minArray.length;i++)
			subArray[i] =maxArray[i] - minArray[i];
		try{
			Scanner scn = new Scanner(new File(input));
			FileWriter fw = new FileWriter(new File(output));
			String temp = scn.nextLine();
			fw.write(temp+"\n");
			while(scn.hasNext()){
				String data[] = scn.nextLine().split(",");
				for(int i=0;i<data.length;i++){
					if(requireFlag[i] == false)
						data[i] = Double.toString((Double.parseDouble(data[i]) - minArray[i])/subArray[i]);
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