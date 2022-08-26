[![Coffee IT - Android Aroma EmojiPicker Component](https://coffeeit.nl/wp-content/uploads/2022/08/Aroma-emoji-picker-cover-android.png)](https://coffeeit.nl/)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/nl.coffeeit.aroma/emojipicker/badge.svg)](https://maven-badges.herokuapp.com/Maven-Central/nl.coffeeit.aroma/emojipicker)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/Coffee-IT-Development/EmojiPicker-Android-Component/blob/main/LICENSE)
[![Mirror Repository](https://img.shields.io/badge/Mirror-Repository-9b34eb?style=flat-square)](https://github.com/Coffee-IT-Development/EmojiPicker-Android-Component)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-@CoffeeIT-blue.svg?style=flat-square)](https://linkedin.com/company/coffee-it)
[![Facebook](https://img.shields.io/badge/Facebook-CoffeeITNL-blue.svg?style=flat-square)](https://www.facebook.com/CoffeeITNL/)
[![Instagram](https://img.shields.io/badge/Instagram-CoffeeITNL-blue.svg?style=flat-square)](https://www.instagram.com/coffeeitnl/)
[![Follow coffeeitnl on Twitter](https://img.shields.io/twitter/follow/coffeeitnl.svg?style=flat-square&logo=twitter)](https://twitter.com/coffeeitnl)

# ☕️ Android Aroma EmojiPicker

The Android Aroma EmojiPicker package provides an emojipicker with automatic day and night mode. This README describes how to implement the EmojiPicker into an app.

This GitHub repository is a mirror, the official repository is hosted privately by Coffee IT.

Created by [Coffee IT](https://coffeeit.nl/). Look at our other repositories on our
[GitHub account](https://github.com/orgs/Coffee-IT-Development/repositories).

<p float="left">
<img src="https://i.imgur.com/Sl2Ih6Z.gif" width="200">
&nbsp; &nbsp; &nbsp; &nbsp;
<img src="https://i.imgur.com/XipAihj.gif" width="200">
</p>

# ⚡ Installation

This component requires minimum SDK 21.

Add the following to `build.gradle`:
```
dependencies {
    implementation "nl.coffeeit.aroma:emojipicker:1.0.4"
}
```

# 📖 Usage

To use the EmojiPicker in a project, simply add it to your code like this:
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

# ⚙️ Customisation
__`EmojiPicker` uses the following color resources, which can be overwritten:__
```
<color name="emojiPickerBackground"></color>
<color name="emojiPickerBackgroundInput"></color>
<color name="emojiPickerCategoryIconTint"></color>
<color name="emojiPickerDivider"></color>
<color name="emojiPickerSearchIconTint"></color>
<color name="emojiPickerSelectedCategoryIconTint"></color>
<color name="emojiPickerText"></color>
```

# 🌎 Multi-language 

The EmojiPicker supports 39 languages: Arabic, Catalan, Czech, Danish,
German, Greek, English, Australian English, British English, Spanish, Latin, Finnish, French, Canadian,
Hebrew, Hindi, Croatian, Hungarian, Indonesian, Italian, Japanese, Korean, Malay, Norwegian, Dutch,
Polish, Brazilian Portuguese, Portuguese, Romanian, Russian, Slovak, Swedish, Thai, Turkish,
Ukrainian, Vietnamese, Simplified Chinese, Traditional Chinese and Hong Kong Chinese.
Searching for an emoji can only be done in the English language.

# ✏️ Changelog

The changelog can be found [here](https://github.com/Coffee-IT-Development/EmojiPicker-Android-Component/blob/main/CHANGELOG).

# 📧 Contact
Do you have questions, ideas or need help? Send us an email at contact@coffeeit.nl.

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://global-uploads.webflow.com/605a171ee93af49275331843/623b23cdea80a92703e61b42_Logo_black_1.svg" width="100">
  <source media="(prefers-color-scheme: light)" srcset="https://coffeeit.nl/wp-content/uploads/2016/09/logo_dark_small_new.png" width="100">
  <img alt="The Coffee IT logo" src="https://coffeeit.nl/wp-content/uploads/2016/09/logo_dark_small_new.png">
</picture>

# ⚠️ License
Android Aroma EmojiPicker is licensed under the terms of the [MIT Open Source license](https://github.com/Coffee-IT-Development/EmojiPicker-Android-Component/blob/main/LICENSE).