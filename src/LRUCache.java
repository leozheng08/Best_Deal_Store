import java.util.HashMap;

public class LRUCache {
    private int capacity;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<Integer, Node>();
    }

    class Node {
        private int key;
        private int val;
        private Node pre;
        private Node next;

        public Node(int key, int value){
            this.key = key;
            this.val = value;
        }
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        remove(map.get(key));
        setHead(map.get(key));
        return map.get(key).val;
    }

    public void put(int key, int value) {
        if(map.get(key)!=null){
            Node n =map.get(key);
            remove(n);
            n.val = value;
            setHead(n);
        }
        else{
            Node input = new Node(key, value);
            if(map.size()>=capacity){
                map.remove(tail.key);
                remove(tail);
                setHead(input);
            }
            else{
                setHead(input);
            }
            map.put(key, input);
        }
    }

    public void remove(Node node){
        if(node.pre!=null){
            node.pre.next= node.next;
        }
        else{
            head = node.next;
        }
        if(node.next !=null){
            node.next.pre= node.pre;
        }
        else{
            tail = node.pre;
        }

    }

    public void setHead(Node node){

        node.next = head;
        node.pre = null;
        if(head!=null) {head.pre = node;}
        head = node;
        if(tail==null) {tail = head;}

    }

    public static void main(String[] args){
        LRUCache newCache =  new LRUCache(2);
        newCache.put(1,1);
        newCache.put(2,2);
        System.out.println(newCache.get(1));
    }
}
