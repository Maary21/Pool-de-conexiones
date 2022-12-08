package mx.com.baz.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CabeceroKey implements Serializable {
	private static final long serialVersionUID = -5211229724979924398L;
	private String keyKafka;
	private Integer partitionKafka;
}
