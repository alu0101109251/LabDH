package top;
/**
 *
 * @class TOPTWRoute
 * @brief Class to represent the route
 *
 */
public class TOPTWRoute {
    int predecessor;        /**< Route predecessor. */
    int succesor;           /**< Route succesor. */
    int id;                 /**< Route id. */

    /**
     * @brief Constructor
     * @param pre -> predecessor
     * @param succ -> sucessor
     * @param id -> route id
     */
    TOPTWRoute(int pre, int succ, int id) {
        this.predecessor = pre;
        this.succesor = succ;
        this.id = id;
    }

    /**
     * @brief Getter
     * @return int -> predecessor
     */
    public int getPredeccesor() {
        return this.predecessor;
    }

    /**
     * @brief Getter
     * @return int -> sucessor
     */
    public int getSuccesor() {
        return this.succesor;
    }

    /**
     * @brief Getter
     * @return int -> route id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @brief Setter
     * @param pre -> predecessor
     */
    public void setPredeccesor(int pre) {
        this.predecessor = pre;
    }
    /**
     * @brief Setter
     * @param suc -> sucessor
     */
    public void setSuccesor(int suc) {
        this.succesor = suc;
    }
    /**
     * @brief Setter
     * @param id -> route id
     */
    public void setId(int id) {
        this.id = id;
    }
}
