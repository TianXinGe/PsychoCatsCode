import java.util.ArrayList;



public class CatA extends Cat{
    
    private Cat otherCat;
    
    public CatA() {
        super();
        clockThinking();
    }
    
    public void otherCat(CatB cat) {
        otherCat = cat;
    }
    
    public boolean tryMove() {
        int n = next(getPos());
        if (n == getPos()) return false;
        setPos(n);
        return true;
    }
    
    public int next(int n) {
        ArrayList<Integer> reachable = new ArrayList<Integer>();
        int[] orient = new int[81]; // ��¼���ܷ���ĵ�һ��
        for (int w : edges.get(n)) {
            if (escaped(w)) return w;
            if (otherCat.getPos() != w) {
                reachable.add(w);
                orient[w] = w;
            }
        }
        int num = 0;
        while (num < reachable.size()) {
            // ��reachable��ȡ����һ��num����������ӵĸ���
            int v = reachable.get(num++);
            for (int w : edges.get(v)) {
                if (escaped(w)) return orient[v];
                if (!reachable.contains(w)) {
                    reachable.add(w);
                    orient[w] = orient[v];
                }
            }
        }
        if (reachable.size() == 0) return n; // һ�����޷���
        return reachable.get(0); // �ѱ�Χס���Կɶ�
    }
    
}
