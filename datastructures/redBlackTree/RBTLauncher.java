package datastructures.redBlackTree;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class RBTLauncher {
	public static void main(String[] args) throws FileNotFoundException {
			int[] inputNums = generateRandomArray(10);
			RBT t = new RBT();
			for(int i=0;i<inputNums.length;i++) {
				t.insert(new Node(inputNums[i]));
			}
			printLevelOrder(t.getRoot());
			
			@SuppressWarnings("resource")
			Scanner s = new Scanner(System.in);
			String option = "";
			System.out.println("Type one of the options from the menu below. x denotes is the integer valued key of a node");
			System.out.println("insert x");
			System.out.println("sort");
			System.out.println("search x");
			System.out.println("delete x");
			System.out.println("predecessor x");
			System.out.println("successor x");
			System.out.println("exit");
			while(true) {
				System.out.println("Enter next Input");
				String input = s.nextLine();
				option = input.split(" ")[0];
				if(option.trim().equals("search")) 	{
					int x = Integer.parseInt(input.split(" ")[1]);
					t.iterativeTreeSearch(t.getRoot(), x);
				}
				else if (option.trim().equals("insert"))	{
					int x = Integer.parseInt(input.split(" ")[1]);
					t.insert(new Node(x));
				}
				else if(option.trim().equals("sort"))	{
					t.sort(t.getRoot());
				}
				else if(option.trim().equals("delete"))	{
					int x = Integer.parseInt(input.split(" ")[1]);
					t.delete(t.iterativeTreeSearch(t.getRoot(), x));
				}
				else if(option.trim().equals("predecessor"))	{
					int x = Integer.parseInt(input.split(" ")[1]);
					System.out.println("predecessor : " + t.treePredecessor(t.iterativeTreeSearch(t.getRoot(), x)).getKey());
				}
				else if(option.trim().equals("successor"))	{
					int x = Integer.parseInt(input.split(" ")[1]);
					System.out.println("successor : " + t.treeSuccessor(t.iterativeTreeSearch(t.getRoot(), x)).getKey());
				}
				else if(option.trim().equals("exit")) {
					System.exit(0);
				}
				System.out.println("Root: " + t.getRoot().getKey());
				System.out.println("Depth : " + t.maxDepth(t.getRoot()));
				printLevelOrder(t.getRoot());
			}				
	}
	
	// Iterative method to do level order traversal line by line
    static void printLevelOrder(Node root) {
        if(root == null)
            return;
         
        Queue<Node> q =new LinkedList<Node>();
        q.add(root);
         
        while(true)	{     
            int nodeCount = q.size();
            if(nodeCount == 0)
                break;
             
            while(nodeCount > 0) {
                Node node = q.peek();
                String r = (node.isRed())?"R":"B";
                System.out.print(" " + node.getKey() + "*" + r + " P:" + node.getP().getKey() + " ");
                q.remove();
                if(node.getLeft() != RBT.NIL)
                    q.add(node.getLeft());
                if(node.getRight() != RBT.NIL)
                    q.add(node.getRight());
                nodeCount--;
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
