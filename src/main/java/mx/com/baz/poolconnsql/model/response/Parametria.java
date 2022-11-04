package mx.com.baz.poolconnsql.model.response;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parametria {
	private ArrayList<TcConciliacion> conciliacion;
	private ArrayList<TcParamConc> parametrosConciliacion;
	private ArrayList<TcOrigenes> origenes;
	private ArrayList<TctConexion> conexion;
}
