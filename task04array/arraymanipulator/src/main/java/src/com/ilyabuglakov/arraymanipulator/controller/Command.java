package src.com.ilyabuglakov.arraymanipulator.controller;

public interface Command {
    void execute();

    default boolean isNullApplicationArray() {
        return ApplicationController.getInstance().getArray() == null;
    }

    default boolean canBeAppliedWhenArrayIsNull() {
        return false;
    }
}
