package br.com.alura.technews.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.projecttest.R
import com.test.projecttest.model.EventItem
import com.test.projecttest.ui.activity.extension.loadImage
import com.test.projecttest.ui.activity.extension.toDateFormatted
import com.test.projecttest.ui.activity.extension.toMoneyFormat
import kotlinx.android.synthetic.main.event_item.view.*

class EventAdapter(
    private val context: Context,
    private val eventItemList: MutableList<EventItem> = mutableListOf(),
    var itemClick: (eventItem: EventItem) -> Unit = {}
) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(
                R.layout.event_item,
                parent, false
            )
        return ViewHolder(view)
    }

    override fun getItemCount() = eventItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventItem = eventItemList[position]
        holder.setItem(eventItem)
    }

    fun update(eventItemList: List<EventItem>) {
        notifyItemRangeRemoved(0, this.eventItemList.size)
        this.eventItemList.clear()
        this.eventItemList.addAll(eventItemList)
        notifyItemRangeInserted(0, this.eventItemList.size)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private lateinit var eventItem: EventItem

        init {
            itemView.setOnClickListener {
                if (::eventItem.isInitialized) {
                    itemClick(eventItem)
                }
            }
        }

        fun setItem(eventItem: EventItem) {
            this.eventItem = eventItem
            itemView.tv_title.text = eventItem.title
            itemView.tv_description.text = eventItem.description
            itemView.tv_date.text = eventItem.date.toDateFormatted()
            itemView.tv_price.text = eventItem.price.toMoneyFormat()
            itemView.iv_banner_image.loadImage(eventItem.image)
        }
    }
}
