import java.util.*;

public class CycleList<T> {
    private T data;
    private CycleList<T> next;

    public CycleList(T data) {
        this.data = data;
        this.next = this;
    }

    public CycleList(T[] data) {
        CycleList<T> current = this;
        Iterator<T> it = Arrays.asList(data).iterator();

        current.data = it.next();
        while (it.hasNext()) {
            current.next = new CycleList<>(it.next());
            current = current.next;
        }
        current.next = this;
    }

    public T getData() {
        return this.data;
    }

    public CycleList<T> getNext() {
        return this.next;
    }

    public void add(T data) {
        CycleList<T> another = new CycleList<>(data);

        if (this.getNext() == null) {
            this.next = another;
            another.next = this;
        } else {
            CycleList<T> save = this.getNext();
            this.next = another;
            another.next = save;
        }
    }

    public void fill(List<T> list) {
        for (T element : list) {
            this.add(element);
        }
    }

    public Deque<T> toDeque() {
        Deque<T> stack = new LinkedList<>();
        CycleList<T> current = this.getNext();
        T save = this.getData();

        while (!save.equals(current.getData())) {
            stack.push(current.getData());
            current = current.getNext();
        }

        return stack;
    }

    public CycleList<T> copy() {
        CycleList<T> another = new CycleList<>(this.getData());
        Deque<T> stack = this.toDeque();

        while (!stack.isEmpty()) {
            another.add(stack.poll());
        }

        return another;
    }

    @Override
    public String toString() {
        CycleList<T> current = this.getNext();
        T save = this.getData();
        StringBuilder str = new StringBuilder("__ -- " + save.toString());

        while (current != null && !save.equals(current.getData())) {
            str.append(" -- ");
            str.append(current.getData());
            current = current.getNext();
        }
        str.append("__");

        return str.toString();
    }
}
