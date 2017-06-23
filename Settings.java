import org.apache.commons.cli.*;
public class Settings {
    // these will be defaul settings and can be configured with command line options. Soon^tm
    public static int tagSuccessChance = 90;
    public static int tagsPerSpool = 500;
    public static int port = 9100;
    public static boolean createTagImages = false;
    public static boolean createLogFile = false;
    public static boolean simulatePaperOut = false;
    public static boolean simulateTagVoids = false;

    private static Options options = new Options();
    public static void createCommandLineOptions(String[] args){
        Option help = new Option("help", "This project will attempt to emulate a zebra printer");
        Option createImages = new Option("i", "Create png images of the tags printed");
        Option log = new Option("l", "Create log file of tags printed");
        Option paperOut = OptionBuilder.withArgName("quantity")
                                        .hasArg()
                                        .withDescription("Simulate paper out with given quantity of tags")
                                        .create("t");
        Option success = OptionBuilder.withArgName("chance")
                                        .hasArg()
                                        .withDescription("Simulate tag voids with a given percent chance.")
                                        .create("v");
        Option portOption = OptionBuilder.withArgName("Port Number")
                                    .hasArg()
                                    .withDescription("Set port number for printer (default 9100)")
                                    .create("p");

        options.addOption(help);
        options.addOption(createImages);
        options.addOption(log);
        options.addOption(paperOut);
        options.addOption(success);
        options.addOption(portOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ZebraMimic", options);
                System.exit(0);
            }
            if (line.hasOption("i")){
                createTagImages = true;
            }
            if (line.hasOption("l")){
                createLogFile = true;
            }
            if (line.hasOption("t")){
                simulatePaperOut = true;
                tagsPerSpool = Integer.parseInt(line.getOptionValue("t"));
            }
            if (line.hasOption("v")){
                simulateTagVoids = true;
                tagSuccessChance = Integer.parseInt(line.getOptionValue("v"));
            }
            if (line.hasOption("p")){
                port = Integer.parseInt(line.getOptionValue("p"));
            }
        }
        catch(ParseException ex) {
            System.out.println("Error trying to parse commandline Options");
        }
    }
}