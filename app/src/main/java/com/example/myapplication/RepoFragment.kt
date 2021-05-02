package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.myapplication.LocalDB.AppDatabase
import com.example.myapplication.LocalDB.RepoModel
import com.example.myapplication.api.repoCall
import com.example.myapplication.databinding.FragmentRepoBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * A simple [Fragment] subclass.
 * Use the [RepoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepoFragment : Fragment() {


    var RepoName:String?=null
    var Description:String?=null
    var owner:String?=null
    var Id:Long?=null
    private var _binding: FragmentRepoBinding? = null
    private var navController: NavController? = null

    val db by lazy{

        AppDatabase.getDatabase(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRepoBinding.inflate(inflater, container, false)

        arguments?.let {
        Description=it.getString("Description")
          RepoName=it.getString("Name")
            Id= it.getLong("ID")
            owner=it.getString("Owner")
        }

        _binding?.apply {
            repoName.text=RepoName
            repoDescription.text= Description
            }



        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.deleteRepo?.setOnClickListener {
            deleteRepo()
            super.getActivity()?.onBackPressed()
            //requireActivity().finish()
        }

        _binding?.toBranches?.setOnClickListener {
            findNavController().navigate(R.id.action_repoFragment_to_branchesFragment,
                bundleOf(
                    "Name" to RepoName,
                    "Owner" to owner
                )
            )
        }

        _binding?.toIssues?.setOnClickListener {
            findNavController().navigate(R.id.action_repoFragment_to_issuesFragment,
                bundleOf(
                    "Name" to RepoName,
                    "Owner" to owner
                )
            )

        }

    }

    fun deleteRepo(){
        GlobalScope.launch(Dispatchers.Main) {
            val id = withContext(Dispatchers.IO) {
                return@withContext db.todoDao().deleteRepo(Id)
            }

        }

    }
}