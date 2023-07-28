package com.example.translator.di

import dagger.Module
import dagger.Provides
import com.example.translator.model.data.DataModel
import com.example.translator.model.repository.Repository
import com.example.translator.view.main.MainInteractor
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}