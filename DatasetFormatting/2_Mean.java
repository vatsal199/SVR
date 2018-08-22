import java.util.*;
import java.io.*;
//(2) replace ? with Mean,Median or Mode
public class Mean{
	
	//----------Declaration----------------
	static String input = "FileName1.csv";
	static String output = "FileName2.csv";
	static int noofCols = 33; //no of column available in dataset
	//-------------------------------------
	
	public static void main(String args[]) throws Exception{
		
		
		try{
		int type[] = new int[noofCols+2];
		checkType(type);
		double typeMean[] =  new double[noofCols+2];
		for(int i=0;i<typeMean.length;i++)
			typeMean[i]=0;
			
		FileWriter fw = new FileWriter(new File(output));
		Scanner scn = new Scanner(new File(input));	
		String temp = scn.nextLine();
		String header[] = temp.split(",");
		fw.write(temp+"\n");
		for(int i=0;i<header.length;i++){
			if(type[i] == 2)
				typeMean[i] = findMean(i);
				//typeMean[i] =findMedian(i);
				//typeMean[i]=findMode(i);
		}
		while(scn.hasNext()){
			//normalized-losses
			String data[] = scn.nextLine().split(",");
			for(int i=0;i<data.length;i++){
				if(data[i].equals("?"))	{
						if(type[i] == 2){
								data[i] = Double.toString(typeMean[i]);
						}			
				}
				if(i==0)
					fw.write(data[i]);
				else
					fw.write(","+data[i]);			
			}
			fw.write("\n");	
		} 
		fw.close();
			
			
		}catch(Exception e){
			
			System.out.println(e);
			}
		
		
	}
	public static void checkType(int type[]) throws Exception{
		int count = noofCols;
		for(int i=0;i<count;i++){
			type[i] = 0;
		}
		Scanner scn = new Scanner(new File(input));
		String temp = scn.nextLine();
		while(scn.hasNext()){
			String line[] = scn.nextLine().split(",");
			for(int i=0;i<line.length;i++){
				if(line[i].equals("?")){
					continue;
				}
				if(isNumeric(line[i])){
						type[i] = 2;
						count--;
				}else{
					type[i] = 1;
					count--;
				}
			}
			if(count == 0)
				break;
		}
		scn.close();
	}
	public static boolean isNumeric(String s) {  
		return s != null &&s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	public static double findMean(int index) throws Exception{
		Scanner scn = new Scanner(new File(input));
		String temp = scn.nextLine();
		int count=0;
		double sum=0;
		while(scn.hasNext()){
				String line[] = scn.nextLine().split(",");
				if(line[index].equals("?"))
					continue;
				
				sum+=Double.parseDouble(line[index]);		
				count++;
		}
			return sum/count;
	}
	
	public static double findMedian(int index) throws Exception{
		Scanner scn = new Scanner(new File(input));
		String temp = scn.nextLine();
		int count=0;
		List<Double>listNumbers = new ArrayList<Double>();
		while(scn.hasNext()){
				String line[] = scn.nextLine().split(",");
				if(line[index].equals("?"))
					continue;
				
				listNumbers.add(Double.parseDouble(line[index]));		
				count++;
		}
		Collections.sort(listNumbers,Collections.reverseOrder());

		if((count%2)==0)
			return listNumbers.get((count+1)/2);
			else
			return listNumbers.get(count/2);
	}
	public static double findMode(int index) throws Exception{
		
		Scanner scn = new Scanner(new File(input));
		String temp = scn.nextLine();

	HashMap<Double, Integer>hm = new HashMap<Double, Integer>();    
		while(scn.hasNext()){
				String line[] = scn.nextLine().split(",");
				if(line[index].equals("?"))
					continue;
			Double tp=Double.parseDouble(line[index]);
			if(hm.containsKey(tp))	
			{
				int value=hm.get(tp);
				value++;
				hm.put(tp,value);			
			}
			else{
				hm.put(tp,0);
			}
			
			
		}
		Integer max=	0;	
		Double key=0.0;	
		for(Map.Entry m:hm.entrySet()){  
		int value=(int)m.getValue();
		if(value>max)
			{
					max=value;
					key=(Double)m.getKey();	
						}
					
				}
				System.out.println(key);
		return key;
		
		
		}
		
		}
