/**
 * 
 */
package mx.com.baz.poolconnsql.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @description
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
@Configuration
public class DataSourceOwnConfig {
	
	private static final HikariConfig config;
	private static final HikariDataSource dataSource;
	
	static {
		config = new HikariConfig();
		String connectionUrl = "jdbc:oracle:thin:@10.82.29.12:1522:godesa";
		config.setJdbcUrl(connectionUrl);
		config.setUsername("PREPROD_GODESA");
		config.setPassword("admi");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		dataSource = new HikariDataSource(config);
	}
	
	public DataSourceOwnConfig() {}
 
	public static final Connection getConnectionAzt() throws SQLException {
		return dataSource.getConnection();
	}
	public static final DataSource getDataSource() throws SQLException {
		return dataSource;
	}
}
