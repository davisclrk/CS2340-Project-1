package com.example.a2340project1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class DynamicElementHandler {
    private int viewCount;

    /**
     * Creates a new dynamic element handler with a view count of 0.
     */
    public DynamicElementHandler() {
        viewCount = 0;
    }

    /**
     * Dynamically adds a view from a resource to a destination layout in a fragment. View count
     * can be used to keep track of added views for repopulating fragments.
     *
     * @param viewGroup the view group destination in the corresponding fragment
     * @param inflater inflater of the destination fragment
     * @param mainResource resource of the added view
     * @param closeButtonId id of the close button for the added view
     */
    public void addView(ViewGroup viewGroup, LayoutInflater inflater, int mainResource, int closeButtonId) {

        View addedView = inflater.inflate(mainResource, null, false);

        ImageButton imageClose = (ImageButton) addedView.findViewById(closeButtonId);

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewGroup.removeView(addedView);
                viewCount--;
            }
        });

        viewGroup.addView(addedView);
        viewCount++;
    }

    /**
     * Returns the view count.
     *
     * @return the number of views
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Sets a new view count.
     *
     * @param newViewCount the new number of views
     */
    public void setViewCount(int newViewCount) {
        viewCount = newViewCount;
    }
}
