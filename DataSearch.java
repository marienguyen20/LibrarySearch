import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Data Search file to read seek.txt and data.txt files 
 * the program will then write to an output file which seek values were found 
 * and which were not. The name of the output file must be provided by the user at the command line
 * @author Marie Nguyen 
 * @version April 14th
 */
public class DataSearch {
    // Constructor: Intentionally empty 
    public DataSearch(){}

    // Main method
    public static void main(String[] args) throws FileNotFoundException{
        // FIRSTLY, Read the SEEK FILE in the command line arguments
        File infileSeek = new File(args[0]);
        // Create a Scanner method for reading the SEEK FILE
        Scanner seekDataIn = new Scanner(infileSeek);
        String seekTitle;   // Initialize a variable for title on each line

        // Create an String arraylist to store book's titles 
        ArrayList<String> arrayListTitle = new ArrayList<String>();

        // Loop through the file until reaching the end
        while(seekDataIn.hasNext()){
            // Read a line from the file 
            seekTitle = seekDataIn.nextLine();
            // Add each title to the String arraylist 
            arrayListTitle.add(seekTitle);
        }
        // Convert the String arraylist to the String array 
        String[] arrayTitle = new String[arrayListTitle.size()];
        arrayTitle = arrayListTitle.toArray(arrayTitle);

        // SECONDLY, Read the DATA FILE in the command line arguments
        File infileData = new File(args[1]);
        // Create a Scanner method for reading the data file 
        Scanner dataIn = new Scanner(infileData);
        String elements;   // Initialize a variable for information on each line

        // Create a Book array list 
        ArrayList<Book> bookList = new ArrayList<Book>();

        // Loop through the file until reaching the end
        while(dataIn.hasNext()){
            // Read a line from the file 
            elements = dataIn.nextLine();
            // Initialize an array for elements seperated by "," in each line
            String[] bookVals = elements.split(",");  
            // Add things to bookList here
            String tit = bookVals[0];  // Title 
            String auth = bookVals[1];  // Author 
            int pagec = Integer.parseInt(bookVals[2]);  // Number of pages
            int year = Integer.parseInt(bookVals[3]);   // Year
            String gen = bookVals[4];   // Genre

            bookList.add(new Book(tit, auth, pagec, year, gen));    // Create a Book object 
        }
        // Convert booklist to an array of book 
        Book[] bookArray = new Book[bookList.size()];
        bookArray = bookList.toArray(bookArray);

        // THIRDLY, sort alphabetically the list of Book objects according to the Book title
        // Generate DataSearchExtended object
        DataSearchExtended myData = new DataSearchExtended();
        // Invoke sortBook method in DataSearchExtended
        Book[] sortedBookArray = myData.sortBook(bookArray);

        // LAST, a found array is created to keep track of whether the search-for book titles have been found 
        String[] foundArray = myData.foundBook(bookArray, arrayTitle);

        // WRITING OUTPUT TO A FILE 
        // Create a Scanner object
        System.out.print("Name your output file: "); 
        Scanner sc = new Scanner(System.in);
        // Ask filename from user
        String outFileName = sc.nextLine();
        PrintWriter outs = new PrintWriter(new FileOutputStream(outFileName));

        // Loop through the title array and found array to write the results in the output file
        for (int m = 0; m < arrayTitle.length; m++){
            outs.println(arrayTitle[m] + " " + foundArray[m]);
        }
        outs.close(); 
    }
}
