package af.gov.anar.lib.logger.util;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import af.gov.anar.lib.logger.exception.XMLConfigurationParseException;

/**
 * This is utility class for Logger
 */
public class LoggerUtils {
    /**
     * COnstructor for this class
     */
    private LoggerUtils() {
    }

    /**
     * Unmarshall object from XML file
     *
     * @param file  XML file provided by user
     * @param clazz clazz whose object is to be extracted
     * @return unmarshalled object
     */
    public static Object unmarshall(File file, Class<?> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new XMLConfigurationParseException(LogExeptionCodeConstant.PHOENIXCONFIGURATIONXMLPARSE.getValue(),
                    LogExeptionCodeConstant.PHOENIXCONFIGURATIONXMLPARSE.getValue(), e);
        }

    }
}