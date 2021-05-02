package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.datsModels.commits.CommitsItem
import com.example.myapplication.api.repoCall
import com.example.myapplication.databinding.FragmentCommitsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 * Use the [CommitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommitsFragment : Fragment() {
    var RepoName:String?=null
    var owner:String?=null
    var branchname:String?=null

    lateinit var commitsRv: RecyclerView
    //private val _feed= MutableLiveData<RepoModel>()
    //val list2: LiveData<RepoModel> =_feed

    val list = arrayListOf<CommitsItem?>()
    lateinit  var adapter:CommitsAdapter
    private var binding: FragmentCommitsBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            RepoName=it.getString("Name")
            owner=it.getString("Owner")
            branchname=it.getString("BranchName")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding =FragmentCommitsBinding.inflate(inflater, container, false)
        adapter= CommitsAdapter(list)

        commitsRv = binding!!.commitsRv
        commitsRv.adapter= adapter
        commitsRv.layoutManager= LinearLayoutManager(activity)



        lifecycleScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                repoCall.getCommits(owner,RepoName,branchname).let {
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