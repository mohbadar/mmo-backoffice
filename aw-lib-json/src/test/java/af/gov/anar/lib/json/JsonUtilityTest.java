package af.gov.anar.lib.json;

import af.gov.anar.lang.infrastructure.exception.common.IOException;
import af.gov.anar.lib.json.exception.JsonMappingException;
import af.gov.anar.lib.json.exception.JsonParseException;
import af.gov.anar.lib.json.exception.JsonProcessingException;
import af.gov.anar.lib.json.date.Car;
import af.gov.anar.lib.json.date.JsonUtilTestConstants;
import af.gov.anar.lib.json.date.ParentCar2;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Unit test for JsonUtility class

 */

public class JsonUtilityTest {

	Car car = new Car("Black", "BMW");
	Car car2;
	ParentCar2 parentCar2;
/*	@Test
	public void testJavaObjectToJsonFile()
			throws JsonGenerationException, JsonMappingException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sample.json").getFile());
		assertThat(JsonUtils.javaObjectToJsonFile(car, file.getAbsolutePath()), is(true));
	}*/

	@Test
	public void testJavaObjectToJsonString() throws JsonProcessingException {
		String jsonString = JsonUtility.javaObjectToJsonString(car);

		jsonString = jsonString.replaceAll("\r", "");// \r and \n
		assertThat(jsonString.contains("Black"), is(true));

	}

	@Test
	public void testJsonStringToJavaObject() throws JsonParseException, JsonMappingException, IOException {
		Car car2 = (Car) JsonUtility.jsonStringToJavaObject(Car.class, JsonUtilTestConstants.json);
		assertNotNull(car2);
		assertThat(car2.getColor(), is("Black"));
		assertThat(car2.getType(), is("BMW"));

	}

/*	@Test
	public void testJsonFileToJavaObject() throws JsonParseException, JsonMappingException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sample2.json").getFile());
		Car car2 = (Car) JsonUtils.jsonFileToJavaObject(Car.class, file.getAbsolutePath());
		assertNotNull(car2);
		assertThat(car2.getColor(), is("Blue"));
		assertThat(car2.getType(), is("Audi"));
	}*/

	@Test
	public void testJsonToJacksonJsonNode() throws IOException {

		assertThat(JsonUtility.jsonToJacksonJson(JsonUtilTestConstants.jsonString, "type"), is("FIAT"));
	}

	@Test
	public void testJsonStringToJavaList() throws JsonParseException, JsonMappingException, IOException {
		List<Object> listElements = JsonUtility.jsonStringToJavaList(JsonUtilTestConstants.jsonCarArray);
		assertThat(listElements.toString(), is("[{color=Black, type=BMW}, {color=Red, type=FIAT}]"));
	}

	@Test
	public void testJsonStringToJavaMap() throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> mapElements = JsonUtility.jsonStringToJavaMap(JsonUtilTestConstants.jsonString);
		assertThat(mapElements.toString(), is("{color=Black, type=FIAT}"));
	}

	/*@Test(expected = IOException.class)
	public void testJavaObjectToJsonFileWithIOException()
			throws JsonGenerationException, JsonMappingException, IOException {

		JsonUtils.javaObjectToJsonFile("", "C:/InvalidLocation");
	}*/

	@Test(expected = JsonParseException.class)
	public void testjsonStringtoJavaObjectWithParseException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtility.jsonStringToJavaObject(Car.class, JsonUtilTestConstants.jsonParserError);
	}

	@Test(expected = JsonMappingException.class)
	public void testjsonStringtoJavaObjectWithMappingException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtility.jsonStringToJavaObject(Car.class, JsonUtilTestConstants.jsonCarArray2);
	}

	/*@Test(expected = JsonParseException.class)
	public void testjsonFiletoJavaObjectWithParseException()
			throws JsonParseException, JsonMappingException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sampleParse.json").getFile());

		JsonUtils.jsonFileToJavaObject(SampleClass.class, file.getAbsolutePath());
	}*/

	/*@Test(expected = JsonMappingException.class)
	public void testjsonFiletoJavaObjectWithMappingException()
			throws JsonParseException, JsonMappingException, IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("samplex.json").getFile());

		JsonUtils.jsonFileToJavaObject(ParentCar2.class, file.getAbsolutePath());
	}*/

	/*@Test(expected = IOException.class)
	public void testjsonFiletoJavaObjectWithIOException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtils.jsonFileToJavaObject(ParentCar2.class, "C:/InvalidLocation");
	}*/

	@Test(expected = IOException.class)
	public void testJsonToJacksonJsonWithIOException() throws IOException {
		JsonUtility.jsonToJacksonJson(JsonUtilTestConstants.jsonCarArray2, "");
	}

	@Test(expected = JsonParseException.class)
	public void testjsonStringToJavaListWithParseException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtility.jsonStringToJavaList(JsonUtilTestConstants.jsonParserError2);
	}

	@Test(expected = JsonMappingException.class)
	public void testjsonStringToJavaListWithMappingException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtility.jsonStringToJavaList(JsonUtilTestConstants.jsonCarArray2);
	}

	@Test(expected = JsonParseException.class)
	public void testjsonStringToJavaMapWithParseException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtility.jsonStringToJavaMap(JsonUtilTestConstants.jsonParserError);
	}

	@Test(expected = JsonMappingException.class)
	public void testjsonStringToJavaMapWithMappingException()
			throws JsonParseException, JsonMappingException, IOException {

		JsonUtility.jsonStringToJavaMap(JsonUtilTestConstants.jsonCarArray2);
	}

}
