import org.apache.commons.cli.*;
public class Settings {
    // these will be defaul settings and can be configured with command line options. Soon^tm
    public static int tagSuccessChance = 90;
    public static int tagsPerSpool = 500;
    public static int port = 9100;
    public static int sleepTimer = 1000;
    public static boolean simulateSleep = false;
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
        Option sleepTime = OptionBuilder.withArgName("Sleep Time")
                                    .hasArg()
                                    .withDescription("Set sleep timer between tag prints (in miliseconds -default 1000)")
                                    .create("s");
        options.addOption(help);
        options.addOption(createImages);
        options.addOption(log);
        options.addOption(paperOut);
        options.addOption(success);
        options.addOption(portOption);
        options.addOption(sleepTime);

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
                System.out.println("Will create tag images");
            }
            if (line.hasOption("l")){
                createLogFile = true;
                System.out.println("Log turned on");
            }
            if (line.hasOption("t")){
                simulatePaperOut = true;
                tagsPerSpool = Integer.parseInt(line.getOptionValue("t"));
                System.out.println("Simulating paper out with " + line.getOptionValue("t") + " tags in spool");
            }
            if (line.hasOption("v")){
                simulateTagVoids = true;
                tagSuccessChance = Integer.parseInt(line.getOptionValue("v"));
                System.out.println("Simulating tag voids with " + line.getOptionValue("v") + "% chance to void");
            }
            if (line.hasOption("p")){
                port = Integer.parseInt(line.getOptionValue("p"));
                System.out.println("Using port " + line.getOptionValue("p"));
            }
            if (line.hasOption("s")){
                simulateSleep = true;
                sleepTimer = Integer.parseInt(line.getOptionValue("s"));
                System.out.println("Sleep time between tags set at " + line.getOptionValue("s") + " miliseconds");
            }
        }
        catch(ParseException ex) {
            System.out.println("Error trying to parse commandline Options");
        }
    }
}