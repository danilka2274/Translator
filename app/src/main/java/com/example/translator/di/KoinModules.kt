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
import com.example.translator.utils.network.OnlineLiveData
import com.example.translator.view.descriptionscreen.DescriptionInteractor
import com.example.translator.view.descriptionscreen.DescriptionViewModel
import com.example.translator.view.history.HistoryInteractor
import com.example.translator.view.history.HistoryViewModel
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(historyDao = get()))
    }
    single { OnlineLiveData(context = get()) }
}

val mainScreen = module {
    viewModel { MainViewModel(interactor = get()) }
    factory { MainInteractor(repositoryRemote = get(), repositoryLocal = get()) }
}

val historyScreen = module {
    viewModel { HistoryViewModel(interactor = get()) }
    factory { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
}

val descriptionScreen = module {
    viewModel { DescriptionViewModel(interactor = get()) }
    factory { DescriptionInteractor(repositoryRemote = get(), repositoryLocal = get()) }
}