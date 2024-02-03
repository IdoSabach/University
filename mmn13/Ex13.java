package mmn13;

class Ex13 {

  // Q1

  /*
   * In this method I receive an array as a parameter and look for
   * three numbers in this array whose product is the highest,
   * and I return the sum.
   */

  // time = O(n)
  // place = O(1)

  public static int maxMul3(int[] arr) {
    if (arr.length < 3) {
      return Integer.MIN_VALUE;
    }

    int i = 0, j = 1, x = 2;
    // int num1 = 0, num2 = 0, num3 = 0;
    int sunMul = arr[i] * arr[j] * arr[x];

    while (i < arr.length - 3) {
      if (arr[i] * arr[j] * arr[x] > sunMul) {
        sunMul = arr[i] * arr[j] * arr[x];
        // num1 = arr[i];
        // num2 = arr[j];
        // num3 = arr[x];
      } else {
        x++;
      }

      if (x == arr.length - 1) {
        j++;
        x = j + 1;
      }

      if (j == arr.length - 2) {
        i++;
        j = i + 1;
        x = j + 1;
      }
    }
    // System.out.println(num1 + " * " + num2 + " * " + num3 + " = " + sunMul);
    return sunMul;
  }

  // Q2

  /*
   * In this method I receive two arrays as a parameter
   * and search in these arrays for the middle of the two
   * and return the average number of their middle i.e. median.
   */

  // time = O(Log(n))
  // place = O(1)

  public static int findMedian(int[] arr1, int[] arr2) {
    int total = arr1.length + arr2.length;
    int half = (total + 1) / 2;

    int left = 0;
    int right = arr1.length;

    int left1, right1, left2, right2;

    int median = 0;

    while (left <= right) {
      int i = left + (right - left) / 2;
      int j = half - i;

      if (i > 0) {
        left1 = arr1[i - 1];
      } else {
        left1 = Integer.MIN_VALUE;
      }

      if (i < arr1.length) {
        right1 = arr1[i];
      } else {
        right1 = Integer.MAX_VALUE;
      }

      if (j > 0) {
        left2 = arr2[j - 1];
      } else {
        left2 = Integer.MIN_VALUE;
      }

      if (j < arr2.length) {
        right2 = arr2[j];
      } else {
        right2 = Integer.MAX_VALUE;
      }

      if (left1 <= right2 && left2 <= right1) {
        if (total % 2 == 0) {
          median = (Math.max(left1, left2) + Math.min(right1, right2)) /
              2;
        } else {
          median = Math.max(left1, left2);
        }
        break;
      } else if (left1 > right2) {
        right = i - 1;
      } else {
        left = i + 1;
      }
    }

    return median;
  }

  // Q3

  /*
   * A recursive static method that accepts two character strings 1st and 2st ,
   * and returns the
   * The minimal (smallest) string that contains both strings.
   */

  public static String minimalSt(String st1, String st2) {
    return minimalSt(st1, st2, 0, 0, "");
  }

  public static String minimalSt(String st1, String st2, int i, int j, String str) {
    if (i == st1.length() && j == st2.length()) {
      return str;
    }

    if (i < st1.length() && j < st2.length() && st1.charAt(i) == st2.charAt(j)) {
      return minimalSt(st1, st2, i + 1, j + 1, str + st1.charAt(i));
    } else {
      String step1 = "";
      String step2 = "";

      if (i < st1.length()) {
        step1 = minimalSt(st1, st2, i + 1, j, str + st1.charAt(i));
      }
      if (j < st2.length()) {
        step2 = minimalSt(st1, st2, i, j + 1, str + st2.charAt(j));
      }

      if (step1.length() < 1) {
        return step2;
      } else if (step2.length() < 1) {
        return step1;
      } else {
        if (step1.length() < step2.length()) {
          return step1;
        } else {
          return step2;
        }
      }
    }
  }

  // Q4

  /*
   * In this method I get a 2D array as a parameter and go
   * through each cell recursively and look for the longest route to the last
   * cell,
   * returning the longest route in number.
   */

  public static int maxSnake(int[][] mat) {
    int sum = maxSnake(mat, 0, 0, mat[0][0]);
    if (sum <= 1) {
      return Integer.MIN_VALUE;
    } else {
      return sum;
    }
  }

  private static int maxSnake(int[][] mat, int i, int j, int prev) {
    if (i < 0 || j < 0 || i > mat.length - 1 || j > mat[0].length - 1 || mat[i][j] == -1
        || mat[i][j] - prev > 1 || mat[i][j] - prev < -1) {
      return Integer.MIN_VALUE;
    }

    if (i == mat.length - 1 && j == mat[0].length - 1) {
      return 1;
    }

    prev = mat[i][j];
    mat[i][j] = -1;

    int down = 1 + maxSnake(mat, i + 1, j, prev);
    int right = 1 + maxSnake(mat, i, j + 1, prev);
    int up = 1 + maxSnake(mat, i - 1, j, prev);
    int left = 1 + maxSnake(mat, i, j - 1, prev);

    mat[i][j] = prev;

    return Math.max(Math.max(down, right), Math.max(up, left));
  }

  public static void main(String[] args) {
    int[] arr = { -8, 1, -4, 11, 9, -15, 10, 8, -3 };
    maxMul3(arr);
    System.out.println(maxMul3(arr));
    int[][] mat = {
        { 4, 5, 2, 3, 1 },
        { 3, 4, 1, 4, 4 },
        { 1, 5, 6, 7, 8 },
        { 3, 4, 5, 8, 9 },
        { 3, 2, 2, 7, 6 }
    };

    System.out.println(maxSnake(mat));

    int[] a1 = { 1, 12, 15, 26, 38 };
    int[] a2 = { 12, 13, 18, 30, 45 };

    System.out.println(findMedian(a1, a2));

  }

}
