package build.classes.rodprogram;

import java.util.Scanner;

public class AddressCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Fixed number of dimensions
        int dimensions =3;
        System.out.println("Number of dimensions: " + dimensions);

        // Step 2: Get base address (alpha or base)
        System.out.println("Enter the base address: ");
        int baseAddress = scanner.nextInt();

        // Step 3: Get element size
        System.out.println("Enter the element size (esize): ");
        int elementSize = scanner.nextInt();

        // Step 4: Get upper bounds for each dimension 
        int[] upperBounds = new int[dimensions];
        for (int i = 0; i < dimensions; i++) {
            System.out.println("Enter the upper bound for UB " + (i + 1) + " (" + (i + 1) + "): ");
            upperBounds[i] = scanner.nextInt();
        }

        // Step 5: Get target indices (i, j, k)
        int[] indices = new int[dimensions];
        for (int i = 0; i < dimensions; i++) {
            System.out.println("Enter the target index for dimension " + (i + 1) + ": ");
            indices[i] = scanner.nextInt();

            // Validate that the index is within bounds
            if (indices[i] < 0 || indices[i] > upperBounds[i]) {
                System.out.println("Error: Index for dimension " + (i + 1) + " is out of bounds.");
                return;
            }
        }

        // Step 6: Calculate the address
        // Calculate the product terms for address calculation
        int[] products = new int[dimensions];
        products[dimensions - 1] = 1; // Last dimension's product is 1
        for (int i = dimensions - 2; i >= 0; i--) {
            products[i] = products[i + 1] * (upperBounds[i + 1] + 1);
        }

        // Compute the offset
        int offset = 0;
        for (int i = 0; i < dimensions; i++) {
            offset += indices[i] * products[i];
        }

        // Final address calculation
        int address = baseAddress + (offset * elementSize);

        // Step 7: Output the result
        System.out.println("The address of the target element is: " + address);

        scanner.close();
    }
}

