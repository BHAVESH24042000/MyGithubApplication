package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.datsModels.Issues.IssuesItem
import com.example.myapplication.api.datsModels.commits.CommitsItem
import com.example.myapplication.api.repoCall
import com.example.myapplication.databinding.FragmentCommitsBinding
import com.example.myapplication.databinding.FragmentIssuesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 * Use the [IssuesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IssuesFragment : Fragment() {
    var RepoName:String?=null
    var owner:String?=null


    lateinit var issuesRv: RecyclerView
    //private val _feed= MutableLiveData<RepoModel>()
    //val list2: LiveData<RepoModel> =_feed

    val list = arrayListOf<IssuesItem?>()
    lateinit  var adapter:IssuesAdapter
    private var binding: FragmentIssuesBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            RepoName=it.getString("Name")
            owner=it.getString("Owner")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =  FragmentIssuesBinding.inflate(inflater, container, false)
        adapter= IssuesAdapter(list)

        issuesRv = binding!!. issuesRv
        issuesRv.adapter= adapter
        issuesRv.layoutManager= LinearLayoutManager(activity)



        lifecycleScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                repoCall.getIssues(owner,RepoName,"open").let {
                    CoroutineScope(Dispatchers.Main).launch {
                        list.clear()
                        list.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                }

            }
        }
        return binding?.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}