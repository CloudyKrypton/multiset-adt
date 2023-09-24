import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)  {
        List<MultiSet> multisets = new ArrayList<>();
        multisets.add(new TreeMultiSet<>());
//        multisets.add(new ArrayListMultiSet<>());
//        multisets.add(new LinkedListMultiSet<>());
        for (MultiSet multiset : multisets) {
            ArrayList<Integer> lst = new ArrayList<Integer>();
            lst.add(500);
            lst.add(1000);
            lst.add(2000);
            lst.add(4000);
            for (int n : lst){
                profileMultiSet(multiset, n);
            }
        }
    }

    public static void profileMultiSet(MultiSet my_input, int n) {

    }
}