import com.landian.dao.UserDao;
import com.landian.dao.impl.UserDaoImpl;
import com.landian.domain.User;
import org.junit.Test;

public class testUserDao {

    @Test
    public void testRegisterDao(){
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setUsername("321");
        user.setPassword("123123");
        user.setEmail("154981919@qq.com");
        boolean flag = userDao.register(user);
        System.out.println(flag);
    }

    @Test
    public void testLogin(){
        UserDao userDao = new UserDaoImpl();
        User user = new User();
        user.setUsername("111");
        user.setPassword("111");
        System.out.println(userDao.login(user).getEmail());
    }

}
