import java.util.*;
import java.io.*;
//(4)  Separate Training and testing dataset
public class DatasetSeparator{
	
	public static void main(String args[]){
		
		//----------Declaration----------------
		String input = "FileName3.csv";
		String output_train = "FileName_training.csv";
		String output_test = "FileName_testing.csv";
		//no of tuples available that you want in training dataset from dataset
		int training_count = 21602;
		//int training_count = 76;
		//-------------------------------------
		
		try{
			FileWriter fw_train = new FileWriter(new File(output_train));
			FileWriter fw_test = new FileWriter(new File(output_test));
			Scanner scn = new Scanner(new File(input));
			String header = scn.nextLine();			
			fw_train.write(header+"\n");
			fw_test.write(header+"\n");
			while(scn.hasNext()){
				if(training_count != 0){
					fw_train.write(scn.nextLine()+"\n");
					training_count--;
				}else{
					fw_test.write(scn.nextLine()+"\n");
					//break;
				}
			}
			fw_train.close();
			fw_test.close();
			scn.close();
		}
		catch(Exception e){			
			System.out.println(e);
		}
		
	}
}