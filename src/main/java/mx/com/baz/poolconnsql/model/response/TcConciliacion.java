package mx.com.baz.poolconnsql.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TcConciliacion {
	
	private Integer pkIdConciliacion;
	private String fcDescripcion;
	private Integer fiEstatus;
	private Integer fkTipoConciliacion;
	private String fcResponsable;
	private String fcResultMod;
	private Date fdFecUltMod;
	private String fcRequerimiento;
	private String fcUsrModif;
	private Date fdFecModif;
	private String fcTipoCon;
	private String fcDateMask;
}
