package es.ull.esit.utilities;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @class PowerSet
 * @brief Class to calculate every subset of a given set
 *
 */
public class PowerSet<E> implements Iterator<Set<E>>, Iterable<Set<E>> {

    private final E[] arr;          /**< Array set. */
    private final BitSet bset;      /**< Bitset. */

    /**
     * @brief Class constructor
     * @param set -> set to calculate its subsets
     */
    @SuppressWarnings("unchecked")
    public PowerSet(Set<E> set) {
        this.arr = (E[]) set.toArray();
        this.bset = new BitSet(this.arr.length + 1);
    }

    /**
     * @brief Check if a subset has a next subset
     * @return boolean -> True or false
     */
    @Override
    public boolean hasNext() {
        return !this.bset.get(this.arr.length);
    }

    /**
     * @brief Calculate next subset
     * @return Set<E> -> set result
     */
    @Override
    public Set<E> next() {
        Set<E> returnSet = new TreeSet<>();
        for (int i = 0; i < this.arr.length; i++) {
            if (this.bset.get(i)) {
                returnSet.add(this.arr[i]);
            }
        }
        for (int i = 0; i < this.bset.size(); i++) {
            if (!this.bset.get(i)) {
                this.bset.set(i);
                break;
            } else {
                this.bset.clear(i);
            }
        }
        return returnSet;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not Supported!");
    }

    @Override
    public Iterator<Set<E>> iterator() {
        return this;
    }
}