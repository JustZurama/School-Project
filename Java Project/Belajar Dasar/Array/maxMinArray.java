public class maxMinArray {
    public static void main (String[] args) {
        int[] numbers = {10, 20, 30, 40, 50, 60, 70};
        
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
        
        int max = numbers[0];
        int min = numbers[0];

        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
        }

        System.out.println("Nilai maksimum array adalah: " + max);
        System.out.println("Nilai minimum array adalah: " + min);
////////////////////////////// ROTATE ARRAY ///////////////////////////////////////////////////////
        int lastElement = numbers[numbers.length - 1];

        for (int i = numbers.length - 1; i > 0; i--) {
            numbers[i] = numbers[i - 1];
        }

        numbers[0] = lastElement;

        System.out.print("Array setelah diputar: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }
}