package com.ilyabuglakov.task0201books.controller.comand;

import com.ilyabuglakov.task0201books.bean.SpecificationName;
import com.ilyabuglakov.task0201books.controller.ApplicationController;
import com.ilyabuglakov.task0201books.controller.PathController;
import com.ilyabuglakov.task0201books.dal.repository.PublicationRepository;
import com.ilyabuglakov.task0201books.dal.specification.Specification;
import com.ilyabuglakov.task0201books.dal.specification.publication.FirstLetterMatchSpecification;
import com.ilyabuglakov.task0201books.dal.specification.publication.SameIdSpecification;
import com.ilyabuglakov.task0201books.dal.specification.publication.SameNameSpecification;
import com.ilyabuglakov.task0201books.model.publication.Publication;
import com.ilyabuglakov.task0201books.storage.SpecificationMap;
import com.ilyabuglakov.task0201books.view.ConsoleView;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This command will find publication in repository by provided specification
 */
public class FindByTagCommand implements Command {

    Logger logger = Logger.getLogger(this.getClass());

    private final String FILENAME = "searchResult.txt";

    @Override
    public void execute() {
        ApplicationController controller = ApplicationController.getInstance();
        ConsoleView view = controller.getView();
        SpecificationMap specificationMap = SpecificationMap.getInstance();
        List<SpecificationName> specificationNames =
                new ArrayList<>();
        specificationNames.add(SpecificationName.BY_NAME);
        specificationNames.add(SpecificationName.BY_ID);
        specificationNames.add(SpecificationName.BY_FIRST_LETTER);
        view.showEnumeratedMessages(specificationNames);
        int choice = view.getInt(1, specificationNames.size());
        --choice;
        Specification<? super Publication> specification = specificationMap.get(specificationNames.get(choice));
        if (choice == 0) {
            String name = controller.getInputName();
            ((SameNameSpecification) specification).setName(name);
        } else if (choice == 1) {
            long id = view.getInt(0);
            ((SameIdSpecification) specification).setId(id);
        } else if (choice == 2) {
            char letter = view.getString().charAt(0);
            ((FirstLetterMatchSpecification) specification).setLetter(letter);
        }

        List<Publication> result = PublicationRepository.getInstance().findAll(specification);
        String path = PathController.getInstance().getResourcePath(FILENAME);
        controller.writeToFile(result, path);
        logger.info("Search result" + result);
    }
}
