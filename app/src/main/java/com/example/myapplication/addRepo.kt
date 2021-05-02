package com.example.myapplication

import android.accounts.AuthenticatorDescription
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.LocalDB.AppDatabase
import com.example.myapplication.LocalDB.RepoModel
import com.example.myapplication.api.repoCall
import com.example.myapplication.databinding.FragmentSecondBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

const val DB_NAME="todo.db"
class SecondFragment : Fragment() {

    private var binding:  FragmentSecondBinding? = null
    lateinit var taskInpLay: TextInputLayout
    lateinit var titleInpLay: TextInputLayout
    val db by lazy{

        AppDatabase.getDatabase(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskInpLay= binding!!.taskInpLay
        titleInpLay=binding!!.titleInpLay
       binding!!.saveBtn.setOnClickListener{
           saveRepo()
           findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
       }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

     fun saveRepo() {

        val owner:String = titleInpLay.editText?.text.toString()
        val repoName:String = taskInpLay.editText?.text.toString()
         var description:String?=null




        GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                description=repoCall.getRepo( owner,repoName)?.description
                return@withContext db.todoDao().insertRepo(
                        RepoModel(
                                owner,
                                repoName,
                                description
                        )
                )
            }

        }

    }




}