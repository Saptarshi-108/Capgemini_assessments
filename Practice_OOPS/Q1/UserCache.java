// UserCache.java
package oops_Qs;

import java.util.*;

public class UserCache {
    private final int capacity;
    private final Map<String, User> userMap;
    private final Deque<String> lruList; // front = LRU, back = MRU

    public UserCache(int capacity) {
        this.capacity = capacity;
        this.userMap = new HashMap<>();
        this.lruList = new ArrayDeque<>();
    }

    public User get(String userId) {
    	if (userMap.containsKey(userId)) {
    		return userMap.get(userId);
    	}
    	else {
    		return null;
    	}
    }

    public void put(User user) {
    	String userid= user.getUserId();
    	if (userMap.containsKey(userid)) {
    		userMap.put(userid,user);
    		lruList.remove(userid);
    		lruList.addLast(userid);
    	}
    	else {
    		if (userMap.size()>=capacity && !lruList.isEmpty()) {
    			String lruId = lruList.removeFirst();
    			userMap.remove(lruId);
    		}
    		userMap.put(userid, user);
    		lruList.addLast(userid);
    	}
    	
    }

    public int size() {
    	return lruList.size();
    }

    public boolean contains(String userId) {
    	if (userMap.containsKey(userId)) {
    		return true;
    	}
    	else return false;
    }

    public void remove(String userId) {
    	if (userMap.remove(userId)!=null) {
    		lruList.remove(userId);
    	}
    }

    public static void main(String[] args) {
        UserCache cache = new UserCache(3);

        User u1 = new User("u1", "Alice");
        User u2 = new User("u2", "Bob");
        User u3 = new User("u3", "Charlie");
        User u4 = new User("u4", "Diana");

        cache.put(u1);
        cache.put(u2);
        cache.put(u3);
        System.out.println("size: " + cache.size()); // 3

        cache.get("u1"); // u1 becomes MRU
        cache.put(u4);   // u2 (LRU) evicted
        System.out.println("contains u2: " + cache.contains("u2")); // false
        System.out.println("contains u4: " + cache.contains("u4")); // true
    }
}
