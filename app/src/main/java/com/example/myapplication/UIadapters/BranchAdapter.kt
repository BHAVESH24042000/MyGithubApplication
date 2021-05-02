package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.dataModels.branches.BranchesItem


lateinit var txtBranchName: TextView
class BranchAdapter(val list:List<BranchesItem>, val listener:(String?)->Unit): RecyclerView.Adapter<BranchViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchViewHolder {

        return BranchViewHolder(
                parent.context.getSystemService(LayoutInflater::class.java).inflate(
                        R.layout.branch_list_view,
                        parent,
                        false
                )
        )

    }



    override fun onBindViewHolder(holder:BranchViewHolder, position: Int) {
        holder.bind(list[position],listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }


}


class BranchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    fun bind(BRANCH: BranchesItem, listener: (String?) -> Unit) {
        with(itemView){

            txtBranchName=findViewById(R.id.txtBranchName)
            txtBranchName.text= BRANCH.name



            //For Handling RecyclerView Item Click
            // GlobalScope.launch(Dispatchers.Main) {
            itemView.setOnClickListener {
                listener( BRANCH.name)
            }

        }

    }

}

