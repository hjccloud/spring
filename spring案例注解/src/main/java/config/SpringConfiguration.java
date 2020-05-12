package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 该类是一个配置类，他的作用和bean.xml是一样的
 * spring中的新注解
 * Configuration
 *      作用：指定当前类是一个配置类
 * ComponentScan
 *       作用：用于通过注解指定spring在创建容器是要扫描的包
 *       属性：
 *              value：它和basePackages的作用是一样的，都适用于指定创建容器时要扫描的包
 *                      我们使用次注解就等同于在xml中配置了：
 *                              <context:component-scan base-package="com.he"></context:component-scan>
 * Bean
 *      作用：用于把当前方法的返回值作为Bean对象存入spring容器中
 *      属性：
 *          name：用于指定bean的id。当不写时，默认值是当前方法的名称
 *      细节：
 *          当我们使用注解配置方法时，如果方法有参数，spring框架回去容器中查找有没有可用的Bean对象
 *          查找的方式和Autowired注解的方式是一样的
 *Import
 *      作用：用于导入其他的配置类
 *      属性
 *          value：用于指定其他配置类的字节码
 *                  当我们使用Import的注解后，有Import注解的类就是朱配置类，导入的都是自配置类
 *PropertySource
 *      作用：用于指定properties的位置
 *      属性：
 *           value：指定文件的名称和路径
 *                      关键字：classpath表示类路径下
 */
@Configuration
@ComponentScan(basePackages = {"com.he"})
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    /**
     * 创建一个QueryRunnner对象
     */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    @Bean("dataSource")
    public DataSource createDataSource(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

    }
}
