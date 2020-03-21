package eu.brickfire.bauerntinder.module;

import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import eu.brickfire.bauerntinder.mapper.FieldMapper;
import eu.brickfire.bauerntinder.mapper.PersonMapper;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;

import javax.sql.DataSource;
import java.util.Properties;

public class MariaDBConfigModule extends PrivateModule {
 
    @Override
    protected void configure() {
        this.install(new MyBatisModule() {
 
            @Override
            protected void initialize() {
                this.bindTransactionFactoryType(JdbcTransactionFactory.class);
                this.addMapperClass(PersonMapper.class);
                this.addMapperClass(FieldMapper.class);

                Properties myBatisProperties = new Properties();
                myBatisProperties.setProperty("mybatis.environment.id", "production");
                Names.bindProperties(this.binder(), myBatisProperties);
            }
 
            @Singleton
            @Provides
            public DataSource getDataSource() {
                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setMaximumPoolSize(1);
                System.out.println(System.getenv("BAUERNTINDER_DB_DRIVER") + ": "+
                        System.getenv("BAUERNTINDER_DB_USER") + "@" + System.getenv("BAUERNTINDER_DB_JDBC_URL") + " password(0-3*): " + System.getenv("BAUERNTINDER_DB_PASSWORD").substring(0,3) + "*");
                dataSource.setDriverClassName(System.getenv("BAUERNTINDER_DB_DRIVER")); //"org.mariadb.jdbc.Driver"
                dataSource.setJdbcUrl(System.getenv("BAUERNTINDER_DB_JDBC_URL")); //"jdbc:mariadb://s2.smarthive.de:3306/bauerntinder"
                dataSource.setAutoCommit(false);
                dataSource.setUsername(System.getenv("BAUERNTINDER_DB_USER"));
                dataSource.setPassword(System.getenv("BAUERNTINDER_DB_PASSWORD"));
 
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setDataSource(dataSource);
                hikariConfig.setMinimumIdle(0);
 
                return new HikariDataSource(hikariConfig);
            }
        });
        this.expose(PersonMapper.class);
        this.expose(FieldMapper.class);
    }

}