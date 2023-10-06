import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static int[] takeUserInput(Scanner inputReader){
        System.out.println("Enter Size of Array");

        int arraySize = inputReader.nextInt();
        int[] arr = new int[arraySize];

        for(int i=0;i<arraySize;i++){
            arr[i]=inputReader.nextInt();
        }

        return arr;
    }
    static void printArray(int[] arr){
        System.out.print("Printing Array: ");
        for(int j : arr){
            System.out.print(j+" ");
        }
        System.out.println();
    }

    static int maxInt(int a,int b){
        return a>=b?a:b;
    }

    static long calculateSum(int[] arr){
        long sum = 0;
        for(int i:arr){
            sum+=i;
        }
        return  sum;
    }

    static int[] calculateArray(int[] arrA,int[] arrB){
        try{
            if(arrA.length!=arrB.length){
                throw new RuntimeException("Arrays must have the same length calculateArray()");
            }

            int[] arr = new int[arrA.length];

            for(int i=0;i<arrA.length;i++){
                arr[i] = maxInt(arrA[i],arrB[i]) + arrA[i]/arrB[i];
            }

            return arr;
        }catch (Exception e){
            e.printStackTrace();
        }

        return new int[0];
    }

    static void testException() {
        try {
            throw new RuntimeException("error testing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int stringLength(String str) {
        if (str == null) {
            return 0;
        }

        int length = 0;

        for (char c : str.toCharArray()) {
            if (c == '\0') {
                break;
            }
            length++;
        }

        return length;
    }

    public static void main(String[] args){
        try{
            Scanner inputReader = new Scanner(System.in);

            int[] arrA = takeUserInput(inputReader);
            int[] arrB = takeUserInput(inputReader);
            String str = "Welcome to Clarivate!";

            long sumA = calculateSum(arrA);
            double averageA = (double) sumA/arrA.length;

            long sumB = calculateSum(arrB);
            double averageB = (double) sumB/arrB.length;

            printArray(arrA);
            System.out.println("Sum of Array A: "+sumA);
            System.out.println("Average of Array A: "+averageA);

            printArray(arrB);
            System.out.println("Sum of Array B: "+sumB);
            System.out.println("Average of Array B: "+averageB);

            int[] arrC = calculateArray(arrA,arrB);

            printArray(arrC);

            System.out.println("Calculate Length of String");
            System.out.println(stringLength(str));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}