package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.LocalDB.RepoModel

lateinit var txtShowTitle: TextView
lateinit var txtShowTask: TextView
lateinit var sendbtn:ImageButton
lateinit var Contex:Context
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
        Contex= holder.itemView.context
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
            sendbtn=findViewById(R.id.send)
            //txtShowTask.text=repoModel.owner
            txtShowTitle.text=repoModel.repoName

            txtShowTask.text.apply {
                 txtShowTask.text= repoModel.description
            }

             var owner=repoModel.owner
            var reponame=repoModel.repoName
            var descrip=repoModel.description
            var urlString="https://github.com/$owner/$reponame"

            sendbtn.setOnClickListener {
                val intent= Intent(Intent.ACTION_SEND)
                intent.type="text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "This is a Github Repository with name: $reponame and \n corresponding details: $descrip. \n To View this click $urlString")
                val chooser=Intent.createChooser(intent,"Share this repository using...")
                startActivity(Contex, chooser, null)
            }

            //For Handling RecyclerView Item Click
          // GlobalScope.launch(Dispatchers.Main) {
               itemView.setOnClickListener {
                   listener(repoModel.repoName, repoModel.description, repoModel.id, repoModel.owner)
               }



        }

    }

}

