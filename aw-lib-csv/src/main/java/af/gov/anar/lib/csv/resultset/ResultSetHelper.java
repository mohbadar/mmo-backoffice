package af.gov.anar.lib.csv.resultset;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface ResultSetHelper {
  public List<String> getColumnNames(ResultSet rs) throws SQLException;

  public List<String> getColumnValues(ResultSet rs) throws SQLException, IOException;

  public List<String> getColumnValues(ResultSet rs, boolean trim) throws SQLException, IOException;

  public List<String> getColumnValues(ResultSet rs, boolean trim, String dateFormatString, String timeFormatString)
      throws SQLException, IOException;
}
