import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Tree <T extends Comparable<T>> {

    private T root;
    private List<Tree<T>> subtrees;

    /*Initialize a new Tree with the given root value and subtrees.

    If <root> is None, the tree is empty.
    Precondition: if <root> is None, then <subtrees> is empty.*/
    public Tree(T root, List<Tree<T>> subtrees) {
        if (root != null) {
            this.root = root;
            this.subtrees = subtrees;
        }
    }

    public Tree(T root) {
        this.root = root;
        this.subtrees = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int length() {
        if (this.isEmpty()) {
            return 0;
        } else {
            int size = 1;
            for (Tree<T> subtree : subtrees) {
                size += subtree.length();
            }
            return size;
        }
    }

    public int count(T item) {
        if (this.isEmpty()) {
            return 0;
        } else {
            int num = 0;
            if (this.root == item) {
                num += 1;
            }
            for (Tree<T> subtree: this.subtrees) {
                num += subtree.count(item);
            }
            return num;
        }
    }

    public String toString() {
        return this.strIndented();
    }

    private String strIndented(int depth) {
        if (this.isEmpty()) {
            return "";
        } else {
            String s = "  ".repeat(depth) + this.root + '\n';
            for (Tree <T> subtree : this.subtrees) {
                s += subtree.strIndented(depth + 1);
            }
            return s;
        }
    }

    private String strIndented() {
        if (this.isEmpty()) {
            return "";
        } else {
            String s = "  ".repeat(0) + this.root + '\n';
            for (Tree <T> subtree : this.subtrees) {
                s += subtree.strIndented(0);
            }
            return s;
        }
    }

    public double average() {
        if (this.isEmpty()) {
            return 0.0;
        } else {
            ArrayList<Integer> myList = this.averageHelper();
            int total = myList.get(0);
            int count = myList.get(1);
            return (double) total / count;
        }
    }

    private ArrayList<Integer> averageHelper() {
        if (this.isEmpty()) {
            ArrayList<Integer> myList = new ArrayList<>();
            myList.add(0);
            myList.add(0);
            return myList;
        } else {
            int total = (int) this.root;
            int size = 1;
            for (Tree<T> subtree : this.subtrees) {
                ArrayList<Integer> averages = subtree.averageHelper();
                int subtree_total = averages.get(0);
                int subtree_size = averages.get(1);
                total += subtree_total;
                size += subtree_size;
            }
            ArrayList<Integer> myList = new ArrayList<>();
            myList.add(total);
            myList.add(size);
            return myList;
        }
    }

    public boolean equals(Tree <T> other) {
        if (this.isEmpty() && other.isEmpty()) {
            return true;
        } else if (this.isEmpty() || other.isEmpty()) {
            return false;
        } else {
            if (this.root.compareTo(other.root) != 0) {
                return false;
            }
            if (this.subtrees.size() != other.subtrees.size()) {
                return false;
            }
            return this.subtrees.equals(other.subtrees);
        }
    }

    public boolean contains(T item) {
        if (this.isEmpty()) {
            return false;
        }

        if (this.root.compareTo(item) == 0) {
            return true;
        } else {
            for (Tree<T> subtree : this.subtrees) {
                if (subtree.contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<T> leaves() {
        if (this.isEmpty()) {
            return new ArrayList<>();
        } else if (this.subtrees.isEmpty()) {
            List<T> myList = new ArrayList<>();
            myList.add(this.root);
            return myList;
        } else {
            List<T> leaves = new ArrayList<>();
            for (Tree<T> subtree : this.subtrees) {
                leaves.addAll(subtree.leaves());
            }
            return leaves;
        }
    }

//    -------------------------------------------------------------------------
//            Mutating methods
//    -------------------------------------------------------------------------
    public boolean deleteItem(T item) {
        if (this.isEmpty()) {
            return false;
        } else if (this.root.compareTo(item) == 0) {
            this.deleteRoot();
            return true;
        } else {
            for (Tree<T> subtree : this.subtrees) {
                boolean deleted = subtree.deleteItem(item);
                if (deleted && subtree.isEmpty()) {
                    this.subtrees.remove(subtree);
                    return true;
                } else if (deleted) {
                    return true;
                } else {
                    ;
                }
            }
            return false;
        }
    }

    private void deleteRoot() {
        if (this.subtrees.isEmpty()) {
            this.root = null;
        } else {
            Tree<T> chosen_subtree = this.subtrees.remove(this.subtrees.size() - 1);

            this.root = chosen_subtree.root;
            this.subtrees.addAll(chosen_subtree.subtrees);
        }
    }

    private T extractLeaf() {
        if (this.subtrees.isEmpty()) {
            T old_root = this.root;
            this.root = null;
            return old_root;
        } else {
            T leaf = this.subtrees.get(0).extractLeaf();
            if (this.subtrees.get(0).isEmpty()) {
                this.subtrees.remove(0);
            }
            return leaf;
        }
    }

    public void insert(T item) {
        if (this.isEmpty()) {
            this.root = item;
        } else if (this.subtrees.isEmpty()) {
            this.subtrees = new ArrayList<>();
            this.subtrees.add(new Tree<>(item));
        } else {
            Random random = new Random();
            if (random.nextInt(3 - 1 + 1) + 1 == 3) {
                this.subtrees.add(new Tree<>(item));
            } else {
                int subtree_index = random.nextInt(this.subtrees.size());
                this.subtrees.get(subtree_index).insert(item);
            }
        }
    }

    public boolean insertChild(T item, T parent) {
        if (this.isEmpty()) {
            return false;
        } else if (this.root.compareTo(parent) == 0) {
            this.subtrees.add(new Tree<>(item));
            return true;
        } else {
            for (Tree<T> subtree : this.subtrees) {
                if (subtree.insertChild(item, parent)) {
                    return true;
                }
            }
            return false;
        }
    }

}