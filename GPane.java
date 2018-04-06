package UserInterface;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.event.Event;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Point3D;
import javafx.geometry.VPos;
import javafx.scene.AccessibleAttribute;
import javafx.scene.AccessibleRole;
import javafx.scene.CacheHint;
import javafx.scene.Cursor;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.InputMethodRequests;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.util.Callback;

/**
 * This class enables cleaner code when defining Panes. It allows for a more C# way of programming by linking multiple 'void' functions to eachoter (e.g.: GPane.gSetWidth(2).gSetHeight(2).gApplyCss(); )
 * Unfortunately overwriting the original functions prevents us from changing the return type so a new functions are created.
 * Every function in the Pane class that can returns a void can be called with a g in front of it's name to return the GPane object. e.g.:  "public void autoSize()"  is also implemented as  "public GPane gAutoSize()".
 * 
 * @TODO: Split class in GPane, GRegion, e.t.c.
 * 
 * @author Gijs van der Meijde
 * @author KingTech
 * @version 1.0
 * @since 06-04-2018
 */
public class GPane extends Pane{
	private Scene scene;
	private double translateX =0, translateY = 0, translateZ=0;
	
	public GPane(Scene scene) {
		this.scene = scene;
	}
	
	public GPane() {
		this.scene = this.getScene();
	}
	
	
	public GPane gSetPrefProperty(DoubleBinding widthProperty, DoubleBinding heightProperty) {
		this.prefWidthProperty().bind(widthProperty);
	    this.prefHeightProperty().bind(heightProperty);
	    return this;
	}

	
	public Scene gGetScene() {
		if(scene == null)
			scene = super.getScene();
		if(scene == null && this.getParent() != null)
			scene = this.getParent().getScene();
		return scene;
	}
	
	/**
	 * This function allows the GPane to scale with its parent Pane.
	 * @param parent, the Pane this GPane needs to scale with.
	 * @return
	 */
	public GPane gScaleToParent(Pane parent) {
		gScaleToParent(parent, 0, 0);
	    return this;
	}
	
	/**
	 * This function allows the GPane to scale with its parent Pane.
	 * @param parent, the Pane this GPane needs to scale with.
	 * @param widthOffset, offset on the width.
	 * @param heightOffset, offset on the height.
	 * @return
	 */
	public GPane gScaleToParent(Pane parent, double widthOffset, double heightOffset) {
		this.prefWidthProperty().bind(parent.widthProperty().add(widthOffset));
	    this.prefHeightProperty().bind(parent.heightProperty().add(heightOffset));
	    return this;
	}
	
	/**
	 * This function sets triggers a given function (Runnable) when the scene is resized.
	 * @param event
	 * @return
	 */
	public boolean gSetOnResizeEvent(Runnable event) {
		if(this.getScene() != null) {
			scene.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					event.run();
				}
			});
			scene.heightProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					event.run();
				}
			});
			return true;
		}
		return false;
	}
	
	
	/*------------------------------------------------------------------*/
	/*------------------------------------------------------------------*/
	/*---------------------- Overruled functions: ----------------------*/
	/*------------------------------------------------------------------*/
	/*------------------------------------------------------------------*/
	
	public GPane gSetPrefSize(double prefWidth, double prefHeight) {
		super.setPrefSize(prefWidth, prefHeight);
		return this;
	}
	
	public GPane gSetMinSize(double minWidth, double minHeight) {
		super.setMinSize(minWidth, minHeight);
		return this;
	}
	
	public GPane gSetLayoutX(double layoutX) {
		super.setLayoutX(layoutX);
		return this;
	}
	
	public GPane gSetLayoutY(double layoutY) {
		super.setLayoutY(layoutY);
		return this;
	}
	
	public GPane gSetBackground(Background value){
		super.setBackground(value);
		return this;
	}
	
	public GPane gApplyCss() {
		super.applyCss();
		return this;
	}
	
	public GPane gAutosize() {
		super.autosize();
		return this;
	}
	
	public GPane gFinalize() throws Throwable {
		super.finalize();
		return this;
	}
	
	public GPane gFireEvent(Event event) {
		super.fireEvent(event);
		return this;
	}
	
	public GPane gLayout() {
		super.layout();
		return this;
	}
	
	public GPane gLayoutChildren() {
		this.layoutChildren();
		return this;
	}
	
	public GPane gLayoutInArea(Node child,double areaX,double areaY,double areaWidth,double areaHeight,double areaBaselineOffset,Insets margin,boolean fillWidth,boolean fillHeight,HPos halignment,VPos valignment) {
		super.layoutInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, margin, fillWidth, fillHeight, halignment, valignment);
		return this;
	}
	
	public GPane gLayoutInArea(Node child,double areaX,double areaY,double areaWidth,double areaHeight,double areaBaselineOffset,Insets margin,HPos halignment,VPos valignment) {
		super.layoutInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, margin, halignment, valignment);
		return this;
	}
	
	public GPane gLayoutInArea(Node child,double areaX,double areaY,double areaWidth,double areaHeight,double areaBaselineOffset,HPos halignment,VPos valignment) {
		super.layoutInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, halignment, valignment);
		return this;
	}
	
	public GPane gNotify() {
		super.notify();
		return this;
	}
	
	public GPane gNotifyAccessibleAttributeChanged(AccessibleAttribute attributes){
		super.notifyAccessibleAttributeChanged(attributes);
		return this;
	}
	
	public GPane gNotifyAll() {
		super.notifyAll();
		return this;
	}
	
	public GPane gPositionInArea(Node child, double areaX, double areaY, double areaWidth, double areaHeight, double areaBaselineOffset, Insets margin, HPos halignment, VPos valignment, boolean isSnapToPixel) {
		super.positionInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, margin, halignment, valignment, isSnapToPixel);
		return this;
	}
	
	public GPane gPositionInArea(Node child, double areaX, double areaY, double areaWidth, double areaHeight, double areaBaselineOffset, HPos halignment, VPos valignment) {
		super.positionInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, halignment, valignment);
		return this;
	}
	
	public GPane gPseudoClassStateChanged(PseudoClass pseudoClass, boolean active) {
		super.pseudoClassStateChanged(pseudoClass, active);
		return this;
	}
	
	public GPane gRelocate(double x, double y) {
		super.relocate(x, y);
		return this;
	}
	
	public GPane gSnapshot(Callback<SnapshotResult, Void> callback, SnapshotParameters params, WritableImage image) {
		super.snapshot(callback, params, image);
		return this;
	}
	
	public GPane gRequestFocus() {
		super.requestFocus();
		return this;
	}
	
	public GPane gRequestLayout() {
		super.requestLayout();
		return this;
	}
	
	public GPane gRequestParentLayout() {
		super.requestParentLayout();
		return this;
	}
	
	public GPane gStartFullDrag() {
		super.startFullDrag();
		return this;
	}
	
	public GPane gToBack() {
		super.toBack();
		return this;
	}
	
	public GPane gToFront() {
		super.toFront();
		return this;
	}
	
	public GPane gUpdateBounds() {
		super.updateBounds();
		return this;
	}
	
	public GPane gWait() throws InterruptedException {
		super.wait();
		return this;
	}
	
	public GPane gWait(long timeout, int nanos) throws InterruptedException {
		super.wait(timeout, nanos);
		return this;
	}
	
	public GPane gWait(long timeout) throws InterruptedException {
		super.wait(timeout);
		return this;
	}
	
	protected GPane gLayoutInArea(Node child,double areaX,double areaY,double areaWidth,double areaHeight,double areaBaselineOffset,Insets margin,boolean fillWidth,boolean fillHeight,HPos halignment,VPos valignment,boolean isSnapToPixel) {
		super.layoutInArea(child, areaX, areaY, areaWidth, areaHeight, areaBaselineOffset, margin, fillWidth, fillHeight, halignment, valignment);
		return this;
	}
	
	public GPane gSetAccessibleHelp(String value) {
		super.setAccessibleHelp(value);
		return this;
	}
	
	public GPane gSetAccessibleRole(AccessibleRole value) {
		super.setAccessibleRole(value);
		return this;
	}
	
	public GPane gSetAccessibleRoleDescription(String value) {
		super.setAccessibleRoleDescription(value);
		return this;
	}
	
	public GPane gSetAccessibleText(String value) {
		super.setAccessibleText(value);
		return this;
	}
	
	public GPane getBlendMode(BlendMode value) {
		super.setBlendMode(value);
		return this;
	}
	
	public GPane gSetBorder(Border value) {
		super.setBorder(value);
		return this;
	}
	
	public GPane gSetCache(boolean value) {
		super.setCache(value);
		return this;
	}
	
	public GPane gSetCacheHint(CacheHint value) {
		super.setCacheHint(value);
		return this;
	}
	
	public GPane gSetCacheShape(boolean value) {
		super.setCacheShape(value);
		return this;
	}
	
	public GPane gSetCenterShape(boolean value) {
		super.setCenterShape(value);
		return this;
	}
	
	public GPane gSetClip(Node value) {
		super.setClip(value);
		return this;
	}
	
	public GPane gSetCursor(Cursor value) {
		super.setCursor(value);
		return this;
	}
	
	public GPane gSetDepthTest(DepthTest value) {
		super.setDepthTest(value);
		return this;
	}
	
	public GPane gSetDisable(boolean value) {
		super.setDisable(value);
		return this;
	}
	
	protected GPane gSetDisabled(boolean value) {
		super.setDisabled(value);
		return this;
	}
	
	public GPane gSetEffect(Effect value) {
		super.setEffect(value);
		return this;
	}
	
	public GPane gSetEventDispatcher(EventDispatcher value) {
		super.setEventDispatcher(value);
		return this;
	}
	
	protected GPane gSetEventHandler(EventType eventType, EventHandler<?> eventHandler) {
		super.setEventHandler(eventType, eventHandler);
		return this;
	}
	
	protected GPane gSetFocused(boolean value) {
		super.setFocused(value);
		return this;
	}
	
	public GPane gSetFocusTraverable(boolean value) {
		super.setFocusTraversable(value);
		return this;
	}
	
	protected GPane gSetHeight(double value) {
		super.setHeight(value);
		return this;
	}
	
	public GPane gSetHover(boolean value) {
		super.setHover(value);
		return this;
	}
	
	public GPane gSetId(String value) {
		super.setId(value);
		return this;
	}
	
	public GPane gSetInputMethodRequest(InputMethodRequests value) {
		super.setInputMethodRequests(value);
		return this;
	}
	
	public GPane gSetManaged(boolean value) {
		super.setManaged(value);
		return this;
	}
	
	public GPane gSetMinHeight(double value) {
		super.setMinHeight(value);
		return this;
	}
	
	public GPane gSetMinWidth(double minWidth) {
		super.setMinWidth(minWidth);
		return this;
	}
	
	public GPane gSetMaxHeight(double value) {
		super.setMaxHeight(value);
		return this;
	}
	
	public GPane gSetMaxWidth(double value) {
		super.setMaxWidth(value);
		return this;
	}
	
	public GPane gSetMaxSize(double maxWidth, double maxHeight) {
		super.setMaxSize(maxWidth, maxHeight);
		return this;
	}
	
	public GPane gSetMouseTransparent(boolean value) {
		super.setMouseTransparent(value);
		return this;
	}
	
	protected GPane gSetNeedsLayout(boolean value) {
		super.setNeedsLayout(value);
		return this;
	}
	
	public GPane gSetNodeOrientation(NodeOrientation orientation) {
		super.setNodeOrientation(orientation);
		return this;
	}
	
	public GPane gSetOnContextMenuRequested(EventHandler<? super ContextMenuEvent> value) {
		super.setOnContextMenuRequested(value);
		return this;
	}
	
	public GPane gSetOnDragDetected(EventHandler<? super MouseEvent> value) {
		super.setOnDragDetected(value);
		return this;
	}
	
	public GPane gSetOnDragDone(EventHandler <? super DragEvent> value) {
		super.setOnDragDone(value);
		return this;
	}
	
	public GPane gSetOnDragDropped(EventHandler <? super DragEvent> value) {
		super.setOnDragDropped(value);
		return this;
	}
	
	public GPane gSetOnDragEntered(EventHandler <? super DragEvent> value) {
		super.setOnDragEntered(value);
		return this;
	}
	
	public GPane gSetOnDragExited(EventHandler <? super DragEvent> value) {
		super.setOnDragExited(value);
		return this;
	}
	
	public GPane gSetOnDragOver(EventHandler <? super DragEvent> value) {
		super.setOnDragOver(value);
		return this;
	}
	
	public GPane gSetOnInputMethodTextChanged(EventHandler<? super InputMethodEvent> value) {
		super.setOnInputMethodTextChanged(value);
		return this;
	}
	
	public GPane gSetOnKeyPressed(EventHandler<? super KeyEvent> value) {
		super.setOnKeyPressed(value);
		return this;
	}
	
	public GPane gSetOnKeyReleased(EventHandler<? super KeyEvent> value) {
		super.setOnKeyReleased(value);
		return this;
	}
	
	public GPane gSetOnKeyTyped(EventHandler<? super KeyEvent> value) {
		super.setOnKeyTyped(value);
		return this;
	}
	
	public GPane gSetOnMouseClicked(EventHandler<? super MouseEvent> value) {
		super.setOnMouseClicked(value);
		return this;
	}
	
	public GPane gSetOnMouseDragEntered(EventHandler<? super MouseEvent> value) {
		super.setOnMouseDragEntered(value);
		return this;
	}
	
	public GPane gSetOnMouseDragExited(EventHandler<? super MouseEvent> value) {
		super.setOnMouseDragExited(value);
		return this;
	}
	
	public GPane gSetOnMouseDragged(EventHandler<? super MouseEvent> value) {
		super.setOnMouseDragged(value);
		return this;
	}
	
	public GPane gSetOnMouseDragOver(EventHandler<? super MouseEvent> value) {
		super.setOnMouseDragOver(value);
		return this;
	}
	
	public GPane gSetOnMouseEntered(EventHandler<? super MouseEvent> value) {
		super.setOnMouseEntered(value);
		return this;
	}
	
	public GPane gSetOnMouseExited(EventHandler<? super MouseEvent> value) {
		super.setOnMouseExited(value);
		return this;
	}
	
	public GPane gSetOnMouseMoved(EventHandler<? super MouseEvent> value) {
		super.setOnMouseMoved(value);
		return this;
	}
	
	public GPane gSetOnMousePressed(EventHandler<? super MouseEvent> value) {
		super.setOnMousePressed(value);
		return this;
	}
	
	public GPane gSetOnMouseReleased(EventHandler<? super MouseEvent> value) {
		super.setOnMouseReleased(value);
		return this;
	}
	
	public GPane gSetOnRotate(EventHandler<? super RotateEvent> value) {
		super.setOnRotate(value);
		return this;
	}
	
	public GPane gSetOnRotationFinished(EventHandler<? super RotateEvent> value) {
		super.setOnRotationFinished(value);
		return this;
	}
	
	public GPane gSetOnRotationStarted(EventHandler<? super RotateEvent> value) {
		super.setOnRotationStarted(value);
		return this;
	}
	
	public GPane gSetOnScroll(EventHandler<? super ScrollEvent> value) {
		super.setOnScroll(value);
		return this;
	}
	
	public GPane gSetOnScrollFinished(EventHandler<? super ScrollEvent> value) {
		super.setOnScrollFinished(value);
		return this;
	}
	
	public GPane gSetOnScrollStarted(EventHandler<? super ScrollEvent> value) {
		super.setOnScrollStarted(value);
		return this;
	}
	
	public GPane gSetOnSwipe(EventHandler<? super SwipeEvent> value) {
		super.setOnSwipeDown(value);
		super.setOnSwipeLeft(value);
		super.setOnSwipeRight(value);
		super.setOnSwipeUp(value);
		return this;
	}
	
	public GPane gSetOnSwipeDown(EventHandler<? super SwipeEvent> value) {
		super.setOnSwipeDown(value);
		return this;
	}
	
	public GPane gSetOnSwipeLeft(EventHandler<? super SwipeEvent> value) {
		super.setOnSwipeLeft(value);
		return this;
	}
	
	public GPane gSetOnSwipeRight(EventHandler<? super SwipeEvent> value) {
		super.setOnSwipeRight(value);
		return this;
	}
	
	public GPane gSetOnSwipeUp(EventHandler<? super SwipeEvent> value) {
		super.setOnSwipeUp(value);
		return this;
	}
	
	public GPane gSetOnTouchMoved(EventHandler<? super TouchEvent> value) {
		super.setOnTouchMoved(value);
		return this;
	}
	
	public GPane gSetOnTouchPressed(EventHandler<? super TouchEvent> value) {
		super.setOnTouchPressed(value);
		return this;
	}
	
	public GPane gSetOnTouchReleased(EventHandler<? super TouchEvent> value) {
		super.setOnTouchReleased(value);
		return this;
	}
	
	public GPane gSetOnTouchStationary(EventHandler<? super TouchEvent> value) {
		super.setOnTouchStationary(value);
		return this;
	}
	
	public GPane gSetOnZoom(EventHandler<? super ZoomEvent> value) {
		super.setOnZoom(value);
		return this;
	}
	
	public GPane gSetOnZoomFinished(EventHandler<? super ZoomEvent> value) {
		super.setOnZoomFinished(value);
		return this;
	}
	
	public GPane gSetOnZoomStarted(EventHandler<? super ZoomEvent> value) {
		super.setOnZoomStarted(value);
		return this;
	}
	
	public GPane gSetOpacity(double value) {
		super.setOpacity(value);
		return this;
	}
	
	public GPane gSetOpaqueInsets(Insets value) {
		super.setOpaqueInsets(value);
		return this;
	}
	
	public GPane gSetPadding(Insets value) {
		super.setPadding(value);
		return this;
	}
	
	public GPane gSetPickOnBounds(boolean value) {
		super.setPickOnBounds(value);
		return this;
	}
	
	public GPane gSetPrefHeight(double value) {
		super.setPrefHeight(value);
		return this;
	}
	
	public GPane gSetPrefWidth(double value) {
		super.setPrefWidth(value);
		return this;
	}
	
	protected GPane gSetPressed(boolean value) {
		super.setPressed(value);
		return this;
	}
	
	public GPane gSetRotate(double value) {
		super.setRotate(value);
		return this;
	}
	
	public GPane gSetRotationAxis(Point3D value) {
		super.setRotationAxis(value);
		return this;
	}
	
	public GPane gSetScaleShape(boolean value) {
		super.setScaleShape(value);
		return this;
	}
	
	public GPane gSetScaleX(double value) {
		super.setScaleX(value);
		return this;
	}
	
	public GPane gSetScaleY(double value) {
		super.setScaleY(value);
		return this;
	}
	
	public GPane gSetScaleZ(double value) {
		super.setScaleZ(value);
		return this;
	}
	
	public GPane gSetScale(double x, double y, double z) {
		super.setScaleX(x);
		super.setScaleY(y);
		super.setScaleZ(z);
		return this;
	}
	
	public GPane gResetScale() {
		super.setScaleX(1);
		super.setScaleY(1);
		super.setScaleZ(1);
		return this;
	}
	
	public GPane gSetShape(Shape value) {
		super.setShape(value);
		return this;
	}
	
	public GPane gSetSnapToPixel(boolean value) {
		super.setSnapToPixel(value);
		return this;
	}
	
	public GPane gSetStyle(String value) {
		super.setStyle(value);
		return this;
	}
	
	public GPane gSetTranslate(double x, double y, double z) {
		super.setTranslateX(x);
		super.setTranslateY(y);
		super.setTranslateZ(z);
		return this;
	}
	
	public void setTranslateX(int value) {
		
		super.setTranslateX(value);
	}
	
	public GPane gSetTranslateX(double x) {
		translateX += x;
		super.setTranslateX(x);
		return this;
	}
	
	public void setTranslateY(int value) {
		translateY += value;
		super.setTranslateY(value);
	}
	
	public GPane gSetTranslateY(double y) {
		translateY += y;
		super.setTranslateY(y);
		return this;
	}
	
	public void setTranslateZ(int value) {
		translateZ += value;
		super.setTranslateZ(value);
	}
	
	public GPane gSetTranslateZ(double z) {
		translateZ += z;
		this.setTranslateZ(z);
		return this;
	}
	
	public GPane gResetTranslateX() {
		super.setTranslateX(- translateX);
		translateX = 0;
		return this;
	}
	
	public GPane gResetTranslateY() {
		super.setTranslateY(- translateY);
		translateY = 0;
		return this;
	}
	
	public GPane gResetTranslateZ() {
		super.setTranslateZ(- translateZ);
		translateZ = 0;
		return this;
	}
	
	public GPane gResetTranslate() {
		this.gResetTranslateX();
		this.gResetTranslateY();
		this.gResetTranslateZ();
		return this;
	}
	
	public GPane gSetUserData(Object value) {
		super.setUserData(value);
		return this;
	}
	
	public GPane gSetVisible(boolean value) {
		super.setVisible(value);
		return this;
	}
	
	protected GPane gSetWidth(double value) {
		super.setWidth(value);
		return this;
	}
}
