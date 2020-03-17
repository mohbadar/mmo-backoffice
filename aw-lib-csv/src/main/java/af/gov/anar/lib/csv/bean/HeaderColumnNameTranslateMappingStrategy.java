package af.gov.anar.lib.csv.bean;
import java.util.HashMap;
import java.util.Map;


public class HeaderColumnNameTranslateMappingStrategy<T> extends HeaderColumnNameMappingStrategy<T> {
  
  private Map<String, String> columnMapping = new HashMap<String, String>();
  
  public HeaderColumnNameTranslateMappingStrategy() {}
  
  /**
   * Use this to set the type immediately rather than via the <code>setType</code> method
   * 
   * @param type
   */
  public HeaderColumnNameTranslateMappingStrategy(Class<T> type) {
    super(type);
  }
  
  public String getColumnName(int col) {
    return col < header.size() ? columnMapping.get(header.get(col).toUpperCase()) : null;
  }
  
  public Map<String, String> getColumnMapping() {
    return columnMapping;
  }
  
  public void setColumnMapping(Map<String, String> columnMapping) {
    for (Map.Entry<String,String> entry: columnMapping.entrySet()) {
      this.columnMapping.put(entry.getKey().toUpperCase(), entry.getValue());
    }
  }
}
