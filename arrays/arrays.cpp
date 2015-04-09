#include <stdio.h>

using namespace std;

/* 
 * ========================== SELECTION SORT ==================================
 * Take every element in squence and scan the remaining set to find the 
 * smallest remaining element. If such element exists swap the two elements.
 * == TIME: O(n^2)
 * == SPACE: O(1)
 */

void selectionSort(int * a, int len) {
  int i;
  for (i=0;i<len;i++) {
    int least = i;
    int j;
    for (j=i+1;j<len;j++) {
      if (a[j] < a[least]) {
        least = j;
      }
    } 
    // swap a[i] with the least
    int temp = a[least];
    a[least] = a[i];
    a[i] = temp;
  }
}

/*
 * ============================= MERGE SORT ===================================
 * Use DIVIDE AND CONQUER to split an array into two, sort the two and merge back. 
 * == TIME: O(n log n)
 * == SPACE: O(n)
 */

void merge(int * a, int len, int low, int mid, int high) {
  // create a temp array and some temp registers for indexes, copy the array
  int temp[len];
  int i;
  for (i=low;i<=high;i++) {
    temp[i] = a[i];
  } 
  i = low;
  int j = mid+1;
  int k = low;
  // push smaller elements onto the left
  while(i<=mid && j <= high) {
    if(temp[i] <= temp[j]) {
      a[k] = temp[i];
      i++; 
    } else {
      a[k] = temp[j];
      j++;
    }
    k++;
  }
  // copy the remaining elements
  while(i<=mid) { a[k] = temp[i]; i++; k++; };
}

void mergeSort(int * a, int len, int low, int high) {
  if (low<high) {
    int mid = (low + high)/2;
    mergeSort(a,len,low,mid); 
    mergeSort(a,len,mid+1,high); 
    merge(a,len,low,mid,high);  
  }
}

/*
 * ============================= QUICK SORT ===================================
 * Use DIVIDE AND CONQUER to split an array into three, sort the two and merge
 * back.  
 * == TIME: Between best O(n log n) and O(n^2) worst
 * == SPACE: Between best O(log n) and O(n)
 */

int partition(int * a,int start, int end) {
  int pivot = a[end];
  int pIndex = start;//this is incremented only if value <= to pivot is found 
  int i;
  for(i=start;i<end;i++) {
    if (a[i]<=pivot) {
      //swap
      int save = a[pIndex];
      a[pIndex] = a[i];
      a[i] = save;
      // increment
      pIndex++;
    }
  }
  //swap
  int save = a[end];
  a[end] = a[pIndex];
  a[pIndex] = save;
  // return pIndex;
  return pIndex;
}

void quickSort(int * a,int start, int end) {
  if (start<end) {
    int pIndex = partition(a,start,end);
    quickSort(a,start,pIndex-1);
    quickSort(a,pIndex+1,end);
  }
}

/**
 * ============================ BINARY SEARCH =================================
 * Search a half of array and each sub-array on every iteration
 * The array needs to be sorted!
 * == TIME: O(log n) 
 */
int binarySearch(int elem, int * arr, int len) {
  int l = 0; int r = (len-1);
  while (l<=r) {
    int m = (l+r)/2;
    if (elem == arr[m]) {
      return arr[m]; 
    } else if (elem < arr[m]) { 
      r = m-1; 
    } else { 
      l = m+1;
    };
  }
  return -1;
}

/**
 * =============================== MERGE ======================================
 * Both arrays need to be sorted.
 * == TIME: O(n) 
 */
void merge(int * arr1, const int l1
  , int * arr2, const int l2, int * arr3) {
  int i = 0; int j = 0; int k = 0; 		// set ls
  while(i <= (l1-1) && j <= (l2-1)) { 	     	// while l1 <= r1 and l2 <= r2
    if (arr1[i] <= arr2[j]) { 
      arr3[k] = arr1[i]; i++; k++; 		// copy smaller element and
    } else {					// advance both the smaller element
      arr3[k] = arr2[j]; j++; k++;		// array index and result array index
    }
  }
  while (i<=(l1-1)) arr3[k++] = arr1[i++];
  while (j<=(l2-1)) arr3[k++] = arr2[j++];
}

/**
 * ============================================================================
 */
void printArray(int * arr,int l) {
  int i;
  for (i=0;i<l;i++){
    printf("%i ",arr[i]);
  }
  printf("\n");
}


int main(int argv, char ** argc) {
  const int LEN = 7;
  printf("== MERGE ARRAYS ==\nTime: O(n)\n");
  int a1[LEN] = {5,8,9,0,4,6,7};
  printArray(a1,LEN); 
  int a2[3] = {10,11,12};
  printArray(a2,3); 
  int a3[10]; 
  merge(a1,LEN,a2,3,a3);
  printArray(a3,10); 

  // selection sort
  printf("== SELECTION SORT ==\nTime: O(n^2) \nSpace: O(1)\n");
  int a5[LEN] = {5,8,9,0,4,6,7};
  printArray(a5,LEN); 
  selectionSort(a5,LEN);
  printArray(a5,LEN); 

  // merge sort
  printf("== MERGE SORT ==\nTime: O(n log n)\nSpace: O(n)\n");
  int a6[LEN] = {5,8,9,0,4,6,7};
  printArray(a6,LEN); 
  mergeSort(a6,LEN,0,LEN-1);
  printArray(a6,LEN); 

  // quick sort
  printf("== QUICK SORT ==\nTime: O(n^2) - O(n log n) \nSpace: O(n) - O(log n)\n");
  int a7[LEN] = {5,8,9,0,4,6,7};
  printArray(a7,LEN); 
  quickSort(a7,0,LEN-1);
  printArray(a7,LEN); 

  // binary search
  printf("== BINARY SEARCH ==\nTime: O(log n)\n");
  int elem = 5;
  int found = binarySearch(elem,a7,LEN); 
  printf("Searching for %i: %i\n",elem,found);

  return 0;
}
