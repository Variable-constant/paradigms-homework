package expression;
import expression.generic.GenericTabulator;
import expression.generic.Tabulator;

public class Main {
    public static void main(String[] args) {
        Tabulator tabulator = new GenericTabulator();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
        }
        Object[][][] res;
        try {
            res = tabulator.tabulate(args[0].substring(1), sb.toString(), -2, 2, -2, 2, -2, 2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return;
        }
        for (Object[][] i : res) {
            for (Object[] j : i) {
                for (Object k : j) {
                    System.out.print(k + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }
}
