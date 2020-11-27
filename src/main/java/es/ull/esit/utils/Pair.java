package es.ull.esit.utils;
import java.util.Objects;

/**
 *
 * @class Pair
 * @brief Class to represent a generic pair of objects
 *
 */
public class Pair<F, S> {
    public final F first;       /**< First pair value. */
    public final S second;      /**< Second pair value. */

    /**
     * @brief Constructor
     * @param first -> first pair value
     * @param second -> second pair value
     */
    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @brief Check is a pair is equal to another
     * @param o -> comparison
     * @return boolean -> True or false
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?, ?> p = (Pair<?, ?>) o;
        return Objects.equals(p.first, first) && Objects.equals(p.second, second);
    }

    /**
     * @brief HashCode of the pair
     * @return int -> hashCode
     */
    @Override
    public int hashCode() {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    /**
     * @brief Creates a new pair
     * @param a -> first pair value
     * @param b -> second pair value
     * @return Pair -> created pair
     */
    public static <A, B> Pair <A, B> create(A a, B b) {
        return new Pair<>(a, b);
    }
}
