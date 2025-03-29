/**
 * Written by Jehoon Park, park2836, 5827316 and Madhav Menon, menon082, 5583844
 */
public class ArrayList <T extends Comparable<T>> implements List<T> {
    private T[] newArray; //array that I am going to create.
    private boolean isSorted; //boolean for check if array is sorted or not.
    private int size;
    //private Object[] arr;
    public ArrayList(){
        isSorted = true;
        newArray = (T[]) new Comparable[2];
        size = 0;
    }
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        if (size() == newArray.length) {
            newArray = newArr(newArray);
        }
        newArray[size] = element;
        size ++;
        if (isSorted && size() > 1 && newArray[size - 2].compareTo(newArray[size - 1]) > 0) {
            isSorted = false;
        }
        return true;
    }
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size()) {
            return false;
        }
        if (size() == newArray.length) {
            newArray = newArr(newArray);
        }
        T temp;
        int j;
        j = index;
        while (newArray[j] != null) {
            temp = newArray[j];
            newArray[j] = element;
            element = temp;
            j++;
        }
        size++;
        newArray[j] = element;
        fixSorted();
        return true;
    }
    public void clear() {
        newArray = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return newArray[index];
    }
    public int indexOf(T element) {
        if (element == null) {
            return -1;
        }
        for (int i = 0; i < size(); i++) {
            if (element.equals(newArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public boolean isEmpty() {
        return newArray[0] == null;
    }
    public int size() {
        return size;
    }
    public void sort() {
        if (isSorted == true){
            return;
        }
        int j;
        T newElement;
        for (int i = 1; i < size(); i++) {
            newElement = newArray[i];
            for (j = i - 1; j >= 0 && newElement.compareTo(newArray[j]) < 0; j--){
                newArray[j + 1] = newArray[j];
            }
            newArray[j + 1] = newElement;
        }
        isSorted = true;
    }
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        T remove = newArray[index];
        T[] temp = (T[]) new Comparable[newArray.length];
        for (int i = 0; i < index; i++) {
            temp[i] = newArray[i];
        }
        for (int j = index + 1; j < newArray.length; j++) {
            temp[j - 1] = newArray[j];
        }
        size--;
        newArray = temp;
        fixSorted();
        return remove;
    }
    public void equalTo(T element) {
        int count = 0;
        for (int i = 0; i < size(); i++) {
            if(!(get(i).compareTo(element) == 0)) {
                newArray[i] = null;
            }
            else {
                count++;
            }
        }
        for (int j = 0; j < size(); j++) {
            if(get(j) == null) {
                for (int i = j + 1; i < size(); i++) {
                    if(get(i) != null) {
                        newArray[j] = get(i);
                        break;
                    }
                }
            }
        }
        size = count;
        isSorted = true;
    }
    public void reverse() {
        ArrayList<T> reversedList = new ArrayList<>();
        for (int i = size() - 1; i >= 0; i--) {
            reversedList.add(get(i));
        }
        newArray = reversedList.newArray;
        fixSorted();
    }
    public void merge(List<T> otherList) {
        T[] mergedList = (T[]) new Comparable[size + otherList.size()];
        if(otherList == null) {
            return;
        }
        ArrayList<T> other = (ArrayList<T>)otherList;
        sort();
        other.sort();
        int integerForNormalList = 0, integerForOtherList = 0, integerForMergedList = 0;
        while (integerForNormalList < size() && integerForOtherList < otherList.size()) {
            if(get(integerForNormalList).compareTo(other.get(integerForOtherList)) < 0) {
                mergedList[integerForMergedList] = get(integerForNormalList);
                integerForNormalList++;
                integerForMergedList++;
            }
            else if(get(integerForNormalList).compareTo(other.get(integerForOtherList)) >= 0) {
                mergedList[integerForMergedList] = other.get(integerForOtherList);
                integerForOtherList++;
                integerForMergedList++;
            }
        }
        if (integerForNormalList < size()) {
            while (integerForNormalList < size()) {
                mergedList[integerForMergedList] = get(integerForNormalList);
                integerForNormalList++;
                integerForMergedList++;
            }
        }
        else if (integerForOtherList < other.size()) {
            while (integerForOtherList < other.size()) {
                mergedList[integerForMergedList] = other.get(integerForOtherList);
                integerForOtherList++;
                integerForMergedList++;
            }
        }
        newArray = mergedList;
        size = newArray.length;
        isSorted = true;
    }
    public void pairSwap() {
        for (int i = 0; i < size(); i += 2) {
            T temp = newArray[i];
            newArray[i] = newArray[i + 1];
            newArray[i + 1] = temp;
        }
        fixSorted();
    }
    public String toString() {
        String str = "";
        for (int i = 0; i < size(); i++) {
            str += newArray[i];
            str += "\n";
        }
        return str;
    }
    //helper method for resize the array.
    public T[] newArr(T[] arr){
        T[] temp = (T[]) new Comparable[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }
    //helper function to know if list is sorted or not.
    public void fixSorted() {
        for (int i = 0; i < size() - 1; i++) {
            if (get(i).compareTo(get(i + 1)) > 0) {
                isSorted = false;
                return;
            }
        }
        isSorted = true;
    }
    public boolean isSorted() {
        return isSorted;
    }

}