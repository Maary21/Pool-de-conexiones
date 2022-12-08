package mx.com.baz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cabecero{
	
	private CabeceroKey id;
	
	private String fcIdTransaccion;
	private String fcIdFlujo;
	
	private String fiFechaOperacion;
	private String fcInstitucion;
	private String fcRastreo;
	private String fcStatus;
	private String fiTipoPago;
	private String fcNomOrd;
	private String fcCuentaOrd;
	private String fiFolio;
	private String fcNomBen;
	private String fcCuentaBen;
	private String fiMonto;
	private String fcConceptoPago; 
	private String procesado;
	
	private Long idTransaccion;
	private Integer fkIdConciliacion;
	
	
}
