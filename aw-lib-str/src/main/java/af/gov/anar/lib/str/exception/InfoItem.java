package af.gov.anar.lib.str.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This class is the entity class for the BaseUncheckedException and
 * BaseCheckedException class.
 */
@AllArgsConstructor
@NoArgsConstructor
class InfoItem implements Serializable {

	private static final long serialVersionUID = -779695043380592601L;

	@Getter
	@Setter
	public String errorCode = null;

	@Getter
	@Setter
	public String errorText = null;

}
