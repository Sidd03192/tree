/* Siddharth Potta
	Period 7
	Porpose: To replicate binary tree structure and functions in java. 
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

 
public class BinarySearchIntTree  {
	public int size;
	public IntTreeNode overallRoot;
	public int largest=Integer.MIN_VALUE;
	public int smallest=Integer.MAX_VALUE;
	
	public BinarySearchIntTree() {
		overallRoot = null;
		// default constructor sets overallRoot to null
	}
	public BinarySearchIntTree( int value) {
		overallRoot = new IntTreeNode(value); // sets overall root to a new tree node with value
		overallRoot.left=null; overallRoot.right = null;
	} 
	public BinarySearchIntTree( ArrayList<Integer>list){ // check!!!
		for( int k:list)
		{
			add(k);
		}

	}

	public int getSize(){ // returns the size of the tree

		if (overallRoot == null)
			return 0;
		return size;
		
	}
	public void clear(){ // clears the tree
		overallRoot.left=null; overallRoot.right=null;overallRoot=null;

	}
	public boolean add(int value) {
    if (size == 0) {
        overallRoot = new IntTreeNode(value);
        overallRoot.left = null;
        overallRoot.right = null;
        size++;
        return true;
    }
    if (value > largest) {
        largest = value; // updates largest value
    }
    if (value < smallest) {
        smallest = value; //updates smallest value
    }  
    if (contains(value)) {
        return false;   // if dont contain value, then return false
    }

    IntTreeNode temp = overallRoot;
    while (true) {
        if (temp.data < value) {
            if (temp.right == null) {
                temp.right = new IntTreeNode(value);
                size++;
                return true;
            }
            temp = temp.right;
        } else {
            if (temp.left == null) {
                temp.left = new IntTreeNode(value);
                size++;
                return true;
            }
            temp = temp.left;
        }
    }
}

	public boolean contains(int value)
	{
		IntTreeNode temp = overallRoot;
		if(temp.data==value){return true;}
		while (true)
		{
			if(temp.data==value){return true;}
			if (temp.data<value)
			{
				if(temp.right ==null)
					return false;
				temp=temp.right;
			}
			else {
				if(temp.left==null)
					return false;
				temp=temp.left;

			}
		}
	}

	public int smallest()
	{
		if (isEmpty())
			throw new IllegalStateException();
		if (overallRoot.right==null)
			return overallRoot.data;
		return smallest(overallRoot.left);
			
	}
	private int smallest(IntTreeNode node)
	{
		if(node.left!=null)
			return smallest(node.left);
		return node.data;
	}
	public int largest()
	{
		if (isEmpty())
			throw new IllegalStateException();
		
		if (overallRoot.right==null)
			return overallRoot.data;
		return largest(overallRoot.right);



	}
	private int largest(IntTreeNode node){		
		if (node.right!=null)
		{
			return largest(node.right);
		}
		return node.data;
	}
	public boolean isEmpty()
	{
		return (size==0); // retusn if size is 0 false, else true;
	}
	private static IntTreeNode minNode (IntTreeNode root)
	{
		IntTreeNode temp = root;// creates a temp 
		while (true){
						// loops through temp and finds left most value. 
			if(temp.left == null)
			{
				return temp;
			}
			else{
				temp=temp.left;
			}

		}

	}
	public int countLeaves(){
		if (isEmpty()){return 0;}  // if empty return 0
		

		return countLeaves(overallRoot); // gets help from the next method
	}
	private int countLeaves(IntTreeNode root)// recursies the tree to find nodes with no children.
	{

		int a =0;
		if (root.left == null && root.right == null)
		{ // if left and right are null, then you are leaf
			a++;
		}
		if(root.left!=null)  // else, look at the left
			a+=  countLeaves(root.left);
		if(root.right!=null)	// and look a the right
			a+= countLeaves(root.right);

		return a;
	}
	
	public boolean remove(int value){
		if (!contains(value))   // if dont contain, then return false
			return false;
		
		if (overallRoot.data ==value && countLeaves(overallRoot)>0)
		{
			int newData =minNode ((overallRoot.right!=null)?overallRoot.right:overallRoot.left).data; // if possible get teh right min node
			remove(newData);
			overallRoot.data = newData;

		}
		
		size--;
		 remove(value, overallRoot); // gets help from next method
		 return true;
	}
	public void remove(int value, IntTreeNode node) // node is the parent
	{
		if (node==null) // to prevent infinite recursion
			return;
		
		if (node.left!=null && node.left.data==value) // // if we find the value in the left of parent
		{
			IntTreeNode guy = node.left;
			if(guy.left==null && guy.right==null) // if no child
				node.left=null;
			else if(guy.left!=null && guy.right!=null) // if both child
			{
				// we have to check if hte right has children first.....

				int newdata =minNode(guy.right).data;
				
				remove(newdata, guy); // removes teh copy
				guy.data=newdata; // " removes the one we wnat to remove and replaces"
			}
			else {
				node.left = (guy.left != null) ? guy.left : guy.right;
			}
		}
		else if (node.right != null && node.right.data == value) {
			IntTreeNode guy = node.right; 
			if (guy.left == null && guy.right == null) {
				node.right = null;
			} 
			else if (guy.left != null && guy.right != null) {
				IntTreeNode minRight = minNode(guy.right);
				guy.data = minRight.data; 
				remove(minRight.data, guy); 
			} 
			// If the node to remove has only one child, bypass it by adjusting the parent's reference
			else {
				node.right = (guy.left != null) ? guy.left : guy.right;
			}
		}

		// recurse until find the guy
			remove(value, node.left);
		
			remove(value,node.right);
		
	
		
	}

	

	public String toString(){

		return toString(overallRoot,""); // returns a string representation of the tree in in order
	}
	private String toString(IntTreeNode node, String a) // recursively runs through the tree and creates a string representation fo the tree. Its a helper method for the toString()
	{
		if(size == 0)
			return "";
		String b="";
		if (node.left!=null)
			a=toString(node.left, a); // updates a with left branch
			 
		a=a+node.data +" ";  // adds current node to a

	    if (node.right!=null)
			a= toString(node.right,a);  //updates a with right branch.
		
		return a;

	}

	




    public static void main(String args[]) 
    {
		int[] elements = {18, 12, 35, 4, 15, 22, 58, -2, 7, 13, 16, 19, 31, 40, 87};

        // Create an ArrayList called nodes
        ArrayList<Integer> nodes = new ArrayList<>();

        // Add elements to the nodes ArrayList
        for (int element : elements) {
            nodes.add(element);
        }

    	BinarySearchIntTree tree = new BinarySearchIntTree(nodes);
        // tree.add(7);
		// tree.add(4);
		// tree.add(10);
		// tree.add(3);
		// tree.add(6);
		// tree.add(9);
		// tree.add(13);

        
        System.out.println("Size of the tree: " + tree.getSize());
        System.out.println("Tree elements: " + tree.toString());
		System.out.println("Smallest: "+tree.smallest());
		System.out.println("Largest:" +tree.largest());
		System.out.println("CountLeaves: " + tree.countLeaves());
		tree.remove(22);
		System.out.println("Removed: "+ tree.toString());
    }
    
}
