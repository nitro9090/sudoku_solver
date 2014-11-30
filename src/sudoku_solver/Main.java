package sudoku_solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String args[]) {
		int SudokuSize = 9;
		
		int[][] SudokuArray = {
				{0, 0, 0, 0, 0, 0, 0, 7, 1},
				{3, 0, 0, 0, 8, 0, 0, 5, 9},
				{0, 0, 0, 0, 2, 0, 8, 0, 0},
				{0, 0, 2, 0, 0, 0, 9, 1, 0},
				{0, 0, 0, 6, 0, 0, 0, 0, 3}, 
				{7, 0, 0, 0, 4, 0, 0, 0, 0},
				{0, 4, 0, 9, 0, 0, 0, 3, 2},
				{0, 5, 0, 0, 7, 0, 0, 4, 8},
				{0, 2, 0, 3, 0, 0, 6, 0, 0}
		};	
		int[][] Solution = Solver(SudokuArray, SudokuSize);
	}
	public static  int[][] Solver(int SudokuVals[][], int size) { 
		
		boolean Solved = false;
		int addVal;
		int[] foundVal = new int[size];
		boolean Error1 = false;
		
		while (Solved==false){
			Solved = true;
			Error1 = true;
			for(int horiz=0; horiz<size; horiz++) {
				for(int vert=0; vert<size; vert++) {
					if (SudokuVals[horiz][vert]==0){
						Solved = false;
						java.util.Arrays.fill(foundVal,0);
						foundVal = checkVert(horiz, SudokuVals, size, foundVal);
						foundVal = checkHoriz(vert, SudokuVals, size, foundVal);
						foundVal = checkBox(horiz, vert, SudokuVals, size, foundVal);
						addVal = checkVal(foundVal, size);
						if(addVal>0) {
							SudokuVals[horiz][vert] = addVal;
							System.out.println(addVal + " "+ horiz +" "+ vert);
							Error1 = false;
						}	
					}							
				}
			}
			if(Error1 == true){
				Solved = true;
				System.out.println("No solution found");
			}	
		}
	return SudokuVals;	   
	}
		
	public static  int[] checkVert(int horiz, int SudokuVals[][], int size, int[] foundVal) { 
		for(int i=0;i<size;i++){
			if(SudokuVals[horiz][i]>0) {
				foundVal[SudokuVals[horiz][i]-1] = SudokuVals[horiz][i];	
			}	
		}
		return foundVal;	   
	}
	
	public static  int[] checkHoriz(int vert, int SudokuVals[][], int size, int[] foundVal) { 
		for(int i=0;i<size;i++){
			if(SudokuVals[i][vert]>0) {
				foundVal[SudokuVals[i][vert]-1] = SudokuVals[i][vert];	
			}	
		}
		return foundVal;	   
	}
	
	public static  int[] checkBox(int horiz,int vert, int SudokuVals[][], int size, int[] foundVal) { 
		int div = 3;
		int horizPos = (int) Math.floor(horiz/3)*div;
		int vertPos = (int) Math.floor(vert/3)*div;
		
		for(int i=horizPos; i<horizPos+div; i++){
			for(int j=vertPos; j<vertPos+div; j++){
				if(SudokuVals[i][j]>0) {
					foundVal[SudokuVals[i][j]-1] = SudokuVals[i][j];	
				}
			}	
		}
		return foundVal;	   
	}
	
	public static  int checkVal(int[] foundVal, int size) {
		int addVal = 0;
		
		for (int i=0; i< size; i++){
			if (foundVal[i]==0) {
				if (addVal>0){
					i = size+1;
					addVal = 0;
				}
				else {
					addVal = i+1;
				}
			}
		}
		return addVal;	   
	}
}

/*String file_name = "SudokuFile.txt";

try {
	   ReadFile file = new ReadFile(file_name);
	   String[] aryLines = file.OpenFile();
}
catch (IOException e) {
	   System.out.println(e.getMessage() );
}
	   File file = new File("SudokuFile.txt");
Scanner SudokuFile;
try {
	SudokuFile = new Scanner(file);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	System.out.println("This sucks");
	e.printStackTrace();
}
List<Float> temps = new ArrayList<Float>();
while(SudokuFile.hasNext()){
	   float Values = SudokuFile.nextFloat();
	   temps.add(Values);
}
SudokuFile.close();

Float[] tempsArray = temps.toArray(new Float[0]);

for(Float s: tempsArray){
	   System.out.println(s);
}

}
public class ReadFile {
private String path;

public ReadFile(string file_path) {
	   path = file_path;
}

public String[] OpentFile() throws IOException {
	   FileReader fr = new FileReader(path);
	   BufferedReader textReader = new BufferedReader(fr);

	   int numberOfLines = readLines();
	   String[] textData = new String[numberOfLines];

	   int i;
	   for (i=0; i<numberOfLines; i++) {
		   textData[i] = textReader.readLine();
	   }
	   textReader.close();
	   return textData;
}

int readLines() throws IOException {
	   FileReader file_to_read = new FileReader(path);
	   BufferedReader bf = new BufferedReader(file_to_read);

	   String aLine;
	   int numberOfLines = 0;

	   while((aline = bf.readLine())!=nuull){
		   numberOfLines++;
	   }
}*/

