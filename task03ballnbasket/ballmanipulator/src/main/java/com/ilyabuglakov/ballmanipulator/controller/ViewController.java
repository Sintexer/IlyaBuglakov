package main.java.com.ilyabuglakov.ballmanipulator.controller;

import main.java.com.ilyabuglakov.ballmanipulator.model.ball.Ball;
import main.java.com.ilyabuglakov.ballmanipulator.model.ball.BallColor;
import main.java.com.ilyabuglakov.ballmanipulator.repository.Basket;
import main.java.com.ilyabuglakov.ballmanipulator.service.BasketService;
import main.java.com.ilyabuglakov.ballmanipulator.service.input.CheckedInput;
import main.java.com.ilyabuglakov.ballmanipulator.service.input.InputBall;
import main.java.com.ilyabuglakov.ballmanipulator.service.output.OutputDecorator;
import main.java.com.ilyabuglakov.ballmanipulator.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ViewController {

    private final BasketController basketController;
    private final BasketService service = new BasketService();
    private Menu mainMenu = new Menu();
    private Menu addBallMenu = new Menu();
    private Menu removeBallMenu = new Menu();
    private Menu removeBallSubMenu = new Menu();
    private Menu removeBasketMenu = new Menu();
    private Menu serviceOperationsMenu = new Menu();
    private Scanner input = new Scanner(System.in);
    private CheckedInput checkedInput = new CheckedInput(input);
    private InputBall inputBall = new InputBall(input);

    public ViewController(BasketController basketController) {

        Consumer<Runnable> mainMenuAction = action -> {
            if (!basketController.getBaskets().isEmpty())
                action.run();
            else
                System.out.println("There are no baskets");
        };

        Consumer<IntConsumer> basketMenuAction = action -> {
            List<Basket> baskets = basketController.getBaskets();
            OutputDecorator.showEnumeratedList(baskets);
            int inp = checkedInput.checkedInt(1, baskets.size() + 1);
            action.accept(inp-1);
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

    public void operateMainMenu() {
        mainMenu.run();
    }


    private void initAddBallMenu(Consumer<IntConsumer> basketMenuAction) {
        addBallMenu.addOption("Add to basket №", () ->
                basketMenuAction.accept(ind -> basketController.getBaskets().get(ind).add(inputBall.getBall())));
        addBallMenu.addOption("Add to all baskets", () -> {
            Ball ball = inputBall.getBall();
            basketController.getBaskets().forEach(list -> list.add(ball));
        });
    }

    private void initRemoveBallMenu(Consumer<IntConsumer> basketMenuAction) {
        removeBallMenu.addOption("Remove from the basket №", () ->
                basketMenuAction.accept(ind -> {
                    Basket basket = basketController.getBaskets().get(ind);
                    removeBallSubMenu.addOption("Remove by index", () -> {
                        List<Ball> balls = basket.getBalls();
                        if (!balls.isEmpty()) {
                            OutputDecorator.showEnumeratedList(balls);
                            int i = checkedInput.checkedInt(1, balls.size() + 1);
                            balls.remove(i - 1);
                        } else System.out.println("This basket is empty");
                    });
                }));
        removeBallMenu.addOption("Remove from all baskets", () -> {
            Ball ball = inputBall.getBall();
            basketController.getBaskets().forEach(list -> list.remove(ball));
        });
    }

    private void initRemoveBasketMenu(Consumer<IntConsumer> basketMenuAction) {
        removeBasketMenu.addOption("Remove basket №", () ->
                basketMenuAction.accept(ind -> basketController.getBaskets().remove(ind)));
        removeBasketMenu.addOption("Remove all", () ->
                basketController.getBaskets().clear());
    }

    private void initServiceOperationsMenu(Consumer<IntConsumer> basketMenuAction) {

        Consumer<Consumer<BallColor>> colorMenuAction = consumer -> {
            List<BallColor> colors = new ArrayList<>(Arrays.asList(BallColor.values()));
            OutputDecorator.showEnumeratedList(colors);
            int index = checkedInput.checkedInt(1, colors.size()+1);
            consumer.accept(colors.get(index-1));
        };

        serviceOperationsMenu.addOption("Count weight", () ->
                basketMenuAction.accept(index ->
                        System.out.println("The weight is "
                                +service.calculateBasketWeight(basketController.getBaskets().get(index)))));
        serviceOperationsMenu.addOption("Count color", () ->
                basketMenuAction.accept(index ->
                        colorMenuAction.accept(color ->
                                System.out.println("There are "
                                        + service.countColor(basketController.getBaskets().get(index),
                                        color) + " " + color + " colors "))));
        serviceOperationsMenu.addOption("Amount of same", ()->
                basketMenuAction.accept(index ->
                        System.out.println("There are "
                        + service.amountOfSameBalls(basketController.getBaskets().get(index))
                        + " same balls")));
        serviceOperationsMenu.addOption("Show by ascending cost", () ->
                basketMenuAction.accept(index ->
                        System.out.println("Sorted: "
                                + service.sortedByCost(basketController.getBaskets().get(index)))));
        serviceOperationsMenu.addOption("Amount of same baskets", () ->
                System.out.println("Amount of same: " +
                        service.amountOfSameBaskets(basketController.getBaskets())));
    }

}
