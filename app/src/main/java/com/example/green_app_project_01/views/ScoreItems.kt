package com.example.green_app_project_01.views

import com.example.green_app_project_01.models.Score
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ScoreItems(val score: Score): Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        //TODO: Bind row items to data
    }

    override fun getLayout(): Int {
        //TODO: Make custom row
        return 0
    }

}