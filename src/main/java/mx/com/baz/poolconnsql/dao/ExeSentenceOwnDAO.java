/**
 * 
 */
package mx.com.baz.poolconnsql.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;


import mx.com.baz.poolconnsql.config.DataSourceOwnConfig;
import mx.com.baz.poolconnsql.model.request.PlKeyValueRequest;
import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
@Repository
public class ExeSentenceOwnDAO {
	public String DATABASE_SCHEMA	= "{ ? = call PREPROD_GODESA.";
	public String FN_GET_CATALOGUE	= "FNGOCATALOGOS(?,?)";
	
	public Mono<Map<String, String>> getCatalogue(/*String idGeneral*/) {
//		String idGeneral = "42";
//		long tini = System.currentTimeMillis();
////		log.info("Start query to DB");
//		try {
//			JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceOwnConfig.getDataSource());
//			log.info("Call {}", DATABASE_SCHEMA + FN_GET_CATALOGUE);
//			return jdbcTemplate.execute((CallableStatementCreator) con ->{
//				con = con.unwrap(OracleConnection.class);
//				CallableStatement cs = con.prepareCall(DATABASE_SCHEMA+FN_GET_CATALOGUE);
//				cs.registerOutParameter(1, CURSOR_TYPE);
//				cs.setString(2, idGeneral);
//				cs.setString(3, null);
//				return cs;
//			},(CallableStatementCallback<Mono<Map<String, String>>>) cs -> {
//				cs.execute();
//				ResultSet rs = (ResultSet) cs.getObject(1);
				Map<String, String> map = new HashMap<String, String>();
				/*while (rs.next()) {
					map.put(rs.getString(4), rs.getString(3));
				}*/
				map.put("ipMsServer", "http://localhost");
				map.put("portMsServer", "8082");
				map.put("uriMsAlnova", "/api/v1/query/alnova/spei");
				map.put("uriMsAzteca", "/api/v1/query/azteca/spei");
				map.put("query Azteca", "SELECT * FROM [MAZP].[BGDT071] WHERE T071_ACC IN ('0112131067','0137697730')");
				map.put("query Alnova", "SELECT LOTE,FECHAOPERACION, STATUS,USUARIO,FOLIOSPEI FROM SPEI.SPEI_PAGOS WITH(NOLOCK) WHERE FECHAOPERACION = 20220810 AND TIPOPAGO = 1 AND TIPOOPERACION = 0 AND STATUS IN ('D','L')");
//				log.info("Map of parameters {}", map);
//				log.info("Query carried out correctly {}, time {}", map.toString(), (System.currentTimeMillis() - tini));
				return Mono.just(map);
//			});
//		} catch (Exception e) {
////			e.printStackTrace();
//			log.info("An fail occurred {} response time {}", e.getMessage(), (System.currentTimeMillis() - tini));
//			return null;
//		}
	}

	public ArrayList<?> getKeyValue(PlKeyValueRequest request) {
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(DataSourceOwnConfig.getDataSource()).withFunctionName("FN_GET_DATA_KEY_VALUE");
			SqlParameterSource in = new MapSqlParameterSource().addValue("T_DATA", request.getTipoDatos())
										.addValue("T_CON", request.getTipoCoinciliacion())
										.addValue("ID_APP", request.getIdAplicacion());

			ArrayList<?> name = jdbcCall.executeFunction(ArrayList.class, in);
			return name;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public ArrayList<?> getDataFromFunction(String nombreProcedimiento){
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(DataSourceOwnConfig.getDataSource()).withFunctionName(nombreProcedimiento);
			SqlParameterSource in = new MapSqlParameterSource();
			ArrayList<?> name = jdbcCall.executeFunction(ArrayList.class, in);
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
