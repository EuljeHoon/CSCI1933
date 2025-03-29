/**
 * Written by Jehoon Park, park2836, 5827316 and Madhav Menon, menon082, 5583844
 */
public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> head;
    private int size;
    private boolean isSorted = true;

    public LinkedList() {
        isSorted = true;
        head = null;
    }
    @Override
    public boolean add(T element) {
        if(element == null) {
            return false;
        }
        else {
            Node<T> addNode = new Node<>(element); // node to be added at the end of the list
            if(head == null) { // if list is empty
                head = addNode;
                size++;
                isSortedHelper();
            }
            else { // if list is not empty
                Node<T> tempNode = head;
                while(tempNode.getNext() != null) { // traverses through list until a node that has a null value for next aka the last node in the list
                    tempNode = tempNode.getNext();
                }
                tempNode.setNext(addNode);  // Adds node to the end of the linked list
                size++;

                isSortedHelper();
            }
            return true;
        }
    }
    @Override
    public boolean add(int index, T element) {
        if(element == null || index < 0 || index >= size) { // if element is null or index is out of bounds, return false
            return false;
        }
        else {
            Node<T> addNode = new Node<>(element);
            if(index == 0) {   // if we want to add node at the start of the linked list
                addNode.setNext(head);
                head = addNode;
                size++;

                isSortedHelper();
            }
            else {
                Node<T> tempNode = head;
                for(int i = 0 ; i < index-1; i++) { // stops when tempNode is the node before where we want to add our node
                    tempNode = tempNode.getNext();
                }
                addNode.setNext(tempNode.getNext()); // sets the next variable of the new node to the node that would come after it in the updated list
                tempNode.setNext(addNode); // sets the next variable of the previous node to the new node
                size++;

                isSortedHelper();
            }
            return true;
        }
    }
    @Override
    public void clear() {
        head = null;
        size = 0;
        isSorted = true;
    }
    @Override
    public T get(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        else {
            Node<T> tempNode = head;
            for(int i = 0; i < index; i++) {
                tempNode = tempNode.getNext();
            }

            return tempNode.getData();
        }
    }
    @Override
    public int indexOf(T element) {
        if(element == null) {
            return -1;
        }
        Node<T> tempNode = head;
        int index = 0; // keeps track of which index is currently being looked at. If the element is found in a certain index, that index is returned
        while (tempNode != null) { // loop is run as long as the current node is not null. If it is null, we are at the end of the list and have not found the element
            if (isSorted && tempNode.getData().compareTo(element) < 0) {
                return -1;
            }
            if (tempNode.getData().compareTo(element) == 0) {
                return index;
            }
            index++;
            tempNode = tempNode.getNext();
        }
        return -1;
    }
    @Override
    public boolean isEmpty() {
        if(head == null) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void sort() {
        if(size <= 1) {
            return;
        }

        Node<T> current = head;
        Node<T> index = null;
        T temp;

        while(current != null) {
            index = current.getNext();

            while(index != null) {
                if(current.getData().compareTo(index.getData()) > 0) {
                    temp = current.getData();
                    current.setData(index.getData());
                    index.setData(temp);
                }
                index = index.getNext();
            }
            current = current.getNext();
        }  // idea: https://www.geeksforgeeks.org/how-to-sort-a-linkedlist-in-java/
        isSorted = true;
    }
    @Override
    public T remove(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        else {
            Node<T> tempNode = head;

            if(index == 0) {
                head = tempNode.getNext();
                size--;
                return tempNode.getData(); // initial head node
            }
            else {
                for(int i = 0; i < index-1; i++) {
                    tempNode = tempNode.getNext();
                }
                Node<T> removedNode = tempNode.getNext();
                tempNode.setNext(tempNode.getNext().getNext());
                size--;

                isSortedHelper();

                return removedNode.getData();
            }
        }
    }
    @Override
    public void equalTo(T element) {
        Node<T> tempNode = head;
        int index = 0;

        while(tempNode != null) {
            if(tempNode.getData().compareTo(element) != 0) {
                remove(index);
                tempNode = tempNode.getNext();
            }
            else {
                index++;
                tempNode = tempNode.getNext();
            }
        }
        size = index;
        isSorted = true;
    }
    @Override
    public void reverse() {
        if(head.getNext() == null) { // if there is only one node in the list, nothing happens
            return;
        }
        else {
            Node<T> prev = null;

            while(head != null) {
                Node<T> nextNode = head.getNext();
                head.setNext(prev);
                prev = head;
                head = nextNode;
            }
            head = prev;
            isSortedHelper();
        } // idea: https://www.youtube.com/watch?v=NhapasNIKuQ
    }

    @Override
    public void merge(List<T> otherList) {
        if(otherList == null) {
            return;
        }
        LinkedList<T> other = (LinkedList<T>) otherList;
        sort();
        other.sort();

        LinkedList<T> mergedList = new LinkedList<>();

        Node<T> ptr = head;
        Node<T> ptr2 = other.head;

        while(ptr != null && ptr2 != null) {
            if(ptr.getData().compareTo(ptr2.getData()) < 0) {
                mergedList.add(ptr.getData());
                ptr = ptr.getNext();
            }
            else if(ptr.getData().compareTo(ptr2.getData()) > 0) {
                mergedList.add(ptr2.getData());
                ptr2 = ptr2.getNext();
            }
        }
        if(ptr != null) {
            while(ptr != null) {
                mergedList.add(ptr.getData());
                ptr = ptr.getNext();
            }
        }
        else if(ptr2 != null) {
            while(ptr2 != null) {
                mergedList.add(ptr2.getData());
                ptr2 = ptr2.getNext();
            }
        }
        size = mergedList.size();
        isSorted = true;
    }

    @Override
    public void pairSwap() {
        if(size <= 1) {
            return;
        }
        Node<T> element1 = head;
        Node<T> element2 = head.getNext();
        T temp;

        while(element1 != null && element2 != null) {
            temp = element1.getData();
            element1.setData(element2.getData());
            element2.setData(temp);

            element1 = element1.getNext().getNext();
            if(element2.getNext() == null) {
                break;
            }
            else {
                element2 = element2.getNext().getNext();
            }
        }
    }

    public String toString() {
        String outputString = "";
        Node<T> tempNode = head;

        while(tempNode != null) {
            outputString += tempNode.getData() + "\n";
            tempNode = tempNode.getNext();
        }
        return outputString;
    }
    public boolean isSorted() {
        return isSorted;
    }
    public void isSortedHelper() {
        Node<T> tempNode1 = head;
        Node<T> tempNode2 = head.getNext();

        while(tempNode1 != null && tempNode2 != null) {
            if(tempNode1.getData().compareTo(tempNode2.getData()) > 0) {
                isSorted = false;
                return;
            }
            else {
                tempNode1 = tempNode1.getNext();
                tempNode2 = tempNode2.getNext();
            }
        }
        isSorted = true;
    }
}