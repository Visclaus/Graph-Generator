package TreeStructure;

public class Main {
    public static void main(String[] args) {
        int vertexCnt = 0;
        Tree tree = null;
        while(vertexCnt < 200){
            tree = new Tree(200, 5, true);
            vertexCnt = tree.getVertexCnt();
        }

        System.out.println(tree);
        System.out.println("----------------levels size--------------------");
        for(int i = 1; i <= tree.getHierarchy().size(); i++){
            System.out.println(tree.getHierarchy().get(i).size()+"\n");
        }
        System.out.println("-----------------------------------------------");
        System.out.println(tree.getVertexCnt());
        System.out.println(tree.getTerminalVertexes());
        System.out.println("alpha: "+(double)tree.getVertexCnt()/(double) tree.getTerminalVertexes().size());
    }
}
