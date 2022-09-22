# Change log

Version 1.0.6 *(22-09-2022)*
----------------------------
* Migrated from `compileSdk` and `targetSdk` 32 to 33.

Version 1.0.5 *(07-09-2022)*
----------------------------
* Removed build variants because it caused issues in other projects.

Version 1.0.4 *(22-08-2022)*
----------------------------
* Changed color names to the following so they are more easily overwritable:
```
<color name="emojiPickerBackground"></color>
<color name="emojiPickerBackgroundInput"></color>
<color name="emojiPickerCategoryIconTint"></color>
<color name="emojiPickerDivider"></color>
<color name="emojiPickerSearchIconTint"></color>
<color name="emojiPickerSelectedCategoryIconTint"></color>
<color name="emojiPickerText"></color>
```

Version 1.0.3 *(04-08-2022)*
----------------------------
* Improved support for mutliple unicodes.
* Added extension method `toEmoji()` to convert unicode to an emoji.

Version 1.0.2 *(03-08-2022)*
----------------------------
* Made unicode field support mutliple unicodes.

Version 1.0.1 *(14-07-2022)*
----------------------------
* Removed automatic refresh of recents. Recents update when the EmojiPicker is opened.

Version 1.0.0 *(08-07-2022)*
----------------------------
* Added EmojiPicker.