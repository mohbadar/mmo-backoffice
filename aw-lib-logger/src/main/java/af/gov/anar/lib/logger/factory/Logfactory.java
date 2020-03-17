package af.gov.anar.lib.logger.factory;


import af.gov.anar.lib.logger.appender.ConsoleAppender;
import af.gov.anar.lib.logger.appender.FileAppender;
import af.gov.anar.lib.logger.appender.RollingFileAppender;
import af.gov.anar.lib.logger.util.LogExeptionCodeConstant;
import af.gov.anar.lib.logger.util.LogLevel;
import af.gov.anar.lib.logger.util.LoggerMethod;
import af.gov.anar.lib.logger.LoggerImpl;
import af.gov.anar.lib.logger.exception.ImplementationNotFound;
import af.gov.anar.lib.logger.Logger;
import af.gov.anar.lib.logger.util.LoggerUtils;

import java.io.File;

/**
 * Factory class for PHOENIX
 */
public class Logfactory {

    /**
     * Default constructor for this class
     */
    private Logfactory() {

    }

    /**
     * Default Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppender {@link ConsoleAppender} instance which contains
     *                             all configurations
     * @param clazz                reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultConsoleLogger(ConsoleAppender PHOENIXConsoleAppender, Class<?> clazz) {
        return LoggerImpl.getConsoleLogger(PHOENIXConsoleAppender, clazz.getName(), null);
    }

    /**
     * Default Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppender {@link ConsoleAppender} instance which contains
     *                             all configurations
     * @param clazz                reference of the calling class
     * @param logLevel             {@link LogLevel} more logger
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultConsoleLogger(ConsoleAppender PHOENIXConsoleAppender, Class<?> clazz,
                                                 LogLevel logLevel) {
        return LoggerImpl.getConsoleLogger(PHOENIXConsoleAppender, clazz.getName(), logLevel);
    }

    /**
     * Default Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppender {@link ConsoleAppender} instance which contains
     *                             all configurations
     * @param name                 name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultConsoleLogger(ConsoleAppender PHOENIXConsoleAppender, String name) {
        return LoggerImpl.getConsoleLogger(PHOENIXConsoleAppender, name, null);
    }

    /**
     * Default Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppender {@link ConsoleAppender} instance which contains
     *                             all configurations
     * @param name                 name of the calling class
     * @param logLevel             {@link LogLevel} more logger
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultConsoleLogger(ConsoleAppender PHOENIXConsoleAppender, String name, LogLevel logLevel) {
        return LoggerImpl.getConsoleLogger(PHOENIXConsoleAppender, name, logLevel);
    }

    /**
     * Default File factory method to configure logger
     *
     * @param PHOENIXFileAppender {@link FileAppender} instance which contains all
     *                          configurations
     * @param clazz             reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultFileLogger(FileAppender PHOENIXFileAppender, Class<?> clazz) {
        return LoggerImpl.getFileLogger(PHOENIXFileAppender, clazz.getName(), null);
    }

    /**
     * Default File factory method to configure logger
     *
     * @param PHOENIXFileAppender {@link FileAppender} instance which contains all
     *                          configurations
     * @param clazz             reference of the calling class
     * @param logLevel          {@link LogLevel} more logger
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultFileLogger(FileAppender PHOENIXFileAppender, Class<?> clazz, LogLevel logLevel) {
        return LoggerImpl.getFileLogger(PHOENIXFileAppender, clazz.getName(), logLevel);
    }

    /**
     * Default File factory method to configure logger
     *
     * @param PHOENIXFileAppender {@link FileAppender} instance which contains all
     *                          configurations
     * @param name              name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultFileLogger(FileAppender PHOENIXFileAppender, String name) {
        return LoggerImpl.getFileLogger(PHOENIXFileAppender, name, null);
    }

    /**
     * Default File factory method to configure logger
     *
     * @param PHOENIXFileAppender {@link FileAppender} instance which contains all
     *                          configurations
     * @param name              name of the calling class
     * @param logLevel          {@link LogLevel} more logger
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultFileLogger(FileAppender PHOENIXFileAppender, String name, LogLevel logLevel) {
        return LoggerImpl.getFileLogger(PHOENIXFileAppender, name, logLevel);
    }

    /**
     * Default Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppender {@link RollingFileAppender} instance which
     *                                 contains all configurations
     * @param clazz                    reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultRollingFileLogger(RollingFileAppender PHOENIXRollingFileAppender, Class<?> clazz) {
        return LoggerImpl.getRollingFileLogger(PHOENIXRollingFileAppender, clazz.getName(), null);
    }

    /**
     * Default Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppender {@link RollingFileAppender} instance which
     *                                 contains all configurations
     * @param clazz                    reference of the calling class
     * @param logLevel                 {@link LogLevel} more logger
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultRollingFileLogger(RollingFileAppender PHOENIXRollingFileAppender, Class<?> clazz,
                                                     LogLevel logLevel) {
        return LoggerImpl.getRollingFileLogger(PHOENIXRollingFileAppender, clazz.getName(), logLevel);
    }

    /**
     * Default Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppender {@link RollingFileAppender} instance which
     *                                 contains all configurations
     * @param name                     name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultRollingFileLogger(RollingFileAppender PHOENIXRollingFileAppender, String name) {
        return LoggerImpl.getRollingFileLogger(PHOENIXRollingFileAppender, name, null);
    }

    /**
     * Default Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppender {@link RollingFileAppender} instance which
     *                                 contains all configurations
     * @param name                     name of the calling class
     * @param logLevel                 {@link LogLevel} more logger
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultRollingFileLogger(RollingFileAppender PHOENIXRollingFileAppender, String name,
                                                     LogLevel logLevel) {
        return LoggerImpl.getRollingFileLogger(PHOENIXRollingFileAppender, name, logLevel);
    }

    /**
     * Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppender {@link ConsoleAppender} instance which contains
     *                             all configurations
     * @param PHOENIXLoggerMethod    type of Logging implementation
     * @param clazz                reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getConsoleLogger(ConsoleAppender PHOENIXConsoleAppender, LoggerMethod PHOENIXLoggerMethod,
                                          Class<?> clazz) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK) {
            return LoggerImpl.getConsoleLogger(PHOENIXConsoleAppender, clazz.getName(), null);
        } else {
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
        }
    }

    /**
     * Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppender {@link ConsoleAppender} instance which contains
     *                             all configurations
     * @param PHOENIXLoggerMethod    type of Logging implementation
     * @param name                 name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getConsoleLogger(ConsoleAppender PHOENIXConsoleAppender, LoggerMethod PHOENIXLoggerMethod,
                                          String name) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getConsoleLogger(PHOENIXConsoleAppender, name, null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * File factory method to configure logger
     *
     * @param PHOENIXFileAppender {@link FileAppender} instance which contains all
     *                          configurations
     * @param PHOENIXLoggerMethod type of Logging implementation
     * @param clazz             reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getFileLogger(FileAppender PHOENIXFileAppender, LoggerMethod PHOENIXLoggerMethod, Class<?> clazz) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getFileLogger(PHOENIXFileAppender, clazz.getName(), null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * File factory method to configure logger
     *
     * @param PHOENIXFileAppender {@link FileAppender} instance which contains all
     *                          configurations
     * @param PHOENIXLoggerMethod type of Logging implementation
     * @param name              name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getFileLogger(FileAppender PHOENIXFileAppender, LoggerMethod PHOENIXLoggerMethod, String name) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getFileLogger(PHOENIXFileAppender, name, null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppender {@link RollingFileAppender} instance which
     *                                 contains all configurations
     * @param PHOENIXLoggerMethod        type of Logging implementation
     * @param clazz                    reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getRollingFileLogger(RollingFileAppender PHOENIXRollingFileAppender,
                                              LoggerMethod PHOENIXLoggerMethod, Class<?> clazz) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getRollingFileLogger(PHOENIXRollingFileAppender, clazz.getName(), null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppender {@link RollingFileAppender} instance which
     *                                 contains all configurations
     * @param PHOENIXLoggerMethod        type of Logging implementation
     * @param name                     name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getRollingFileLogger(RollingFileAppender PHOENIXRollingFileAppender,
                                              LoggerMethod PHOENIXLoggerMethod, String name) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getRollingFileLogger(PHOENIXRollingFileAppender, name, null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * Default Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppenderFile XML file containing PHOENIX console logger
     *                                 configurations
     * @param clazz                    reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultConsoleLogger(File PHOENIXConsoleAppenderFile, Class<?> clazz) {
        return LoggerImpl.getConsoleLogger(
                (ConsoleAppender) LoggerUtils.unmarshall(PHOENIXConsoleAppenderFile, ConsoleAppender.class),
                clazz.getName(), null);
    }

    /**
     * Default Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppenderFile XML file containing PHOENIX console logger
     *                                 configurations
     * @param name                     name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultConsoleLogger(File PHOENIXConsoleAppenderFile, String name) {
        return LoggerImpl.getConsoleLogger(
                (ConsoleAppender) LoggerUtils.unmarshall(PHOENIXConsoleAppenderFile, ConsoleAppender.class), name, null);
    }

    /**
     * Default File factory method to configure logger
     *
     * @param PHOENIXFileAppenderFile XML file containing PHOENIX file logger
     *                              configurations
     * @param clazz                 reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultFileLogger(File PHOENIXFileAppenderFile, Class<?> clazz) {
        return LoggerImpl.getFileLogger(
                (FileAppender) LoggerUtils.unmarshall(PHOENIXFileAppenderFile, FileAppender.class), clazz.getName(),
                null);
    }

    /**
     * Default File factory method to configure logger
     *
     * @param PHOENIXFileAppenderFile XML file containing PHOENIX file logger
     *                              configurations
     * @param name                  name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultFileLogger(File PHOENIXFileAppenderFile, String name) {
        return LoggerImpl.getFileLogger(
                (FileAppender) LoggerUtils.unmarshall(PHOENIXFileAppenderFile, FileAppender.class), name, null);
    }

    /**
     * Default Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppenderFile XML file containing PHOENIX rolling file
     *                                     logger configurations
     * @param clazz                        reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultRollingFileLogger(File PHOENIXRollingFileAppenderFile, Class<?> clazz) {
        return LoggerImpl.getRollingFileLogger(
                (RollingFileAppender) LoggerUtils.unmarshall(PHOENIXRollingFileAppenderFile, RollingFileAppender.class),
                clazz.getName(), null);
    }

    /**
     * Default Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppenderFile XML file containing PHOENIX rolling file
     *                                     logger configurations
     * @param name                         name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getDefaultRollingFileLogger(File PHOENIXRollingFileAppenderFile, String name) {
        return LoggerImpl.getRollingFileLogger(
                (RollingFileAppender) LoggerUtils.unmarshall(PHOENIXRollingFileAppenderFile, RollingFileAppender.class),
                name, null);
    }

    /**
     * Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppenderFile XML file containing PHOENIX console logger
     *                                 configurations
     * @param PHOENIXLoggerMethod        type of Logging implementation
     * @param clazz                    reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getConsoleLogger(File PHOENIXConsoleAppenderFile, LoggerMethod PHOENIXLoggerMethod,
                                          Class<?> clazz) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getConsoleLogger(
                    (ConsoleAppender) LoggerUtils.unmarshall(PHOENIXConsoleAppenderFile, ConsoleAppender.class),
                    clazz.getName(), null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * Console factory method to configure logger
     *
     * @param PHOENIXConsoleAppenderFile XML file containing PHOENIX console logger
     *                                 configurations
     * @param PHOENIXLoggerMethod        type of Logging implementation
     * @param name                     name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getConsoleLogger(File PHOENIXConsoleAppenderFile, LoggerMethod PHOENIXLoggerMethod, String name) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getConsoleLogger(
                    (ConsoleAppender) LoggerUtils.unmarshall(PHOENIXConsoleAppenderFile, ConsoleAppender.class), name,
                    null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * File factory method to configure logger
     *
     * @param PHOENIXFileAppenderFile XML file containing PHOENIX file logger
     *                              configurations
     * @param PHOENIXLoggerMethod     type of Logging implementation
     * @param clazz                 reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getFileLogger(File PHOENIXFileAppenderFile, LoggerMethod PHOENIXLoggerMethod, Class<?> clazz) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getFileLogger(
                    (FileAppender) LoggerUtils.unmarshall(PHOENIXFileAppenderFile, FileAppender.class), clazz.getName(),
                    null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * File factory method to configure logger
     *
     * @param PHOENIXFileAppenderFile XML file containing PHOENIX file logger
     *                              configurations
     * @param PHOENIXLoggerMethod     type of Logging implementation
     * @param name                  name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getFileLogger(File PHOENIXFileAppenderFile, LoggerMethod PHOENIXLoggerMethod, String name) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getFileLogger(
                    (FileAppender) LoggerUtils.unmarshall(PHOENIXFileAppenderFile, FileAppender.class), name, null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppenderFile XML file containing PHOENIX rolling file
     *                                     logger configurations
     * @param PHOENIXLoggerMethod            type of Logging implementation
     * @param clazz                        reference of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getRollingFileLogger(File PHOENIXRollingFileAppenderFile, LoggerMethod PHOENIXLoggerMethod,
                                              Class<?> clazz) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getRollingFileLogger((RollingFileAppender) LoggerUtils
                    .unmarshall(PHOENIXRollingFileAppenderFile, RollingFileAppender.class), clazz.getName(), null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    /**
     * Rolling file factory method to configure logger
     *
     * @param PHOENIXRollingFileAppenderFile XML file containing PHOENIX rolling file
     *                                     logger configurations
     * @param PHOENIXLoggerMethod            type of Logging implementation
     * @param name                         name of the calling class
     * @return configured {@link Logger} instance
     */
    public static Logger getRollingFileLogger(File PHOENIXRollingFileAppenderFile, LoggerMethod PHOENIXLoggerMethod,
                                              String name) {
        if (PHOENIXLoggerMethod == LoggerMethod.ANARLOGBACK)
            return LoggerImpl.getRollingFileLogger((RollingFileAppender) LoggerUtils
                    .unmarshall(PHOENIXRollingFileAppenderFile, RollingFileAppender.class), name, null);
        else
            throw new ImplementationNotFound(LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUND.getValue(),
                    LogExeptionCodeConstant.IMPLEMENTATIONNOTFOUNDMESSAGE.getValue());
    }

    public static void stop(String appendersName) {
        LoggerImpl.stop(appendersName);
    }

    public static void stopAll() {
        LoggerImpl.stopAll();
    }

}