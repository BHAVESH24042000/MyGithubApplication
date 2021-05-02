package com.example.myapplication

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.LocalDB.RepoModel

lateinit var txtShowTitle: TextView
lateinit var txtShowTask: TextView


class RepoAdapter(val list:List<RepoModel>, val listener:( String?, String?, Long?, String?)->Unit):RecyclerView.Adapter<RepoViewHolder>() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {

        return RepoViewHolder(
                parent.context.getSystemService(LayoutInflater::class.java).inflate(
                        R.layout.repo_list_view,
                        parent,
                        false
                )
        )

        }



    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(list[position],listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }
}


class  RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
   fun bind(repoModel: RepoModel, listener: (String?, String?, Long?, String?) -> Unit) {
        with(itemView){

            txtShowTitle=findViewById(R.id.txtShowTitle)
            txtShowTask=findViewById(R.id.txtShowTask)

            //txtShowTask.text=repoModel.owner
            txtShowTitle.text=repoModel.repoName

            txtShowTask.text.apply {
                //if( repoModel.description!=null)
                 txtShowTask.text= repoModel.description
                //else
                //txtShowTask.text="No Description"
            }

            //For Handling RecyclerView Item Click
          // GlobalScope.launch(Dispatchers.Main) {
               itemView.setOnClickListener {
                   listener(repoModel.repoName, repoModel.description, repoModel.id, repoModel.owner)
               }

        }

    }

}

