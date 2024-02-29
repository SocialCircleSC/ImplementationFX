// specify the package
package model;

// system imports
import java.util.Hashtable;
import java.util.Hashtable;
import java.util.Properties;

import event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

// project imports
import impresario.IModel;
import impresario.IView;
import impresario.ModelRegistry;
import userinterface.MainStageContainer;

import event.Event;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;
import impresario.ModelRegistry;
import userinterface.MainStageContainer;

import event.Event;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

public class Librarian implements IView, IModel {
// This class implements all these interfaces (and does NOT extend 'EntityBase')
// because it does NOT play the role of accessing the back-end database tables.
// It only plays a front-end role. 'EntityBase' objects play both roles.

    // For Impresario
    private Properties dependencies;
    private ModelRegistry myRegistry;

    // Instead of AccountHolder, we will use instances of our Book and Patron
    private Book myBook;
    private Patron myPatron;

    // GUI Components
    private Stage myStage;
    private Hashtable<String, Scene> myViews;

    public Librarian()
    {
        myStage = MainStageContainer.getInstance(); // Get the main stage to be used to change/update scenes
        myViews = new Hashtable<String, Scene>(); // 

        // STEP 3.1: Create the Registry object - if you inherit from
        myRegistry = new ModelRegistry("Librarian");
        if(myRegistry == null)
        {
            new Event(Event.getLeafLevelClassName(this), "Librarian",
				"Could not instantiate Registry", Event.ERROR);
        }

        // STEP 3.2: Be sure to set the dependencies correctly
        setDependencies(); // Need to create this function below

        // Set up the initial view
		createAndShowLibrarianView(); // Need to create this function below

    } // end of Librarian constructor

    //-----------------------------------------------------------------------------------
    private void setDependencies()
    {
        
    } // end of setDependencies

    // Create Initial Startup View of program to be displayed
    //-----------------------------------------------------------------------------------
    private void createAndShowLibrarianView()
    {
        Scene currentScene = (Scene)myViews.get("LibrarianView");

        if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("LibrarianView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("LibrarianView", currentScene);
		}
				
		swapToView(currentScene); // Need to create this function below
    }

        //-----------------------------------------------------------------------------------
        private void createAndShowBookView()
        {
            Scene currentScene = (Scene)myViews.get("BookView");
    
            if (currentScene == null)
            {
                // create our initial view
                View newView = ViewFactory.createView("BookView", this); // USE VIEW FACTORY
                currentScene = new Scene(newView);
                myViews.put("BookView", currentScene);
            }
                    
            swapToView(currentScene); // Need to create this function below
        }

    public void swapToView(Scene newScene)
    {
        if (newScene == null) // Make sure that we have a new view/scene to swap to
        {
            System.out.println("Librarian.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView",
				"Missing view for display ", Event.ERROR);
			return;
        }

        myStage.setScene(newScene);
        myStage.sizeToScene();

        // Place screen in center
        WindowPosition.placeCenter(myStage);
    }

    @Override
    public Object getState(String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public void subscribe(String key, IView subscriber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'subscribe'");
    }

    @Override
    public void unSubscribe(String key, IView subscriber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unSubscribe'");
    }

    @Override
    public void stateChangeRequest(String key, Object value) {
        // TODO Auto-generated method stub
        if (key.equals("RequestBookView") == true)
        {
            createAndShowBookView();
        }
    }

    @Override
    public void updateState(String key, Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateState'");
    }

} // ENd of Librarian Class


