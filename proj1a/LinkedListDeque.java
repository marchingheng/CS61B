public class LinkedListDeque<T>{
    private IntNode sentinel;
    private int size;

    /**Define helper class IntNode */
    //if you use "private class IntNode<T>", there will be two different T. Therefore
        //only use <T> once in the outer class
    private class IntNode{
        private IntNode prev;
        private IntNode next;
        private T item;
        private IntNode(T x, IntNode p, IntNode n){
            item = x;
            prev = p;
            next = n;
        }

    }

    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** when add one node between two node, the previous back(prev) and forward(next) arrows
     *  should both be modified*/
    public void addFirst(T x){
        sentinel.next = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;

    }

    public void addLast(T x){
        sentinel.prev.next = new IntNode(x, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0){return true;}
        else{return false;}
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        IntNode p = sentinel;
        while(p.next != sentinel){
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
        }
    }

    public T removeFirst(){
        if(size == 0){return null;}
        T valueRemoved = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return valueRemoved;
    }

    public T removeLast(){
        if(size == 0){return null;}
        T valueRemoved = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return valueRemoved;
    }

    public T get(int index){
        if(index < 0 || index >= size){return null;}
        int i = 0;
        IntNode p = sentinel;
        while(i <= index){
            p = p.next;
            i+=1;
        }
        return p.item;
    }




    //how many properties changed in each recursion will determine how many arguments
        //you will need in the helper function.
    public T helper(IntNode node, int index){
        if(index == 0){return node.item;}
        return helper(node.next, index-1);
    }

    public T getRecursive(int index){
        if(index < 0 || index >= size){return null;}
        return helper(sentinel.next, index);
    }


//    public static void main(String[] args){
//        LinkedListDeque<Integer> lld = new LinkedListDeque<Integer>();
//        lld.addFirst(1);
//        lld.addFirst(2);
//        lld.addFirst(3);
//        lld.printDeque();
//        System.out.println();
//        System.out.println(lld.get(0));
//        System.out.println(lld.getRecursive(100));
//    }
}