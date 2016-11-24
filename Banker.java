import java.util.*;

public class Banker {
 private static int need[][];
 private static int allocate[][];
 private static int max[][];
 private static int avail[][]; 

 public static void main(String args[]) {
   Scanner sc = new Scanner(System.in);
   System.out.print("Enter no. of processes and resources : ");
   int np = sc.nextInt();
   int nr = sc.nextInt();
   need = new int[np][nr];
   max = new int[np][nr];
   allocate = new int[np][nr];
   avail = new int[1][nr];

   System.out.println("Enter allocation matrix -->");
   for (int i = 0; i < np; i++) {
    for (int j = 0; j < nr; j++) {
      allocate[i][j] = sc.nextInt();
    }
  }

  System.out.println("Enter max matrix -->");
  for (int i = 0; i < np; i++) {
    for (int j = 0; j < nr; j++) {
      max[i][j] = sc.nextInt();
    }
  }

  System.out.println("Enter available matrix -->");
  for (int j = 0; j < nr; j++) {
    avail[0][j] = sc.nextInt();
  }

  for (int i = 0; i < np; i++) {
    for (int j = 0; j < nr; j++) {
      need[i][j] = max[i][j] - allocate[i][j];
    }
  } 
  System.out.println("Need Matrix:");
  for (int i = 0; i < np; i++) {
    for (int j = 0; j < nr; j++) {
      System.out.print(need[i][j] + "\t");
    }
    System.out.println();
  }

  boolean done[] = new boolean[np];
  int j = 0;

	while (j < np) { // until all process allocated
    boolean allocated = false;
    for (int i = 0; i < np; i++) {
			if (!done[i] && check(i,np,nr)) { // trying to allocate
        for (int k = 0; k < nr; k++) {
          avail[0][k] = avail[0][k] - need[i][k] + max[i][k];
        }
        System.out.println("Allocated process : " + i);
        allocated = done[i] = true;
        j++;
      }
    }
    if (!allocated) {
      break;
		} // if no allocation
	}
	if (j == np) {// if all processes are allocated
    System.out.println("\nSafely allocated");
  }
  else {
    System.out.println("All proceess cant be allocated safely");
  }

}

static boolean check(int i,int np, int nr) {
	for (int j = 0; j < nr; j++) {
    if (avail[0][j] < need[i][j]) {
      return false;
    }
  }
  return true;
}

}

/*
Sample Output - 

Enter no. of processes and resources : 5
4
Enter allocation matrix -->
1 1 1 1
2 2 2 2
3 3 3 3
4 4 4 4
5 5 5 5
Enter max matrix -->
5 5 5 5
5 5 5 5
5 5 5 5
5 5 5 5
5 5 5 5
Enter available matrix -->
8 8 8 8
Need Matrix:
4       4       4       4
3       3       3       3
2       2       2       2
1       1       1       1
0       0       0       0
Allocated process : 0
Allocated process : 1
Allocated process : 2
Allocated process : 3
Allocated process : 4

Safely allocated
*/
