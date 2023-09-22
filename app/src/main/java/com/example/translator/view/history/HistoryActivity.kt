package com.example.translator.view.history

import android.os.Bundle
import com.example.translator.databinding.ActivityHistoryBinding
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.view.base.BaseActivity
import com.example.translator.view.descriptionscreen.DescriptionActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    override val model: HistoryViewModel by scope.inject()
    private lateinit var binding: ActivityHistoryBinding
    private val adapter: HistoryAdapter by lazy { HistoryAdapter(onListItemClickListener) }
    private val onListItemClickListener: HistoryAdapter.OnListItemClickListener =
        object : HistoryAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@HistoryActivity,
                        data.text!!
                    )
                )
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToView(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        model.subscribe().observe(this, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}