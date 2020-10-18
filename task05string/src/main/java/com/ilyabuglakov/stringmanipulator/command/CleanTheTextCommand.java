package com.ilyabuglakov.stringmanipulator.command;

import com.ilyabuglakov.stringmanipulator.beans.MessageId;
import com.ilyabuglakov.stringmanipulator.controller.ApplicationController;
import com.ilyabuglakov.stringmanipulator.exception.ReadException;
import com.ilyabuglakov.stringmanipulator.file.FileBufferedIterator;
import com.ilyabuglakov.stringmanipulator.service.string.ExcludeService;
import com.ilyabuglakov.stringmanipulator.service.string.StringService;
import com.ilyabuglakov.stringmanipulator.view.ConsoleView;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * This command allows user to clean the text from characters, that are not letters or spaces.
 * Input file path comes up from ApplicationController INPUT field,
 * output from OUTPUT field.
 */
public class CleanTheTextCommand implements Command {

    @Override
    public void execute() {
        String inputPath = ApplicationController.INPUT;
        String outputPath = ApplicationController.OUTPUT;
        final int BUFFER_SIZE = 256;
        ConsoleView view = ApplicationController.getInstance().getView();
        String previous = "";
        try (FileBufferedIterator in = new FileBufferedIterator(inputPath, BUFFER_SIZE);
             FileWriter out = new FileWriter(outputPath)) {

            while (in.hasNext()) {
                String chunk = in.next();
                chunk = ExcludeService.deleteAllByPattern(chunk,
                        Pattern.compile("[^a-zа-я\\s]", Pattern.CASE_INSENSITIVE));
                chunk = StringService.innerTrim(chunk);
                if (!chunk.isEmpty() && chunk.charAt(0) == ' ') {
                    if (!previous.isEmpty() && previous.charAt(0) == ' ')
                        chunk = chunk.substring(1);
                }
                if (!chunk.isEmpty()) {
                    previous = chunk;
                    out.write(chunk + '\n');
                    view.show(chunk);
                }
            }
        } catch (FileNotFoundException e) {
            view.showMessage(MessageId.CANT_OPEN_FILE);
        } catch (IOException e) {
            view.showMessage(MessageId.CANT_CREATE_FILE);
        } catch (ReadException e) {
            view.showMessage(MessageId.NOTHING_TO_READ);
        }
    }
}
