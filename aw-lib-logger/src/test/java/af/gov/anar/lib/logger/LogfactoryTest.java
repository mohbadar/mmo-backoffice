package af.gov.anar.lib.logger;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import af.gov.anar.lang.infrastructure.exception.common.PatternSyntaxException;
import af.gov.anar.lib.logger.appender.ConsoleAppender;
import af.gov.anar.lib.logger.appender.FileAppender;
import af.gov.anar.lib.logger.appender.RollingFileAppender;
import af.gov.anar.lib.logger.exception.*;
import af.gov.anar.lib.logger.util.LogLevel;
import af.gov.anar.lib.logger.util.LoggerMethod;
import af.gov.anar.lib.logger.factory.Logfactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;


public class LogfactoryTest {

    private FileAppender fileAppender;
    private ConsoleAppender consoleAppender;
    private RollingFileAppender rollingFileAppender;
    private static String FILENAME;
    private static String FILEPATH;
    private File consoleAppenderFile;
    private File fileAppenderFile;
    private File rollingFileAppenderFile;

    @BeforeClass
    public static void preSetUp() throws IOException {
        FILEPATH = "src/test/resources/test";
        FILENAME = FILEPATH + "/test.txt";
    }

    @Before
    public void setUp() throws IOException {
        fileAppender = new FileAppender();
        consoleAppender = new ConsoleAppender();
        rollingFileAppender = new RollingFileAppender();

        consoleAppenderFile = new ClassPathResource("/consoleappender.xml").getFile();
        fileAppenderFile = new ClassPathResource("/fileappender.xml").getFile();
        rollingFileAppenderFile = new ClassPathResource("/rollingfileappender.xml").getFile();
    }

    @AfterClass
    public static void cleanUp() throws IOException {
        Logfactory.stopAll();
        Files.walk(new File("src/test/resources/test").toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    public void testgetDefaultConsoleLoggerClazz() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        assertThat(Logfactory.getDefaultConsoleLogger(consoleAppender, LogfactoryTest.class), isA(Logger.class));
    }

    @Test
    public void testgetDefaultConsoleLoggerName() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        assertThat(Logfactory.getDefaultConsoleLogger(consoleAppender, "LogfactoryTest"), isA(Logger.class));

    }

    @Test
    public void testgetDefaultConsoleLoggerLogLevelClazz() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        assertThat(Logfactory.getDefaultConsoleLogger(consoleAppender, LogfactoryTest.class, LogLevel.DEBUG),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultConsoleLoggerLogLevelName() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        assertThat(Logfactory.getDefaultConsoleLogger(consoleAppender, "LogfactoryTest", LogLevel.DEBUG),
                isA(Logger.class));

    }

    @Test
    public void testgetDefaultConsoleLoggerClazzImplementation() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        assertThat(Logfactory.getConsoleLogger(consoleAppender, LoggerMethod.ANARLOGBACK, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetDefaultConsoleLoggerClazzImplementationExcepTion() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        Logfactory.getConsoleLogger(consoleAppender, null, LogfactoryTest.class);
    }

    @Test
    public void testgetDefaultConsoleLoggerNameImplementation() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        assertThat(Logfactory.getConsoleLogger(consoleAppender, LoggerMethod.ANARLOGBACK, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetDefaultConsoleLoggerNameImplementationExcepTion() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        Logfactory.getConsoleLogger(consoleAppender, null, "LogfactoryTest");
    }

    @Test(expected = ClassNameNotFoundException.class)
    public void testgetDefaultConsoleLoggerNameWithTargetNameException() {
        consoleAppender.setAppenderName("testConsoleappender");
        consoleAppender.setImmediateFlush(true);
        consoleAppender.setTarget("System.out");
        Logfactory.getDefaultConsoleLogger(consoleAppender, "");
    }

    @Test
    public void testgetDefaultFileLoggerClassWithoutRolling() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        assertThat(Logfactory.getDefaultFileLogger(fileAppender, LogfactoryTest.class), isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameWithoutRolling() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        assertThat(Logfactory.getDefaultFileLogger(fileAppender, "LogfactoryTest"), isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerClassLogLevelWithoutRolling() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        assertThat(Logfactory.getDefaultFileLogger(fileAppender, LogfactoryTest.class, LogLevel.DEBUG),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameLogLevelWithoutRolling() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        assertThat(Logfactory.getDefaultFileLogger(fileAppender, "LogfactoryTest", LogLevel.DEBUG),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerClassWithoutRollingImplementation() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        assertThat(Logfactory.getFileLogger(fileAppender, LoggerMethod.ANARLOGBACK, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameWithoutRollingImplementation() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        assertThat(Logfactory.getFileLogger(fileAppender, LoggerMethod.ANARLOGBACK, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetDefaultFileLoggerClassWithoutRollingImplementationException() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        Logfactory.getFileLogger(fileAppender, null, LogfactoryTest.class);
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetDefaultFileLoggerNameWithoutRollingImplementationException() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        Logfactory.getFileLogger(fileAppender, null, "LogfactoryTest");
    }

    @Test(expected = ClassNameNotFoundException.class)
    public void testgetDefaultFileLoggerNameWithoutRollingNameException() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(FILENAME);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        Logfactory.getDefaultFileLogger(fileAppender, "");
    }

    @Test(expected = FileNameNotProvided.class)
    public void testgetDefaultFileLoggerNameWithoutRollingFileNullException() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName(null);
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        Logfactory.getDefaultFileLogger(fileAppender, "LogfactoryTest");
    }

    @Test(expected = FileNameNotProvided.class)
    public void testgetDefaultFileLoggerNameWithoutRollingFileEmptyException() {
        fileAppender.setAppenderName("testFileappender");
        fileAppender.setAppend(true);
        fileAppender.setFileName("");
        fileAppender.setImmediateFlush(true);
        fileAppender.setPrudent(false);
        Logfactory.getDefaultFileLogger(fileAppender, "LogfactoryTest");
    }

    @Test
    public void testgetDefaultFileLoggerClazzWithRolling() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppender, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameWithRolling() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerClazzLogLevelWithRolling() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        assertThat(
                Logfactory.getDefaultRollingFileLogger(rollingFileAppender, LogfactoryTest.class, LogLevel.DEBUG),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameLogLevelWithRolling() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest", LogLevel.DEBUG),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerClazzWithRollingImplementation() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        assertThat(Logfactory.getRollingFileLogger(rollingFileAppender, LoggerMethod.ANARLOGBACK,
                LogfactoryTest.class), isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameWithRollingImplementation() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        assertThat(
                Logfactory.getRollingFileLogger(rollingFileAppender, LoggerMethod.ANARLOGBACK, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetDefaultFileLoggerClazzWithRollingImplementationException() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getRollingFileLogger(rollingFileAppender, null, LogfactoryTest.class);
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetDefaultFileLoggerNameWithRollingImplementationException() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getRollingFileLogger(rollingFileAppender, null, "LogfactoryTest");

    }

    @Test
    public void testgetDefaultFileLoggerClazzWithFullRolling() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}-%i.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppender, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerNameWithFullRolling() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}-%i.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = FileNameNotProvided.class)
    public void testgetDefaultFileLoggerNameWithRollingFileNullException() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(null);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}-%i.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        Logfactory.getDefaultFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = FileNameNotProvided.class)
    public void testgetDefaultFileLoggerNameWithRollingFileEmptyException() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName("");
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/testFileappender-%d{ss}-%i.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        Logfactory.getDefaultFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = EmptyPatternException.class)
    public void testgetDefaultFileLoggerNameWithRollingNullFilePattern() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern(null);
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = EmptyPatternException.class)
    public void testgetDefaultFileLoggerNameWithRollingEmptyFilePattern() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = PatternSyntaxException.class)
    public void testgetDefaultFileLoggerNameWithRollingWrongFilePattern() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern(FILENAME);
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");

        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = PatternSyntaxException.class)
    public void testgetDefaultFileLoggerNameWithRollingWrongFileNamePattern() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        rollingFileAppender.setMaxFileSize("10kb");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = ClassNameNotFoundException.class)
    public void testgetDefaultFileLoggerNameWithRollingClassMissing() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "");
    }

    @Test(expected = IllegalStateException.class)
    public void testgetDefaultFileLoggerNameWithRollingIllegalState() {
        rollingFileAppender.setAppenderName("testRollingFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{aaaa}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = FileNameNotProvided.class)
    public void testgetDefaultFileLoggerNameWithRollingNullConstraintsException() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(null);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = FileNameNotProvided.class)
    public void testgetDefaultFileLoggerNameWithRollingEmptyConstraintsException() {
        rollingFileAppender.setAppenderName("testFileappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName("");
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = PatternSyntaxException.class)
    public void testgetDefaultFileLoggerNameWithRollingNotIConstraintsException() {
        rollingFileAppender.setAppenderName("testFileRollingappender");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{ss}-%i.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("100KB");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testgetDefaultFileLoggerNameWithRollingIllegalArgumentException() {
        rollingFileAppender.setAppenderName("testRollingFileappenderIllegalArgumentException");
        rollingFileAppender.setAppend(true);
        rollingFileAppender.setFileName(FILENAME);
        rollingFileAppender.setImmediateFlush(true);
        rollingFileAppender.setPrudent(false);
        rollingFileAppender.setFileNamePattern("src/test/resources/test/test-%d{ss}.txt");
        rollingFileAppender.setMaxHistory(5);
        rollingFileAppender.setTotalCap("aaaaaaaaaaa");
        Logfactory.getDefaultRollingFileLogger(rollingFileAppender, "LogfactoryTest");
    }

    @Test
    public void testgetFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getConsoleLogger(consoleAppenderFile, LoggerMethod.ANARLOGBACK, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetFileConsoleLoggerClazzImplementationExcepTion() {
        Logfactory.getConsoleLogger(consoleAppenderFile, null, LogfactoryTest.class);
    }

    @Test
    public void testgetFileLoggerClazzImplementation() {
        assertThat(Logfactory.getFileLogger(fileAppenderFile, LoggerMethod.ANARLOGBACK, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetFileLoggerClazzImplementationExcepTion() {
        Logfactory.getFileLogger(fileAppenderFile, null, LogfactoryTest.class);
    }

    @Test
    public void testgetRollingFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getRollingFileLogger(rollingFileAppenderFile, LoggerMethod.ANARLOGBACK,
                LogfactoryTest.class), isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetRollingFileConsoleLoggerClazzImplementationExcepTion() {
        Logfactory.getRollingFileLogger(rollingFileAppenderFile, null, LogfactoryTest.class);
    }

    @Test
    public void testgetNameFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getConsoleLogger(consoleAppenderFile, LoggerMethod.ANARLOGBACK, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetNameFileConsoleLoggerClazzImplementationExcepTion() {
        Logfactory.getConsoleLogger(consoleAppenderFile, null, "LogfactoryTest");
    }

    @Test
    public void testgetNameFileLoggerClazzImplementation() {
        assertThat(Logfactory.getFileLogger(fileAppenderFile, LoggerMethod.ANARLOGBACK, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetNameFileLoggerClazzImplementationExcepTion() {
        Logfactory.getFileLogger(fileAppenderFile, null, "LogfactoryTest");
    }

    @Test
    public void testgetNameRollingFileConsoleLoggerClazzImplementation() {
        assertThat(
                Logfactory.getRollingFileLogger(rollingFileAppenderFile, LoggerMethod.ANARLOGBACK, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = ImplementationNotFound.class)
    public void testgetNameRollingFileConsoleLoggerClazzImplementationExcepTion() {
        Logfactory.getRollingFileLogger(rollingFileAppenderFile, null, "LogfactoryTest");
    }

    @Test
    public void testgetDefaultFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getDefaultConsoleLogger(consoleAppenderFile, LogfactoryTest.class), isA(Logger.class));
    }

    @Test
    public void testgetDefaultFileLoggerClazzImplementation() {
        assertThat(Logfactory.getDefaultFileLogger(fileAppenderFile, LogfactoryTest.class), isA(Logger.class));
    }

    @Test
    public void testgetDefaultRollingFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppenderFile, LogfactoryTest.class),
                isA(Logger.class));
    }

    @Test
    public void testgetNameDefaultFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getDefaultConsoleLogger(consoleAppenderFile, "LogfactoryTest"), isA(Logger.class));
    }

    @Test
    public void testgetNameDefaultFileLoggerClazzImplementation() {
        assertThat(Logfactory.getDefaultFileLogger(fileAppenderFile, "LogfactoryTest"), isA(Logger.class));
    }

    @Test
    public void testgetNameDefaultRollingFileConsoleLoggerClazzImplementation() {
        assertThat(Logfactory.getDefaultRollingFileLogger(rollingFileAppenderFile, "LogfactoryTest"),
                isA(Logger.class));
    }

    @Test(expected = XMLConfigurationParseException.class)
    public void testgetNameDefaultRollingFileConsoleLoggerClazzImplementationParseException() throws IOException {
        rollingFileAppenderFile = new ClassPathResource("/rollingfileappenderexception.xml").getFile();
        Logfactory.getDefaultRollingFileLogger(rollingFileAppenderFile, "LogfactoryTest");
    }

}