package academy.pocu.comp3500.assignment2;

import academy.pocu.comp3500.assignment2.datastructure.ArrayList;

public final class Indent {
    private final String data;
    private Indent prev;
    private ArrayList<Indent> next;

    public Indent(String data, Indent prev) {
        this.data = data;
        this.prev = prev;
        this.next = new ArrayList<>(32);
    }

    public String getData() {
        return this.data;
    }

    public Indent getPrev() {
        return this.prev;
    }

    public ArrayList<Indent> getNext() {
        return this.next;
    }

    public void discard() {

    }
}