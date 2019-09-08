package cn.smbms.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fic on 2019.03.21 10:15
 * Package:mybatis0321.cn.smbms.utils
 * Desc:
 */
public class MybatisUtil {
    private static SqlSessionFactory factory;//使用SqlSessionFactory工厂得到实例SqlSession，通过openSession方法得到。有一个工厂就够了
            static {
                try {
            //获取mybatis-config的输入流，加载mybatis文件，为了访问数据库
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //创建SqlSessionFactory对象，完成对配置文件的读取
            factory = new SqlSessionFactoryBuilder().build(resourceAsStream);//流把mybatis文件读进去，然后工厂再把流读出来，
            // 有了factory，才能创建SqlSession，才能访问数据库，SqlSession相当于connection
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//有了SqlSession，才能对数据库进行数据库增删改查。创建SqlSession，利用SqlSessionFactory的openSession方法
    public SqlSession createSqlSession(){ return factory.openSession(false);}//ture是关闭事务控制，false是开启事务

    public void closeSqlSession(SqlSession sqlSession){
            if(sqlSession!=null){
            sqlSession.close();
        }
    }
}