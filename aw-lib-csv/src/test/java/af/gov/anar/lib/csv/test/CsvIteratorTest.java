package af.gov.anar.lib.csv.test;

import af.gov.anar.lib.csv.util.CsvIterator;
import af.gov.anar.lib.csv.reader.CsvReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CsvIteratorTest {

  private CsvIterator iterator;
  private CsvReader mockReader;
  private static final String[] STRINGS = {"test1", "test2"};

  @Before
  public void setUp() throws IOException {
    mockReader = mock(CsvReader.class);
    when(mockReader.readNext()).thenReturn(Arrays.asList(STRINGS));
    iterator = new CsvIterator(mockReader);
  }

  @Test(expected = RuntimeException.class)
  public void readerExceptionCausesRunTimeException() throws IOException {
    when(mockReader.readNext()).thenThrow(new IOException("reader threw test exception"));
    iterator.next();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void removethrowsUnsupportedOperationException() {
    iterator.remove();
  }

  @Test
  public void initialReadReturnsStrings() {
    assertArrayEquals(STRINGS, iterator.next().toArray());
  }

  @Test
  public void hasNextWorks() throws IOException {
    when(mockReader.readNext()).thenReturn(null);
    assertTrue(iterator.hasNext()); // initial read from constructor
    iterator.next();
    assertFalse(iterator.hasNext());
  }
}
