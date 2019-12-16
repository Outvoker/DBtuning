package INLJ;
import java.util.Random;

public class BplusTree implements BTree {

    /** 根节点 */
    protected Node root;

    /** 阶数，M值 */
    protected int order;

    /** 叶子节点的链表头*/
    protected Node head;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public Object get(Comparable key) {
        return root.get(key);
    }

    @Override
    public void remove(Comparable key) {
        root.remove(key, this);

    }

    @Override
    public void insertOrUpdate(Comparable key, Object obj) {
        root.insertOrUpdate(key, obj, this);

    }

    public BplusTree(int order){
        if (order < 3) {
            System.out.print("order must be greater than 2");
            System.exit(0);
        }
        this.order = order;
        root = new Node(true, true);
        head = root;
    }

    //测试
    public static void main(String[] args) {
        BplusTree tree = new BplusTree(6);
        Random random = new Random();
        long current = System.currentTimeMillis();
        String [] employee = {"Emma","Mary","Allen","Olivia","Kevin","Rose","Kelly","Jeanne","Hale","David","Steve","Andrew","Richard","Robert"
                ,"Viola","Lucy","Angel","Gloria","Hannah","Ruth","Diana","Jessie","Jason","June","Julie","Carol","Gina","Lois","Diane","Ella",
                "Donna","Julia","Alice","Beth","Sandra","Bonnie","Ann","Alma","Helen","Denise","Frances","Betty","Theresa","Pamela","Daoer","Elaine"
                ,"Dorothy"};
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < 1000; i++) {
                int index = random.nextInt(10000);
                tree.insertOrUpdate(index, employee[index%employee.length]);
            }

//            for (int i = 0; i < 100; i++) {
//                int randomNumber = random.nextInt(1000);
//                tree.remove(randomNumber);
//            }
        }

        int search = 2435234;
        System.out.println("1"+tree.get(search)+"1");
        long duration = System.currentTimeMillis() - current;
        System.out.println("time elpsed for duration: " + duration);
    }

}