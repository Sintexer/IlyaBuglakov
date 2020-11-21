package com.ilyabuglakov.composite.bean.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Composite is realization of Component<String> class, used as nodes of tree
 */
public class Composite extends Component<String> {

    private List<Component<String>> children;

    /**
     * Composite should be initialised with componentType and some List with children, may be empty
     * @param componentType - type of node's content
     * @param children - list with node children
     */
    public Composite(ComponentType componentType, List<Component<String>> children) {
        super(componentType);
        this.children = new ArrayList<>(children);
    }

    /**
     * Adds a new child to node
     * @param component - node child
     */
    public void add(Component<String> component) {
        children.add(component);
    }

    /**
     * Removes child component from node
     * @param component - node child
     */
    public void remove(Component<String> component) {
        children.remove(component);
    }

    /**
     * This method realisation collects all node children of certain type
     * @param type - type of nodes to collect
     * @return List of collected components
     */
    @Override
    public List<Component<String>> collectChildren(ComponentType type){
        List<Component<String>> collected = new ArrayList<>();
        for(Component<String> child : children){
            if(child.getType().equals(type)){
                collected.add(child);
            } else{
                collected.addAll(child.collectChildren(type));
            }
        }
        return collected;
    }

    /**
     * Generates total value of all components represented as concatenation of all Strings
     * @return total value of all sub nodes
     */
    public String collect() {
        return children.stream()
                .map(Component<String>::collect)
                .collect(Collectors.joining());
    }
}
