package java7.exit;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Description.
 *
 * @author: huang
 * Date: 18-6-11
 */
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 3437482786855580091L;

    private List<Integer> products;
    private int first;
    private int last;

    public Task(List<Integer> products, int first, int last) {
        this.products = products;
        this.first = first;
        this.last = last;
    }

    @Override
    protected void compute() {

        if (last - first > 10) {
            int middel = ( last + first ) / 2;
            Task task1 = new Task(products, first, middel + 1);
            Task task2 = new Task(products, middel + 1, last);
            invokeAll(task1, task2);
        } else {
            System.out.println(Thread.currentThread().getName() + ": compute end: " + (last - first));
        }
    }
}
