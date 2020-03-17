package af.gov.anar.lib.csv.bean;


import af.gov.anar.lib.csv.reader.CsvReader;

import java.io.IOException;

public class ColumnPositionMappingStrategy<T> extends HeaderColumnNameMappingStrategy<T> {
  private String[] columnMapping = new String[]{};

  public ColumnPositionMappingStrategy() {}
  
  /**
   * Use this to set the type immediately rather than via the <code>setType</code> method
   * 
   * @param type
   */
  public ColumnPositionMappingStrategy(Class<T> type) {
    super(type);
  }
  
  public void captureHeader(CsvReader reader) throws IOException {
    //do nothing, first line is not header
  }

  public String getColumnName(int col) {
    return col < columnMapping.length ? columnMapping[col] : null;
  }

  public String[] getColumnMapping() {
    return columnMapping.clone();
  }

  public void setColumnMapping(String... columnMapping) {
    this.columnMapping = columnMapping != null ? columnMapping.clone() : new String[]{};
  }
}
