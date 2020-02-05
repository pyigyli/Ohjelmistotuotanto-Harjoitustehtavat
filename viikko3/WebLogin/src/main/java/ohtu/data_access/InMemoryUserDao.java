package ohtu.data_access;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {

  private List<User> users;

  public InMemoryUserDao() {
    this.users = new ArrayList<User>();
    User u = new User();
    this.users.add(new User("pekka", "akkep"));
  }  

  @Override
  public List<User> listAll() {
    return this.users;
  }

  @Override
  public User findByName(String name) {
    for (User user : this.users) {
      if (user.getUsername().equals(name)) {
        return user;
      }
    }

    return null;
  }

  @Override
  public void add(User user) {
    this.users.add(user);
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<User> getUsers() {
    return this.users;
  }
}
