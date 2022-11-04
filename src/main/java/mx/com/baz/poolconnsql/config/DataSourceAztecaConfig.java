/**
 * 
 */
package mx.com.baz.poolconnsql.config;

import java.sql.Connection;
import java.sql.SQLException;

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
public class DataSourceAztecaConfig {
	
	private static final HikariConfig config;
	private static final HikariDataSource dataSource;
	
	static {
		config = new HikariConfig();
		String connectionUrl = "jdbc:sqlserver://10.82.56.56:1533;";
//		String connectionUrl = "jdbc:sqlserver://KIOBANDEVMXSPEI\\SPEI;";
		config.setJdbcUrl(connectionUrl);
		config.addDataSourceProperty("domain", "BANCOAZTECA");
		config.addDataSourceProperty("database", "SPEI");
		config.addDataSourceProperty("authenticationScheme", "NTLM");
		config.addDataSourceProperty("integratedSecurity", "true");
		config.addDataSourceProperty("encrypt", "true");
		config.addDataSourceProperty("trustServerCertificate", "true");
		
		config.setUsername("B1013840");
		config.setPassword("BancoAzteca$%1");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		dataSource = new HikariDataSource(config);
//		config = new HikariConfig();
//		String connectionUrl = "jdbc:sqlserver://10.82.56.32:1433;";
//		config.setJdbcUrl(connectionUrl);
//		config.addDataSourceProperty("domain", "BANCOAZTECA");
//		config.addDataSourceProperty("database", "MAZP");
//		config.addDataSourceProperty("authenticationScheme", "NTLM");
//		config.addDataSourceProperty("integratedSecurity", "true");
//		config.addDataSourceProperty("encrypt", "true");
//		config.addDataSourceProperty("trustServerCertificate", "true");
//		
//		config.setUsername("B1013840");
//		config.setPassword("BancoAzteca$%1");
//		config.addDataSourceProperty("cachePrepStmts", "true");
//		config.addDataSourceProperty("prepStmtCacheSize", "250");
//		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//		
//		dataSource = new HikariDataSource(config);
	}
	
	public DataSourceAztecaConfig() {}
 
	public static final Connection getConnectionAzt() throws SQLException {
		return dataSource.getConnection();
	}
}
