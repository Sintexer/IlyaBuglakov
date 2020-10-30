package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.CommandName;
import com.ilyabuglakov.task0201books.bean.MessageName;
import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.CommandController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.storage.BookComparatorMap;
import com.ilyabuglakov.task0201books.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

public class SortCommand implements Command {
    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        List<SpecificationName> sortTypes = new ArrayList<>(BookComparatorMap.getInstance().getSpecificationsNames());
        view.showMessage(MessageName.CHOOSE_SORT_TYPE);
        view.showEnumeratedMessages(sortTypes);
        int choice = view.getInt(1, sortTypes.size());
        BookRepository.getInstance().sortBy(BookComparatorMap.getInstance().get(sortTypes.get(choice - 1)));
        CommandController.getInstance().executeCommand(CommandName.SHOW_BOOK_REPOSITORY);
    }
}
