import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {

    private class Node {
        public Node next;
        public T value;

        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    private Node head, tail;
    private int size = 0;

    public CustomList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        Node n = head;
        for (int i=0; i<index; i++){
            n = n.next;
            if(n == null) {
                throw new NoSuchElementException("index is out of bounds");
            }
        }
        return n.value;
    }

    public void addLast(T value) {
        if (tail == null) {
            assert head == null;
            tail = new Node(value);
            head = tail;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(T value) {
        if (head == null) {
            assert tail == null;
            head = new Node(value);
            tail = head;
        } else {
            Node newHead = new Node(value);
            newHead.next = head;
            head = newHead;
        }
        size++;
    }

    public T getLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        return tail.value;
    }

    public T getFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        return head.value;
    }

    public T removeLast() {
        if (tail == null) throw new NoSuchElementException("Trying to remove element from empty list");
        assert head != null;
        T result;
        if (head == tail) {
            result = tail.value;
            head = tail = null;
        } else {
            Node n = head;
            while (n.next != tail) {
                n = n.next;
            }
            tail = n;
            n.next = null;
            result = n.value;
        }
        size--;
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node n = head;
            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public T next() {
                T result = n.value;
                n = n.next;
                return result;
            }
        };
    }

    @Override
    public Stream<T> stream(){
        Stream.Builder<T> builder = Stream.builder();
        for(T i : this){
            builder.accept(i);
        }
        return builder.build();
    }

    public T removeFirst() {
        if (head == null) throw new NoSuchElementException("Trying to remove element from empty list");
        T result = head.value;
        head = head.next;
        size--;
        return result;
    }

    @Override
    public String toString() {
        if (head == null) return "CustomList []";

        StringBuilder result = new StringBuilder("CustomList [ ");
        Node n = head;
        while (n != null) {
            result.append(n.value).append(" ");
            n = n.next;
        }
        result.append("] size=");
        result.append(size);

        return result.toString();
    }

    @Override
    public boolean add(T t) {
        this.addLast(t);
        return true;
    }

    public static <S> List<S> filterByClass(List<S> list, Class<?> cls){
//        List<S> output = new CustomList<>();
//
//        for(S elem : list){
//            if(cls.isInstance(elem)){
//                output.add(elem);
//            }
//        }
//
//        return output;
        return list.stream().filter(cls::isInstance).toList();
    }

}
