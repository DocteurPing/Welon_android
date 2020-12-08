package com.welon.android.ratingList

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.welon.android.R
import com.welon.android.databases.entities.Item
import com.welon.android.restaurant.fragments.ItemHolder
import kotlinx.android.synthetic.main.popup_rating.view.*


class RatingListAdapter(
    private val list: List<Item>,
    private val context: Context,
    private val presenter: RatingListPresenter
) : RecyclerView.Adapter<ItemHolder>() {

    private var rateQuality = "1"
    private var rateQuantity = "1"
    private var ratePrice = "1"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val entite: Item = list[position]
        holder.view.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            val dialogViewTrack = View.inflate(context, R.layout.popup_rating, null)
            dialogViewTrack.name_restaurant.text = entite.name
            dialogViewTrack.star1_quality.setOnClickListener { setStar1Quality(dialogViewTrack) }
            dialogViewTrack.star2_quality.setOnClickListener { setStar2Quality(dialogViewTrack) }
            dialogViewTrack.star3_quality.setOnClickListener { setStar3Quality(dialogViewTrack) }
            dialogViewTrack.star4_quality.setOnClickListener { setStar4Quality(dialogViewTrack) }
            dialogViewTrack.star5_quality.setOnClickListener { setStar5Quality(dialogViewTrack) }
            dialogViewTrack.star1_quantity.setOnClickListener { setStar1Quantity(dialogViewTrack) }
            dialogViewTrack.star2_quantity.setOnClickListener { setStar2Quantity(dialogViewTrack) }
            dialogViewTrack.star3_quantity.setOnClickListener { setStar3Quantity(dialogViewTrack) }
            dialogViewTrack.star4_quantity.setOnClickListener { setStar4Quantity(dialogViewTrack) }
            dialogViewTrack.star5_quantity.setOnClickListener { setStar5Quantity(dialogViewTrack) }
            dialogViewTrack.star1_price.setOnClickListener { setStar1Price(dialogViewTrack) }
            dialogViewTrack.star2_price.setOnClickListener { setStar2Price(dialogViewTrack) }
            dialogViewTrack.star3_price.setOnClickListener { setStar3Price(dialogViewTrack) }
            dialogViewTrack.star4_price.setOnClickListener { setStar4Price(dialogViewTrack) }
            dialogViewTrack.star5_price.setOnClickListener { setStar5Price(dialogViewTrack) }
            builder.setPositiveButton("ENVOYER") { _, _ -> presenter.sendRate(rateQuality, rateQuantity, ratePrice, entite) }
            builder.setNegativeButton("ANNULER") { dialog, _ -> dialog.cancel() }
            builder.setView(dialogViewTrack)

            val alertDialog = builder.create()
            alertDialog.show()
        }
        holder.bind(entite)
    }

    private fun setStar1Quality(dialogViewTrack: View) {
        dialogViewTrack.star1_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star3_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star4_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuality = "1"
    }

    private fun setStar2Quality(dialogViewTrack: View) {
        dialogViewTrack.star1_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star4_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuality = "2"
    }

    private fun setStar3Quality(dialogViewTrack: View) {
        dialogViewTrack.star1_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuality = "3"
    }

    private fun setStar4Quality(dialogViewTrack: View) {
        dialogViewTrack.star1_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star5_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuality = "4"
    }

    private fun setStar5Quality(dialogViewTrack: View) {
        dialogViewTrack.star1_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star5_quality.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        rateQuality = "5"
    }

    private fun setStar1Quantity(dialogViewTrack: View) {
        dialogViewTrack.star1_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star3_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star4_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuantity = "1"
    }

    private fun setStar2Quantity(dialogViewTrack: View) {
        dialogViewTrack.star1_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star4_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuantity = "2"
    }

    private fun setStar3Quantity(dialogViewTrack: View) {
        dialogViewTrack.star1_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuantity = "3"
    }

    private fun setStar4Quantity(dialogViewTrack: View) {
        dialogViewTrack.star1_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star5_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        rateQuantity = "4"
    }

    private fun setStar5Quantity(dialogViewTrack: View) {
        dialogViewTrack.star1_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star5_quantity.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        rateQuantity = "5"
    }

    private fun setStar1Price(dialogViewTrack: View) {
        dialogViewTrack.star1_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star3_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star4_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        ratePrice = "1"
    }

    private fun setStar2Price(dialogViewTrack: View) {
        dialogViewTrack.star1_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star4_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        ratePrice = "2"
    }

    private fun setStar3Price(dialogViewTrack: View) {
        dialogViewTrack.star1_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        dialogViewTrack.star5_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        ratePrice = "3"
    }

    private fun setStar4Price(dialogViewTrack: View) {
        dialogViewTrack.star1_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star5_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork))
        ratePrice = "4"
    }

    private fun setStar5Price(dialogViewTrack: View) {
        dialogViewTrack.star1_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star2_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star3_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star4_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        dialogViewTrack.star5_price.setImageDrawable(context.getDrawable(R.drawable.ic_fork_full))
        ratePrice = "5"
    }

}