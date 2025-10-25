package pe.com.master.machines.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import pe.com.master.machines.data.repository.database.StoryCharacterLocalDataRepository
import pe.com.master.machines.data.repository.preferences.PreferencesDataRepository
import pe.com.master.machines.data.repository.remote.StoryCharacterRemoteDataRepository
import pe.com.master.machines.data.repositoryImpl.local.database.StoryCharacterLocalDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.local.preferences.PreferencesDataRepositoryImpl
import pe.com.master.machines.data.repositoryImpl.remote.StoryCharacterRemoteDataRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun provideStoryCharacterDataRepository(impl: StoryCharacterRemoteDataRepositoryImpl): StoryCharacterRemoteDataRepository

    @Binds
    abstract fun provideStoryCharacterLocalDataRepository(impl: StoryCharacterLocalDataRepositoryImpl): StoryCharacterLocalDataRepository

    @Binds
    abstract fun providePreferencesDataRepository(impl: PreferencesDataRepositoryImpl): PreferencesDataRepository

}