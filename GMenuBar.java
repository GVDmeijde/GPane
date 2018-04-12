package userinterface;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class GMenuBar extends MenuBar{
	ArrayList<MenuButton> buttons = new ArrayList<>();
	MenuBar menuBar;
	
	public GMenuBar(BorderPane window) {
		menuBar = new MenuBar();		
		window.setTop(menuBar);
	}
	/**
	 * Add menu button to top menu.
	 * @param text, text shown on button.
	 * @return the new MenuButton.
	 */
	public MenuButton addButton(String text){
		MenuButton mb = new MenuButton(text);
		buttons.add(mb);
		menuBar.getMenus().add(mb.getInnerButton());
		return mb;
	}
	/**
	 * Add menu button with event to top menu.
	 * @param text, text shown on button.
	 * @param event, event triggered when button is pressed.
	 * @return the new top menu.
	 */
	public GMenuBar addButton(String text, EventHandler<ActionEvent> event){
		MenuButton mb = new MenuButton(text, event);
		menuBar.getMenus().add(mb.getInnerButton());
		return this;
	}
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	/**
	 * This function returns the (sub-)button with the given text.
	 * @param text, the text on the menu button.
	 * @return MenuDropdownButton.
	 */
	public MenuButton getButton(String text) {
		MenuButton mb = null;
		for(MenuButton m : buttons) {
			mb = m.getButton(text); 
			if(mb != null)
				return mb;
		}
		return null;	
	}
	
	/**
	 * Helper class for menu (dropdown)buttons.
	 * @author Gijs van der Meijde
	 */
	public class MenuButton extends Menu{
		ArrayList<MenuButton> items = new ArrayList<>();
		MenuButton parent = null;
		Menu button;
		/**
		 * Constructor for dropdown button.
		 * @param text, text shown on button.
		 */
		public MenuButton(String text) {
			button = new Menu(text);
			buttons.add(this);
		}
		/**
		 * Constructor for menu button with event.
		 * @param text, text shown on button.
		 * @param event, event triggered when button is pressed.
		 */
		public MenuButton(String text, EventHandler<ActionEvent> event){
			button = new Menu(text);
			if(event != null)
				button.setOnAction(event);
			buttons.add(this);
		}
		
		/**
		 * This function returns the sub-button with the given text.
		 * @param text, the text on the menu button.
		 * @return MenuDropdownButton.
		 */
		public MenuButton getButton(String text) {
			if(button.getText().equals(text))
				return this;
			for(MenuButton mb : items) {
				MenuButton toReturn = mb.getButton(text);
				if(toReturn != null)
					return mb;
			}
			return null;
		}
		
		/**
		 * This function adds a sub button with an event.
		 * @param text, the text on the menu button. 
		 * @param event, event triggered when button is pressed.
		 * @return the current button.
		 */
		public MenuButton addButton(String text, EventHandler<ActionEvent> event) {
			MenuItem mi = new MenuItem(text);
			mi.setOnAction(event);
			return addButton(mi);
		}
		
		/**
		 * This function adds a sub button with an event and a style.
		 * @param text, the text on the menu button. 
		 * @param event, event triggered when button is pressed.
		 * @param style, the javaFX style for the inner MenuItem.
		 * @return the current button.
		 */
		public MenuButton addButton(String text, EventHandler<ActionEvent> event, String style) {
			MenuItem mi = new MenuItem(text);
			mi.setOnAction(event);
			mi.setStyle(style);
			return addButton(mi);
		}
		
		/**
		 * This function adds a sub menuItem with an event and a style.
		 * @param menuItem, the menuItem to add to the Menu. 
		 * @param event, event triggered when button is pressed.
		 * @param style, the javaFX style for the inner MenuItem.
		 * @return the current button.
		 */
		public MenuButton addMenuItem(MenuItem menuItem, EventHandler<ActionEvent> event, String style) {
			MenuItem mi = menuItem;
			mi.setOnAction(event);
			mi.setStyle(style);
			return addButton(mi);
		};
		
		/**
		 * This function adds a sub button without an event.
		 * @param text, the text on the menu button. 
		 * @return the new sub button.
		 */
		public MenuButton addButton(String text) {
			items.add(new MenuButton(text));
			button.getItems().add(items.get(items.size()-1).getInnerButton());
			items.get(items.size()-1).setParent(this);
			return items.get(items.size()-1);
		}
		
		/**
		 * This function adds a menuItem as a sub-button.
		 * @param menuItem, the menuItem we want to add.
		 * @return the current button.
		 */
		private MenuButton addButton(MenuItem menuItem) {
			button.getItems().add(menuItem);
			return this;
		}
		
		/**
		 * This function returns the inner javafx Menu.
		 * @return
		 */
		public Menu getInnerButton() {
			return button;
		}
		
		public MenuButton getParent(){
			return parent;
		}
		public MenuButton setParent(MenuButton parent){
			this.parent = parent;
			return this;
		}
	}
}