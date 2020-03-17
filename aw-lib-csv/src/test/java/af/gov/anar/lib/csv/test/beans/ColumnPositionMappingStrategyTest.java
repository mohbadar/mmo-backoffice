package af.gov.anar.lib.csv.test.beans;

import af.gov.anar.lib.csv.bean.ColumnPositionMappingStrategy;
import af.gov.anar.lib.csv.bean.CsvToBean;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.*;


public class ColumnPositionMappingStrategyTest {
  private ColumnPositionMappingStrategy<MockBean> strat;

  @Before
  public void setUp() throws Exception {
    strat = new ColumnPositionMappingStrategy<MockBean>();
    strat.setType(MockBean.class);
  }

  @Test
  public void testParse() {
    String s = "" +
        "kyle,123456,emp123,1\n" +
        "jimmy,abcnum,cust09878,2";

    strat.setColumnMapping("name", "orderNumber", "id", "num");

    CsvToBean<MockBean> csv = new CsvToBean<MockBean>();
    List<MockBean> list = csv.parse(strat, new StringReader(s));
    assertNotNull(list);
    assertTrue(list.size() == 2);
    MockBean bean = list.get(0);
    assertEquals("kyle", bean.getName());
    assertEquals("123456", bean.getOrderNumber());
    assertEquals("emp123", bean.getId());
    assertEquals(1, bean.getNum());
  }

  @Test
  public void testParseSettingTypeInConstructor() {
    ColumnPositionMappingStrategy<MockBean> locstrat = 
        new ColumnPositionMappingStrategy<MockBean>(MockBean.class);
    
    String s = "" +
        "kyle,123456,emp123,1\n" +
        "jimmy,abcnum,cust09878,2";

    locstrat.setColumnMapping("name", "orderNumber", "id", "num");

    CsvToBean<MockBean> csv = new CsvToBean<MockBean>();
    List<MockBean> list = csv.parse(locstrat, new StringReader(s));
    assertNotNull(list);
    assertTrue(list.size() == 2);
    MockBean bean = list.get(0);
    assertEquals("kyle", bean.getName());
    assertEquals("123456", bean.getOrderNumber());
    assertEquals("emp123", bean.getId());
    assertEquals(1, bean.getNum());
  }

  @Test
  public void testParseWithTrailingSpaces() {
    String s = "" +
        "kyle  ,123456  ,emp123  ,  1   \n" +
        "jimmy,abcnum,cust09878,2   ";

    String[] columns = new String[]{"name", "orderNumber", "id", "num"};
    strat.setColumnMapping(columns);

    CsvToBean<MockBean> csv = new CsvToBean<MockBean>();
    List<MockBean> list = csv.parse(strat, new StringReader(s));
    assertNotNull(list);
    assertTrue(list.size() == 2);
    MockBean bean = list.get(0);
    assertEquals("kyle  ", bean.getName());
    assertEquals("123456  ", bean.getOrderNumber());
    assertEquals("emp123  ", bean.getId());
    assertEquals(1, bean.getNum());
  }

  @Test
  public void testGetColumnMapping() {
    String[] columnMapping = strat.getColumnMapping();
    assertNotNull(columnMapping);
    assertEquals(0, columnMapping.length);

    String[] columns = new String[]{"name", "orderNumber", "id"};
    strat.setColumnMapping(columns);

    columnMapping = strat.getColumnMapping();
    assertNotNull(columnMapping);
    assertEquals(3, columnMapping.length);
    assertArrayEquals(columns, columnMapping);

  }

  @Test
  public void testGetColumnNames() {

    strat.setColumnMapping("name", null, "id");

    assertEquals("name", strat.getColumnName(0));
    assertEquals(null, strat.getColumnName(1));
    assertEquals("id", strat.getColumnName(2));
    assertEquals(null, strat.getColumnName(3));
  }

  @Test
  public void testGetColumnNamesArray() {

    strat.setColumnMapping("name", null, "id");
    String[] mapping = strat.getColumnMapping();

    assertEquals(3, mapping.length);
    assertEquals("name", mapping[0]);
    assertEquals(null, mapping[1]);
    assertEquals("id", mapping[2]);
  }

  @Test
  public void getColumnNamesWhenNullArray() {
    strat.setColumnMapping((String[]) null);

    assertEquals(null, strat.getColumnName(0));
    assertEquals(null, strat.getColumnName(1));
    assertArrayEquals(new String[0], strat.getColumnMapping());
  }

  @Test
  public void getColumnNamesWhenNullColumnName() {
    String[] columns = {null};
    strat.setColumnMapping(columns);

    assertEquals(null, strat.getColumnName(0));
    assertEquals(null, strat.getColumnName(1));
    assertArrayEquals(columns, strat.getColumnMapping());
  }

  @Test
  public void getColumnNamesWhenEmptyMapping() {
    strat.setColumnMapping();

    assertEquals(null, strat.getColumnName(0));
    assertArrayEquals(new String[0], strat.getColumnMapping());
  }

}
