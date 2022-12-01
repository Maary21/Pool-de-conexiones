/**
 * 
 */
package mx.com.baz.poolconnsql.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import mx.com.baz.poolconnsql.config.DataSourceOwnConfig;
import mx.com.baz.poolconnsql.model.request.PlKeyValueRequest;
import mx.com.baz.poolconnsql.model.response.TcLecturaTrans;
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
@Slf4j
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
	
	
	public ArrayList<?> getLecturaTrans(Integer idConciliacion){
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(DataSourceOwnConfig.getDataSource()).withFunctionName("FN_TCLECTURATRAN");
			SqlParameterSource in = new MapSqlParameterSource().addValue("T_DATA", idConciliacion);
			ArrayList<?> name = jdbcCall.executeFunction(ArrayList.class, in);
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Boolean saveLecturaTran(TcLecturaTrans data){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			//for(TcLecturaTrans data:dataList) {
				String fecha = sdf.format(data.getFdFecAlta());
				SimpleJdbcCall jdbcCall = new SimpleJdbcCall(DataSourceOwnConfig.getDataSource()).withProcedureName("SP_INS_TCLECTURATRAN");
				SqlParameterSource in = new MapSqlParameterSource().addValue("C_FCPARSEO", data.getFcParseo())
				.addValue("C_FCRESULTADO", data.getFcResultado())
				.addValue("C_FCNOMBRE", data.getFcNombre())
				.addValue("C_FIESTATUS", data.getFiEstatus())
				.addValue("C_FKIDCONCILIACION", data.getFkIdConciliacion())
				.addValue("C_FCUSRMODIF", data.getFcUsrModif())
				.addValue("C_FDFECALTA", fecha)
				.addValue("C_FIORIGENINFO", data.getFiOrigenInfo());
				jdbcCall.execute(in);
			//}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Boolean updateLecturaTran(List<TcLecturaTrans> dataList){
		try {
			for(TcLecturaTrans data:dataList) {
				SimpleJdbcCall jdbcCall = new SimpleJdbcCall(DataSourceOwnConfig.getDataSource()).withProcedureName("SP_UPD_TCLECTURATRAN");
				SqlParameterSource in = new MapSqlParameterSource().addValue("C_PKIDTRANSACCION", data.getPkIdTransaccion())
				.addValue("C_FKIDCONCILIACION", data.getFkIdConciliacion());
				jdbcCall.execute(in);
				log.debug("data.getPkIdTransaccion()): "+data.getPkIdTransaccion()+ "  data.getFkIdConciliacion()  "+ data.getFkIdConciliacion() );
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public Boolean saveLecturaTranBatch(List<TcLecturaTrans> dataList) {
		 try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceOwnConfig.getDataSource());
			jdbcTemplate.batchUpdate(" INSERT INTO TCLECTURATRAN (PKIDTRANSACCION,FCPARSEO,FCRESULTADO,FCNOMBRE,FIESTATUS,FKIDCONCILIACION,FCFASE,FCUSRMODIF,FDFECMODIF,FDFECALTA, FIORIGENINFO) "
					+ " SELECT id_tran_seq.NEXTVAL,?,?,?,?,?,FN_AVANCE_INIT(?),?,?,?,? FROM DUAL ",dataList, 100, (PreparedStatement ps, TcLecturaTrans dto) -> {
		          ps.setString(1, dto.getFcParseo());
		          ps.setString(2, dto.getFcResultado());
		          ps.setString(3, dto.getFcNombre());
		          if(dto.getFiEstatus()!=null)
		        	  ps.setInt(4, dto.getFiEstatus());
		          else
		        	  ps.setString(4, null);
		          ps.setInt(5, dto.getFkIdConciliacion());
		          ps.setInt(6, dto.getFkIdConciliacion());
		          ps.setString(7, dto.getFcUsrModif());
		          ps.setDate(8, new java.sql.Date(dto.getFdFecModif().getTime()));
		          ps.setDate(9, new java.sql.Date(dto.getFdFecAlta().getTime()));
		          ps.setInt(10, dto.getFiOrigenInfo());
		        });	
			return true;
		} catch (SQLException e) {
			log.error("saveLecturaTranBatch: ", e);
			return false;
		}
	}
	
	
	public Boolean updateLecturaTranBatch(List<TcLecturaTrans> dataList) {
		JdbcTemplate jdbcTemplate;
		try {
			jdbcTemplate = new JdbcTemplate(DataSourceOwnConfig.getDataSource());
			jdbcTemplate.batchUpdate(" UPDATE TCLECTURATRAN "
					+ "	SET FCFASE = FN_AVANCE_CHAR(?) "
					+ "	WHERE PKIDTRANSACCION = ?",dataList, 100, (PreparedStatement ps, TcLecturaTrans dto) -> {
		          ps.setInt(1, dto.getFkIdConciliacion());
		          ps.setInt(2, dto.getPkIdTransaccion());
		        });	
			return true;
		} catch (SQLException e) {
			log.error("updateLecturaTranBatch: ", e);
			return false;
		}
	}
	
	
	

}

