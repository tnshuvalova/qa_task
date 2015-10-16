public class GnomeSort<E extends Comparable> implements Sorter<E>{
    public E[] sortAsc(E[] array) {
        for (int i = 1; i < array.length; i++) {
            if(i == 0) i++;
            if(array[i].compareTo(array[i-1]) < 0) {
                E c = array[i];
                array[i] = array[i-1];
                array[i-1] = c;
                i-=2;
            }
        }
        return array;
    }
}
