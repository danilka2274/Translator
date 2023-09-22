package com.example.translator.view.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import com.example.translator.R
import com.example.translator.databinding.LoadingLayoutBinding
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.utils.network.OnlineLiveData
import com.example.translator.utils.ui.AlertDialogFragment
import com.example.translator.viewmodel.BaseViewModel
import com.example.translator.viewmodel.Interactor

abstract class BaseActivity<T : AppState, I : Interactor<T>> : AppCompatActivity() {

    private lateinit var binding: LoadingLayoutBinding
    abstract val model: BaseViewModel<T>
    private val onlineLiveData: OnlineLiveData by inject()
    protected var isNetworkAvailable: Boolean = true
    private val snackbarNoNetwork: Snackbar by lazy {
        val mainView = findViewById<View>(android.R.id.content)
        return@lazy Snackbar.make(mainView,
            resources.getString(R.string.dialog_message_device_is_offline),
            Snackbar.LENGTH_INDEFINITE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToNetworkChange()
    }

    private fun subscribeToNetworkChange() {
        onlineLiveData.observe(
            this,
            {
                isNetworkAvailable = it
                when (isNetworkAvailable) {
                    false -> snackbarNoNetwork.show()
                    true -> snackbarNoNetwork.dismiss()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        binding = LoadingLayoutBinding.inflate(layoutInflater)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToView(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = View.VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToView(data: List<DataModel>)

    companion object {
        const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}