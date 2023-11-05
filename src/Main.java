import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] randomArray = gatherUserInputAndGenerateArray(scanner);
        if (randomArray != null) {
            int choice = getUserChoice(scanner);
            sortAndMeasureTime(randomArray, choice);
        }
        scanner.close();
    }

    private static int[] gatherUserInputAndGenerateArray(Scanner scanner) {
        System.out.print("The size of array is: ");
        int size = scanner.nextInt();
        System.out.print("The minimum value is: ");
        int minValue = scanner.nextInt();
        System.out.print("The maximum value is: ");
        int maxValue = scanner.nextInt();
        return new Random().ints(size, minValue, maxValue).toArray();
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("1 - selection sort\n2 - bubble sort\n3 - even_odd sort\n4 - insertion sort\nChoose: ");
        return scanner.nextInt();
    }

    private static void sortAndMeasureTime(int[] randomArray, int choice) {
        long startTime = System.nanoTime();
        switch (choice) {
            case 1:
                selectionSort(randomArray);
                break;
            case 2:
                bubbleSort(randomArray);
                break;
            case 3:
                int amountOfEvenNumbers = countTheAmountOfEvenNumbers(randomArray);
                oddEvenSort(randomArray);
                sortEvenAndOdd(randomArray, amountOfEvenNumbers);
                break;
            case 4:
                insertionSort(randomArray);
                break;
            default:
                System.out.println("Wrong choice!");
                return;
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Array after sorting: " + Arrays.toString(randomArray));
        System.out.println("Method execution time: " + duration + " milliseconds");
    }

    private static void insertionSort(int[] randomArray){
        for (int i = 1; i < randomArray.length; ++i) {
            int key = randomArray[i];
            int j = i - 1;
            while (j >= 0 && randomArray[j] > key) {
                randomArray[j + 1] = randomArray[j];
                j = j - 1;
            }
            randomArray[j + 1] = key;
        }
    }

    private static void bubbleSort(int[] randomArray){
        for (int i = 0; i < randomArray.length - 1; i++) {
            for (int j = 0; j < randomArray.length - i - 1; j++) {
                if(randomArray[j] > randomArray[j + 1]){
                    int temp = randomArray[j];
                    randomArray[j] = randomArray[j + 1];
                    randomArray[j + 1] = temp;
                }
            }
        }
    }

    private static void sortEvenAndOdd(int[] randomArray, int amountOfEvenNumbers) {
        for (int i = 0; i < amountOfEvenNumbers - 1; i++) {
            int indexOfMinValue = i;
            for (int j = i + 1; j < amountOfEvenNumbers; j++) {
                if(randomArray[j] < randomArray[indexOfMinValue]){
                    indexOfMinValue = j;
                }
            }
            swap(randomArray, i, indexOfMinValue);
        }

        for (int i = amountOfEvenNumbers; i < randomArray.length - 1; i++) {
            int indexOfMinValue = i;
            for (int j = i + 1; j < randomArray.length; j++) {
                if(randomArray[j] < randomArray[indexOfMinValue]){
                    indexOfMinValue = j;
                }
            }
            swap(randomArray, i, indexOfMinValue);
        }
    }
    private static void oddEvenSort(int[] randomArray) {
        for (int i = 0; i < randomArray.length - 1; i++) {
            int indexOfOddNumber = i;
            if(randomArray[i] % 2 == 1){
                for (int j = i + 1; j < randomArray.length; j++) {
                    if(randomArray[j] % 2 == 0){
                        indexOfOddNumber = j;
                    }
                }
                swap(randomArray, i, indexOfOddNumber);
            }
        }
    }

    public static int countTheAmountOfEvenNumbers(int[] randomArray) {
        int amountOfEvenNumbers = 0;
        for (int j : randomArray) {
            if (j % 2 == 0) {
                amountOfEvenNumbers++;
            }
        }
        return amountOfEvenNumbers;
    }

    private static void selectionSort(int[] randomArray) {
        for (int i = 0; i < randomArray.length - 1; i++) {
            int indexOfMinValue = i;
            for (int j = i + 1; j < randomArray.length; j++) {
                if (randomArray[j] < randomArray[indexOfMinValue]) {
                    indexOfMinValue = j;
                }
            }
            swap(randomArray, i, indexOfMinValue);
        }
    }
    private static void swap(int[] randomArray, int a, int b) {
        int temp = randomArray[a];
        randomArray[a] = randomArray[b];
        randomArray[b] = temp;
    }
}