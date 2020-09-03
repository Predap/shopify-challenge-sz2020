import java.io.*; // imports classes
import java.util.Scanner;
public class Sneakers { // opens class

    public static final int max = 9999; // defines max length of array

    public static final int fields = 7; // defines number of fields in spreadsheet

    public static String[][] data = new String[fields][max]; // creates array to contain data in spreadsheet

    public static double mean(int orders) { // opens method to calculate mean

        double sum = 0; // initializes sum of sneaker prices to 0
        double items = 0; // initializes number of items to 0
        for (int i = 1; i < orders; i++) { // runs through spreadsheet to calculate mean

            sum += Integer.parseInt(data[3][i]); // adds price of sneakers to sum
            items += Integer.parseInt(data[4][i]); // adds number of sneakers to items

        }
        double m = sum / items; // calculates the mean price per sneaker
        return m; // returns value

    }

    public static double median(int orders) { // opens method to calculate median number of sneakers per order, implementing insertion sort

        int[] arr = new int[orders - 1]; // creates array to contain order quantities
        for (int i = 1; i < orders; i++) { // loop to fill array

            arr[i - 1] = Integer.parseInt(data[4][i]); // fills array with order quantities

        }
        for (int i = 1; i < orders - 1; i++) { // outer loop to increase size of partition

            int check = arr[i]; // sets current item being checked
            int j = 0; // sets value being checked to zero
            for (j = i - 1; j >= 0 && arr[j] > check; j--) { // checks each value that is greater than the check value

                arr[j+1] = arr[j]; // shifts array

            }
            arr[j+1] = check; // replaces last value in array

        }
        if (orders % 2 == 0) { // checks if length is even

            return (arr[orders / 2] + arr[orders / 2 + 1]) / 2.0; // calculates and returns average of middle two values

        }
        else {

            return arr[orders / 2 + 1] / 1.0; // returns middle value

        }
    }

    public static void main (String[] args) throws Exception { // opens main method

        Scanner scan = new Scanner(new File("DataSet.csv")); // creates scanner with data set
        scan.useDelimiter(",|\\n"); // checks for new lines and commas
        int column = 0; // sets column position for array
        int row = 0; // sets row position for array
        while (scan.hasNext()) { // moves through file

            data[column][row] = scan.next(); // sets array value
            if (column == 6) { // if at end of row
                column = 0; // resets column value
                row++; // moves to next row
            }
            else { // if not at end of row
                column++; // increase column
            }

        }
        scan.close(); // close scanner
        double mean = mean(row); // calculate mean
        System.out.println("Mean: " + mean); // print mean
        double median = median(row); // calculate median
        System.out.println("Median: " + median); // print median
        double total = mean * median; // calculate final metric
        System.out.println("Total: " + total); // print final metric

    }

}
