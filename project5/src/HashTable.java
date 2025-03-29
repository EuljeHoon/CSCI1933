import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashTable<T>{
    NGen<T>[] hashTable;

    //Integer variable for size of hash table
    int tableSize;

    //TODO: Create a constructor that takes in a length and initializes the hash table array
    public HashTable(int tableLength) {
        hashTable = new NGen[tableLength];
        tableSize = tableLength;
    }

    /**
     * this is the hash function that calls toString() to get string representation and then do .length()
     * to create the array.
     */
    //TODO: Implement a simple hash function
    public int hash1(T item) {
        int index = item.toString().length() % tableSize;
        return index;
    }

    /**
     *This is different and improved one. Each character increase 7 from right to left, for example 7^2 < 7^1 < 7^0
     * I used % tableSize because I had to take care of the case that the hash function is bigger than the number of buckets.
     */
    //TODO: Implement a second (different and improved) hash function
    public int hash2(T item) {
        int index = 0;
        for (int i = 0; i < item.toString().length(); i++) {
            index = (((index * 7) % tableSize) + item.toString().charAt(i)) % tableSize;
        }
        return index;
    }

    //TODO: Implement the add method which adds an item to the hash table using your best performing hash function
    // Does NOT add duplicate items
    public void add(T item) {
        int index = hash2(item);
        NGen<T> node = hashTable[index];
        NGen<T> newNode = new NGen<>(item, null);
        if (node != null) {
            while(node.getNext() != null) {
                if(node.getData().equals(item)) {
                    return;
                }
                node = node.getNext(); //Update the value for node.
            }
            node.setNext(newNode); // set the bucket to the next node.
        }
        else { // check when the node is null
            hashTable[index] = newNode;
        }
    }

    // ** Already implemented -- no need to change **
    // Adds all words from a given file to the hash table using the add(T item) method above
    @SuppressWarnings("unchecked")
    public void addWordsFromFile(String fileName) {
        Scanner fileScanner = null;
        String word;
        try {
            fileScanner = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("File: " + fileName + " not found.");
            System.exit(1);
        }
        while (fileScanner.hasNext()) {
            word = fileScanner.next();
            word = word.replaceAll("\\p{Punct}", ""); // removes punctuation
            this.add((T) word);
        }
    }

    //TODO: Implement the display method which prints the indices of the hash table and the number of words "hashed"
    // to each index. Also prints:
    // - total number of unique words
    // - number of empty indices
    // - number of nonempty indices
    // - average collision length
    // - length of longest chain

    //Used linked list for the display the hash table with making the node.
    public void display() {
        int unique = 0;
        int nonEmpty = 0;
        int longestChain = 0;
        for (int i = 0; i < tableSize; i++) {
            int chainLength = 0;
            NGen<T> node = hashTable[i];
            while (node != null) {
                chainLength++;
                unique++;
                node = node.getNext(); //Update the value for node
            }
            if (chainLength == 0) {
                continue;
            }
            longestChain = Math.max(longestChain, chainLength);
            nonEmpty++;
            System.out.println(i + ": " + chainLength); //it prints out the chain length.
        }
        int empty = tableSize - nonEmpty; //For the empty size, distract nonEmpty from total table size.
        int collisionLength = unique / nonEmpty; //For the length of collision, divide unique with nonEmpty.

        //Printing Statements
        System.out.println("# unique words : " + unique);
        System.out.println("# empty indices : " + empty);
        System.out.println("# nonempty indices : " + nonEmpty);
        System.out.println("average collision length : " + (double) collisionLength); //make the output as double with (double)
        System.out.println("length of longest chain: " + longestChain);
    }

    /**
     * After I tested out both hash function, I realized hash2 is better hash function. Hash 1 gave more collision than the hash2.
     * And also hash1 print out less table than the hash 2.
     * Prime number makes the difference because if I put prime number, it reduces the occurrence of collisions.
     * However, I am not sure about odd and even numbers. Because they don't make big difference like prime number.
     */
    // TODO: Create a hash table, store all words from "canterbury.txt", and display the table
    //  Create another hash table, store all words from "keywords.txt", and display the table
    public static void main(String args[]) {
        HashTable<String> hashTable = new HashTable<>(150);
        hashTable.addWordsFromFile("canterbury.txt");
        //hashTable.addWordsFromFile("keywords.txt");
        //hashTable.addWordsFromFile("gettysburg.txt");
        //hashTable.addWordsFromFile("proverbs.txt");
        //hashTable.addWordsFromFile("that_bad.txt");
        hashTable.display();
    }
}
