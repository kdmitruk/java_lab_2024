import java.util.NoSuchElementException;

public class CustomList<T> {

    private class Node {
        public Node next;
        public T value;

        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    private Node head, tail;

    public CustomList() {
        this.head = null;
        this.tail = null;
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
    }

    public T getLast() {
        if (tail == null) throw new NoSuchElementException("List is empty");
        return tail.value;
    }

    public T getFirst() {
        if (head == null) throw new NoSuchElementException("List is empty");
        return head.value;
    }

    public void removeLast() {
        if (tail == null) throw new NoSuchElementException("Trying to remove element from empty list");
        assert head != null;

        if (head == tail) {
            head = tail = null;
        } else {
            Node n = head;
            while (n.next != tail) {
                n = n.next;
            }
            tail = n;
            n.next = null;
        }
    }

    public void removeFirst() {
        if (head == null) throw new NoSuchElementException("Trying to remove element from empty list");
        head = head.next;
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
        result.append("]");

        return result.toString();
    }

}
