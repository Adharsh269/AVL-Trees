import java.util.Queue;
import java.util.LinkedList;

public class AVLTrees{
  public BinaryNode root;
  AVLTrees(){
    root=null;
  }

  //pre order
  public void preOrder(BinaryNode root){
    if(root == null) return;
    System.out.print(root.value+" ");
    preOrder(root.left);
    preOrder(root.right);
  }
  public void inOrder(BinaryNode root){
    if(root == null) return;
    inOrder(root.left);
     System.out.print(root.value+" ");
    inOrder(root.right);
  }
  public void postOrder(BinaryNode root){
    if(root == null) return;
    postOrder(root.left);
    postOrder(root.right);
    System.out.print(root.value+" ");
  }

  //level order
  public void levelOrder(){
    Queue<BinaryNode> queue=new LinkedList<BinaryNode>();
    queue.add(root);
    while(!queue.isEmpty()){
      BinaryNode pNode=queue.remove();
      System.out.print(pNode.value+" ");
      if(pNode.left!=null){
        queue.add(pNode.left);
      }
      if(pNode.right!=null){
        queue.add(pNode.right);
      }
    }
  }

  public BinaryNode search(BinaryNode node,int value){
    if(node==null) return null;
    if(node.value==value){
      System.out.println("Value found");
      return node;
    }else if(value<node.value){
      return search(node.left,value);
    }else{
      return search(node.right,value);
    }
  }

  public int getHeight(BinaryNode node){
    if (node==null) return 0;
    return node.height;
  }

  private BinaryNode rotateLeft(BinaryNode disBalance){
    BinaryNode newRoot=disBalance.right;
    disBalance.right= disBalance.right.left;
    newRoot.left=disBalance;
    disBalance.height=1+Math.max(getHeight(disBalance.left),getHeight(disBalance.right));
    newRoot.height=1+Math.max(getHeight(newRoot.left),getHeight(newRoot.right));
    return newRoot;
  }

  private BinaryNode rotateRight(BinaryNode disBalance){
    BinaryNode newRoot = disBalance.left;
    disBalance.left= disBalance.left.right;
    newRoot.right=disBalance;
    disBalance.height=1+Math.max(getHeight(disBalance.left),getHeight(disBalance.right));
    newRoot.height=1+Math.max(getHeight(newRoot.left),getHeight(newRoot.right));
    return newRoot;
  }

  public int getBalance(BinaryNode node){
    if(node==null) return 0;
    return getHeight(node.left)-getHeight(node.right);
  }

  //insertNode
  private BinaryNode insertNode(BinaryNode node,int value){
    if(node ==null){
      BinaryNode newNode=new BinaryNode();
      newNode.value=value;
      newNode.height=1;
      return newNode;
    }else if(value < node.value){
      node.left=insertNode(node.left,value);
    }else{
      node.right=insertNode(node.right,value);
    }
    node.height=1+Math.max(getHeight(node.left),getHeight(node.right));
    int balance=getBalance(node);

    //left-left comdition
    if(balance>1 && value<node.left.value){
      return rotateRight(node);
    }
    //left-right condition
    if(balance>1 && value>node.left.value){
      node.left=rotateLeft(node.left);
      return rotateRight(node);
    }

    //rigth-rigth condtion
    if(balance<-1 && value>node.right.value){
      return rotateLeft(node);
    }
    //left-right condition
    if(balance<-1 && value<node.right.value){
      node.right=rotateRight(node.right);
      return rotateLeft(node);
    }

    return node;
  }

  public void insert(int value){
    root=insertNode(root,value);
  }

  //get minumnode value
  private  static BinaryNode getMinimumNode(BinaryNode node){
    if(node.left==null) return node;
    return getMinimumNode(node.left);
  }

  //delete node
  public BinaryNode deleteNode(BinaryNode node,int value){
    if(node==null){
      System.out.println("Node does not exist.");
      return null;
    }else if(value<node.value){
      node.left=deleteNode(node.left, value);
    }else if(value>node.value) node.right=deleteNode(node.right, value);
    else{
      if(node.left!=null && node.right!=null){
        BinaryNode temp=node;
        BinaryNode minOnRight=getMinimumNode(temp);
        node.value=minOnRight.value;
        node.right=deleteNode(node.right, value);
      }else if(node.left!=null) {
        node=node.left;
      } 
      else if(node.right!=null) node=node.right;
      else node=null;
    }
    
    int balance=getBalance(node);

    //left-left comdition
    if(balance>1 && getBalance(node.left)>=0){
      return rotateRight(node);
    }
    //left-right condition
    if(balance>1 && getBalance(node.left)<0){
      node.left=rotateLeft(node.left);
      return rotateRight(node);
    }

    //rigth-rigth condtion
    if(balance<-1 && getBalance(node.left)<=0){
      return rotateLeft(node);
    }
    //left-right condition
    if(balance<-1 && getBalance(node.left)>0){
      node.right=rotateRight(node.right);
      return rotateLeft(node);
    }
    
    return node;
  }
  public void delete(int value){
    root=deleteNode(root, value);
  }
}