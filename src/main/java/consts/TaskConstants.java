package consts;

public class TaskConstants {

    //Properties
    public static final String DATA_FILE_NAME_PROPERTY_KEY = "data-file-name";
    public static final String EXIT_COMMAND_PROPERTY_KEY = "exit-command";

    //Messages
    public static final String WRONG_COMMAND_MESSAGE = "Wrong command! Please, select one of the items below or type '%s' for exit";
    public static final String WELCOME_MESSAGE = "Hi! To start searching select one of the items below or type '%s' for exit";
    public static final String BEST_RESULTS = "There're the best results we found:";

    //Other
    public static final char CSV_SEPARATOR = ';';
    public static final long ONE_DAY_IN_MINUTES = 1440;
    public static final int FLOAT_PLACES_TO_ROUND = 2;
    public static final int MINUTES_IN_HOUR = 60;

    private TaskConstants() {
        throw new UnsupportedOperationException();
    }
}
