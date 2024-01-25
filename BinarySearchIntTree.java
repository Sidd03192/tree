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
	private int size;
	private IntTreeNode overallRoot;
	private int largest=Integer.MIN_VALUE;
	private int smallest=Integer.MAX_VALUE;
	
	public BinarySearchIntTree() {
		overallRoot = null;
		// default constructor sets overallRoot to null
	}
	public BinarySearchIntTree( int value) {
		overallRoot = new IntTreeNode(value); // sets overall root to a new tree node with value
		overallRoot.left=null; overallRoot.right = null;
	} 
	public BinarySearchIntTree( ArrayList<Integer>list){ // check!!!
		for(int i=0; i<list.size(); i++)	
		{
			this.add(i);
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
		return smallest;//return smallest value
	}
	public int largest()
	{
		if (isEmpty())
			throw new IllegalStateException();
		return largest; // returns largest value
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
	private int countLeaves(IntTreeNode root)// wrong
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
		size--;
		return remove(value, overallRoot); // gets help from next method
	}
	public boolean remove(int value, IntTreeNode node)
	{
		boolean a= false;
		if ( node.data==value) // if you found the node... then remove it
		{
			IntTreeNode temp1= node.left!=null ? node.left : null;
			IntTreeNode temp2= node.right!=null ? node.right:null;
			if (node.left==null && node.right==null) // if no children
			{

				getThroughParent(overallRoot,value);
				return true;

			}
			if (node.right!=null &&node.left!=null) // both children case ( smallest value from right)
			{
				System.out.println("foudn it " + node.data);

				IntTreeNode newroot= minNode(overallRoot);
				newroot.left=overallRoot.left; newroot.right=overallRoot.right; // updates children of new root
					
				overallRoot=newroot; // updates root to new one.
				node=null; // updates the current node and sets to null
				

				return true;
			}


			if (temp1!=null)
				node=node.left;
			else if (temp2!=null) 
				node=node.right;
		}	

		if (node.left!=null) // check left for node
		{
			remove(value, node.left);
		}
		if(node.right!=null)  // check right for node we want
		{
			remove(value,node.right);
		}
		return a;
	}
public void getThroughParent( IntTreeNode node,int value){
	
	if (node.left!=null && node.left.data==value) 
		node.left=null;
	if(node.right!=null && node.right.data==value) 
	{
		node.right=null;
	}
	
	if (node.left!=null)
		{
			getThroughParent(node.left,value);
		}
	if(node.right!=null)
		{
			getThroughParent(node.right,value);
		}

	
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
		ArrayList<Integer> nodes = new ArrayList<Integer>();
    	BinarySearchIntTree tree = new BinarySearchIntTree();
        tree.add(7);
		tree.add(4);
		tree.add(3);
		tree.add(6);
		tree.add(10);
		tree.add(9);
		tree.add(11);

        
        System.out.println("Size of the tree: " + tree.getSize());
        System.out.println("Tree elements: " + tree.toString());
		System.out.println("Smallest: "+tree.smallest());
		System.out.println("Largest:" +tree.largest());
		System.out.println("CountLeaves: " + tree.countLeaves());
		//tree.remove(7);
		//System.out.println("Removed: "+ tree.toString());
    }
    
}