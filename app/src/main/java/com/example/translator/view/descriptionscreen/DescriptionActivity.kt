package com.example.translator.view.descriptionscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.core.BaseActivity
import com.example.model.AppState
import com.example.model.DataModel
import com.example.repository.convertMeaningsToString
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.translator.R
import com.example.translator.databinding.ActivityDescriptionBinding
import com.example.utils.network.isOnline
import com.example.utils.ui.AlertDialogFragment

class DescriptionActivity : BaseActivity<AppState, DescriptionInteractor>() {

    private lateinit var binding: ActivityDescriptionBinding
    override val model: DescriptionViewModel by viewModel()
    private var searchWord: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    override fun onResume() {
        super.onResume()
        model.subscribe().observe(this) { renderData(it) }
    }

    private fun initView() {
        setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        val bundle = intent.extras
        searchWord = bundle?.getString(WORD_EXTRA)
        binding.descriptionHeader.text = searchWord
        startLoadingOrShowError()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun setDataToView(data: List<DataModel>) {
        val dataEntity = data.filter { it.text == searchWord }
        binding.descriptionTextview.text = convertMeaningsToString(dataEntity[0].meanings!!)
        val imageLink = dataEntity[0].meanings?.get(0)?.imageUrl
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            useCoilToLoadPhoto(binding.descriptionImageview, imageLink)
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(applicationContext)) {
            searchWord?.let { model.getData(it, true) }
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                supportFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        imageView.load("https:$imageLink") {
            crossfade(true)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
            transformations(RoundedCornersTransformation(5.0F))
        }
    }

    companion object {
        private const val WORD_EXTRA = "key_word"

        fun getIntent(
            context: Context,
            word: String
        ): Intent = Intent(context, DescriptionActivity::class.java).apply {
            putExtra(WORD_EXTRA, word)
        }
    }
}