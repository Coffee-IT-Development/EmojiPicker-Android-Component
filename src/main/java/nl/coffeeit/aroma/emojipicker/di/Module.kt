package nl.coffeeit.aroma.emojipicker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewScoped
import nl.coffeeit.aroma.emojipicker.data.local.KEY_SHARED_PREFERENCES
import nl.coffeeit.aroma.emojipicker.data.local.SharedPreferencesHelper
import nl.coffeeit.aroma.emojipicker.domain.repository.EmojiRepository

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @ViewScoped
    @Provides
    fun provideEmojiRepository(): EmojiRepository = EmojiRepository()

    @ViewScoped
    @Provides
    fun provideSharedPreferencesHelper(context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(
            context.getSharedPreferences(
                KEY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE
            )
        )
    }
}