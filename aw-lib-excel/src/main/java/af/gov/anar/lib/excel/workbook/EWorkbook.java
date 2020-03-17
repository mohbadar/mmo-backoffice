
package af.gov.anar.lib.excel.workbook;

import af.gov.anar.lib.excel.sheet.ESheet;
import af.gov.anar.lib.excel.styles.Style;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Workbook.
 */
public interface EWorkbook {

    /**
     * Describe object as a byte stream.
     * @return Byte array output stream
     * @throws IOException IOException
     */
    ByteArrayOutputStream asStream() throws IOException;

    /**
     * Describe object as a workbook.
     * @return Workbook
     * @throws IOException IOException
     */
    Workbook asWorkbook() throws IOException;

    /**
     * Save workbook to a file.
     * @param path Path
     * @throws IOException IOException
     */
    void saveTo(String path) throws IOException;

    /**
     * Add sheet to the workbook.
     * @param sheet Sheet
     * @return Workbook
     */
    EWorkbook with(ESheet sheet);

    /**
     * Add style to the workbook.
     * @param style Style
     * @return Workbook
     */
    EWorkbook with(Style style);
}
