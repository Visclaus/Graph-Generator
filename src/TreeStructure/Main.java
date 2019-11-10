package TreeStructure;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(10);

        System.out.println(tree);
        System.out.println(tree.getVertexCnt());
        System.out.println(tree.getTerminalVertexes());
    }
}
