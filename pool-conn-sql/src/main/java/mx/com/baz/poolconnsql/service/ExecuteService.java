/**
 * 
 */
package mx.com.baz.poolconnsql.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mx.com.baz.poolconnsql.dao.ExeSentenceAnvDAO;
import mx.com.baz.poolconnsql.dao.ExeSentenceAztDAO;
import mx.com.baz.poolconnsql.dao.ExeSentenceOwnDAO;
import mx.com.baz.poolconnsql.model.response.GenericResponse;
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
