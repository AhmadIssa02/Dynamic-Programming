package Assignment3;

public class LongestIncreasingSequence {
	
	public void printSeq (int [] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println("");
	}
	
	public void print (Pointer [] arr) {
		for(int i = 0; i<arr.length; i++) {
			System.out.print(i + " ");
			System.out.print("index = " + arr[i].getIndex() + " ");
			System.out.println("length = " + arr[i].getLength() + " ");
		}
		System.out.println("");
	}
	
	public int longestIncreasingSequence(int [] arr) {
		Pointer [] pointers = new Pointer [arr.length]; 
		for(int i = 0; i<arr.length; i++) { //filling the pointers array with -1 pointers and lengths of 1 
			Pointer p = new Pointer(-1,1);
			pointers [i] = p;
		}
		
		for(int i = arr.length; i >= 0;i--) { //I will loop through the array in reverse
			for(int j = i; j<arr.length; j++) { //checking every element with all the ones in front of it 
				if(arr[j] > arr[i]) { // if the two elements are in an increasing order 
					if(pointers[j].getLength() + 1 > pointers[i].getLength()) { //checking if the new sequence is longer
							pointers[i].setIndex(j);
							pointers[i].setLength(pointers[j].getLength()+1);
					}
				}
			}
		}
		print(pointers);
		int max = pointers[0].getLength();
		int index = 0;
		for(int i = 1; i < pointers.length; i++) { //getting the maximum length and the index of the element that starts the sequence
			if(pointers[i].getLength() > max) {
				max = pointers[i].getLength();
				index = 1;
			}
		}
		
		int [] seq = backtracking(pointers, arr, max, index);
		printSeq(seq);
		return max;
		
	}
	
	public int [] backtracking(Pointer [] pointers, int [] arr, int max, int index) { 
		// pointers is the pointers array, arr is the original array of numbers, max gives us the length of the sequence,
		//index is the index of the first element in the sequence in the original array
		int [] seq = new int [max];
		int i = index;
		int j = 0;
		while(i != -1) { //filling the array with the sequence
			seq[j] = arr[i];
			i = pointers[i].getIndex();
			j++;
		}
		return seq;
	}
	
	
	
	public static void main(String[]args) {
		LongestIncreasingSequence a = new LongestIncreasingSequence();
		int [] arr = {1,10,5,2,17,8,9,11};
		System.out.println("The longest increasing sequence has the length "  + a.longestIncreasingSequence(arr));
	}
}
