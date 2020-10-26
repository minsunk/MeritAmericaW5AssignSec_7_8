import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxDiagram extends GraphicsProgram {
	/*Private instance variable*/
	private HashMap<String, GObject> contents;
	private JTextField nameField;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private GObject currentObject;
	private GPoint last;
	
	/*Private constants*/
	private static final int MAX_NAME = 25;
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

	/*Initializes the program*/
	public void init() {
		contents = new HashMap<String, GObject>();
		createController();
		addActionListeners();
		addMouseListeners();
	}
	
	/*Creates the control strip at the bottom of the window*/
	private void createController() {
		nameField = new JTextField(MAX_NAME);
		nameField.addActionListener(this);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
	}
	
	/*Adds a box with the given name at the center of the window*/
	private void addBox(String name) {
		GCompound box = new GCompound();
		GRect outline = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name);
		box.add(outline, -BOX_WIDTH / 2, -BOX_HEIGHT/2);
		box.add(label, -label.getWidth() / 2, label.getAscent()/2);
		add(box, getWidth() /2, getHeight() /2);
		contents.put(name, box);
	}
	
	/* Remove the box with the given name*/
	private void removeBox(String name) {
		GObject obj = contents.get(name);
		if (obj != null) {
			remove(obj);
		}
	}
	
	/*Removes all boxes in the contents table */
	private void removeContents() {
		Iterator<String> iterator = contents.keySet().iterator();
		while (iterator.hasNext()) {
			removeBox(iterator.next());
		}
		contents.clear(); // Clear all entries in the hashmap
	}
	
	/* Called in response to button action */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addButton || source == nameField) {
			addBox(nameField.getText());
		} else if (source == removeButton) {
			removeBox(nameField.getText());
		} else if (source == clearButton) {
			removeContents();
		}
	}
	
	/* Called on mouse press to record the coordinates of the click */
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		currentObject = getElementAt(last);
	}
	
	/* Called on mouse drag to reposition the object */
	public void mouseDragged(MouseEvent e) {
		if (currentObject != null) {
			currentObject.move(e.getX() - last.getX(), e.getY() - last.getY());
			last = new GPoint(e.getPoint());
							
		}
	}
	

}
