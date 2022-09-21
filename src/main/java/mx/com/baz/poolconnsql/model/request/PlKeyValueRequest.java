package mx.com.baz.poolconnsql.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlKeyValueRequest {
	
	private Integer tipoCoinciliacion;
	private Integer idAplicacion;
	private Integer tipoDatos;

}
