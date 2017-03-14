package edu.nju.hostelworld.service.bean;

import edu.nju.hostelworld.DAO.HostelDAO;
import edu.nju.hostelworld.DAO.ManagerDAO;
import edu.nju.hostelworld.DAO.MemberDAO;
import edu.nju.hostelworld.DAO.UserDAO;
import edu.nju.hostelworld.entity.Hostel;
import edu.nju.hostelworld.entity.Manager;
import edu.nju.hostelworld.entity.Member;
import edu.nju.hostelworld.entity.User;
import edu.nju.hostelworld.service.UserService;
import edu.nju.hostelworld.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 17/3/3.
 */
@Transactional
@Service
public class UserServiceBean implements UserService {
    @Autowired
    UserDAO userDao;
    @Autowired
    MemberDAO vipDao;
    @Autowired
    HostelDAO hostelDao;
    @Autowired
    ManagerDAO managerDAO;


    @Override
    public ResultMessage register(Class<?> c, String userName, String password) {
        List users = userDao.getByRestrictEqual("userName", userName);
        if (users.size() != 0) return ResultMessage.DUPLICATE_NAME;
        int userId = 0;
        if (c == Member.class) {
            Member vip = new Member();
            vip.setRealName(userName);
            try {
                userId = vipDao.add(vip);
            } catch (Exception e) {
                return ResultMessage.FAILURE;
            }
        } else if (c == Hostel.class) {
            Hostel hostel = new Hostel();
            hostel.setName(userName);
            try {
                userId = hostelDao.add(hostel);
            } catch (Exception e) {
                return ResultMessage.FAILURE;
            }
        } else if (c == Manager.class) {
            Manager manager = new Manager();
            manager.setName(userName);
            try {
                userId = managerDAO.add(manager);
            } catch (Exception e) {
                return ResultMessage.FAILURE;
            }
        }
        User user = new User();
        user.setType(c.getSimpleName().toLowerCase());
        user.setUserName(userName);
        user.setUserid(userId);
        user.setPassword(password);

        try {
            userDao.add(user);

        } catch (Exception e) {
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage register(String userName, String password) {
        return register(Member.class, userName, password);
    }

    @Override
    public ResultMessage delete(int userId) {
        //TODO
        return null;
    }

    @Override
    public ResultMessage changePassword(int id, String original, String password) {
        User user = userDao.get(id);
        if (!user.getPassword().equals(original)) {
            return ResultMessage.WRONG_ORIGINAL;
        }
        if (original.equals(password)) {
            return ResultMessage.NOT_CHANGE;
        }
        user.setPassword(password);
        return userDao.update(user);
    }

    @Override
    public ResultMessage changeBankMoneyTo(int id, double money) {
        User user = userDao.get(id);
        user.setBankMoney(money);
        return userDao.update(user);
    }

    @Override
    public ResultMessage changeBankMoneyAdd(int id, double money) {
        User user = userDao.get(id);
        System.out.println(user.getBankMoney());
        user.setBankMoney(user.getBankMoney() + money);
        System.out.println(user.getBankMoney());
        return userDao.update(user);
    }

    @Override
    public ResultMessage changeBankMoneyMinus(int id, double money) {
        User user = userDao.get(id);
        System.out.println(user.getBankMoney());
        user.setBankMoney(user.getBankMoney()-money);
        System.out.println(user.getBankMoney());
        return userDao.update(user);
    }

    @Override
    public User getById(int userId) {
        return userDao.get(userId);
    }

    @Override
    public List<User> getByType(String type) {
        return userDao.getByRestrictEqual("type", type);
    }

    @Override
    public User login(String userName, String password) {
        //System.out.println("UserServiceBean-login " + userName + "  " + password);
        Map map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("password", password);
        List<User> ans = userDao.getByRestrictEqual(map);
        if (ans == null) {
            return null;
        } else if (ans.size() == 0) {
            return null;
        } else {
            return ans.get(0);
        }
    }

    @Override
    public ResultMessage checkPassword(String userName, String password) {
        List<User> users = userDao.getByRestrictEqual("userName", userName);
        if (users == null || users.size() == 0) {
            return ResultMessage.NOT_EXIST;
        }
        User user = users.get(0);
        if (user.getPassword().equals(password)) {
            return ResultMessage.SUCCESS;
        } else {
            return ResultMessage.WRONG_PASSWORD;
        }
    }

    @Override
    public ResultMessage checkUserExist(String userName) {
        List<User> users = userDao.getByRestrictEqual("userName", userName);
        if (users == null) {
            return ResultMessage.NOT_EXIST;
        } else if (users.size() == 0) {
            return ResultMessage.NOT_EXIST;
        } else return ResultMessage.SUCCESS;
    }
}
