/**
 * File for Sorting the data array by title using the selection sort, 
 * Loop up each seek value in the data array, using a recursive binary search 
 * Create an found array to store whether the titles were found or not 
 * @author Marie Nguyen 
 * @version April 14th, 2022
 */
import java.util.ArrayList;

public class DataSearchExtended {    
    // Construction: Intentionally empty 
    public DataSearchExtended(){}

    /**
     * sortBook method using selection sort to sort the passed-in array in alphabetically according to the Book title 
     * @param bookArray passed-in array to be sorted 
     * @return a sorted array for next step 
     */
    public Book[] sortBook(Book[] bookArray){
        // Loop through the passed-in array 
        for (int i = 0; i < bookArray.length - 1; i++){
            // Find the book's title that comes first in alphabetical order in all book's titles and place it at the start of the array 
            int minIndex = i; // Assume that book at index i has the title's int value is the smallest 
            int j;
            // Loop through all the remaining book's titles to find the one that its title comes first in alphabetical order  
            for (j = i + 1; j < bookArray.length; j++){
                /* Compare book's title at index j to book's title at minIndex 
                if the book's title at index j come before, change the minIndex to j;
                negative value shows that the book at index j comes first */
                if (bookArray[j].getTitle().compareTo(bookArray[minIndex].getTitle()) < 0){
                    minIndex = j;
                }
            }
            /** Swap the book at minIndex we just found with the value at front of the array. 
            e.g: Swap the first smallest with the first element, second smallest with second element */
            Book temp = bookArray[minIndex];
            bookArray[minIndex] = bookArray[i];
            bookArray[i] = temp;
        }
        return bookArray;
    }

    /**
     * foundBook method invoke recursive binary search to find whether each title 
     * in the titleArray exists in the bookArray or not
     * @param bookArray given sorted bookArray 
     * @param titleArray given array contains titles to look for existances in the bookArray
     * @return a String array stores "Yes" at index i if the tile at index i in titleArray was found; 
     * otherwise the value at that index is No
     */
    public String[] foundBook(Book[] bookArray, String[] titleArray){
        // Create an string found array 
        String[] foundArray = new String[titleArray.length];
        
        /* Loop through the found array to add Yes or No when the book in the bookArray were found or not
        If the book's title were found at index i, the value at index i of the found array is yes; 
        Otherwise, the value at index i is no */
        for (int i = 0; i < foundArray.length; i++){
            String title = titleArray[i];
            // Edge case: if the title is an empty string 
            if (titleArray[i].equals("")){
                foundArray[i] = "Empty Title Here :(";
            }
            else if (this.binarySearch(bookArray, 0, bookArray.length - 1, title)){
                foundArray[i] = "Yes";
            }
            else{
                foundArray[i] = "No";
            }
        }
        return foundArray;
    }

    /**
     * recursive binarySearch function 
     * @param bookArray passed-in array, the array will gradually shrink to look for the title
     * @param left left index 
     * @param right right index 
     * @param title passed-in title to look for 
     * @return true or false 
     */
    public boolean binarySearch(Book[] bookArray, int left, int right, String title){

        int middle;

        /* Beginning at a full array then cut it in half based on the middle index 
        loop through all the sorted book's title in the shrinking array (right smaller array or left smaller array)
        to compare the title at middle index to the given title, which one comes first, gradually cut half the current array
        until left = right, there's only one book's title left. Hence the book's title was found */
        
        while (left <= right){
            // Set up the middle index
            middle = left + ((right - left) / 2);

            // If the title is at middle return true 
            if (bookArray[middle].getTitle().equals(title)){
                return true;
            }
            /* If the book's title at middle comes first the given title, 
            that means the given title should be on the right array*/
            else if (bookArray[middle].getTitle().compareTo(title) < 0){
                /* keep the right, change the left to middle + 1 to focus on the smaller right array, 
                recursively search for the title in that index */
                return binarySearch(bookArray, middle + 1, right, title);
            }
            /* Otherwise (the book's title at middle comes after the given title, 
            that means the given title should be in the left array) */
            else {
                // keep the left, change the right to middle - 1 to focus on the smaller left array 
                return binarySearch(bookArray, left, middle - 1, title);
            }
        }
        // if the title were not found, return false
        return false;
    }

    // Test binary search 
    // public static void main (String[] args){
    //     DataSearchExtended mySearch = new DataSearchExtended();
    //     Book[] exampleBooks = {new Book("Bob", "Author", 1, 1990, "Genre"), 
    //     new Book("Melody","Author",1,1990,"Genre"), 
    //     new Book("Test", "Author", 1, 1990, "Genre"), 
    //     new Book("Zzz", "Author", 1, 1990, "Genre")};

    //     String example_seek_data = "";
    //     System.out.println(mySearch.binarySearch(exampleBooks,0,exampleBooks.length - 1, example_seek_data ));
    //     //  "Bob", "Melody", "Zzzz", "Nothing", "False"
    // }
}
