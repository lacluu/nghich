package vn.elca.training.util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class ApplicationUtil {
    public static final int LIMIT_RESULT_NUMBER = 5;
    
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String GROUP_STATUS_NEW = "New";
    public static final String PROJECT_STATUS_NEW = "NEW";
    public static final int FIRST_NUMBER = 0;
    public static final String VISA_JOIN_CHARACTOR = ",";
    public static final String PREFIX = "{";
    public static final String SUFFIX = "}";
}
