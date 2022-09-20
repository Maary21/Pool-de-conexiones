/**
 * 
 */
package mx.com.baz.poolconnsql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.jooq.tools.json.JSONParser;
import org.jooq.tools.json.ParseException;
import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;
import mx.com.baz.poolconnsql.config.DataSourceAlnovaConfig;
import mx.com.baz.poolconnsql.exceptions.SentenceExeption;
import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
@Log4j2
@Repository
public class ExeSentenceAnvDAO {

	public Mono<String> getSpeiAlnova(String query){
		try {
			Connection connection = DataSourceAlnovaConfig.getConnectionAln();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet r = statement.executeQuery();
			
			ResultSetMetaData md = r.getMetaData();
			int numCols = md.getColumnCount();
			List<String> colNames = IntStream.range(0, numCols)
					.mapToObj(i -> {
					try {
							return md.getColumnName(i + 1);
						} catch (SQLException e) {
							e.printStackTrace();
							return "?";
						}
					})
					.collect(Collectors.toList());
					
			JSONArray result = new JSONArray();
			while (r.next()) {
				JSONObject row = new JSONObject();
				colNames.forEach(cn -> {
					try {
						row.put(cn, r.getObject(cn));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				result.add(row);
			}
			log.info(result.toString());
			return Mono.just(result.toString());
		} catch (Exception e) {
			return Mono.error(new SentenceExeption(400, "", e.getMessage()));
		}
//		String data="{\"Consulta\":[{\"T071_ENT\":\"0127\",\"T071_CEN_REG\":\"2526\",\"T071_ACC\":\"0182957559\",\"T071_NUM_RNWSEQ\":\"0\",\"T071_NUM_OPERATION\":\"50\",\"T071_CODE\":\"907\",\"T071_AMOUNT\":\"510.70\",\"T071_DAT_ACCT\":\"2008-08-17\",\"T071_DAT_OPERATION\":\"2008-08-17\",\"T071_TIM_OPERATION\":\"0028\",\"T071_DAT_VALUE\":\"2008-08-17\",\"T071_FCC\":\"MXP\",\"T071_CHECK\":\"0\",\"T071_TYP_ACCT\":\"024\",\"T071_OBSERVATIONS\":\"SEMANA 32                     &\",\"T071_AUTBAL\":\"8.20\",\"T071_ENT_ACCT\":\"0127\",\"T071_CEN_ACCT\":\"2526\",\"T071_COD_PRODUCT\":\"01\",\"T071_COD_SPROD\":\"0032\",\"T071_FLG_AUTOMAN\":\"A\",\"T071_FLG_UPDATE\":\"N\",\"T071_FLG_MODIFIED\":\"N\",\"T071_FLG_ANN\":\"N\",\"T071_FLG_CHECKED\":\"N\",\"T071_FLG_CREDEB\":\"H\",\"T071_ENT_UPDATE\":\"0127\",\"T071_CEN_UPDATE\":\"0254\",\"T071_USERUPD\":\"DM4C2020\",\"T071_UPDATM\":\"A\",\"T071_NTNMUPD\":\"T428\",\"T071_TIMESTAMP\":\"2008-08-16-00.28.20.723847\",\"T071_AMT_ORIGIN\":\"510.70\",\"T071_OCC\":\"MXP\",\"T071_AMT_FREE1\":\"0.00\",\"T071_AMT_FREE2\":\"0.00\",\"T071_AMT_FREE3\":\"8.20\",\"T071_FLG_FREE1\":\"03\"},{\"T071_ENT\":\"0127\",\"T071_CEN_REG\":\"2526\",\"T071_ACC\":\"0182808633\",\"T071_NUM_RNWSEQ\":\"0\",\"T071_NUM_OPERATION\":\"22\",\"T071_CODE\":\"907\",\"T071_AMOUNT\":\"900.00\",\"T071_DAT_ACCT\":\"2008-08-17\",\"T071_DAT_OPERATION\":\"2008-08-17\",\"T071_TIM_OPERATION\":\"0028\",\"T071_DAT_VALUE\":\"2008-08-17\",\"T071_FCC\":\"MXP\",\"T071_CHECK\":\"0\",\"T071_TYP_ACCT\":\"024\",\"T071_OBSERVATIONS\":\"SEMANA 32                     &\",\"T071_AUTBAL\":\"0.00\",\"T071_ENT_ACCT\":\"0127\",\"T071_CEN_ACCT\":\"2526\",\"T071_COD_PRODUCT\":\"01\",\"T071_COD_SPROD\":\"0032\",\"T071_FLG_AUTOMAN\":\"A\",\"T071_FLG_UPDATE\":\"N\",\"T071_FLG_MODIFIED\":\"N\",\"T071_FLG_ANN\":\"N\",\"T071_FLG_CHECKED\":\"N\",\"T071_FLG_CREDEB\":\"H\",\"T071_ENT_UPDATE\":\"0127\",\"T071_CEN_UPDATE\":\"0254\",\"T071_USERUPD\":\"DM4C2020\",\"T071_UPDATM\":\"A\",\"T071_NTNMUPD\":\"T428\",\"T071_TIMESTAMP\":\"2008-08-16-00.28.20.803490\",\"T071_AMT_ORIGIN\":\"900.00\",\"T071_OCC\":\"MXP\",\"T071_AMT_FREE1\":\"0.00\",\"T071_AMT_FREE2\":\"0.00\",\"T071_AMT_FREE3\":\"0.00\",\"T071_FLG_FREE1\":\"03\"}]}";
//		data = data.replaceAll("\\\"", "\"");
//		return Mono.just(data);
	}
}
