package com.test.projecttest.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.technews.ui.recyclerview.adapter.EventAdapter
import com.test.projecttest.R
import com.test.projecttest.model.EventItem
import com.test.projecttest.repository.EventRepository
import com.test.projecttest.ui.activity.extension.showError
import com.test.projecttest.ui.viewmodel.EventViewModel
import com.test.projecttest.ui.viewmodel.factory.EventViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loading_view.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy {
        EventAdapter(context = this)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, EventViewModelFactory(EventRepository())).get(EventViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        getEvents()
    }

    private fun getEvents() {
        loader(50)
        viewModel.getEvents().observe(this, Observer { resource ->
            resource.data?.let {
                loader(100)
                adapter.update(it) }
            resource.error?.let {
                loader(100)
                showError(it)
            }
        })
    }

    private fun loader(progress: Int){
        lpIndicator.setProgressCompat(progress, true)
    }

    private fun setRecyclerView() {
        val divisor = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        rvListagem.addItemDecoration(divisor)
        rvListagem.adapter = adapter
        setAdapter()
    }

    private fun setAdapter() {
        adapter.itemClick = this::openItem
    }

    private fun openItem(it: EventItem) {
        val intent = Intent(this, DetailItemActivity::class.java)
        intent.putExtra("idEvent", it.id)
        startActivity(intent)
    }

}