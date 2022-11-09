package mx.com.baz.poolconnsql.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TcLecturaTrans {
	private Integer pkIdTransaccion;
	private String fcParseo;
	private String fcResultado;
	private String fcNombre;
	private Integer fiEstatus;
	private Integer fkIdConciliacion;
	private String fcFase;
	private String fcUsrModif;
	private Date fdFecModif;
}
