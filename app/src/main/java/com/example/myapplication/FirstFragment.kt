package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.LocalDB.AppDatabase
import com.example.myapplication.LocalDB.RepoModel
import com.example.myapplication.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    lateinit var todoRv: RecyclerView
    private val _feed= MutableLiveData<RepoModel>()
    val list2: LiveData<RepoModel> =_feed

    val list = arrayListOf<RepoModel>()
    lateinit  var adapter:RepoAdapter
    private var binding: FragmentFirstBinding?=null
    //private lateinit var feedAdapter: RepoAdapter
    val db by lazy {
        AppDatabase.getDatabase(this)
    }



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        activity!!.finish()// in here you can do logic when backPress is clicked
            }
        })

        binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapter= RepoAdapter(list){ s: String?, s1: String?, id:Long? , s2: String?->

            lifecycleScope.launch(Dispatchers.Main) {
                onClick(s,s1, id, s2)
            }
        }


        todoRv = binding!!.repoRv
        todoRv.adapter= adapter
        todoRv.layoutManager=LinearLayoutManager(activity)


        db.todoDao().getTask().observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }else{
                list.clear()
                adapter.notifyDataSetChanged()
            }
        })



        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       binding?.addRepo?.setOnClickListener{
           findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
       }

    }

   suspend fun onClick(reponame:String?, description:String?, id:Long? ,owner: String?){
        findNavController().navigate(R.id.action_FirstFragment_to_repoFragment,
        bundleOf(
               "Name" to reponame,
                "Description" to description,
                "ID" to id,
               "Owner" to owner
        ))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }



}