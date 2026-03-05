// RateLimiterDesign.java
package oops_Qs;

//MovingAverageDesign.java
public interface MovingAverage<T extends Number> {
 /**
  * 
  * Adds a new value to the window.
  * If window is full, oldest value is replaced.
  */
 void add(T value);

 /**
  * Returns the current average of all values in the window.
  * Returns 0.0 if window is empty.
  */
 double getAverage();

 /**
  * Returns current number of values in the window.
  */
 int size();

 /**
  * Returns true if window is full.
  */
 boolean isFull();

 /**
  * Resets the window (clears all values).
  */
 void clear();
}
