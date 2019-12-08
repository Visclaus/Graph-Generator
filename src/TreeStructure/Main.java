package TreeStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int vertexCnt = 0;
        Tree tree = null;
        File file = new File("graphics.txt");
        double avg = 0;
        double dis = 0;
        double [] alphaArr = new double[100];
        tree = new Tree(200,5,true);
        System.out.println(tree.getAlphaValue());
        System.out.println(tree.toString());
        /*try (FileWriter writer = new FileWriter(file)) {
            for (int i = 3; i < 600; i++) {
                vertexCnt = 0;
                while (vertexCnt != i) {
                    tree = new Tree(i, 5, true);
                    vertexCnt = tree.getVertexCnt();
                }
                writer.write(Integer.toString(tree.getVertexCnt()));
                writer.write("\t");
                writer.write(Double.toString(Math.round(tree.getAlphaValue() * 1000.0) / 1000.0));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        /*try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < 100; i++) {
                vertexCnt = 0;
                while (vertexCnt < 200) {
                    tree = new Tree(200, 5, true);
                    vertexCnt = tree.getVertexCnt();
                }
                avg += tree.getAlphaValue();
                int k = i + 1;
                int levelSize = tree.getHierarchy().size();
                int size = tree.getVertexCnt();
                int vis = tree.getTerminalVertexes().size();
                writer.write(Integer.toString(k));
                writer.write("\t");
                writer.write(Integer.toString(levelSize));
                writer.write("\t");
                writer.write(Integer.toString(size));
                writer.write("\t");
                writer.write(Integer.toString(vis));
                writer.write("\t");
                alphaArr[i] = tree.getAlphaValue();
                double roundOff = Math.round(tree.getAlphaValue() * 100.0) / 100.0;
                writer.write(Double.toString(roundOff));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
 /*       avg /= 100;
        for (int i = 0; i < 100 ; i++) {
            dis += Math.pow(alphaArr[i] - avg, 2);
        }
        dis /= 99;
        System.out.println(avg);
        System.out.println(dis);
        System.out.println(tree);
        System.out.println(tree.getVertexCnt());
        System.out.println(tree.getTerminalVertexes());
        System.out.println(tree.getTerminalVertexes().size());
        System.out.println("alpha: " + (double) tree.getVertexCnt() / (double) tree.getTerminalVertexes().size());
        double mo = 0;
        double avgs = 0;
        for (int i = 0; i < tree.gI.length; i++) {
            mo += tree.gI[i] * i;
            avgs += tree.gI[i];
            System.out.println(tree.gI[i]);
        }
        System.out.println("MO: " + mo / avgs);
        System.out.println("Theory MO: " + (5 - 1) / 2);*/
    }
}