import java.util.Scanner;
public class MergeSort {
    // O(nlogn)
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int arr[];

        System.out.println("Enter the number of elements in an Array: ");
        int n = input.nextInt();

        arr = new int[n];

        System.out.println("Sorted Array to be sorted: ");
        for(int i=0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        
        divide(arr, 0, n-1);

        System.out.println("Sorted Array is: ");
        for(int i=0; i < n; i++) {
            System.out.println(arr[i] + " ");
        }

        input.close();
    }

    public static void divide(int arr[], int si, int ei) {
        if( si >= ei) {
            return;
        }
        // O(logn)
        if(si < ei) {
            int mid = si + (ei - si) / 2 ;
            divide(arr, si, mid);
            divide(arr, mid + 1, ei);
            conquer(arr, si, mid, ei); 

        }
    }

    public static void conquer(int arr[], int si, int mid, int ei) {
        int merged[] = new int[ei - si + 1];
        int indx1 = si;
        int indx2 = mid + 1;
        int x = 0;
        // O(n)
        while(indx1 <= mid && indx2 <= ei) {
            if(arr[indx1] <= arr[indx2]) {
                merged[x++] = arr[indx1++];
            }else {
                merged[x++] = arr[indx2++];
            }
        }
        
        while(indx1 <= mid) {
            merged[x++] = arr[indx1++];
        }
        
        while(indx2 <=ei) {
            merged[x++] = arr[indx2++];
        }
        
        for(int i=0, j =si; i < merged.length; i++, j++) {
            arr[j] = merged[i];
        }
    }
}