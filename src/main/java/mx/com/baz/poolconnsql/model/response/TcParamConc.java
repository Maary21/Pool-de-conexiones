package mx.com.baz.poolconnsql.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TcParamConc {
	
	private Integer PkIdCruce;
	private String fcSelect;
	private String fcWhere;
	private String fcGroupBy;
	private String fcAddColumns;
	private String fcJoin;
	private Integer fkIdOrigen;
	private String fcIdFase;
	private String fcDestino;
	private String fcEstatus;
	private Integer fkIdConciliacion;
	private String fcUsrModif;
	private Date fdFecModif;
	private String fcLlaveMaestra;
	private String fcOpLlave;
	private String fcNodoKafka;
	
}
