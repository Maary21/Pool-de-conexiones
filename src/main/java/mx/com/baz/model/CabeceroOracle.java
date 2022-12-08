package mx.com.baz.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CabeceroOracle {
	
	private String keyKafka;
	private Integer partitionKafka;
	
	@SerializedName("FCID_TRANSACCION")
	private String fcIdTransaccion;
	@SerializedName("FCIDFLUJO")
	private String fcIdFlujo;
	
	@SerializedName("FIFECHAOPERACION")
	private String fiFechaOperacion;
	@SerializedName("FCINSTITUCION")
	private String fcInstitucion;
	@SerializedName("FCRASTREO")
	private String fcRastreo;
	@SerializedName("FCSTATUS")
	private String fcStatus;
	@SerializedName("FITIPOPAGO")
	private String fiTipoPago;
	@SerializedName("FCNOM_ORD")
	private String fcNomOrd;
	@SerializedName("FCCUENTA_ORD")
	private String fcCuentaOrd;
	@SerializedName("FIFOLIO")
	private String fiFolio;
	@SerializedName("FCNOM_BEN")
	private String fcNomBen;
	@SerializedName("FCCUENTA_BEN")
	private String fcCuentaBen;
	@SerializedName("FIMONTO")
	private String fiMonto;
	@SerializedName("FCCONCEPTOPAGO")
	private String fcConceptoPago; 

	private String procesado;
	private Long idTransaccion;
	private Integer fkIdConciliacion;
	
}
