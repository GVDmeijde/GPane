# GPane
This class enables cleaner code when defining Panes. It allows for a more C# way of programming by linking multiple 'void' functions to eachoter (e.g.: GPane.gSetWidth(2).gSetHeight(2).gApplyCss(); )
Unfortunately overwriting the original functions prevents us from changing the return type so a new functions are created.
Every function in the Pane class that can returns a void can be called with a g in front of it's name to return the GPane object. e.g.:  "public void autoSize()"  is also implemented as  "public GPane gAutoSize()".

##Extra functionalities:
- Reimplemented all void functions with a 'g' function to return the current instance instead of void.
- Added Scene as variable (for scaling).
- Added new constructor that automatically inherit's the parent's Scene.
- Added gScaleToParent function that scales the GPane to it's parent Panel.
- Added gSetOnResizeEvent function to trigger a Runnable when the scene is resized.
- Added gSetOnSwipe function to trigger an event if a swipe in any direction is detected.
- Added gSetScale to set all scales at once.
- Added gResetScale to set all scales to 1.
- Added gSetTranslate to set all translates at once.
- Added gResetTranslate to reset the translations.


##Future work:
This is only a very first version of the GPane. I am planning to extend more classes in the Pane hierarchie (like Region) and spread the new functions over these classes instead of having them all in one class.

##Support:
For Suggestions or Issues you can contact me at Info@kingtech.nl
  (All rights reserved).
