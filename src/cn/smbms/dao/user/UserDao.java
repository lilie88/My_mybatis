package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    //查询用户数量
    int count();//方法名要和mapper中的id名相同，这样接口的方法才能映射，也就是找到mapper要执行的语句

       List<User> getListUser();

    List<User> ListUser(int id);



    List<User> LListUser(User user);

    List<User> DongTaiUser(@Param("id") Integer id,@Param("userName") String userName);

    //利用choose标签进行查询
    List<User> Choose(@Param("userRole") Integer userRole,@Param("gender") Integer gender);

    //分页查询
    List<User> FenYe(@Param("userRole") Integer userRole,@Param("from") Integer from,@Param("page") Integer page);

    List<User> LListUserGetMap(Map<String,String> map);

    List<User> GetMap(Map<String,String> map);//查询user表中的用户在role表中所对应的角色

    int add(User user);

    int update(User user);

    int update1( @Param("id") int id,@Param("userName") String userName);//一个参数没有问题，当接口中的方法参数是多参数的时候，和mapper文件中的数据库字段名一一对应，也会报错。
    // 所以参数前加注释@param，@param中的值要和mapper数据库字段名一致。

    int delete(User user);

    List<User> getUserListByRoleId( int id);//通过角色表中的id来获取user表中的信息

//参数为数组的查询，也就是用户在选择复选框的时候，查询
    List<User> getUserListByRoleId_foreach_array( Integer[] userRoleId);


//当页面有一个复选框，还有一个单选框的时候，mapper查询语句的时候，有一个条件肯定要有集合或者数组，另一个也可能是集合或者数组，这时就把这俩条件放在map里面，
    //当前方法是一个条件是array，另一个查询条件是具体参数，属性gender
    List<User> getUserListByMap_foreach_map( Map<String,Object> map);





}
