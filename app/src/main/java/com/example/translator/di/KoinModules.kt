package com.example.translator.di

import androidx.room.Room
import org.koin.dsl.module
import com.example.translator.model.data.DataModel
import com.example.translator.model.datasource.RetrofitImplementation
import com.example.translator.model.datasource.RoomDataBaseImplementation
import com.example.translator.model.repository.Repository
import com.example.translator.model.repository.RepositoryImplementation
import com.example.translator.model.repository.RepositoryImplementationLocal
import com.example.translator.model.repository.RepositoryLocal
import com.example.translator.room.HistoryDataBase
import com.example.translator.view.descriptionscreen.DescriptionActivity
import com.example.translator.view.descriptionscreen.DescriptionInteractor
import com.example.translator.view.descriptionscreen.DescriptionViewModel
import com.example.translator.view.history.HistoryActivity
import com.example.translator.view.history.HistoryInteractor
import com.example.translator.view.history.HistoryViewModel
import com.example.translator.view.main.MainActivity
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    scope<MainActivity> {
        viewModel { MainViewModel(interactor = get()) }
        scoped { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    }
}

val historyScreen = module {
    scope<HistoryActivity> {
        viewModel { HistoryViewModel(interactor = get()) }
        scoped { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    }
}

val descriptionScreen = module {
    scope<DescriptionActivity> {
        viewModel { DescriptionViewModel(interactor = get()) }
        scoped { DescriptionInteractor(repositoryRemote = get(), repositoryLocal = get()) }
    }
}