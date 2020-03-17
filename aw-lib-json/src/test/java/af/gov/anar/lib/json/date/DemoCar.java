package af.gov.anar.lib.json.date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;


@JsonAutoDetect(fieldVisibility = Visibility.NON_PRIVATE)
public class DemoCar {
	public int age;
	public long no;

}
