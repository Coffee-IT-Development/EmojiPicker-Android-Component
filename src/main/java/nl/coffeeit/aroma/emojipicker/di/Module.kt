package nl.coffeeit.aroma.emojipicker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewScoped
import nl.coffeeit.aroma.emojipicker.domain.repository.EmojiRepository

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @ViewScoped
    @Provides
    fun provideEmojiRepository(): EmojiRepository = EmojiRepository()
}