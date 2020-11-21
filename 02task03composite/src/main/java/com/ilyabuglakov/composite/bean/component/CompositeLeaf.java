package com.ilyabuglakov.composite.bean.component;

import java.util.Collections;
import java.util.List;

/**
 * CompositeLeaf is realization of Component<String> class, used as leafs of tree.
 * Stores String.
 */
public class CompositeLeaf extends Component<String> {

    private String content;

    public CompositeLeaf(ComponentType compositeType, String content) {
        super(compositeType);
        this.content = content;
    }

    /**
     * Empty method
     */
    public void add(Component<String> component) {

    }

    /**
     * Empty method
     */
    public void remove(Component<String> component) {

    }

    /**
     * Since Leaf don't have children, returns empty list
     * @param type - type of nodes to collect
     * @return empty list
     */
    @Override
    public List<Component<String>> collectChildren(ComponentType type) {
        return Collections.emptyList();
    }

    /**
     * Returns content.
     * @return String content
     */
    public String collect() {
        return content;
    }
}
