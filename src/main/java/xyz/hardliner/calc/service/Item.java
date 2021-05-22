package xyz.hardliner.calc.service;

/**
 * Represent any processable entity for the {@link Calculator}.
 */
public interface Item {

    /**
     * @return - String representation of an {@link Item}.
     */
    String print();

    /**
     * @return - Instruction how {@link Calculator} should process an {@link Item}.
     */
    ItemResolvingRule resolvingRule();

}
