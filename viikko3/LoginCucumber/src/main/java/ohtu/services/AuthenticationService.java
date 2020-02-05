package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

  private UserDao userDao;

  public AuthenticationService(UserDao userDao) {
    this.userDao = userDao;
  }

  public boolean logIn(String username, String password) {
    for (User user : this.userDao.listAll()) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }

  public boolean createUser(String username, String password) {
    if (this.userDao.findByName(username) != null) {
      return false;
    }
    if (invalid(username, password)) {
      return false;
    }
    this.userDao.add(new User(username, password));
    return true;
  }

  private boolean invalid(String username, String password) {
    if (username.length() < 3 || password.length() < 8) {
      return true;
    }
    if (this.userDao.listAll().contains(username)) {
      return true;
    }
    for (char c : username.toCharArray()) {
      if(!Character.isLetter(c)) {
        return true;
      }
    }
    char[] passwordChars = password.toCharArray();
    for (int i = 0; i < passwordChars.length; i++) {
      if (!Character.isLetter(passwordChars[i])) {
        break;
      }
      if (i == passwordChars.length - 1) {
        return true;
      }
    }
    return false;
  }
}
