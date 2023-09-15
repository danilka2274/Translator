package com.example.translator.di

import androidx.room.Room
import com.example.model.DataModel
import com.example.repository.Repository
import com.example.repository.RepositoryImplementation
import com.example.repository.RepositoryImplementationLocal
import com.example.repository.RepositoryLocal
import com.example.repository.RetrofitImplementation
import com.example.repository.RoomDataBaseImplementation
import com.example.repository.room.HistoryDataBase
import org.koin.dsl.module
import com.example.translator.view.descriptionscreen.DescriptionInteractor
import com.example.translator.view.descriptionscreen.DescriptionViewModel
import com.example.translator.view.history.HistoryInteractor
import com.example.translator.view.history.HistoryViewModel
import com.example.translator.view.main.MainInteractor
import com.example.translator.view.main.MainViewModel

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImplementation(RetrofitImplementation()) }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplementationLocal(RoomDataBaseImplementation(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}

val descriptionScreen = module {
    factory { DescriptionViewModel(get()) }
    factory { DescriptionInteractor(get(), get()) }
}