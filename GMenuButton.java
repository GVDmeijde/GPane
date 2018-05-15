package userinterface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * Small helper class for choiceboxes.
 * @author Gijs van der Meijde
 */
class GMenuButton extends MenuButton{
	public GMenuButton(String name){
		super(name);
		this.setOnAction(e -> {this.setText("selected");});
	}
	public GMenuButton add(String name, EventHandler<ActionEvent> event){
		MenuItem mi = new MenuItem(name);
		//mi.setOnAction(event);
		mi.addEventHandler(ActionEvent.ACTION, event);
		mi.addEventHandler(ActionEvent.ACTION, e -> {this.setText(name);});
		this.getItems().add(mi);
		return this;
	}
}