package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.PathController;
import com.ilyabuglakov.task0201books.dal.repository.BookRepository;
import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.dal.specification.book.SameAuthorsSpecification;
import com.ilyabuglakov.task0201books.dal.specification.book.SameNameSpecification;
import com.ilyabuglakov.task0201books.dal.specification.book.SameNumberOfPagesSpecification;
import com.ilyabuglakov.task0201books.dal.specification.book.SamePublishingHouseSpecification;
import com.ilyabuglakov.task0201books.dal.specification.book.SamePublishingYear;
import com.ilyabuglakov.task0201books.model.book.Book;
import com.ilyabuglakov.task0201books.storage.SpecificationMap;
import com.ilyabuglakov.task0201books.view.ConsoleView;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FindByTagCommand implements Command {

    private final String FILENAME = "searchResult.txt";

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        SpecificationMap specificationMap = SpecificationMap.getInstance();
        List<SpecificationName> specificationNames =
                new ArrayList<>();
        specificationNames.add(SpecificationName.BY_NAME);
        specificationNames.add(SpecificationName.BY_AUTHORS);
        specificationNames.add(SpecificationName.BY_PUBLISHING_HOUSE);
        specificationNames.add(SpecificationName.BY_NUMBER_OF_PAGES);
        specificationNames.add(SpecificationName.BY_YEAR_OF_PUBLISHING);
        specificationNames.add(SpecificationName.BY_ALL);
        view.showEnumeratedMessages(specificationNames);
        int choice = view.getInt(1, specificationNames.size());
        --choice;
        Specification<Book> specification = specificationMap.get(specificationNames.get(choice));
        if (choice == 0) {
            String name = controller.getInputName();
            ((SameNameSpecification) specification).setName(name);
        } else if (choice == 1) {
            Set<String> authors = controller.getInputAuthors();
            ((SameAuthorsSpecification) specification).setAuthors(authors);
        } else if (choice == 2) {
            String publishingHouse = controller.getInputPublishingHouse();
            ((SamePublishingHouseSpecification) specification).setPublishingHouse(publishingHouse);
        } else if (choice == 3) {
            int numberOfPages = controller.getInputNumberOfPages();
            ((SameNumberOfPagesSpecification) specification).setNumberOfPages(numberOfPages);
        } else if (choice == 4) {
            Year year = controller.getInputYearOfPublishing();
            ((SamePublishingYear) specification).setYearOfPublishing(year);
        }
//        } else if (choice == 5) {
//            Book book = controller.getInputBook();
//            ((SameBookSpecification) specification).setBook(book);
//        }

            List<Book> result = BookRepository.getInstance().getBooksByCriteria(specification);
            String path = PathController.getInstance().getResourcePath(FILENAME);
            controller.writeToFile(result, path);
            view.show(result);
        }
    }
