//public class ArrayListPr <T extends Comparable<T>> implements List<T> {
//    private T[] newArray;
//    private boolean isSorted;
//    private int size;
//    public ArrayListPr() {
//        isSorted = true;
//        size = 0;
//        newArray = (T[]) new Comparable[2];
//    }
//    public boolean add(T element) {
//        return false;
//    }
//    public boolean add(int index, T element) {
//        return false;
//    }
//    public void clear() {
//        newArray = (T[]) new Comparable[2];
//        size = 0;
//        isSorted = true;
//    }
//    public T get(int index) {
//        if (index < 0 || index > newArray.length) {
//            return null;
//        }
//        return newArray[index];
//    }
//    public int indexOf(T element) {
//        if(element == null) {
//            return -1;
//        }
//        for(int i = 0; i < newArray.length; i++) {
//            if(element.equals(newArray[i])) {
//                return i;
//            }
//        }
//        return -1;
//    }
//    public boolean isEmpty() {
//        return newArray[0] == null;
//    }
//    public int size() {
//        return size;
//    }
//    public void sort() {
//        if(isSorted == true) {
//            return;
//        }
//        int num = 1;
//        while(num < newArray.length) {
//            for(int i = num; i >= 1; i--) {
//                T current = newArray[i];
//                for(int j = i - 1; j >= 0; j--) {
//                    if (newArray[i].compareTo(newArray[j]) < 0) {
//                        newArray[i] = newArray[j];
//                        newArray[j] = current;
//                    }
//                }
//            }
//            num++;
//        }
//        isSorted = true;
//    }
//    public T remove(int index) {
//        if (index < 0 || index >= size()) {
//            return null;
//        }
//        T remove = newArray[index];
//        T[] temp = (T[]) new Comparable[newArray.length];
//        int i = 0;
//        while(i < temp.length) {
//            for(int j = 0; j < newArray.length; j++) {
//                if(temp[i].equals(remove)) {
//                    temp[i] = newArray[j];
//                    i++;
//                }
//            }
//        }
//        size--;
//        newArray = temp;
//        fixSorted();
//        return remove;
//    }
//    public void fixSorted() {
//        for (int i = 0; i < size() - 1; i++) {
//            if (get(i).compareTo(get(i + 1)) > 0) {
//                isSorted = false;
//                return;
//            }
//        }
//        isSorted = true;
//    }
//    public static void main(String[] args) {
//        int[] a = {5, 2, 1, 6, 8, 4, 0};
////        int num = 1;
////        while (num < a.length) {
////            for (int i = num; i >= 1; i--) {
////                int current = a[i];
////                for (int j = i - 1; j >= 0; j--) {
////                    if (a[i] < a[j]) {
////                        a[i] = a[j];
////                        a[j] = current;
////                    }
////                }
////            }
////            num++;
////        }
////        for(int i = 0; i < a.length; i++) {
////            System.out.println(a[i]);
////        }
//        //------------------------------------------
//        int[] newArray = new int[a.length];
//        int removeIndex = 3;
//        int i = 0;
//        while(i < newArray.length) {
//            for(int j = 0; j < a.length; j++) {
//                if(newArray[i] == a[j]) {
//                    newArray[i] = a[j];
//                    i++;
//                }
//            }
//        }
//        for(int i = 0; i < a.length; i++) {
//            System.out.println(a[i]);
//        }
//    }
//
//}
