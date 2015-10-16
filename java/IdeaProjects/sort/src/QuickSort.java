public class QuickSort<E extends Comparable>{
    public E[] sortAsc(E[] array, int left, int right) {
        E piv = array[left];
        int l = left;
        int r = right;

        while (l <= r) {
            while (array[l].compareTo(piv) < 0) {
                l++;
            }

            while (array[r].compareTo(piv) > 0) {
                r--;
            }

            if(l <= r) {
                E c = array[l];
                array[l] = array[r];
                array[r] = c;
                l++;
                r--;
            }

            if(left < r) {
                sortAsc(array, left, r);
            }

            if(l < right) {
                sortAsc(array, l, right);
            }
        }
        return array;
    }
}
