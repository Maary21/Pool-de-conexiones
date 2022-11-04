package mx.com.baz.poolconnsql.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TctConexion {
	private Integer pkIdConexion;
	private String fcDescripcion;
	private String fcTipoBase;
	private String fcUsuario;
	private String fcContrasena;
	private String fcCadenaDeConexion;
	private String fcUsrModif;
	private Date fdFecModif;
}
