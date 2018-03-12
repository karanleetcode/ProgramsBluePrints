
import java.util.Stack;
class node{
    int id;
    int data;
    node left,right;
    node(int id,int data)
    {
        this.id = id;
        this.data = data;
    }
};
class Tree1{
    node root;
    Tree1(){
        root = null;
    }
    public node find(int id){
        node newNode=root;
        while(newNode.id!=id){
            if(newNode.id<id)
            {
                newNode = newNode.right;
            }
            if(newNode.id>id)
            {
                newNode = newNode.left;
            }
            if(newNode==null) return null;
        }
        return newNode;
    }

    public void insert(int data,int id){
        node newNode = new node(id,data);
        if(root == null)root = newNode;
        else {
            node current = root;
            node parent;
            while (true) {
                parent = current;
                if(current.id>id)
                    {
                    current = current.left;
                    if(current == null)
                    {
                        parent.left = newNode;
                        return;
                    }
                    }
                else if(current.id<id)
                    {
                    current= current.right;
                    if(current==null)
                    {
                        parent.right = newNode;
                        return;
                    }
                    }
            }
        }
    }
    public boolean delete(int key) // delete node with given key
    { // (assumes non-empty list)
        node current = root;
        node parent = root;
        boolean isLeftChild = true;
        while(current.data != key) // search for node
        {
            parent = current;
            if(key < current.data) // go left?
            {
                isLeftChild = true;
                current = current.left;
            }
            else // or go right?
            {
                isLeftChild = false;
                current = current.right;
            }
            if(current == null) // end of the line,
                return false; // didn’t find it
        } // end while
// found node to delete
// if no children, simply delete it
        if(current.left==null &&
                current.right==null)
        {
            if(current == root) // if root,
                root = null; // tree is empty

            else if(isLeftChild)
                parent.left = null; // disconnect
            else // from parent
                parent.right = null;
        }
// if no right child, replace with left subtree
        else if(current.right==null)
            if(current == root)
                root = current.left;
            else if(isLeftChild)
                parent.right = current.left;
            else
                parent.right = current.left;
// if no left child, replace with right subtree
        else if(current.left==null)
            if(current == root)
                root = current.right;
            else if(isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        else // two children, so replace with inorder successor
        {
// get successor of node to delete (current)
            node successor = getSuccessor(current);
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = current.left;
        }
        return true;
    } // end delete()
    public node getSuccessor(node delNode){
        node parentSuccessor = delNode;
        node successor = delNode;
        node current = delNode.right;
        while(current != null)
        {
            parentSuccessor = successor;
            successor = current;
            current = current.left;
        }
        while (successor != delNode.right)
        {
            parentSuccessor.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }
    public void traverse(int type){
        switch (type)
        {
            case 1:
                System.out.println("Preorder traversal");
                preorder(root);
                break;
            case 2:
                System.out.println("Inorder traversal");
                inorder(root);
                break;
            case 3:
                System.out.println("Postorder traversal");
                postorder(root);
                break;
        }
    }
    public void preorder(node root){
        if(root!=null)
        {
            System.out.print(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }
    }
    public void postorder(node root){
        if(root!=null)
        {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data+" ");

        }
    }
    public void inorder(node root){
        if(root!=null)
        {
            inorder(root.left);
            System.out.print(root.data+" ");
            inorder(root.right);
        }
    }

    public void displayTree()
    {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "......................................................");
        while(isRowEmpty==false)
        {

            Stack localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<nBlanks; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false)
            {
                node temp = (node)globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||
                            temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<nBlanks*2-2; j++)
                    System.out.print( ' ');
            } // end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        } // end while isRowEmpty is false
        System.out.println(
                "......................................................");
    }
};

public class Test {
    public static void main(String[] args){
        Tree1 tree = new Tree1();
        tree.insert(10,10);
        tree.insert(8,8);
        tree.insert(9,9);
        tree.insert(15,15);
        tree.insert(11,11);
        tree.insert(20,20);
        //tree.displayTree();
        tree.delete(15);
       // tree.displayTree();
        tree.insert(13,13);
        tree.displayTree();
    }
}
