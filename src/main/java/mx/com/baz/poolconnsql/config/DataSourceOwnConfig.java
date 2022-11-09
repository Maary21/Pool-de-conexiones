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
		String connectionUrl = "jdbc:oracle:thin:@10.82.56.210:4410/EOBDDes";
		config = new HikariConfig();
		config.setJdbcUrl(connectionUrl);
		config.setUsername("USRADMINDES");
		config.setPassword("ADMADNg39jK12Jo");
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
