package datastructures.binomialHeap;
import java.util.Random;

public class BinomialLauncher {

	public static void main(String[] args){
		for(int i = 4; i<=64; i+=4) {
			System.out.println("===========Next Iteration with " + i + " nodes ==============");
			
			int[] inputNums = generateRandomArray(i);
			BinomialHeap h = BinomialHeap.makeBinaryHeap();
			System.out.println();
			System.out.print("Array: ");
			for(int j=0;j<i;j++) {
				System.out.print(inputNums[j] + " ");
				Node n = new Node(inputNums[j]);
				h.binomialHeapInsert(n);
			}
			System.out.println("");
			h.printHeap();
			
			System.out.println("Minimum : " + h.binomialHeapMinimum().key);
			
			System.out.println("Extracting Minimum : " + h.binomialHeapExtractMin().key);
			System.out.println();
			System.out.println("Multiple Degree Trees in BinHeap");
			h.printHeap();
			System.out.println("Delete all nodes");
			while(!h.isHeapEmpty()) {
				Node x = h.binomialHeapExtractMin();
				System.out.print(x.key + " ");
			}
			System.out.println();
		}
	}
				
	public static int[] generateRandomArray(int n)	{
	    int[] arr = new int[n];
	    Random random = new Random();
	    for (int i = 0; i < n; i++)
	    	arr[i] = random.nextInt(1000);
	    return arr;
	}
}
