/**
 * 
 */
package mx.com.baz.poolconnsql.service;

import java.util.ArrayList;
import java.util.Map;

import mx.com.baz.poolconnsql.model.request.PlKeyValueRequest;
import mx.com.baz.poolconnsql.model.response.PLKeyValueResponse;
import mx.com.baz.poolconnsql.model.response.Parametria;
import mx.com.baz.poolconnsql.model.response.TcLecturaTrans;
import reactor.core.publisher.Mono;

/**
 * @description La interfaz {@link IExecuteService} se utiliza para definir las consultas
 * para solicitar lla informacion de la base de datos de ALNOVA
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
public interface IExecuteService {
	/**
	 * @return el resultado de la consulta de parametros
	 */
	public Mono<Map<String, String>> getParameter();

	public Mono<ArrayList<PLKeyValueResponse>> getKeyValue(PlKeyValueRequest request );
	
	public Mono<Parametria> obtenParametria();
	
	public Mono<ArrayList<TcLecturaTrans>> getTcLecturaTran(Integer idConciliacion);
	
	/**
	 * 
	 * @param query representa el script de consulta a ejecutar en base de datos
	 * @return el resultado de la consulta de la base de datos de alnova
	 */
	public Mono<String> queryExeAlnovaSpei(String query);
	/**
	 * 
	 * @param query representa el script de consulta a ejecutar en base de datos
	 * @return el resultado de la consulta de la base de datos de alnova
	 */
	public Mono<String> queryExeAztecaSpei(String query);
}
