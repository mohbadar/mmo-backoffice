package af.gov.anar.lib.csv.test;

import af.gov.anar.lib.csv.writer.CsvWriter;

import java.io.IOException;
import java.io.Writer;


public class CsvWriterExceptionThrower extends CsvWriter {
  public CsvWriterExceptionThrower(Writer writer) {
    super(writer);
  }

  @Override
  public void flush() throws IOException {
    throw new IOException("Exception thrown from Mock test flush method");
  }
}