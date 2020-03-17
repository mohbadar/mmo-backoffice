package af.gov.anar.lib.csv.test.beans;



import af.gov.anar.lib.csv.bean.CsvToBean;
import af.gov.anar.lib.csv.bean.HeaderColumnNameTranslateMappingStrategy;

import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class HeaderColumnNameTranslateMappingStrategyTest {

  @Test
  public void testParse() {
    String s = "n,o,foo\n" +
        "kyle,123456,emp123\n" +
        "jimmy,abcnum,cust09878";
    HeaderColumnNameTranslateMappingStrategy<MockBean> strat = new HeaderColumnNameTranslateMappingStrategy<MockBean>();
    strat.setType(MockBean.class);
    Map<String, String> map = new HashMap<String, String>();
    map.put("n", "name");
    map.put("o", "orderNumber");
    map.put("foo", "id");
    strat.setColumnMapping(map);

    CsvToBean<MockBean> csv = new CsvToBean<MockBean>();
    List<MockBean> list = csv.parse(strat, new StringReader(s));
    assertNotNull(list);
    assertTrue(list.size() == 2);
    MockBean bean = list.get(0);
    assertEquals("kyle", bean.getName());
    assertEquals("123456", bean.getOrderNumber());
    assertEquals("emp123", bean.getId());
  }

  @Test
  public void getColumnNameReturnsNullIfColumnNumberIsTooLarge() {
    String s = "n,o,foo\n" +
        "kyle,123456,emp123\n" +
        "jimmy,abcnum,cust09878";
    HeaderColumnNameTranslateMappingStrategy<MockBean> strat = new HeaderColumnNameTranslateMappingStrategy<MockBean>();
    strat.setType(MockBean.class);
    Map<String, String> map = new HashMap<String, String>();
    map.put("n", "name");
    map.put("o", "orderNumber");
    map.put("foo", "id");
    strat.setColumnMapping(map);

    CsvToBean<MockBean> csv = new CsvToBean<MockBean>();
    List<MockBean> list = csv.parse(strat, new StringReader(s));

    assertTrue(list.size() > 0);
    assertEquals("name", strat.getColumnName(0));
    assertEquals("orderNumber", strat.getColumnName(1));
    assertEquals("id", strat.getColumnName(2));
    assertNull(strat.getColumnName(3));
  }

  @Test
  public void columnNameMappingShouldBeCaseInsensitive() {
    String s = "n,o,Foo\n" +
        "kyle,123456,emp123\n" +
        "jimmy,abcnum,cust09878";
    HeaderColumnNameTranslateMappingStrategy<MockBean> strat = 
        new HeaderColumnNameTranslateMappingStrategy<MockBean>(MockBean.class);

    Map<String, String> map = new HashMap<String, String>();
    map.put("n", "name");
    map.put("o", "orderNumber");
    map.put("foo", "id");
    strat.setColumnMapping(map);
    assertNotNull(strat.getColumnMapping());

    CsvToBean<MockBean> csv = new CsvToBean<MockBean>();
    List<MockBean> list = csv.parse(strat, new StringReader(s));

    assertTrue(list.size() > 0);
    assertEquals("name", strat.getColumnName(0));
    assertEquals("orderNumber", strat.getColumnName(1));
    assertEquals("id", strat.getColumnName(2));
    assertNull(strat.getColumnName(3));
  }

}
