package de.dytanic.cloudnetwrapper.bootstrap;

import de.dytanic.cloudnet.help.HelpService;
import de.dytanic.cloudnet.help.ServiceDescription;
import de.dytanic.cloudnet.lib.NetworkUtils;
import de.dytanic.cloudnet.lib.SystemTimer;
import de.dytanic.cloudnet.logging.CloudLogger;
import de.dytanic.cloudnetwrapper.CloudNetWrapper;
import de.dytanic.cloudnetwrapper.CloudNetWrapperConfig;
import de.dytanic.cloudnetwrapper.util.FileUtility;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CloudBootstrap {

    public static void main(String[] args) throws Exception {

        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("java.net.preferIPv4Stack", "true");

        OptionParser optionParser = new OptionParser();

        optionParser.allowsUnrecognizedOptions();
        optionParser.acceptsAll(Arrays.asList("version", "v"));
        optionParser.acceptsAll(Arrays.asList("help", "?"));
        optionParser.accepts("disable-queue");
        optionParser.accepts("systemTimer");
        optionParser.accepts("noconsole");
        optionParser.accepts("systemTimer");
        optionParser.accepts("debug");
        optionParser.accepts("disable-autoupdate");
        optionParser.accepts("disallow_bukkit_download");

        OptionSet optionSet = optionParser.parse(args);

        if (optionSet.has("help") || optionSet.has("?")) {
            HelpService helpService = new HelpService();
            helpService.getDescriptions().put("help",
                                              new ServiceDescription[] {new ServiceDescription("--help | --?",
                                                                                               "This is the main argument to get all information about other parameters")});
            helpService.getDescriptions().put("noconsole",
                                              new ServiceDescription[] {new ServiceDescription("--noconsole",
                                                                                               "Disables the console, for the rest of the service run time")});
            helpService.getDescriptions().put("disable-autoupdate",
                                              new ServiceDescription[] {new ServiceDescription("--disable-autoupdate",
                                                                                               "Disabled the autoupdate function of cloudnet 2")});
            helpService.getDescriptions().put("version",
                                              new ServiceDescription[] {new ServiceDescription("--version | --v",
                                                                                               "Displays the current version of CloudNet used")});
            helpService.getDescriptions().put("systemTimer",
                                              new ServiceDescription[] {new ServiceDescription("--systemTimer",
                                                                                               "Time all informations of this instance into a custom log file")});
            helpService.getDescriptions().put("requestTerminationSignal",
                                              new ServiceDescription[] {new ServiceDescription("--requestTerminationSignal",
                                                                                               "Enables the request if you use STRG+C")});
            System.out.println(helpService);
            return;
        }

        if (optionSet.has("version") || optionSet.has("v")) {
            System.out.printf("CloudNet-Wrapper RezSyM Version %s-%s%n",
                              CloudBootstrap.class.getPackage().getImplementationVersion(),
                              CloudBootstrap.class.getPackage().getSpecificationVersion());
            return;
        }

        /*==============================================*/
        FileUtility.deleteDirectory(new File("temp"));

        if (Files.exists(Paths.get("local"))) {
            FileUtility.deleteDirectory(new File("local/cache"));
        }
        /*==============================================*/

        CloudLogger cloudNetLogging = new CloudLogger();
        if (optionSet.has("debug")) {
            cloudNetLogging.setDebugging(true);
        }

        NetworkUtils.header();
        CloudNetWrapperConfig cloudNetWrapperConfig = new CloudNetWrapperConfig(cloudNetLogging.getReader());
        CloudNetWrapper cloudNetWrapper = new CloudNetWrapper(optionSet, cloudNetWrapperConfig, cloudNetLogging);

        if (optionSet.has("systemTimer")) {
            CloudNetWrapper.getExecutor().scheduleWithFixedDelay(SystemTimer::run, 0, 1, TimeUnit.SECONDS);
        }

        if (!cloudNetWrapper.bootstrap()) {
            System.exit(0);
        }

        if (!optionSet.has("noconsole")) {
            System.out.println("Use the command \"help\" for further information!");

            String user = System.getProperty("user.name");

            String commandLine;
            final String prompt = user + '@' + cloudNetWrapper.getWrapperConfig().getWrapperId() + " $ ";
            while ((commandLine = cloudNetLogging.readLine(prompt)) != null && CloudNetWrapper.RUNNING) {

                try {
                    if (!cloudNetWrapper.getCommandManager().dispatchCommand(commandLine)) {
                        System.out.println("Command not found. Use the command \"help\" for further information!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            while (!Thread.currentThread().isInterrupted() && CloudNetWrapper.RUNNING) {
                NetworkUtils.sleepUninterruptedly(Long.MAX_VALUE);
            }
        }
        cloudNetLogging.info("Shutting down now!");
    }
}
