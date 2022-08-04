# Android Aroma EmojiPicker

This document describes how to implement the Aroma EmojiPicker component into an app. 
The EmojiPicker has an automatic day and night mode and supports 39 languages.
Searching for an emoji can only be done in the English language.

# Installation

This component requires minimum SDK 21.

Add the following to `build.gradle`:
```
dependencies {
    implementation "nl.coffeeit.aroma:emojipicker:1.0.0"
}
```

# Usage

To use the EmojiPicker, simply add it to your code as:
```
val emojiBottomSheetDialogFragment = EmojiBottomSheet.newInstance({ emoji ->
        // Handle emoji click
    })
emojiBottomSheetDialogFragment.show(supportFragmentManager, EmojiBottomSheet.TAG)
```

Make sure to add the following in the `AndroidManifest.xml` as an activity tag attribute of the 
Activity you're using the EmojiPicker in `android:configChanges="screenLayout|orientation|screenSize"`.
This makes sure the EmojiPicker keeps working when the configuration changes when it's opened. 

EmojiPicker also contains an extension method to convert unicode back into an emoji. Example:
The unicode for the Welsh flag emoji (󠁢󠁷󠁬󠁳󠁢󠁷󠁬󠁳󠁢󠁷󠁬󠁳🏴󠁧󠁢󠁷󠁬󠁳󠁿) is `0x1F3F40xE00670xE00620xE00770xE006C0xE00730xE007F` and when using the `toEmoji` 
method on that String, it's converted to 󠁢󠁷󠁬󠁳󠁢󠁷󠁬󠁳󠁢󠁷󠁬󠁳🏴󠁧󠁢󠁷󠁬󠁳󠁿 again. It can be used as follows: 
`"0x1F3F40xE00670xE00620xE00770xE006C0xE00730xE007F".toEmoji()` or `emoji.unicode.toEmoji()`.

# Change log

Version 1.0.0 *(08-07-2022)*
----------------------------
* Added EmojiPicker

Version 1.0.1 *(14-07-2022)*
----------------------------
* Removed automatic refresh of recents. Recents update when the EmojiPicker is opened.

Version 1.0.2 *(03-08-2022)*
----------------------------
* Made unicode field support mutliple unicodes.

Version 1.0.3 *(04-08-2022)*
----------------------------
* Improved support for mutliple unicodes.
* Added extension method `toEmoji()` to convert unicode to an emoji.