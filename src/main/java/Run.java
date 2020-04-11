import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import bean.Flight;
import consts.TaskConstants;
import factory.PathFinderServiceFactory;
import repository.StationRepository;
import repository.impl.StationRepositoryImpl;
import service.PathFinderService;
import util.AppPropertiesManager;
import util.CsvImportUtils;
import util.RepositoryUtils;

public class Run {
    private static final Logger LOGGER = Logger.getLogger(Run.class);

    private static AppPropertiesManager propertiesManager;
    private static PathFinderServiceFactory serviceFactory;

    public static void main(String[] args) {
        init();

        Scanner scanner = new Scanner(System.in);
        String exitCommand = propertiesManager.getProperty(TaskConstants.EXIT_COMMAND_PROPERTY_KEY);
        String errorMessage = String.format(TaskConstants.WRONG_COMMAND_MESSAGE, exitCommand);
        String welcomeMessage = String.format(TaskConstants.WELCOME_MESSAGE, exitCommand);

        System.out.println(welcomeMessage);
        System.out.println(getServicesList());
        String input = scanner.nextLine().trim();
        while (!exitCommand.equals(input)) {
            PathFinderService pathFinderService = serviceFactory.getService(input);
            if (Objects.nonNull(pathFinderService)) {
                System.out.println(TaskConstants.BEST_RESULTS);
                pathFinderService.find().forEach(System.out::println);
            } else {
                System.out.println(errorMessage);
            }
            System.out.println(System.lineSeparator() + getServicesList());
            input = scanner.nextLine().trim();
        }
    }

    private static void init() {
        propertiesManager = new AppPropertiesManager();
        List<Flight> flights = retrieveFlightsFromDataFile();
        StationRepository repository = new StationRepositoryImpl();
        RepositoryUtils.fillStationRepository(flights, repository);
        serviceFactory = new PathFinderServiceFactory(repository);
        LOGGER.info("Main class initialized");
    }

    private static List<Flight> retrieveFlightsFromDataFile() {
        String dataFileName = propertiesManager.getProperty(TaskConstants.DATA_FILE_NAME_PROPERTY_KEY);
        CsvToBean<Flight> csvToBean = new CsvToBeanBuilder<Flight>(CsvImportUtils.getCsvReader(dataFileName))
                .withType(Flight.class)
                .build();
        LOGGER.info("Data file was successfully read");
        return csvToBean.parse();
    }

    private static String getServicesList() {
        StringBuilder sb = new StringBuilder();
        serviceFactory.getServices().forEach((key, service) ->
                sb.append(key).append(" - ").append(service.getClass().getSimpleName()).append(System.lineSeparator()));
        return sb.toString();
    }
}
