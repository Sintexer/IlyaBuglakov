package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.service.string.ExcludeService;
import com.ilyabuglakov.stringmanipulator.service.string.StringService;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CleanTheTextCommand implements Command {

    @Override
    public void execute() {
        String inputPath = ApplicationController.INPUT;
        String outputPath = ApplicationController.OUTPUT;
        ConsoleView view =ApplicationController.getInstance().getView();
        String previous = "";
        try ( Scanner in = new Scanner(new FileInputStream(new File(inputPath)));
              FileWriter out = new FileWriter(outputPath)){

            while (in.hasNextLine()){
                String line = in.nextLine();
                line = ExcludeService.deleteAllByPattern(line,
                        Pattern.compile("[^a-zа-я\\s]", Pattern.CASE_INSENSITIVE));
                line = StringService.innerTrim(line);
                if(!line.isEmpty() && line.charAt(0)==' ') {
                    if (!previous.isEmpty() && previous.charAt(0) == ' ')
                        line = line.substring(1);
                }
                if(!line.isEmpty()) {
                    previous = line;
                    out.write(line + '\n');
                    view.show(line);
                }
            }
        } catch (FileNotFoundException e) {
            view.showMessage(MessageId.CANT_OPEN_FILE);
        } catch (IOException e) {
            view.showMessage(MessageId.CANT_CREATE_FILE);
        }
    }
}
