package com.example.myapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.dataModels.Issues.IssuesItem

lateinit var author: TextView
lateinit var title: TextView
lateinit var imageauthor: ImageView
class IssuesAdapter(val list:List<IssuesItem?>): RecyclerView.Adapter<IssuesViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder {

        return IssuesViewHolder(
                parent.context.getSystemService(LayoutInflater::class.java).inflate(
                        R.layout.issue_list_view,
                        parent,
                        false
                )
        )

    }



    override fun onBindViewHolder(holder:IssuesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}


class IssuesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    fun bind(ISSUES: IssuesItem?) {
        with(itemView){
            author=findViewById(R.id.authorTextView)
            title=findViewById(R.id.Issuetitle)
            imageauthor=findViewById(R.id.autorImageView)

            author.text=ISSUES?.milestone?.creator?.login
            title.text=ISSUES?.title

            imageauthor.loadImage(ISSUES?.milestone?.creator?.avatarUrl, circleCrop = true)
            //ISSUES?.milestone?.creator?.avatarUrl?.let { imageauthor.loadImage(it, circleCrop = true) }

        }

    }

}


