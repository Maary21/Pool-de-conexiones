/**
 * 
 */
package mx.com.baz.poolconnsql.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import mx.com.baz.poolconnsql.dao.ExeSentenceAnvDAO;
import mx.com.baz.poolconnsql.dao.ExeSentenceAztDAO;
import mx.com.baz.poolconnsql.dao.ExeSentenceOwnDAO;
import mx.com.baz.poolconnsql.model.request.PlKeyValueRequest;
import mx.com.baz.poolconnsql.model.response.PLKeyValueResponse;
import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
@Service
public class ExecuteService implements IExecuteService {
	/**
	 * Inyecta un {@link ExeSentenceAnvDAO} bean
	 */
	@Autowired
	private ExeSentenceAnvDAO daoAlnova;
	/**
	 * Inyecta un {@link ExeSentenceAnvDAO} bean
	 */
	@Autowired
	private ExeSentenceOwnDAO dao;
	/**
	 * Inyecta un {@link ExeSentenceAztDAO} bean
	 */
	@Autowired
	private ExeSentenceAztDAO daoAzteca;
	
	/**
	 * @return el resultado de la consulta de parametros
	 */
	@Override
	public Mono<Map<String, String>> getParameter() {
		return dao.getCatalogue();
	}
	@Override
	public Mono<ArrayList<PLKeyValueResponse>> getKeyValue(PlKeyValueRequest request ) {
		ArrayList<?> lista = dao.getKeyValue(request);
		Iterator<?> respuesta = lista.iterator();
		ArrayList<PLKeyValueResponse> responseList = new ArrayList<>();
		while(respuesta.hasNext()) {
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			String key_r = String.valueOf(response.get("KEY_R"));
			String value_r = response.get("VALUE_R");
			PLKeyValueResponse responseObject = new PLKeyValueResponse(key_r, value_r);
			responseList.add(responseObject);
		}
		return Mono.just(responseList);
	}
	/**
	 * @param query representa el script de consulta a ejecutar en base de datos
	 * @return El resultado de la consulta de la base de datos de alnova
	 */
	@Override
	public Mono<String> queryExeAlnovaSpei(String query) {
		return daoAlnova.getSpeiAlnova(query);
	}
	/**
	 * @param query representa el script de consulta a ejecutar en base de datos
	 * @return El resultado de la consulta de la base de datos de azteca
	 */
	@Override
	public Mono<String> queryExeAztecaSpei(String query) {
		return daoAzteca.getSpeiAzteca(query);
	}
	
}
