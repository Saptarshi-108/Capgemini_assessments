// UserCacheDesign.java
package oops_Qs;

interface UserCace {
    User get(String userId);
    void put(User user);
    int size();
    boolean contains(String userId);
    void remove(String userId);
}

public class User {
    private final String userId;
    private final String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
}
