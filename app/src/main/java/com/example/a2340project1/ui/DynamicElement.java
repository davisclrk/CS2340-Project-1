package com.example.a2340project1.ui;

/**
 * Stores data about views dynamically added to a fragment.
 *
 * @author aidannguyen
 * @version 1.0
 */
public class DynamicElement {
    protected int mainResource;

    /**
     * Creates a DynamicElement with a resource layout id.
     *
     * @param mainResource the id of the resource layout the view pulls from
     */
    public DynamicElement(int mainResource) {
        this.mainResource = mainResource;
    }

    public int getMainResource() {
        return mainResource;
    }
}
