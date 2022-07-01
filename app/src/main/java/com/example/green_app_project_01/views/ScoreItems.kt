package com.example.green_app_project_01.views

import com.example.green_app_project_01.R
import com.example.green_app_project_01.models.Score
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.profile_row.view.*

class ScoreItems(val score: Score): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        // Bind row items to data
        viewHolder.itemView.score_text_view.text = score.overallScore.toString()
        viewHolder.itemView.date_text_view.text = score.date.toString()
    }

    override fun getLayout(): Int {
        // Get layout form Custom Row
        return R.layout.profile_row
    }

}