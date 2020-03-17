package af.gov.anar.lib.csv.bean;


import af.gov.anar.lib.csv.reader.CsvReader;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;

public interface MappingStrategy<T> {

  /**
   * Implementation will have to return a property descriptor from a bean based on the current column.
   * @param col the column to find the description for
   * @throws IntrospectionException
   * @return the related PropertyDescriptor
   */
  public abstract PropertyDescriptor findDescriptor(int col) throws IntrospectionException;

  public abstract T createBean() throws InstantiationException, IllegalAccessException;

  /**
   * Implementation of this method can grab the header line before parsing begins to use to map columns
   * to bean properties.
   * @param reader the CSVReader to use for header parsing
   * @throws IOException if parsing fails
   */
  public void captureHeader(CsvReader reader) throws IOException;
}