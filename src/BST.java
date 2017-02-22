

class Node 
{
	int data;
	Node right=null;
	Node left=null;
	public Node(int data)
	{
		this.data=data;
	}
}

public class BST {
	
	public static Node root ;
	
	public BST()
	{
		root = null;
	}
	
	public void insert(int data)
	{
		Node newnode = new Node(data);
		
		if(root==null)
		{
			root = newnode;
			return;
		}

		Node current = root;
		
		while(true)
		{
			Node parent = current;
			
			if(data<current.data)
			{
				current = current.left;
				if(current == null)
				  {
					parent.left = newnode;
					return;
				  }
			}
			else
			{
				current = current.right;
				if(current == null)
				  {
					parent.right = newnode;
					return;
				  }
			}
		}
	}
	
	public Boolean search(int data)
	{
	   if(root!=null)
	   {
		    Node current=root;
		   while(current!=null)
		   {
			   if(current.data==data)
				   return true;
			   if(data<current.data)
				   current = current.left;
			   else
				   current = current.right;
		   }
	   }
		return false;
	}
	
	public void inorder(Node root)
	{
	    if(root!=null)
	    {
	    	inorder(root.left);
	    	System.out.print(root.data+" , ");
	    	inorder(root.right);
	    }
	}

}


