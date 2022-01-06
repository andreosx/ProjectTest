package com.test.projecttest.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.projecttest.R
import com.test.projecttest.model.EventItem
import com.test.projecttest.repository.EventRepository
import com.test.projecttest.ui.activity.extension.loadImage
import com.test.projecttest.ui.activity.extension.showError
import com.test.projecttest.ui.activity.extension.toDateFormatted
import com.test.projecttest.ui.activity.extension.toMoneyFormat
import com.test.projecttest.ui.viewmodel.EventItemViewModel
import com.test.projecttest.ui.viewmodel.factory.EventItemViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_item.*
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.view_dialog.view.*

class DetailItemActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, EventItemViewModelFactory(EventRepository())).get(EventItemViewModel::class.java)
    }

    var idEvent:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_item)

        idEvent = intent.getIntExtra("idEvent",0)

        getEventItem(idEvent)
    }

    private fun getEventItem(idEvent: Int) {
        loader(50)
        viewModel.getEventItem(idEvent).observe(this, Observer { resource ->
            resource.data?.let {
                loader(100)
                setEvent(it)
            }
            resource.error?.let {
                loader(100)
                showError(it)
            }
        })
    }

    private fun postCheckin(idEvent: Int, name: String, email: String) {
        loader(50)
        viewModel.setCheckin(idEvent,name,email).observe(this, Observer { resource ->
            resource.data?.let {
                loader(100)
                showError(it.code.toString())
            }
            resource.error?.let {
                loader(100)
                showError(it)
            }
        })
    }

    private fun setEvent(eventItem: EventItem){
        tv_title.text = eventItem.title
        tv_description.text = eventItem.description
        tv_date.text = eventItem.date.toDateFormatted()
        tv_price.text = eventItem.price.toMoneyFormat()
        iv_banner_image.loadImage(eventItem.image)

        btCheckin.setOnClickListener {
            formCheckin()
        }
    }

    private fun formCheckin(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.view_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle(getString(R.string.txt_checkin))
        val  mAlertDialog = mBuilder.show()

        mDialogView.dialogLoginBtn.setOnClickListener {
            mAlertDialog.dismiss()
            val name = mDialogView.dialogNameEt.text.toString()
            val email = mDialogView.dialogEmailEt.text.toString()

            postCheckin(idEvent,name,email)
        }
        mDialogView.dialogCancelBtn.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    private fun loader(progress: Int){
        lpIndicator.setProgressCompat(progress, true)
    }
}