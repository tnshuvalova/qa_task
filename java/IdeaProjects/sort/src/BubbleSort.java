import java.util.Comparator;

public class BubbleSort<E extends Comparable> implements Sorter<E> {

    public E[] sortAsc(E[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if(array[j].compareTo(array[j+1]) > 0) {
                    E c = array[j];
                    array[j] = array[j+1];
                    array[j+1] = c;
                }
            }
        }
        return array;
    }

    public E[] sortDesc(E[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length-i-1; j++) {
                if(array[j].compareTo(array[j+1]) < 0) {
                    E c = array[j];
                    array[j] = array[j+1];
                    array[j+1] = c;
                }
            }
        }
        return array;
    }
}
