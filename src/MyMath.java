public final class MyMath {
  public static final double EPSILON = 0.0001;

  /**
   * Checks if two double values are equal.
   * 
   * @param num1 the first double value
   * @param num2 the second double value
   * @return true if the doubles are considered equal, false otherwise
   */
  public static boolean doubleUguali(double num1, double num2) {
    if (Math.abs(num1 - num2) < EPSILON) {
      return true;
    } else {
      return false;
    }
  }
}
