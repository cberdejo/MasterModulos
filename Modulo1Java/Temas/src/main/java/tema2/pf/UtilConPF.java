package tema2.pf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class UtilConPF {
    public static <T,R> List<R> map(List<T> list, Function<T,R> mapper) {
        List<R> sol = new ArrayList<>();
        for (T elem : list)
            sol.add(mapper.apply(elem));
        return sol;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> pred) {
        List<T> sol = new ArrayList<>();
        for (T elem : list)
            if (pred.test(elem))
                sol.add(elem);
        return sol;
    }
}
