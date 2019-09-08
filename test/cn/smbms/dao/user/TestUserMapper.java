package cn.smbms.dao.user;



import cn.smbms.pojo.User;
import cn.smbms.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUserMapper {
    Logger logger = Logger.getLogger(TestUserMapper.class);

    @Before
    public void defore(){
        System.out.println("before");
    }

    @Test
    //利用selectList查询，参数一个是映射文件路径，一个是查询语句的where条件的id
        public void testUserList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        List<User> listUser = sqlSession.getMapper(UserDao.class).getListUser();//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.ListUser",1);//这两种方法都行。这个是通过映射文件路径加id，完全限定名,找到sql语句
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
            }
        }

    @Test
    public void tstUserList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
//        List<User> listUser = sqlSession.getMapper(UserDao.class).getListUser();//加载接口，getListUser()的方法
        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.getListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }

    @Test
    //单表查询，通过id查询表中用户的所有信息
    public void tstrList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();
        int i=1;
     List<User> listUser = sqlSession.getMapper(UserDao.class).ListUser(i);//加载接口，getListUser()的方法

        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }

    @Test
    //参数为数组的foreach查询
    public void Array(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        Integer[] Id={1,2};
        List<User> listUser = sqlSession.getMapper(UserDao.class).getUserListByRoleId_foreach_array(Id);
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.getListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }

    @Test
    //参数为map的foreach多参数查询，也就是条件是两个复选框，或者多个，这是一个复选框，一个单选框
    public void ArrayMap(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
            Map<String,Object > map=new HashMap<String,Object>();
            Integer[] Id={1,2};

            map.put("aaa","1");
            map.put("bbb",Id);//bbb是数组Id，也就是map中的K值，是自己起名的，Id是map中的v值，通过k值找到Id值，也就是用户给的值。所以bbb一定要和mappper中的collection值保持一致
        List<User> listUser = sqlSession.getMapper(UserDao.class).getUserListByMap_foreach_map(map);
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.getListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }
    @Test
    //对象封装
    public void ttUserList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        User user1 = new User();
        user1.setUserName("bbb");
        user1.setId(1);
        List<User> listUser = sqlSession.getMapper(UserDao.class).LListUser(user1);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }

    @Test
    //动态
    public void DongUserList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        Integer id=2;
        String userName="李明";
        List<User> listUser = sqlSession.getMapper(UserDao.class).DongTaiUser(id,userName);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }
//
    @Test
    //choos标签的使用
    public void ChooseList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        Integer userRole=2;
        Integer gender=2;
        List<User> listUser = sqlSession.getMapper(UserDao.class).Choose(userRole,gender);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getUserName());
        }
    }

    //
    @Test
    //分页查询
    public void FenYeList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        Integer userRole=3;
        Integer from=0;
        Integer page=3;
        List<User> listUser = sqlSession.getMapper(UserDao.class).FenYe(userRole,from,page);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getUserName());
        }
    }

    @Test
    //数据库添加数据
    public void add(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        User user1 = new User();
        user1.setUserCode("sfsd");
        user1.setUserName("dsfhjksdf");
        int count = sqlSession.getMapper(UserDao.class).add(user1);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        System.out.println(count);
        sqlSession.commit();
    }
    @Test
    //数据库修改数据
    public void update(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        User user1 = new User();
        user1.setUserCode("sdggds");
        user1.setUserName("dsfhjksdf");
        user1.setId(1);
        int count = sqlSession.getMapper(UserDao.class).update(user1);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        System.out.println(count);
    }
    @Test
    //数据库修改数据,接口多参
    public void update1(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        int i=1;
        String s="bbb";
        int count = sqlSession.getMapper(UserDao.class).update1(i,s);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        System.out.println(count);
        sqlSession.commit();
    }
    @Test
    //数据库删除数据
    public void del(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        User user1 = new User();
        user1.setId(1);
        int count = sqlSession.getMapper(UserDao.class).delete(user1);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        System.out.println(count);
    }
    @Test
    //map封装，如果查询的语句的where条件是两个，那么就进行map封装
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
       Map<String,String> map = new HashMap<String,String>();
        map.put("id","1");
        map.put("name","系统管理员");
        List<User> listUser = sqlSession.getMapper(UserDao.class).LListUserGetMap(map);
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate());
        }
    }
    @Test
   //两个表联合查询,查询user表中的用户在role表中的角色。
    public void UserListMap(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        Map<String,String> map = new HashMap<String,String>();
        map.put("id","1");
        map.put("name","bbb");
        List<User> listUser = sqlSession.getMapper(UserDao.class).GetMap(map);
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getCreationDate()+user.getUsernameRole()+user.getAddress()+user.getUserCode());
    }
    }
    @Test
    //表role作为表user的属性，联合查询两个表中的信息
        public void UserList(){
        SqlSession sqlSession = new MybatisUtil().createSqlSession();//调用MybatisUtil中的方法createSqlSession()，直接返回sqlSession
        int id=3;
        List<User> listUser = sqlSession.getMapper(UserDao.class).getUserListByRoleId(id);//加载接口，getListUser()的方法
//        List<User> listUser = sqlSession.selectList("cn.smbms.dao.user.UserDao.LListUser");//这两种方法都行。这个是通过路径加id，完全限定名
        for (User user : listUser) {
            logger.debug(user.getUserCode()+user.getCreationDate()+user.getRole().getRoleCode()+user.getRole().getId());//user.getRole().getRoleCode()因为Role是user中的属性，
            // 但也是javabean，所以要得到role中的属性，就要两次get
        }
    }
    @Test
    //查询一条记录
        public void testUserMapper(){
        //调用mapper文件来对数据库进行操作，必须将mapper文件引入到mybatis-config中
        SqlSession sqlSession = new MybatisUtil().createSqlSession();
        //sqlSession.selectOne方法返回的是object类型，因为配置文件已经规定返回类型是int，所以可以int接受
        int i = sqlSession.selectOne("cn.smbms.dao.user.UserDao.count");//通过selectone方法执行sql语句，参数要填配置文件的路径，加count是查询标签中的id
        //通过selectone方法执行sql语句，要找到sql语句，参数就是语句所在的配置文件中的namespace，加上每一个SQL语句的id是不一样的，所以要加上SQL语句的id值
        System.out.println(i);
}
    @After
        public void testAfter(){
        System.out.println("after");
        }
}
