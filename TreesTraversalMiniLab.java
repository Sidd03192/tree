//Name:
//Class Period:


/*************************************
 * Mini-Lab: Tree Traversal
 *
 * Given a tree, create recursive print 
 * methods for each traversl pattern.
 *
 *	pre-, in-, & post- order
 *
 *************************************/

public class TreesTraversalMiniLab
{
		
	public static void main (String[] args) 
	{
		//Creating the Tree
		IntTreeNode overallRoot = new IntTreeNode(17);
		overallRoot.left = new IntTreeNode(41, new IntTreeNode(29), new IntTreeNode(6));
		overallRoot.right = new IntTreeNode(9, new IntTreeNode(81), new IntTreeNode(40));
		System.out.println("Pre Order");

		printPreOrder(overallRoot);
		System.out.println();
		System.out.println("In Order");
		printInOrder(overallRoot);
		System.out.println();

		System.out.println("Post Order");
		printPostOrder(overallRoot);
		//CALL the 3 print methods using the overallRoot
	}
	
	public static void printPreOrder(IntTreeNode root){
		/* Prints the given tree using the pre-order pattern
		 */

		 System.out.print(root.data+" "); // root
		 if (root.left!=null)
		  	printPreOrder(root.left);  // right node
		 if (root.right!=null)
		  	printPreOrder(root.right);  // left node 

		 
	}
	
	public static void printInOrder(IntTreeNode root){
		/* Prints the given tree using the in-order pattern
		 */
		 if (root.left!=null)
		  	printInOrder(root.left);   // left node
		System.out.print(root.data+" ");  // root 

		 if (root.right!=null)
		  	printInOrder(root.right);  // right node
		 
	}
	
	public static void printPostOrder(IntTreeNode root){
		/* Prints the given tree using the pre-order pattern
		 */
		if (root.left!=null)
		  	printPostOrder(root.left); // left node
		
		 if (root.right!=null)
		  	printPostOrder(root.right);  // right node
		System.out.print(root.data+" ");  // root node
	}

}

