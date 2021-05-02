package com.example.myapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.api.dataModels.commits.CommitsItem

lateinit var username: TextView
lateinit var date: TextView
lateinit var message: TextView
lateinit var image: ImageView
class CommitsAdapter(val list:List<CommitsItem?>): RecyclerView.Adapter<CommitsViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsViewHolder {

        return CommitsViewHolder(
                parent.context.getSystemService(LayoutInflater::class.java).inflate(
                        R.layout.commit_list_view,
                        parent,
                        false
                )
        )

    }



    override fun onBindViewHolder(holder:CommitsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}


class CommitsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    fun bind(COMMIT: CommitsItem?) {
        with(itemView){
            username=findViewById(R.id.authorTextView)
            date=findViewById(R.id.dateTextView)
            message=findViewById(R.id.bodySnippetTextView)
            image=findViewById(R.id.avatarImageView)

            username.text=COMMIT?.commit?.author?.name
            date.text=COMMIT?.commit?.author?.date
            message.text=COMMIT?.commit?.message
            COMMIT?.author?.avatarUrl?.let { image.loadImage(it, circleCrop = true) }

        }

    }

}
fun ImageView.loadImage(uri: String?, circleCrop: Boolean = false) {
    if (circleCrop) {
        Glide.with(this).load(uri).circleCrop().into(this)
    } else {
        Glide.with(this).load(uri).into(this)
    }
}
