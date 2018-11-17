import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.function.Predicate;

public class Cogwheel<T> {
    private CycleList<T> list;
    private T starter;
    private Cogwheel<T> next;
    private StringBuilder sequence;

    public Cogwheel(CycleList<T> list) {
        this.list = list;
        this.starter = this.list.getData();
    }

    public Cogwheel(int length, CycleList<T> list) {
        this(list);
        if (length <= 0)    throw new InvalidParameterException("The first parameter should be a positive interger.");

        Cogwheel<T> save;
        StringBuilder str = new StringBuilder();

        for (int i = 1; i < length; i++) {
            save = this.next;
            this.next = new Cogwheel<>(list.copy());
            this.next.sequence = new StringBuilder(str.toString());
            this.next.next = save;
            str.append(this.next.list.getData());
        }
        this.sequence = new StringBuilder(str);
    }

        @Override
    public String toString() {
        Cogwheel current = this;
        StringBuilder str = new StringBuilder();

        while (current != null) {
            str.append(current.list.toString());
            str.append("\n");
            current = current.next;
        }

        return str.toString();
    }

    public StringBuilder getSequence() {
        return this.sequence;
    }

    public StringBuilder getFullSequence() {
        StringBuilder str = new StringBuilder(this.list.getData().toString());
        str.append(this.getSequence());
        return str;
    }

    public void updateSequence() {
        this.sequence = new StringBuilder(this.next.list.getData().toString());
        this.sequence.append(this.next.getSequence());      // Saving time
    }

    public void turn() {
        this.list = this.list.getNext();
        if (this.next != null && this.list.getData().equals(this.starter)) {
            this.next.turn();
            this.updateSequence();
        }
    }

    public void action(PrintWriter writer) {
        StringBuilder first = this.getFullSequence();
        StringBuilder strb = new StringBuilder(first.toString());

        do {
            writer.println(strb.toString());
            this.turn();
            strb = this.getFullSequence();
        } while(!strb.toString().equals(first.toString()));
    }

    public StringBuilder action(Predicate<String> pred) {
        StringBuilder first = this.getFullSequence();
        StringBuilder strb = new StringBuilder(first.toString());

        do {
            if (pred.test(strb.toString()))    return strb;
            this.turn();
            strb = this.getFullSequence();
        } while(!strb.toString().equals(first.toString()));

        return null;
    }
}
