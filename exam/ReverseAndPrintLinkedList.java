//评测题目: 用Java实现单链表的反向打印。
/**
 * Created by kimmking on 2018/7/12.
 */
public class ReverseAndPrintLinkedList {

    public static void main(String[] args){

        // init a linkedlist
        Node linkedList = generate(10);
        System.out.print("Original List: ");
        print(linkedList);

        // reverse a linkedlist
        Node reverseList = reverse(linkedList);
        System.out.print("Reverse  List: ");
        print(reverseList);

    }

    // init a List by 0,1,2....
    private static Node generate(int count){
        Node head = new Node(0,null);
        Node pre = head;
        for (int i = 1; i< count; i++){
            Node node = new Node(i,null);
            pre.next = node;
            pre = node;
        }
        return head;
    }

    // reverse a list by move each node: pre node to next node
    private static Node reverse(Node node) {
        Node reverseNode = node;
        Node temp = null;
        Node pNode = node.next;
        node.next = null;
        while (pNode != null){
            temp = pNode.next;
            pNode.next = reverseNode;
            reverseNode = pNode;
            pNode = temp;
        }

        return reverseNode;
    }

    private static void print(Node node) {
        while (node != null){
            System.out.print(node.value + "->");
            node = node.next;
        }
        System.out.println("NIL");
    }


    // simple Linked List for this test
    public static class Node{
        public int value;
        public Node next;

        public Node(int v, Node n){
            this.value = v;
            this.next = n;
        }
    }

}
