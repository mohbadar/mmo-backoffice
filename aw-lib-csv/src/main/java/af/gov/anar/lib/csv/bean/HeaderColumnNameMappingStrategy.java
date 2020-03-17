package af.gov.anar.lib.csv.bean;


import af.gov.anar.lib.csv.reader.CsvReader;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class HeaderColumnNameMappingStrategy<T> implements MappingStrategy<T> {
  public List<String> header;
  public Map<String, PropertyDescriptor> descriptorMap = null;
  public Class<T> type;

  public HeaderColumnNameMappingStrategy() {}

  /**
   * Use this to set the type immediately rather than via the <code>setType</code> method
   * 
   * @param type
   */
  public HeaderColumnNameMappingStrategy(Class<T> type) {
    setType(type);
  }

  public void captureHeader(CsvReader reader) throws IOException {
    header = reader.readNext();
  }

  public PropertyDescriptor findDescriptor(int col) throws IntrospectionException {
    String columnName = getColumnName(col);
    return (null != columnName && columnName.trim().length() > 0) ? findDescriptor(columnName) : null;
  }

  public String getColumnName(int col) {
    return (null != header && col < header.size()) ? header.get(col) : null;
  }

  public PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
    if (null == descriptorMap) descriptorMap = loadDescriptorMap(getType()); //lazy load descriptors
    return descriptorMap.get(name.toUpperCase().trim());
  }

  public boolean matches(String name, PropertyDescriptor desc) {
    return desc.getName().equals(name.trim());
  }

  public Map<String, PropertyDescriptor> loadDescriptorMap(Class<T> cls) throws IntrospectionException {
    Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();

    PropertyDescriptor[] descriptors;
    descriptors = loadDescriptors(getType());
    for (PropertyDescriptor descriptor : descriptors) {
      map.put(descriptor.getName().toUpperCase().trim(), descriptor);
    }

    return map;
  }

  private PropertyDescriptor[] loadDescriptors(Class<T> cls) throws IntrospectionException {
    BeanInfo beanInfo = Introspector.getBeanInfo(cls);
    return beanInfo.getPropertyDescriptors();
  }

  public T createBean() throws InstantiationException, IllegalAccessException {
    return type.newInstance();
  }

  public Class<T> getType() {
    return type;
  }

  public void setType(Class<T> type) {
    this.type = type;
  }
}
