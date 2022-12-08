/**
 * 
 */
package mx.com.baz.poolconnsql.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import mx.com.baz.model.Cabecero;
import mx.com.baz.model.CabeceroKey;
import mx.com.baz.model.CabeceroOracle;
import mx.com.baz.poolconnsql.dao.ExeSentenceAnvDAO;
import mx.com.baz.poolconnsql.dao.ExeSentenceAztDAO;
import mx.com.baz.poolconnsql.dao.ExeSentenceOwnDAO;
import mx.com.baz.poolconnsql.model.request.PlKeyValueRequest;
import mx.com.baz.poolconnsql.model.response.PLKeyValueResponse;
import mx.com.baz.poolconnsql.model.response.Parametria;
import mx.com.baz.poolconnsql.model.response.TcConciliacion;
import mx.com.baz.poolconnsql.model.response.TcLecturaTrans;
import mx.com.baz.poolconnsql.model.response.TcOrigenes;
import mx.com.baz.poolconnsql.model.response.TcParamConc;
import mx.com.baz.poolconnsql.model.response.TctConexion;
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
@Slf4j
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
			@SuppressWarnings("unchecked")
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			PLKeyValueResponse responseObject = new PLKeyValueResponse();
			responseObject.setKEY_R(response.get("KEY_R"));
			responseObject.setVALUE_R(response.get("VALUE_R"));
			responseList.add(responseObject);
		}
		return Mono.just(responseList);
	}
	
	private ArrayList<TcParamConc> getTcParamConc(){
		ArrayList<?> lista = dao.getDataFromFunction("FN_TCPARAMCONC");
		Iterator<?> respuesta = lista.iterator();
		
		ArrayList<TcParamConc> responseList = new ArrayList<>();
		while(respuesta.hasNext()) {
			@SuppressWarnings("unchecked")
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			TcParamConc tcParamConc = new TcParamConc();
			tcParamConc.setPkIdCruce(Integer.valueOf(String.valueOf(response.get("PKIDCRUCE"))));
			tcParamConc.setFcSelect(response.get("FCSELECT"));
			tcParamConc.setFcWhere(response.get("FCWHERE"));
			tcParamConc.setFcGroupBy(response.get("FCGRUPBY"));
			tcParamConc.setFcAddColumns(response.get("CDADDCOLUMS"));
			tcParamConc.setFcJoin(response.get("FCJOIN"));
			tcParamConc.setFkIdOrigen(Integer.valueOf(String.valueOf(response.get("FKIDORIGEN"))));
			tcParamConc.setFcIdFase(response.get("FCIDFASE"));
			tcParamConc.setFcDestino(response.get("FCDESTINO"));
			tcParamConc.setFcEstatus(response.get("FCESTATUS"));
			tcParamConc.setFkIdConciliacion(Integer.valueOf(String.valueOf(response.get("FKIDCONCILIACION"))));
			tcParamConc.setFcUsrModif(response.get("FCUSRMODIF"));
			tcParamConc.setFcLlaveMaestra(response.get("FCLLAVEMAESTRA"));
			tcParamConc.setFcOpLlave(response.get("FCOPLLAVE"));
			tcParamConc.setFcNodoKafka(response.get("FCNODOKAFKA"));
			responseList.add(tcParamConc);
		}
		return responseList;
	}
	
	
	
	private ArrayList<TcConciliacion> getTcConciliacion(){
		ArrayList<?> lista = dao.getDataFromFunction("FN_TCCONCILIACION");
		Iterator<?> respuesta = lista.iterator();
		
		ArrayList<TcConciliacion> responseList = new ArrayList<>();
		while(respuesta.hasNext()) {
			@SuppressWarnings("unchecked")
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			TcConciliacion dto = new TcConciliacion();
			dto.setPkIdConciliacion(Integer.valueOf(String.valueOf(response.get("PKIDCONCILIACION"))));
			dto.setFcDescripcion(response.get("FCDESCRIPCION") );
			dto.setFiEstatus(Integer.valueOf(String.valueOf(response.get("FIESTATUS"))));
			dto.setFkTipoConciliacion(Integer.valueOf(String.valueOf(response.get("FKTIPOCONCILIACION"))));
			dto.setFcResponsable(response.get("FCRESPONSABLE"));
			dto.setFcResultMod(response.get("FCRESULTMOD"));
			dto.setFcRequerimiento(response.get("FCREQUERIMIENTO"));
			dto.setFcTipoCon(response.get("FCTIPOCON"));
			dto.setFcDateMask(response.get("FCDATEMASK"));
			responseList.add(dto);
		}
		return responseList;
	}
	
	
	private ArrayList<TctConexion> getTcTconexion(){
		ArrayList<?> lista = dao.getDataFromFunction("FN_TCTCONEXION");
		Iterator<?> respuesta = lista.iterator();
		
		ArrayList<TctConexion> responseList = new ArrayList<>();
		while(respuesta.hasNext()) {
			@SuppressWarnings("unchecked")
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			TctConexion dto = new TctConexion();
			dto.setPkIdConexion(Integer.valueOf(String.valueOf(response.get("PKIDCONEXION"))));
			dto.setFcDescripcion(response.get("FCDESCRIPCION"));
			dto.setFcTipoBase(response.get("FCTIPOBASE"));
			dto.setFcUsuario(response.get("FCUSUARIO"));
			dto.setFcContrasena(response.get("FCCONTRASENA"));
			dto.setFcCadenaDeConexion(response.get("FCCADENADECONEXION"));
			responseList.add(dto);
		}
		return responseList;
	}
	
	
	
	private ArrayList<TcOrigenes> getTcOrigenes(){
		ArrayList<?> lista = dao.getDataFromFunction("FN_TCORIGENES");
		Iterator<?> respuesta = lista.iterator();
		
		ArrayList<TcOrigenes> responseList = new ArrayList<>();
		while(respuesta.hasNext()) {
			@SuppressWarnings("unchecked")
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			TcOrigenes dto = new TcOrigenes();
			dto.setPkIdOrigen(Integer.valueOf(String.valueOf(response.get("PKIDORIGEN"))));
			dto.setFkIdConexion(Integer.valueOf(String.valueOf(response.get("FKIDCONEXION"))));
			dto.setFcNombre(response.get("FCNOMBRE"));
			dto.setFcUsrModif(response.get("FCUSRMODIF"));
			responseList.add(dto);
		}
		return responseList;
	}
	
	
	
	public List<Cabecero> getTcLecturaTran(Integer idConciliacion){
		ArrayList<?> lista = dao.getLecturaTrans(idConciliacion);
		log.info("Se obtienen de getTcLecturaTran: "+lista.size());
		Iterator<?> respuesta = lista.iterator();
		
		List<Cabecero> listaCabecero = new ArrayList<>();
		Gson gson = new Gson(); 
		log.info("Se prepara la respuesta");
		while(respuesta.hasNext()) {
			@SuppressWarnings("unchecked")
			LinkedCaseInsensitiveMap<String> response = (LinkedCaseInsensitiveMap<String>) respuesta.next();
			
			CabeceroOracle cabeceroOracle = gson.fromJson(response.get("FCPARSEO"), CabeceroOracle.class);
			Cabecero cabecero = convierteCabecero(cabeceroOracle);
			cabecero.setIdTransaccion(Long.valueOf(String.valueOf(response.get("PKIDTRANSACCION"))));
			cabecero.setProcesado("N");
			listaCabecero.add(cabecero);
			
//			TcLecturaTrans dto = new TcLecturaTrans();
//			dto.setPkIdTransaccion(Integer.valueOf(String.valueOf(response.get("PKIDTRANSACCION"))));
//			dto.setFcParseo(response.get("FCPARSEO"));
//			dto.setFcResultado(response.get("FCRESULTADO"));
//			dto.setFcNombre(response.get("FCNOMBRE"));
//			dto.setFiEstatus(response.get("FIESTATUS") != null ? Integer.valueOf(String.valueOf(response.get("FIESTATUS"))):null);
//			dto.setFkIdConciliacion(Integer.valueOf(String.valueOf(response.get("FKIDCONCILIACION"))));
//			dto.setFcFase(response.get("FCFASE"));
//			responseList.add(dto);
		}
		log.info("Datos preparados");
		return listaCabecero;
	}
	
	
	/**
	 * @return Obtiene los datos de la parametria.
	 */
	public Mono<Parametria> obtenParametria(){
		Parametria parametria = new Parametria();
		parametria.setOrigenes(getTcOrigenes());
		parametria.setConexion(getTcTconexion());
		parametria.setConciliacion(getTcConciliacion());
		parametria.setParametrosConciliacion(getTcParamConc());
		return Mono.just(parametria);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public Mono<Boolean> saveLecturaTran(ArrayList<TcLecturaTrans> in) {
		Boolean resultado = false; 
		try {
			resultado = dao.saveLecturaTranBatch(in);
		} catch (Exception e) {
			log.error("saveLecturaTran",e);
			resultado = false;
		}
		return Mono.just(resultado);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Mono<Boolean> updateLecturaTran(ArrayList<TcLecturaTrans> in) {
		Boolean resultado = false; 
		try {
			dao.updateLecturaTranBatch(in);
			resultado = true;
		} catch (Exception e) {
			log.error("saveLecturaTran",e);
			resultado = false;
		}
		return Mono.just(resultado);
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
	
	
	private Cabecero convierteCabecero(CabeceroOracle in) {
		CabeceroKey key = new CabeceroKey();
		Cabecero cabecero = new Cabecero();
		key.setKeyKafka(in.getKeyKafka());
		key.setPartitionKafka(in.getPartitionKafka());
		
		cabecero.setId(key);
		
		cabecero.setFcIdTransaccion(in.getFcIdTransaccion());
		cabecero.setFcIdFlujo(in.getFcIdFlujo());
		
		cabecero.setFcConceptoPago(in.getFcConceptoPago());
		cabecero.setFcCuentaBen(in.getFcCuentaBen());
		cabecero.setFcCuentaOrd(in.getFcCuentaOrd());
		cabecero.setFcInstitucion(in.getFcInstitucion());
		cabecero.setFcNomBen(in.getFcNomBen());
		cabecero.setFcNomOrd(in.getFcNomOrd());
		cabecero.setFcRastreo(in.getFcRastreo());
		cabecero.setFcStatus(in.getFcStatus());
		cabecero.setFiFechaOperacion(in.getFiFechaOperacion());
		cabecero.setFiFolio(in.getFiFolio());
		cabecero.setFiMonto(in.getFiMonto());
		cabecero.setFiTipoPago(in.getFiTipoPago());
		
		cabecero.setIdTransaccion(in.getIdTransaccion());
		cabecero.setFkIdConciliacion(in.getFkIdConciliacion());
		return cabecero;
	}
	
}
