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
}