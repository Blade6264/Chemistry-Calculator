package Stoichiometry;

import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for chemical name and number of moles
        System.out.print("Enter chemical name: ");
        String temp = scanner.nextLine();
        String chem = temp.substring(0,1).toUpperCase() + temp.substring(1).toLowerCase();

        System.out.print("Enter number of moles: ");
        double moles = scanner.nextDouble();

        // Create the element with the user-provided information*
        Element newElement = new Element(chem, moles);

        // Print out the molar mass of the new element
        if (newElement.getAtomicNumber() != -1){
            System.out.println("");
            System.out.println("Symbol: " + newElement.getSymbol());
            System.out.println("Name: " + newElement.getAtomName());
            System.out.println("Atomic number: " + newElement.getAtomicNumber() );
            System.out.println("Molar mass: " + newElement.getMolarMass() + " g/mol");
            System.out.println("Total mass: " + newElement.getTotalMass() + " g/mol");
            System.out.println("");
        }
        else
            System.out.println("Element not found");

        scanner.close();
    }
}
