/*
 * Author:      Justin Schwarzwald
 * Date:        7/09/2019
 * Assignment:  Assignment 4
 * Description: Program takes user input to set up a list of people number 1 through n,
 *              where n is the number the user enters. The user then chooses a number (inclusive)
 *              of how many people get skipped between elimination. (Josephus Problem)
 *              The program will continue to loop eliminating based on the number given until
 *              just one remains. The last number is the one which survives.
 */

import java.util.Scanner;

public class Schwarzwald_Justin_Assignment4
{

    public static void main(String[] args)
    {
        
        Scanner userInput = new Scanner(System.in);
        
        boolean finished = false; // For when program is over
        
        boolean notValidEntry; // For looping program YES or NO input
        String choiceToContinue; // Input for YES or NO

        int numInCircle; // How many people are in the circle
        int numToSkip; // Inclusive skipping number
        int dataToDelete; // The number needing to be deleted
        
        // For tracking nodes
        Node<Integer> currentNode;
        Node<Integer> nextNode;

        
        
        System.out.println("Welcome to the Josephus Problem.");

        do
        {            
            // Reset for loop case
            numInCircle = 0;
            
            System.out.print("How many people in the circle (1 – 100)?  >> ");
            
            // Loop to check value entered is a number is between 1 and 100
            while (numInCircle < 1 || numInCircle > 100)
            {
                if (userInput.hasNextInt())
                {
                    // Sets variable numInCircle to input if a number was given
                    numInCircle = userInput.nextInt();
                    // Checks if number is greater than 0
                    if (numInCircle < 1 || numInCircle > 100)
                    {
                        // Tells user to input a number between 1 and 100
                        System.out.println("\nYou need to enter a integer between 1 and 100 to continue\n");
                        System.out.print("How many people in the circle (1 – 100)?  >> ");
                    }
                }
                // If a number wasn't entered the following runs
                else
                {
                    // Reset the scanner
                    userInput.next();
                    // Tells user to input a number between 1 and 100
                    System.out.println("\nYou need to enter a integer between 1 and 100 to continue\n");
                    System.out.print("How many people in the circle (1 – 100)?  >> ");
                }
            } // Ends loop once value between 1 and 100 was entered
            
            
            // If circle is more than 1 person
            if (numInCircle > 1)
            {
                System.out.print("Enter the number of people to skip between eliminations(inclusive)  >> ");
                numToSkip = -1; // Reset for loop case
                
                // Loop to check value entered is a number greater than 0
                while (numToSkip < 1)
                {
                    if (userInput.hasNextInt())
                    {
                        // Sets variable numToSkip to input if a number was given
                        numToSkip = userInput.nextInt();
                        // Checks if number is greater than 0
                        if (numToSkip < 1)
                        {
                            // Tells user to input a integer greater than 0
                            System.out.println("\nYou need to enter an integer greater than 0 continue\n");
                            System.out.print("Enter the number of people to skip between eliminations(inclusive)  >> ");
                        }
                    }
                    // If a number wasn't entered the following runs
                    else
                    {
                        // Reset the scanner
                        userInput.next();
                        // Tells user to input a integer greater than 0
                        System.out.println("\nYou need to enter an integer greater than 0\n");
                        System.out.print("Enter the number of people to skip between eliminations(inclusive)  >> ");
                    }
                } // Ends loop once value greater than 0 was entered
                
                // Create linked list from 1 to number entered
                LinkedList<Integer> linkedListOfPeople = new LinkedList<Integer>();
                for (int i = 1; i < numInCircle + 1; i++)
                    linkedListOfPeople.addToEnd(i);

                System.out.println("\nRunning:");

                // Prints initial circle
                System.out.println("Intial Puzzel: " + linkedListOfPeople.toString());
                
                // Sets currentNode to head and nextNode to next
                currentNode = linkedListOfPeople.getHead();
                nextNode = currentNode.getNext();

                // If skip is 1, removes first to last leaving the last element as survivor
                if (numToSkip == 1)
                {
                    // Runs until last 3rd to last element is removed
                    for (int i = 1; i < numInCircle - 1; i++)
                    {
                        linkedListOfPeople.deleteFront();
                        System.out.println("\nEliminated #" + i);
                        System.out.println("\nRemaining: " + linkedListOfPeople.toString());
                    }
                    
                    // Deletes 2nd to last element
                    System.out.println("\nEliminated #" + linkedListOfPeople.getHead().getData());
                    linkedListOfPeople.deleteFront();

                }
                
                // If skip is greater than 1 code runs
                else
                {
                    // Runs until the currentNode and nextNode are the same (happens when only one element is left in array)
                    while (currentNode != nextNode)
                    {
                        // Updates position of nodes per numToSkip
                        for (int i = 1; i < numToSkip; i++)
                        {
                            currentNode = nextNode;
                            
                            // If nextNode.next is null then it loops to beginning of linked list, if not null then moves up one spot
                            if (nextNode.getNext() == null)
                                nextNode = linkedListOfPeople.getHead();
                            else
                                nextNode = nextNode.getNext();
                            
                        }
                        
                        // After updating the position to node that needs to get deleted, it stores the integer
                        dataToDelete = currentNode.getData();
                        
                        // Updates the position of new currentNode(after elimination)
                        currentNode = nextNode;
                        
                        
                        // If nextNode.next is null then it loops to beginning of linked list, if not updates to next
                        if (nextNode.getNext() == null)
                            nextNode = linkedListOfPeople.getHead();
                        else
                            nextNode = nextNode.getNext();

                        // If there are more than one element left in list
                        if (currentNode != nextNode)
                        {
                            // Deletes the node per integer finder
                            linkedListOfPeople.deleteFirst(dataToDelete);
                            
                            System.out.println("\nEliminated #" + dataToDelete);
                            
                            // Only prints if there are 2 elements left or more
                            if (linkedListOfPeople.getHead().getNext() != null)
                                System.out.println("\nRemaining: " + linkedListOfPeople.toString());
                        }
                    }
                }
                
                // Once there is only one left survivor is found and printed
                System.out.println("\nSurvivor: " + linkedListOfPeople.toString());
            }

            // If user picks only one person for circle that person survives
            else
                System.out.println("Survivor: 1");

            // Loop for running program again
            System.out.println("\nWould you like to run again?");
            userInput.nextLine();
            System.out.print("Type Y for Yes, or N for No\n");
            System.out.print(">> ");

            // Resetting default for loop case
            notValidEntry = true;

            // Loops until user enters yes or no
            while (notValidEntry)
            {
                // Takes in the users input and stores as a string
                choiceToContinue = userInput.nextLine().toUpperCase();

                // Checks if value is (y, Y, Yes, YES, or yes)
                if (choiceToContinue.equals("Y") || choiceToContinue.equals("YES"))
                {
                    // Changes the condition to false to end loop
                    notValidEntry = false;
                }
                else if (choiceToContinue.contentEquals("N") || choiceToContinue.contentEquals("NO"))
                {
                    // Changes the condition to false to end loop
                    notValidEntry = false;

                    // Ends program
                    finished = true;
                }
                // Condition stays true if yes or no was not entered, shows below error message
                else
                {
                    System.out.println("\nThat was not a vaild entry");
                    System.out.println("Type Y for Yes, or N for No\n");
                    System.out.print(">> ");
                }
            }

            System.out.println("\n");

        } while (!finished); // End of program if finished

        System.out.println("The program has ended.");

        userInput.close();
    }
}
