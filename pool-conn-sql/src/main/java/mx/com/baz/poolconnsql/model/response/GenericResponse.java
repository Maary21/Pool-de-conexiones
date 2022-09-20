/**
 * 
 */
package mx.com.baz.poolconnsql.model.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description La clase {@link GenericResponse} administra
 * distintos tipos de respuesta de salida
 * 
 * @param <T> objeto generico
 * 
 * @project pool-conn-sql
 * @author Andrés
 * @created xx ago. 2022
 * @version 1.0
 */
 @Getter
 @Setter
 @ToString
public class GenericResponse <T>{
	protected int code = 200;
	protected String message = "";
	protected List<String> errors = new ArrayList<>();
	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected T response;
	
	public GenericResponse() {}
	
	public GenericResponse(int code, String message) {
		this.code = code;
		this.message = message;
	}
	public GenericResponse(int code, String message, List<String> errors) {
		//new GenericResponse<Object>(code, message);
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
	public GenericResponse(int code, String message, T response) {
		//new GenericResponse<Object>(code, message);
		this.code = code;
		this.message = message;
		this.response = response;
	}
	public GenericResponse(int code, String message, List<String> errors, T response) {
		//new GenericResponse<Object>(code, message, errors);
		this.code = code;
		this.message = message;
		this.errors = errors;
		this.response = response;
	}
	
}
