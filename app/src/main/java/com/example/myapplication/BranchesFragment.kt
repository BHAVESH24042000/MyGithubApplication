package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.api.dataModels.branches.BranchesItem
import com.example.myapplication.api.repoCall
import com.example.myapplication.databinding.FragmentBranchesBinding
import kotlinx.coroutines.*


class BranchesFragment : Fragment() {

    var RepoName:String?=null
    var owner:String?=null

    lateinit var branchesRv: RecyclerView


    val list = arrayListOf<BranchesItem>()
    lateinit  var adapter:BranchAdapter
    private var binding: FragmentBranchesBinding?=null

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
        binding =FragmentBranchesBinding.inflate(inflater, container, false)
        adapter= BranchAdapter(list){ s: String?->
            lifecycleScope.launch(Dispatchers.Main) {
                onClick(s)
            }
        }

        branchesRv = binding!!.branchesRv
        branchesRv.adapter= adapter
        branchesRv.layoutManager= LinearLayoutManager(activity)



        lifecycleScope.launch(Dispatchers.Main) {
             withContext(Dispatchers.IO) {
            repoCall.getBranches(owner,RepoName).let {
                CoroutineScope(Dispatchers.Main).launch {
                    list.clear()
                    if (it != null) {
                        list.addAll(it)
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            }
        }




        return binding?.root
    }

    private fun onClick(s: String?) {
        findNavController().navigate(R.id.action_branchesFragment_to_commitsFragment,
            bundleOf(
                "Name" to RepoName,
                "Owner" to owner,
                "BranchName" to s
            )
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}