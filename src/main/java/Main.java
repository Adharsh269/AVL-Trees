public class Main {
  public static void main(String[] args) {
    AVLTrees avl=new AVLTrees();
    avl.insert(70);
    avl.insert(50);
    avl.insert(75);
    avl.insert(20);
    avl.insert(30);
    avl.insert(72);
    avl.insert(80);
    avl.insert(10);
    avl.insert(5);
    

    avl.levelOrder();
    System.out.println();
    avl.delete(50);
    avl.levelOrder();
    
  } 
}