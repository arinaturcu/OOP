import java.util.Iterator;

class MyIterator<T> implements Iterator<T> {
    private Node<T> current;

    public MyIterator(CircularList2020<T> circularList) {
        current = circularList.getHead();
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T data = current.getData();
        current = current.getNext();
        return data;
    }
}

class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public T getData() {
        return data;
    }
}

public class CircularList2020<E> implements Iterable<E> {
    Node<E> head;

    public CircularList2020() {
        head = null;
    }

    public Node<E> getHead() {
        return head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contains(E element) {
        if (isEmpty()) return false;
        if (head.getData() == element) return true;

        Node<E> current = head.getNext();
        while (current != head) {
            if (current.getData() == element) return true;
            current = current.getNext();
        }

        return false;
    }

    public boolean add(E element) {
        if (this.contains(element))
            return false;

        Node<E> node = new Node<>(element);

        if (head == null) {
            head = node;
            head.setNext(head);
            head.setPrev(head);
            return true;
        }

        head.getPrev().setNext(node);
        node.setPrev(head.getPrev());
        node.setNext(head);
        head.setPrev(node);
        return true;
    }

    public boolean remove(E element) {
        if (!this.contains(element)) return false;
        Node<E> current = head;

        if (head.getData() == element) {
            head.getPrev().setNext(head.getNext());
            head.getNext().setPrev(head.getPrev());
            head = head.getNext();
            return true;
        }

        current = head.getNext();
        while (current != head) {
            if (current.getData() == element) {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<>(this);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder listString = new StringBuilder("[ ");

        Iterator<E> it = iterator();
        E data = it.next();
        listString.append(data).append(" ");

        data = it.next();
        while (data != head.data) {
            listString.append(data).append(" ");
            data = it.next();
        }

        return listString.toString() + "]";
    }
}

class Main {
    public static void main(String[] args) {
        CircularList2020<Integer> list = new CircularList2020<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(12);
        list.add(72);
        list.add(9);

        System.out.println(list);

        list.remove(1);
        list.remove(4);
        list.remove(9);

        System.out.println(list);
    }
}
