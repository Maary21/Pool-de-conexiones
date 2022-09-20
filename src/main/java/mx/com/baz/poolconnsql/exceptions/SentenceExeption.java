/**
 * 
 */
package mx.com.baz.poolconnsql.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @description
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class SentenceExeption extends Exception {
	private int codigoE;
	private String mensaje;
	private String fallo;
}
