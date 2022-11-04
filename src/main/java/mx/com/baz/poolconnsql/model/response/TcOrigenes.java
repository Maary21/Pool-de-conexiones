package mx.com.baz.poolconnsql.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TcOrigenes {
	
	private Integer pkIdOrigen;
	private Integer fkIdConexion;
	private String fcNombre;
	private String fcUsrModif;
	private Date fdFecModif;

}
