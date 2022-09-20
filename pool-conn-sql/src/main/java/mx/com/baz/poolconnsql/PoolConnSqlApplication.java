package mx.com.baz.poolconnsql;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//import org.jooq.tools.json.JSONArray;
//import org.jooq.tools.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

//import mx.com.baz.poolconnsql.config.DataSourceAlnovaConfig;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class PoolConnSqlApplication {

	public static void main(String[] args) /*throws SQLException*/ {
		SpringApplication.run(PoolConnSqlApplication.class, args);
		
		/**
		 * * * * * * * * * * * * * * * * * * * * * conn 1 * * * * * * * * * * * * * * * * * * * *
		 */
//		String selectSql2 = "SELECT * FROM [MAZP].[BGDT071] WHERE T071_ACC IN ('0112131067','0137697730')";
//		Connection connection = DataSourceAlnovaConfig.getConnection();
//		PreparedStatement statement = connection.prepareStatement(selectSql2);
//		ResultSet resultSet = statement.executeQuery();
//		
//		ResultSetMetaData md = resultSet.getMetaData();
//		int numCols = md.getColumnCount();
//		List<String> colNames = IntStream.range(0, numCols)
//		.mapToObj(i -> {
//			try {
//				return md.getColumnName(i + 1);
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return "?";
//			}
//		})
//		.collect(Collectors.toList());
//		
//		JSONArray result = new JSONArray();
//		while (resultSet.next()) {
//			JSONObject row = new JSONObject();
//			colNames.forEach(cn -> {
//				try {
//					row.put(cn, resultSet.getObject(cn));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			});
//			result.add(row);
//		}
//
//		System.out.println("********"+result);
//		
//		
//		/**
//		 * * * * * * * * * * * * * * * * * * * * * conn 2 * * * * * * * * * * * * * * * * * * * *
//		 */
//		String selectSql1 = "SELECT LOTE,FECHAOPERACION, STATUS,USUARIO,FOLIOSPEI FROM SPEI.SPEI_PAGOS WITH(NOLOCK) WHERE FECHAOPERACION = 20220810 AND TIPOPAGO = 1 AND TIPOOPERACION = 0 AND STATUS IN ('D','L')";
//		Connection connection2 = hikariDataSource();
//		PreparedStatement statement2 = connection2.prepareStatement(selectSql1);
//		ResultSet resultSet2 = statement2.executeQuery();
//		
//		ResultSetMetaData md2 = resultSet2.getMetaData();
//		int numCols2 = md2.getColumnCount();
//		List<String> colNames2 = IntStream.range(0, numCols2)
//		.mapToObj(i -> {
//			try {
//				return md2.getColumnName(i + 1);
//			} catch (SQLException e2) {
//				e2.printStackTrace();
//				return "?";
//			}
//		})
//		.collect(Collectors.toList());
//		
//		JSONArray result2 = new JSONArray();
//		while (resultSet2.next()) {
//			JSONObject row2 = new JSONObject();
//			colNames2.forEach(cn2 -> {
//				try {
//					row2.put(cn2, resultSet2.getObject(cn2));
//				} catch (Exception e2) {
//					e2.printStackTrace();
//				}
//			});
//			result2.add(row2);
//		}
//
//		System.out.println("********"+result2);
//	}
//	
//	
//	@SuppressWarnings("resource")
//	public static final Connection hikariDataSource() throws SQLException  {
//		HikariConfig config = new HikariConfig();
//		HikariDataSource dataSource;
//		
//		//KIOBANDEVMXSPEI\SPEI
//		//String connectionUrl = "jdbc:sqlserver://10.82.56.32:1433;";
//		//10.82.56.56:1433
////		String connectionUrl = "jdbc:sqlserver://10.82.56.56:1433;";
//		String connectionUrl = "jdbc:sqlserver://KIOBANDEVMXSPEI\\SPEI;";
//		config.setJdbcUrl(connectionUrl);
//		config.addDataSourceProperty("domain", "BANCOAZTECA");
//		config.addDataSourceProperty("database", "SPEI");
//		config.addDataSourceProperty("authenticationScheme", "NTLM");
//		config.addDataSourceProperty("integratedSecurity", "true");
//		config.addDataSourceProperty("encrypt", "true");
//		config.addDataSourceProperty("trustServerCertificate", "true");
////		config.addDataSourceProperty("loginTimeout", "30");
//		
//		config.setUsername("B1013840");
//		config.setPassword("BancoAzteca$%1");
//		config.addDataSourceProperty("cachePrepStmts", "true");
//		config.addDataSourceProperty("prepStmtCacheSize", "250");
//		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//		
//		dataSource = new HikariDataSource(config);
//		
//		return dataSource.getConnection();
	}
}
