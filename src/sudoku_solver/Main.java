package sudoku_solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		//String fileName = "2x2_sudoku.txt";
		//String fileName = "9x9_sudoku.txt";
		String fileName = "6x6_sudoku.txt";
		
		int[] sudSize = loadSudSize(fileName);
		int[][] sudArray = loadSudArray(fileName, sudSize);
		
		/*int[][] sudArray = {
				{0, 0, 0, 0, 0, 0, 0, 7, 1},
				{3, 0, 0, 0, 8, 0, 0, 5, 9},
				{0, 0, 0, 0, 2, 0, 8, 0, 0},
				{0, 0, 2, 0, 0, 0, 9, 1, 0},
				{0, 0, 0, 6, 0, 0, 0, 0, 3}, 
				{7, 0, 0, 0, 4, 0, 0, 0, 0},
				{0, 4, 0, 9, 0, 0, 0, 3, 2},
				{0, 5, 0, 0, 7, 0, 0, 4, 8},
				{0, 2, 0, 3, 0, 0, 6, 0, 0}
		};*/	
		int[][] Solution = Solver(sudArray, sudSize);
		
		for(int column = 0; column < sudSize[0]; column++) {
			if(column % sudSize[1] == 0){
				for(int row = 0; row < sudSize[0]+sudSize[2]+1; row++) {
					System.out.print("-");
				}
				System.out.println("");
			}
			for(int row = 0; row < sudSize[0]; row++) {
				if(row % sudSize[2] == 0){
					System.out.print("|");
				}
				System.out.print(Solution[column][row]);
				if(row == sudSize[0]-1){
					System.out.print("|");
				}
			}
			System.out.println("");
			if(column == sudSize[0]-1){
				for(int row = 0; row < sudSize[0]+sudSize[2]+1; row++) {
					System.out.print("-");
				}
			}
			
		}
	}
	
	public static  int[][] Solver(int sudSol[][], int[] sudSize) { 
		int addVal;
		int[] foundVal = new int[sudSize[1]*sudSize[2]];
		boolean solved = false;
		boolean error1 = true;
		
		while (solved==false){
			solved = true;
			error1 = true;
			for(int column = 0; column < sudSize[0]; column++) {
				for(int row = 0; row < sudSize[0]; row++) {
					if (sudSol[column][row]==0){
						solved = false;
						java.util.Arrays.fill(foundVal,0);
						
						foundVal = checkRow(column, sudSol, sudSize, foundVal);
						foundVal = checkColumn(row, sudSol, sudSize, foundVal);
						foundVal = checkBox(column, row, sudSize, sudSol, foundVal);
						addVal = checkVal(foundVal, sudSize);
						
						if(addVal > 0) {
							sudSol[column][row] = addVal;
							error1 = false;
						}	
					}							
				}
			}
			if(error1 == true  && solved == false){
				System.out.println("Can't solve (this code doesn't have a guessing method included, so it can't solve hard sudoku)");
				break;
			}	
		}
	return sudSol;	   
	}
		
	public static  int[] checkRow(int column, int sudVals[][], int[] sudSize, int[] foundVal) { 
		for(int i=0;i<sudSize[0];i++){
			if(sudVals[column][i]>0) {
				foundVal[sudVals[column][i]-1] = sudVals[column][i];	
			}	
		}
		return foundVal;	   
	}
	
	public static  int[] checkColumn(int row, int sudVals[][], int[] sudSize, int[] foundVal) { 
		for(int i=0;i<sudSize[0];i++){
			if(sudVals[i][row]>0) {
				foundVal[sudVals[i][row]-1] = sudVals[i][row];	
			}	
		}
		return foundVal;	   
	}
	
	public static  int[] checkBox(int column,int row, int[] sudSize, int sudVals[][], int[] foundVal) { 
		int columnPos = (int) Math.floor(column/sudSize[1])*sudSize[1];
		int rowPos = (int) Math.floor(row/sudSize[2])*sudSize[2];
		
		for(int i=columnPos; i<columnPos+sudSize[1]; i++){
			for(int j=rowPos; j<rowPos+sudSize[2]; j++){
				if(sudVals[i][j]>0) {
					foundVal[sudVals[i][j]-1] = sudVals[i][j];	
				}
			}	
		}
		return foundVal;	   
	}
	
	public static  int checkVal(int[] foundVal, int[] sudSize) {
		int addVal = 0;
		
		for (int i=0; i< sudSize[1]*sudSize[2]; i++){
			if (foundVal[i]==0) {
				if (addVal>0){
					addVal = 0;
					break;
				}
				else {
					addVal = i+1;
				}
			}
		}
		return addVal;	   
	}
	
	public static int[] loadSudSize(String fileName) {
		int[] sudSize = new int[3];
		Scanner sc1 = null;
		
		try {
			sc1 = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sudSize[0] = sc1.nextInt(); //number of columns/rows
		sudSize[1] = sc1.nextInt(); //number of column divisions
		sudSize[2] = sc1.nextInt(); //number of row divisions
		
		sc1.close();
		return sudSize;
	}

	private static int[][] loadSudArray(String fileName, int[] sudSize) {
		int[][] sudArray = new int[sudSize[0]][sudSize[0]];
		Scanner sc1 = null;
		
		try {
			sc1 = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sc1.nextInt(); //skip number of columns/row value
		sc1.nextInt(); //skip number of column divisions value
		sc1.nextInt(); //skip number of row divisions value
		
		for(int column=0; column < sudSize[0]; column++) {
			for(int row=0; row < sudSize[0]; row++) {
				sudArray[column][row] = sc1.nextInt();
			}
		}
		
		sc1.close();
		return sudArray;
	}
}