package es.ull.esit.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @class ExpositoUtilities
 * @brief Different auxiliary utilities to be used along the project
 *
 * @details This class implements a number of methods which will be used in the project.
 * It will be used as a library.
 *
 */
public class ExpositoUtilities {

    public static final int DEFAULT_COLUMN_WIDTH = 10;      /**< Constant to define column width */
    public static final int ALIGNMENT_LEFT = 1;             /**< Constant to define left alignment */
    public static final int ALIGNMENT_RIGHT = 2;            /**< Constant to define right alignment */

    /**
     * @brief Method for printing files
     * @param file -> filename
     */
    public static void printFile(String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception ex) {
            Logger.getLogger(ExpositoUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(ExpositoUtilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @brief Parser to simplify strings containing undesirable characters
     * @param string -> String to be simplified
     * @return String -> simplified string
     */
    public static String simplifyString(String string) {
        string = string.replaceAll("\t", " ");
        for (int i = 0; i < 50; i++) {
            string = string.replaceAll("  ", " ");
        }
        string = string.trim();
        return string;
    }

    /**
     * @brief Method to multiply 2 double matrix
     * @param a -> Left matrix
     * @param b -> Right matrix
     * @return double[][] -> Matrix product result
     */
    public static double[][] multiplyMatrices(double[][] a, double[][] b) {
        if (a.length == 0) {
            return new double[0][0];
        }
        if (a[0].length != b.length) {
            return null;
        }
        int n = a[0].length;
        int m = a.length;
        int p = b[0].length;
        double[][] ans = new double[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return ans;
    }

    /**
     * @brief Method to get the format of a given string
     * @param string -> string to be analyzed
     * @return String -> result of the analysis
     */
    public static String getFormat(String string) {
        if (!ExpositoUtilities.isInteger(string)) {
            if (ExpositoUtilities.isDouble(string)) {
                double value = Double.parseDouble(string);
                string = ExpositoUtilities.getFormat(value);
            }
        }
        return string;
    }

    /**
     * @brief Double to string formatter
     * @param value -> value to be formatted
     * @return String -> formatted result
     */
    public static String getFormat(double value) {
        DecimalFormat decimalFormatter = new DecimalFormat("0.000");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        decimalFormatter.setDecimalFormatSymbols(symbols);
        return decimalFormatter.format(value);
    }

    /**
     * @brief Double to string formatter
     * @param value -> value to be formatted
     * @param zeros -> desired decimal precision
     * @return String -> formatted result
     */
    public static String getFormat(double value, int zeros) {
        StringBuilder format = new StringBuilder("0.");
        for (int i = 0; i < zeros; i++) {
            format.append("0");
        }
        DecimalFormat decimalFormatter = new DecimalFormat(format.toString());
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        decimalFormatter.setDecimalFormatSymbols(symbols);
        return decimalFormatter.format(value);
    }

    /**
     * @brief Auxiliary method for getting a string format
     * @param string -> string to be analyzed
     * @param width -> width of the string
     * @return String -> string format
     */
    public static String getFormat(String string, int width) {
        return ExpositoUtilities.getFormat(string, width, ExpositoUtilities.ALIGNMENT_RIGHT);
    }

    /**
     *
     * @brief Auxiliary method for getting a string format
     * @param string -> string to be analyzed
     * @param width -> width of the string
     * @param alignment -> string alignment
     * @return String -> string format
     */
    public static String getFormat(String string, int width, int alignment) {
        String format;
        if (alignment == ExpositoUtilities.ALIGNMENT_LEFT) {
            format = "%-" + width + "s";
        } else {
            format = "%" + 1 + "$" + width + "s";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String[] data = new String[]{string};
        return String.format(format, (Object[]) data);
    }

    /**
     *
     * @param strings -> strings to be analyzed
     * @param width -> width of the string
     * @return String -> string format
     */
    public static String getFormat(ArrayList<String> strings, int width) {
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            format.append("%").append(i + 1).append("$").append(width).append("s");
        }
        String[] data = new String[strings.size()];
        for (int t = 0; t < strings.size(); t++) {
            data[t] = "" + ExpositoUtilities.getFormat(strings.get(t));
        }
        return String.format(format.toString(), (Object[]) data);
    }

    /**
     *
     * @param strings -> strings to be analyzed
     * @return String -> formatted string
     */
    public static String getFormat(ArrayList<Integer> strings) {
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            format.append("%").append(i + 1).append("$").append(DEFAULT_COLUMN_WIDTH).append("s");
        }
        Integer[] data = new Integer[strings.size()];
        for (int t = 0; t < strings.size(); t++) {
            data[t] = strings.get(t);
        }
        return String.format(format.toString(), (Object[]) data);
    }

    /**
     *
     * @param strings -> strings to be analyzed
     * @param width -> string width
     * @return String -> string format
     */
    public static String getFormat(String[] strings, int width) {
        int[] alignment = new int[strings.length];
        Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
        int[] widths = new int[strings.length];
        Arrays.fill(widths, width);
        return ExpositoUtilities.getFormat(strings, widths, alignment);
    }
    
        public static String getFormat(String[][] matrixStrings, int width) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrixStrings.length; i++) {
            String[] strings = matrixStrings[i];
            int[] alignment = new int[strings.length];
            Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
            int[] widths = new int[strings.length];
            Arrays.fill(widths, width);
            result.append(ExpositoUtilities.getFormat(strings, widths, alignment));
            if (i < (matrixStrings.length - 1)) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     *
     * @param strings -> String of strings to be analyzed
     * @return String -> string format
     */
    public static String getFormat(String[] strings) {
        int[] alignment = new int[strings.length];
        Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
        int[] widths = new int[strings.length];
        Arrays.fill(widths, ExpositoUtilities.DEFAULT_COLUMN_WIDTH);
        return ExpositoUtilities.getFormat(strings, widths, alignment);
    }

    /**
     *
     * @param strings -> String of strings to be analyzed
     * @param width -> string width
     * @return String -> string format
     */
    public static String getFormat(String[] strings, int[] width) {
        int[] alignment = new int[strings.length];
        Arrays.fill(alignment, ExpositoUtilities.ALIGNMENT_RIGHT);
        return ExpositoUtilities.getFormat(strings, width, alignment);
    }

    /**
     *
     * @param strings -> String of strings to be analyzed
     * @param width -> string width
     * @param alignment -> string alignment
     * @return String -> string foramt
     */
    public static String getFormat(String[] strings, int[] width, int[] alignment) {
        StringBuilder format = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (alignment[i] == ExpositoUtilities.ALIGNMENT_LEFT) {
                format.append("%").append(i + 1).append("$-").append(width[i]).append("s");
            } else {
                format.append("%").append(i + 1).append("$").append(width[i]).append("s");
            }
        }
        String[] data = new String[strings.length];
        for (int t = 0; t < strings.length; t++) {
            data[t] = "" + ExpositoUtilities.getFormat(strings[t]);
        }
        return String.format(format.toString(), (Object[]) data);
    }

    /**
     * @brief Check is a given number is integer
     * @param str -> string containing the number
     * @return boolean -> True of false
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * @brief Check is a given number is double
     * @param str -> string containing the number
     * @return boolean -> True of false
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * @brief Check is a graph is acyclic
     * @param distanceMatrix -> Matrix distances
     * @return boolean -> True or false
     */
    public static boolean isAcyclic(int[][] distanceMatrix) {
        int numRealTasks = distanceMatrix.length - 2;
        int node = 1;
        while (node <= numRealTasks) {
            if (ExpositoUtilities.thereIsPath(distanceMatrix, node)) {
                return false;
            }
            node++;
        }
        return true;
    }

    /**
     * @brief Checks if a given node is reachable
     * @param distanceMatrix -> Matrix distances
     * @param node -> goal node
     * @return boolean -> True or false
     */
    public static boolean thereIsPath(int[][] distanceMatrix, int node) {
        HashSet<Integer> visits = new HashSet<>();
        HashSet<Integer> noVisits = new HashSet<>();
        for (int i = 0; i < distanceMatrix.length; i++) {
            if (i != node) {
                noVisits.add(i);
            }
        }
        visits.add(node);
        while (!visits.isEmpty()) {
            Iterator<Integer> it = visits.iterator();
            int toCheck = it.next();
            visits.remove(toCheck);
            for (int i = 0; i < distanceMatrix.length; i++) {
                if (toCheck != i && distanceMatrix[toCheck][i] != Integer.MAX_VALUE) {
                    if (i == node) {
                        return true;
                    }
                    if (noVisits.contains(i)) {
                        noVisits.remove(i);
                        visits.add(i);
                    }
                }
            }
        }
        return false;
    }
}
