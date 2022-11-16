/**
 * 
 */
package mx.com.baz.poolconnsql.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.com.baz.poolconnsql.model.request.PlKeyValueRequest;
import mx.com.baz.poolconnsql.model.request.TcLecturaTrans;
import mx.com.baz.poolconnsql.model.response.GenericResponse;
import mx.com.baz.poolconnsql.model.response.PLKeyValueResponse;
import mx.com.baz.poolconnsql.model.response.Parametria;
import mx.com.baz.poolconnsql.service.IExecuteService;

import static mx.com.baz.poolconnsql.utils.Constant.SEPARADOR;
import reactor.core.publisher.Mono;

/**
 * @description
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created 22 ago. 2022
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/query")
public class queryExecuteController {

	/**
	 * La interfaz {@link IExecuteService} define el método para efectuar el pago desde la cuenta del
	 * clientelas consultas de spei de la base de alnova
	 */
	@Autowired
	private IExecuteService service;

	/**
	 * Método efectúa la consulta de los SPEI ALNOVA genéricos.
	 * 
	 * @param query contiene la sentencia para la consulta SPEI ALNOVA
	 * @return el resultado de la consulta de los SPEI ALNOVA genéricos
	 */
	@GetMapping("/catalogue/parameter")
	public Mono<ResponseEntity<GenericResponse<Map<String, String>>>> getParameter() {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS DE PARAMETROS A NUESTRA BASE DE DATOS");
		log.info(SEPARADOR);
		return service.getParameter()
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS ALNOVA");
					log.info(SEPARADOR);
				});
	}

	/**
	 * Método efectúa la consulta de los SPEI ALNOVA genéricos.
	 * 
	 * @param query contiene la sentencia para la consulta SPEI ALNOVA
	 * @return el resultado de la consulta de los SPEI ALNOVA genéricos
	 */
	@PostMapping(path="/own/keyvalue", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GenericResponse<ArrayList<PLKeyValueResponse>>>> getParameterKeyValue(@RequestBody PlKeyValueRequest request ) {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS DE PARAMETROS A NUESTRA BASE DE DATOS");
		log.info(SEPARADOR);
		return service.getKeyValue(request)
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS ALNOVA");
					log.info(SEPARADOR);
				});
	}


	@GetMapping(path="/own/parametria")
	public Mono<ResponseEntity<GenericResponse<Parametria>>> getTcParamConc() {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS DE PARAMETROS A NUESTRA BASE DE DATOS");
		log.info(SEPARADOR);
		return service.obtenParametria()
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS ALNOVA");
					log.info(SEPARADOR);
				});
	}

	
	
	@PostMapping(path="/own/tclectura",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GenericResponse<ArrayList<mx.com.baz.poolconnsql.model.response.TcLecturaTrans>>>> getTcLecturaTran(@RequestBody TcLecturaTrans request) {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS DE PARAMETROS A NUESTRA BASE DE DATOS");
		log.info(SEPARADOR);
		return service.getTcLecturaTran(request.getIdConciliacion())
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS ALNOVA");
					log.info(SEPARADOR);
				});
	}
	
	
	
	@PostMapping(path="/own/save/tclectura",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GenericResponse<Boolean>>> saveTcLecturaTran(@RequestBody ArrayList<mx.com.baz.poolconnsql.model.response.TcLecturaTrans> request) {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT saveTcLecturaTran DE PARAMETROS A NUESTRA BASE DE DATOS");
		log.info(SEPARADOR);
		return service.saveLecturaTran(request)
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE saveTcLecturaTran");
					log.info(SEPARADOR);
				});
	}
	
	
	@PostMapping(path="/own/update/tclectura",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<ResponseEntity<GenericResponse<Boolean>>> updateTcLecturaTran(@RequestBody ArrayList<mx.com.baz.poolconnsql.model.response.TcLecturaTrans> request) {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT updateTcLecturaTran DE PARAMETROS A NUESTRA BASE DE DATOS");
		log.info(SEPARADOR);
		return service.updateLecturaTran(request)
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE updateTcLecturaTran");
					log.info(SEPARADOR);
				});
	}
	
	/**
	 * Método efectúa la consulta de los SPEI ALNOVA genéricos.
	 * 
	 * @param query contiene la sentencia para la consulta SPEI ALNOVA
	 * @return el resultado de la consulta de los SPEI ALNOVA genéricos
	 */
	@GetMapping("/alnova/spei")
	public Mono<ResponseEntity<GenericResponse<String>>> executeAlnovaSql(@RequestParam (value = "query", required = true) String query) {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS ALNOVA");
		log.info(query);
		log.info(SEPARADOR);
		return service.queryExeAlnovaSpei(query)
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS ALNOVA");
					log.info(SEPARADOR);
				});
	}
	/**
	 * Método efectúa la consulta de los SPEI Azteca genéricos.
	 * 
	 * @param query contiene la sentencia para la consulta SPEI Azteca
	 * @return el resultado de la consulta de los SPEI Azteca genéricos
	 */
	@GetMapping("/azteca/spei")
	public Mono<ResponseEntity<GenericResponse<String>>> executeAztecaSql(@RequestParam (value = "query", required = true) String query) {
		log.info(SEPARADOR);
		log.info("SE INICIALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS AZTECA");
		log.info(query);
		log.info(SEPARADOR);
		return service.queryExeAztecaSpei(query)
				/**
				 * Cuando obtenemos la respuesta se contruye la respuesta
				 */
				.map(q -> new ResponseEntity<>(new GenericResponse<>(200, "consulta correcta", q), HttpStatus.OK))
				/**
				 * Cuenta se completa la accion u ocurre un error
				 * se recibe la señal y se pintan los logs
				 */
				.doFinally(signalType -> {
					log.info(SEPARADOR);
					log.info("SE FINALIZA EL ENDPOINT DE CONSULTAS A BASE DE DATOS AZTECA");
					log.info(SEPARADOR);
				});
	}
}
