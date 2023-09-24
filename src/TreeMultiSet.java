public class TreeMultiSet<T extends Comparable<T>> extends MultiSet<T> {
    private final Tree<T> tree = new Tree<T>(null);

    @Override
    public boolean add(T item) {
        this.tree.insert(item);
        return true;
    }
    @Override
    public void remove(T item) {
        this.tree.deleteItem(item);
    }

    @Override
    public boolean contains(T item) {
        return this.tree.contains(item);
    }

    @Override
    public boolean isEmpty() {
        return this.tree.isEmpty();
    }

    @Override
    public int count(T item) {
        return this.tree.count(item);
    }

    @Override
    public int size() {
        return tree.length();
    }
}
