public class SelectionSort<E extends Comparable> implements Sorter<E>{
    public E[] sortAsc(E[] array) {
        for (int i = 0; i < array.length; i++) {
            E min = array[i];
            int minInd = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j].compareTo(min) < 0) {
                    min = array[j];
                    minInd = j;
                }
            }
            if(i != minInd) {
                E c = array[i];
                array[i] = array[minInd];
                array[minInd] = c;
            }
        }
        return array;
    }
}
