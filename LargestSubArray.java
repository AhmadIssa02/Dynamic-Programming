package Assignment3;
import java.util.*;
public class LargestSubArray {
	
	public int max(int a, int b) {
		if(a>=b) {
			return a;
		}
		else {
			return b;
		}
	}
	
	public void print (int [] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
	
	public void print2d (int [][] tab) {
		for(int i = 0; i<tab.length; i++) {
			for(int j = 0; j< tab[i].length; j++) {
				System.out.print(tab[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void printPointers (int [][][] tab) {
		for(int i = 0; i<tab.length; i++) {
			for(int j = 0; j< tab[i].length; j++) {
				for(int k = 0; k< 2; k++ ) {
					System.out.print(tab[i][j][k] + ",");
				}
				System.out.print("  ");
			}
			System.out.println();
		}
	}
	
	
	public int sum(int [] arr, int i, int j) { // returns the sum of the previous elements
		int sum = 0;
		for(int k = j-i; k<=j; k++) {
			sum = sum + arr[k];
		}
		return sum;
	}
	
	public int[][] largestSubArray(int [] arr) {
		int [] [] table = new int [arr.length] [arr.length]; //the dp table 
		int [] [] [] pointers = new int [arr.length][arr.length][2]; // the pointers in every element
		for(int i = 0; i< arr.length; i++) {
			for(int j = 0; j<arr.length ; j++) {
				if (i == 0) {
					if(j ==0) { //the first element of the table 
						table[i][j] = arr[j];
						pointers[i][j][0] = -1;
						pointers[i][j][1] = -1;
					}
					else { // the rest of the first row
						table [i][j] = Math.max(table[i][j-1], arr[j]); 	
						if(max(table[i][j-1], arr[j]) == arr[j]) {// if the element is greater than the previous one
								pointers[i][j][0] = -1;
								pointers[i][j][1] = j;
						}
						else { //if the previous element is the greater one 
							pointers[i][j][0] = -1;
							pointers[i][j][1] = j-1;
						}
					}
				}
				else if(i > j) { //whenever the row ( which indicates the sequence of numbers) is greater than the numbers that we can possibly add together
					table[i][j] = table[i-1][j];
					pointers[i][j][0] = i-1;
					pointers[i][j][1] = j;
				}
				else {
					// we compare the element in the previous row and the one in the previous colomn and choose the largest
					int a = max(table[i-1][j],table[i][j-1]);
					// compare the sequence of i elements with the maximum of the previous 2 
					int b = max(sum(arr,i,j), a);
					table[i][j] = b;
					//filling the pointers accordingly 
					if( b == table[i][j-1]) {
						pointers[i][j][0] = i;
						pointers[i][j][1] = j-1;
						
					}
					else if(b == table[i-1][j]) {
						pointers[i][j][0] = i-1;
						pointers[i][j][1] = j;
						
					}
					else {
						pointers[i][j][0] = i-1;
						pointers[i][j][1] = j-1;
						
					}

				}
			}
		}
		System.out.println("The pointers array is as follows");
		printPointers(pointers);
		
		int [] subArr = backtracking(table, pointers, arr);
		System.out.println("The largest subarray sequence is ");
		print(subArr);
		
		int x = 0;
		for(int i = 0; i<subArr.length; i++) {
			x = x + subArr[i];
		}
		System.out.println("The largest sum = " + x);
		
		return table;
	}
	
	public int [] backtracking(int [][] table, int [][][]pointers, int [] num) {		
		//getting the index of the last element in order to follow the pointers
		int row = table.length-1;
		int colomn = table[row].length-1; 
		int i = pointers[row][colomn][0];
		int j = pointers[row][colomn][1];
			
		int [] arr = new int[4];
		int k = 0;
		
		while(i != -1) {
			//looking for the last element in the sequence
			int oldi = i;
			int oldj = j;
			i = pointers[i][j][0];
			j = pointers[oldi][j][1];
			if(pointers[oldi][oldj][0] == pointers[i][j][0]+1 && pointers[oldi][oldj][1] == pointers[i][j][1]+1) {
				//the first time we moved one up and one left that means that that is where the sequence ends
				while(i>0) {
					//fill the array with the sequence (it will be in reverse)
					oldi = i;
					arr[k] = num[j];
					i = pointers[i][j][0];
					j = pointers[oldi][j][1];
					
					k++;
				}
				break;
			}
			
		}
		int [] reversedArr = new int [4];
		int x=0;
		for(int l = 3; l>=0; l--) {//reverse the array to get the sequence in the correct order
			reversedArr[x] = arr[l];
			x++;
		}
		return reversedArr;
	}
	
	public static void main(String [] args) {
		LargestSubArray a = new LargestSubArray();
		int [] num = {-2, 1, -3, 4, -1 , 2, 1, -5, 4};
		int [] [] table = a.largestSubArray(num);
		System.out.println("the dynamic programming table looks like this");
		a.print2d(table);
		
	}
}
