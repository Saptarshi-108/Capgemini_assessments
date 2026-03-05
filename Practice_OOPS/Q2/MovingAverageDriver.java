package oops_Qs;

//MovingAverageDriver.java
import java.util.*;

public class MovingAverageDriver<T extends Number> implements MovingAverage<T> {
 private final List<T> values;
 private final int capacity;

 public MovingAverageDriver(int capacity) {
     this.values = new ArrayList<>();
     this.capacity = capacity;
 }

 @Override
 public void add(T value) {
	 values.add(value);
	 if (values.size()>capacity) {
		 values.remove(0);
	 }
 }

 @Override
 public double getAverage() {
	 if (values.isEmpty()) {
		 return 0.0;
	 }
	 double sum=0d;
	 for (T i:values) {
		 sum+=i.doubleValue();
	 }
	 return sum/values.size();
 }

 @Override
 public int size() {
    return values.size();
 }

 @Override
 public boolean isFull() {
     return values.size()==capacity;
 }

 @Override
 public void clear() {
    values.clear();
 }

 public static void main(String[] args) {
     MovingAverageDriver<Integer> ma = new MovingAverageDriver<>(3);

     ma.add(1);
     ma.add(2);
     ma.add(3);
     System.out.println("avg: " + ma.getAverage()); // 2.0

     ma.add(4); // drops 1
     System.out.println("avg: " + ma.getAverage()); // 3.0
 }
}
