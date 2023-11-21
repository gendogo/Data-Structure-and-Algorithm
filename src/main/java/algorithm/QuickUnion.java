package algorithm;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: QuickUnion, 使用负数表示该连通块的元素数量
 * @Author: whj
 * @Date: 2023-10-06 21:45
 */
public class QuickUnion {
    private int[] parent;

    public QuickUnion(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            //-1表示每个结点就一个元素，就是自己
            parent[i] = -1;
        }
    }

    public void connect(int p, int q) {
        //这里需要连接p和q的父节点，而不是p和q
        int pAncestor = find(p);
        int qAncestor = find(q);
        if (pAncestor == qAncestor) {
            return;
        }
        if (Math.abs(parent[pAncestor]) >= Math.abs(parent[qAncestor])) {
            //q祖先的块的元素数量
            int originSize = Math.abs(parent[qAncestor]);
            //q的祖先更新为p的祖先
            parent[qAncestor] = pAncestor;
            //两个元素块数量相加
            parent[pAncestor] -= originSize;
        } else {
            int originSize = Math.abs(parent[pAncestor]);
            parent[pAncestor] = qAncestor;
            parent[qAncestor] -= originSize;
        }
    }

    public boolean isConnect(int p, int q) {

        return find(p) == find(q);
    }

    //find parent of x
    public int find(int x) {
        //非路径压缩
        while (parent[x] >= 0) {
            x = parent[x];
        }
        return x;

//路径压缩
//        if (parent[x] < 0) {
//            return x;
//        } else {
//            return parent[x] = find(parent[x]);
//        }

    }

    public static void main(String[] args) {
        QuickUnion quickUnion = new QuickUnion(5);
        quickUnion.connect(0, 1);
        quickUnion.connect(2, 3);
        quickUnion.connect(1, 3);
        System.out.println(quickUnion.isConnect(0, 4));
        System.out.println(quickUnion.isConnect(0, 3));
        List<Integer> list = new ArrayList<>();




    }

}
