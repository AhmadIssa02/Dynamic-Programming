package Assignment3;

public class EditDistance {
	

	public void print2d (int [][] tab) {
		for(int i = 0; i<tab.length; i++) {
			for(int j = 0; j< tab[i].length; j++) {
				System.out.print(tab[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void printPointers (Pointer2 [][] pointers) {
		for(int i = 0; i<pointers.length; i++) {
			for(int j = 0; j< pointers[i].length; j++) {
				System.out.print(pointers[i][j].getI() + ",");
				System.out.print(pointers[i][j].getJ() + ", ");
			}
			System.out.println();
		}
	}
	
	public int editDistance(String [] a, String [] b) {
		int [] [] table = new int [a.length][b.length];
		Pointer2 [] [] pointers = new Pointer2[a.length][b.length];
		
		//initialize the tables;
		for(int i = 0; i< a.length; i++) {
			for(int j = 0; j<b.length; j++) {
				Pointer2 p = new Pointer2(0,0);
				pointers[i][j] = p;
			}
		}
		for(int j = 0; j<a.length; j++) {
			table[0][j] = j;
			pointers[0][j].setI(0);
			pointers[0][j].setJ(j-1);
		}
		
		for(int i = 0; i<b.length; i++) {
			table[i][0] = i;
			pointers[i][0].setI(i-1);
			pointers[i][0].setJ(0);
		}
		
		
		for(int i = 1; i<b.length; i++) {
			for(int j = 1; j < a.length; j++) {
				//filling the table and the pointers
				if(a[j].equals(b[i])) {
					table[i][j] = table [i-1][j-1];
					pointers[i][j].setI(i-1);
					pointers[i][j].setJ(j-1);
				}
				else {
					if(table[i-1][j] < table[i][j-1]) {
						if(table[i-1][j-1] < table[i-1][j]) {
							table[i][j] = table[i-1][j-1] + 1;
							pointers[i][j].setI(i-1);
							pointers[i][j].setJ(j-1);
						}
						else {
							table[i][j] = table[i-1][j] + 1;
							pointers[i][j].setI(i-1);
							pointers[i][j].setJ(j);
						}
					}
					else {
						if(table[i-1][j-1] < table[i][j-1]) {
							table[i][j] = table[i-1][j-1] + 1;
							pointers[i][j].setI(i-1);
							pointers[i][j].setJ(j-1);
						}
						else {
							table[i][j] = table[i][j-1] + 1;
							pointers[i][j].setI(i);
							pointers[i][j].setJ(j-1);
						}
					}
				}
			}
		}
		System.out.println("The pointers table is as follows");
		printPointers(pointers);
		
		System.out.println("\nThe dp table is as follows");
		print2d(table);
		
		System.out.print("\nThe number of edits needed is: ");
		return(table[a.length-1][b.length-1]);
		
	}
	
	
	public static void main(String [] args) {
		EditDistance x = new EditDistance();
		String a = " HELLO";
		String b = " ELLEN";
		String[] alist = a.split("");
		String [] blist = b.split("");
		System.out.println(x.editDistance(alist, blist));
		
		
		
	}
	
}
