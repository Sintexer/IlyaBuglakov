package main.java.com.ilyabuglakov.ballmanipulator.view;

import main.java.com.ilyabuglakov.ballmanipulator.controller.BasketController;
import main.java.com.ilyabuglakov.ballmanipulator.exception.BallCreationException;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;
import main.java.com.ilyabuglakov.ballmanipulator.service.BasketModifier;
import main.java.com.ilyabuglakov.ballmanipulator.service.BasketService;
import main.java.com.ilyabuglakov.ballmanipulator.service.BasketSorter;
import main.java.com.ilyabuglakov.ballmanipulator.service.input.CheckedInput;
import main.java.com.ilyabuglakov.ballmanipulator.service.input.InputBall;
import main.java.com.ilyabuglakov.ballmanipulator.service.output.OutputDecorator;
import main.java.com.ilyabuglakov.ballmanipulator.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;


/**
 * ViewController class manufacture dialog with user and handles
 * user input. ViewController initialise and run various menu's and submenu's.
 * ViewController delegates input to CheckedInput and InputBall classes.
 * Has a reference to the parent BasketController.
 *
 * @author Ilya Buglakov
 * @version 1.0
 * @since 2020-09-28
 */
public class View {

    private final BasketController basketController;
    private final BasketService service = new BasketService();
    private Menu mainMenu = new Menu();
    private Menu addBallMenu = new Menu();
    private Menu removeBallMenu = new Menu();
    private Menu removeBasketMenu = new Menu();
    private Menu serviceOperationsMenu = new Menu();
    private Scanner input = new Scanner(System.in);
    private CheckedInput checkedInput = new CheckedInput(input);
    private InputBall inputBall = new InputBall();

    /**
     * ViewController constructor initialises reference to the parent BasketController and
     * initialises various menu's.
     * @param basketController - parent BasketController.
     */
    public View(BasketController basketController) {

        Consumer<Runnable> mainMenuAction = action -> {
            if (!basketController.getBaskets().isEmpty())
                action.run();
            else
                System.out.println("There are no baskets");
        };

        Consumer<IntConsumer> basketMenuAction = action -> {
            List<Basket> baskets = basketController.getBaskets();
            System.out.println("Choose basket:");
            OutputDecorator.showEnumeratedList(baskets);
            int inp = checkedInput.checkedInt(1, baskets.size() + 1);
            action.accept(inp - 1);
        };

        initAddBallMenu(basketMenuAction);
        initRemoveBallMenu(basketMenuAction);
        initRemoveBasketMenu(basketMenuAction);
        initServiceOperationsMenu(basketMenuAction);

        mainMenu.addOption("Add ball", () ->
                mainMenuAction.accept(addBallMenu::run));

        mainMenu.addOption("Remove ball", () ->
                mainMenuAction.accept(removeBallMenu::run));

        mainMenu.addOption("Remove basket", () ->
                mainMenuAction.accept(removeBasketMenu::run));

        mainMenu.addOption("Add basket", () ->
                basketController.getBaskets().add(new Basket()));

        mainMenu.addOption("Show baskets", () -> mainMenuAction.accept(() ->
                basketController.getBaskets().forEach(System.out::println)));

        mainMenu.addOption("Perform operation with basket", () ->
                mainMenuAction.accept(serviceOperationsMenu::run));

        mainMenu.setContinuous(true);
        this.basketController = basketController;

    }

    /**
     * operateMainMenu() - starts mainMenu dialog with user
     */
    public void operateMainMenu() {
        mainMenu.run();
    }

    /**
     * Initialises addBallMenu
     * @param basketMenuAction - reference to the before init lambda, that ask's user about
     * the index of basket to work with.
     */
    private void initAddBallMenu(Consumer<IntConsumer> basketMenuAction) {
        BasketModifier modifier = new BasketModifier();
        addBallMenu.addOption("Add to basket №", () ->
                basketMenuAction.accept(ind -> {
                    try {
                        basketController.setBasket(ind, modifier.addBall(basketController.getBaskets().get(ind), inputBall.getBall()));
                    } catch (BallCreationException e) {
                        System.out.println("Wrong parameters");
                        e.printStackTrace();
                    }
                }));
        addBallMenu.addOption("Add to all baskets", () -> {
            Ball ball = null;
            do {
                try {
                    ball = inputBall.getBall();
                } catch (BallCreationException e) {
                    System.out.println("Wrong parameters");
                    e.printStackTrace();
                }
            } while (ball == null);
            final Ball finalBall = ball;
            basketController.setBaskets(
                basketController.getBaskets().stream()
                        .map(list -> modifier.addBall(list, finalBall))
                        .collect(Collectors.toList())
            );
        });
    }

    /**
     * Initialises removeBallMenu
     * @param basketMenuAction - reference to the before init lambda, that ask's user about
     * the index of basket to work with.
     */
    private void initRemoveBallMenu(Consumer<IntConsumer> basketMenuAction) {

        BasketModifier modifier = new BasketModifier();

        Consumer<Integer> removeBallMenuAction = index -> {
            Basket basket = basketController.getBaskets().get(index);
            if (!basket.getBalls().isEmpty()) {
                System.out.println("What ball to remove?");
                OutputDecorator.showEnumeratedList(basket.getBalls());
                int i = checkedInput.checkedInt(1, basket.getBalls().size() + 1);
                basketController.setBasket(index,  modifier.removeBall(basket, i-1));
            } else System.out.println("This basket is empty");
        };

        removeBallMenu.addOption("Remove from the basket №", () ->
                basketMenuAction.accept(removeBallMenuAction::accept));
        removeBallMenu.addOption("Remove from all baskets", () -> {
            Ball ball = null;
            try {
                ball = inputBall.getBall();
            } catch (BallCreationException e) {
                e.printStackTrace();
            }
            Ball finalBall = ball;
            basketController.setBaskets(basketController.getBaskets().stream()
                    .map(list -> modifier.removeBall(list, finalBall))
                    .collect(Collectors.toList()));
        });
    }

    /**
     * Initialises removeBasketMenu
     * @param basketMenuAction - reference to the before init lambda, that ask's user about
     * the index of basket to work with.
     */
    private void initRemoveBasketMenu(Consumer<IntConsumer> basketMenuAction) {
        removeBasketMenu.addOption("Remove basket №", () ->
                basketMenuAction.accept(ind -> basketController.getBaskets().remove(ind)));
        removeBasketMenu.addOption("Remove all", () ->
                basketController.getBaskets().clear());
    }

    /**
     * Initialises serviceOperationsMenu
     * @param basketMenuAction - reference to the before init lambda, that ask's user about
     * the index of basket to work with.
     */
    private void initServiceOperationsMenu(Consumer<IntConsumer> basketMenuAction) {

        Consumer<Consumer<BallColor>> colorMenuAction = consumer -> {
            List<BallColor> colors = new ArrayList<>(Arrays.asList(BallColor.values()));
            System.out.println("What list to operate with?");
            OutputDecorator.showEnumeratedList(colors);
            int index = checkedInput.checkedInt(1, colors.size() + 1);
            consumer.accept(colors.get(index - 1));
        };

        BasketSorter sorter = new BasketSorter();

        serviceOperationsMenu.addOption("Count weight", () ->
                basketMenuAction.accept(index ->
                        System.out.println("The weight is "
                                + service.calculateBasketWeight(basketController.getBaskets().get(index)))));
        serviceOperationsMenu.addOption("Count color", () ->
                basketMenuAction.accept(index ->
                        colorMenuAction.accept(color ->
                                System.out.println("There are "
                                        + service.countColor(basketController.getBaskets().get(index),
                                        color) + " " + color + " colors "))));
        serviceOperationsMenu.addOption("Amount of same", () ->
                basketMenuAction.accept(index ->
                        System.out.println("There are "
                                + service.amountOfSameBalls(basketController.getBaskets().get(index))
                                + " same balls")));
        serviceOperationsMenu.addOption("Show by ascending cost", () ->
                basketMenuAction.accept(index ->
                        System.out.println("Sorted: "
                                + sorter.sort(basketController.getBaskets().get(index), Comparator.comparing(Ball::getCost)))));
        serviceOperationsMenu.addOption("Amount of same baskets", () ->
                System.out.println("Amount of same: " +
                        service.amountOfSameBaskets(basketController.getBaskets())));
    }

}
