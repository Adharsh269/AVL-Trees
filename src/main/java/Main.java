public class Main {
  public static void main(String[] args) {
    AVLTrees avl=new AVLTrees();
    avl.insert(15);
    avl.insert(10);
    avl.insert(7);
    avl.insert(5);
    avl.insert(1);

    avl.levelOrder();
  } 
}