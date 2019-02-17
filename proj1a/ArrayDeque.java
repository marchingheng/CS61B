/** https://sp18.datastructur.es/materials/proj/proj1a/proj1a */
public class ArrayDeque<T>{
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque(){
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[8];
    }

    public void addFirst(T item){
        checkSize();/** resizing before adding*/
        items[nextFirst] = item;
        nextFirst = moveIndexLeft(nextFirst);
        size += 1;
    }

    public void addLast(T item){
        checkSize();/** resizing before adding*/
        items[nextLast] = item;
        nextLast = moveIndexRight(nextLast);
        size += 1;
    }

    public T removeFirst(){
        if(size == 0){return null;}
        nextFirst = moveIndexRight(nextFirst);
        T result = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        checkSize();/** resizing after removing*/
        return result;
    }

    public T removeLast(){
        nextLast = moveIndexLeft(nextLast);
        T result = items[nextLast];
        items[nextLast] = null;
        size --;
        checkSize();/** resizing after removing*/
        return result;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int index = moveIndexRight(nextFirst);
        while(index != nextLast){
            System.out.print(items[index]);
            System.out.print(" ");
            index = moveIndexRight(index);
        }
        System.out.println();
    }

    public T get(int index){
        int indexOfItems = nextFirst + 1;
        indexOfItems = (indexOfItems + index)%items.length;
        return items[indexOfItems];
    }

    /** these 3 helper functions are for resizing */
    private void upSize(){
        T[] newArray = (T[]) new Object[items.length*2];
        int index = 1;
        int indexFirst = moveIndexRight(nextFirst);
        while(index <= size){
            newArray[index] = items[indexFirst];
            indexFirst = moveIndexRight(indexFirst);
            index ++;
        }
        items = newArray;
        nextFirst = 0;
        nextLast = size + 1;
    }

    private void downSize(){
        T[] newArray = (T[]) new Object[items.length/2];
        int index = 1;
        int indexFirst = moveIndexRight(nextFirst);
        while(index <= size){
            newArray[index] = items[indexFirst];
            indexFirst = moveIndexRight(indexFirst);
            index ++;
        }
        items = newArray;
        nextFirst = 0;
        nextLast = size + 1;
    }

    private void checkSize(){
        if(size!=0 && size < (items.length/2)){downSize();}
        if(size!=0 && size >= (items.length/2)){upSize();}
    }


    /** these 2 helper function help to ensure the Allist is circular */
    private int moveIndexRight(int x){
        x ++;
        x = x%items.length;
        return x;
    }

    private int moveIndexLeft(int x){
        x --;
        if(x < 0){x = x + items.length;}
        return x;
    }

    /** testing */
    public static void main(String[] args){
        ArrayDeque <Integer> allist = new ArrayDeque <Integer>();
        allist.addFirst(1);
        allist.printDeque();
        allist.addFirst(2);
        allist.printDeque();
        allist.addLast(2);
        allist.printDeque();
        allist.addFirst(1);
        allist.printDeque();
        allist.addFirst(1);
        allist.printDeque();
        allist.addFirst(1);
        allist.printDeque();
        allist.addFirst(2);
        allist.printDeque();
        allist.addFirst(3);
        allist.printDeque();
        allist.removeFirst();
        allist.printDeque();
        allist.removeLast();
        allist.printDeque();
        System.out.println(allist.isEmpty());
        allist.removeLast();
        allist.removeLast();
        allist.removeLast();
        allist.printDeque();
        allist.removeLast();
        allist.removeLast();
    }

}
