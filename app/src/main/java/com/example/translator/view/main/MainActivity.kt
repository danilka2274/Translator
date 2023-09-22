package com.example.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.R
import com.example.translator.model.data.AppState
import com.example.translator.model.data.DataModel
import com.example.translator.utils.ui.viewById
import com.example.translator.view.base.BaseActivity
import com.example.translator.view.descriptionscreen.DescriptionActivity
import com.example.translator.view.history.HistoryActivity
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class MainActivity : BaseActivity<AppState, MainInteractor>(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    override val model: MainViewModel by scope.inject()
    private val searchEditText by viewById<EditText>(R.id.search_edit_text)
    private val recyclerView by viewById<RecyclerView>(R.id.recycler_view)
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!
                    )
                )
            }
        }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (searchEditText.text != null && searchEditText.text.toString()
                    .isNotEmpty()
            ) {
                if (isNetworkAvailable) {
                    model.getData(searchEditText.text.toString(), true)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        model.subscribe().observe(this, { renderData(it) })
    }

    private fun initViews() {
        searchEditText.addTextChangedListener(textWatcher)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setDataToView(data: List<DataModel>) {
        adapter.setData(data)
    }
}